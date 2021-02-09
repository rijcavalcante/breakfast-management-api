package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Participant;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.ParticipantRespository;

@Repository
public class ParticipantRespositoryImpl implements ParticipantRespository {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Participant> queryParticipants() {
		return manager.createQuery("from Participant", Participant.class).getResultList();
	}

	@Override
	public Participant queryParticipant(String cpf) throws IllegalArgumentException {
		TypedQuery<Participant> typedQuery = manager.createNamedQuery(Participant.QUERY_FIND_CPF_PARTICIPANT, Participant.class);
		typedQuery.setParameter("partCpf", cpf);
		return typedQuery.getSingleResult();
	}

	@Override
	@Transactional
	public Participant save(Participant participant) {
		return manager.merge(participant);
	}

	@Override
	@Transactional
	public void remove(String cpf) {
		manager.remove(queryParticipant(cpf));
	}
}
