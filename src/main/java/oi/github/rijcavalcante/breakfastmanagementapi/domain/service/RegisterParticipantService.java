package oi.github.rijcavalcante.breakfastmanagementapi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.exception.EntityAlreadyCreatedException;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Participant;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.ParticipantRespository;

@Service
public class RegisterParticipantService {

	@Autowired
	ParticipantRespository respository;

	@Autowired
	RegisterItemService itemService;
	
	public List<Participant> queryAll(){
		List<Participant> paticipants = respository.queryParticipants();
		if(paticipants != null && paticipants.isEmpty()) {
			throw new EmptyResultDataAccessException(paticipants.size());
		}
		return paticipants;
	}
	
	public Participant query(String cpf) {
		return respository.queryParticipant(cpf);
	}

	public Participant save(Participant participant) throws EntityAlreadyCreatedException {

		Participant participantSource = respository.queryParticipant(participant.getCpf());

		if (participantSource != null) {
			throw new EntityAlreadyCreatedException("Participante ja inserido");
		}
		participant = respository.save(participant);
		itemService.save(participant, participant.getItens());

		return participant;
	}

	public Participant update(String cpf, Participant participantTarget) {
		Participant participantSource = null;

		participantSource = respository.queryParticipant(cpf);

		if (participantSource != null) {
			if (participantTarget.getItens().isEmpty()) {
				BeanUtils.copyProperties(participantTarget, participantSource, "id", "itens");
			} else {
				BeanUtils.copyProperties(participantTarget, participantSource, "id");
			}

			participantSource = respository.save(participantSource);
			itemService.update(participantSource, participantSource.getItens());
		}

		return participantSource;
	}

	public void delete(String cpf) {
		respository.remove(cpf);
	}
}
