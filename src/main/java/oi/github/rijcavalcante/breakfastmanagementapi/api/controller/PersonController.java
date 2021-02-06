package oi.github.rijcavalcante.breakfastmanagementapi.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Person;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.PersonRespository;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.service.RegisterPersonService;

@RestController
@RequestMapping(value = "/person", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PersonController {

	@Autowired
	PersonRespository repository;
	
	@Autowired
	RegisterPersonService personService;

	@GetMapping
	public List<Person> list() {
		return repository.queryPeople();
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Person> query(@PathVariable String cpf) {
		Person person = repository.queryPersonForCpf(cpf);

		return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person) {
		personService.save(person);	
		return ResponseEntity.created(URI.create("/person/" + person.getFirstName())).build();
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Person> update(@PathVariable String cpf, @RequestBody Person personTarget){
		Person personSource = repository.queryPersonForCpf(cpf);
		
		if(personSource != null) {
			BeanUtils.copyProperties(personTarget, personSource, "id");
			personSource = personService.save(personSource);
			return ResponseEntity.ok(personSource);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Person> delete(@PathVariable String cpf){
		try {
			personService.delete(cpf);
			return ResponseEntity.noContent().build();
		}catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
