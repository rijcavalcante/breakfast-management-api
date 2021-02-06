package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Person;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.PersonRespository;

@Repository
public class PersonRespositoryImpl implements PersonRespository {
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Person> queryPeople() {
		// TODO Auto-generated method stub
		return manager.createQuery("from person", Person.class).getResultList();
	}

	@Override
	public Person queryPersonForCpf(String cpf) {
		// TODO Auto-generated method stub
		return manager.find(Person.class, cpf);
	}

	@Override
	@Transactional
	public Person save(Person person) {
		// TODO Auto-generated method stub
		return manager.merge(person);
	}

	@Override
	@Transactional
	public void remove(String cpf) {
		// TODO Auto-generated method stub
		manager.remove(queryPersonForCpf(cpf));
	}

}
