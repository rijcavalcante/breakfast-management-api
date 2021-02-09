package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository;

import java.util.List;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Participant;

public interface ParticipantRespository {

	public List<Participant> queryParticipants();
	public Participant queryParticipant(String cpf) throws IllegalArgumentException;
	public Participant save(Participant participant);
	public void remove(String cpf);
}
