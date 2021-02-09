package oi.github.rijcavalcante.breakfastmanagementapi.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.exception.EntityAlreadyCreatedException;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Participant;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.service.RegisterParticipantService;

@RestController
@RequestMapping(value = "/api/v1/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ParticipantController {

	@Autowired
	RegisterParticipantService participantService;

	@GetMapping("participants")
	public List<Participant> list() {
		return participantService.queryAll();
	}

	@GetMapping("participants/{cpf}")
	public ResponseEntity<Participant> query(@PathVariable String cpf) {
		return ResponseEntity.ok(participantService.query(cpf));
	}

	@PostMapping("participants")
	public ResponseEntity<Participant> create(@RequestBody Participant participant) {
		try {
			participantService.save(participant);
		} catch (EntityAlreadyCreatedException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		return ResponseEntity.created(URI.create("/participant/" + participant.getFirstName())).build();
	}

	@PutMapping("participants/{cpf}")
	public ResponseEntity<Participant> update(@PathVariable String cpf, @RequestBody Participant participantTarget) {
			Participant updatedParticipant = participantService.update(cpf, participantTarget);
			return ResponseEntity.ok(updatedParticipant);
	}

	@DeleteMapping("participants/{cpf}")
	public ResponseEntity<Participant> delete(@PathVariable String cpf) {
		try {
			participantService.delete(cpf);
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
