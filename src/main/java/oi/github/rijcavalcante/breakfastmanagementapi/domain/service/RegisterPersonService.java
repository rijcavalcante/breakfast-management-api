package oi.github.rijcavalcante.breakfastmanagementapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Person;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.PersonRespository;

@Service
public class RegisterPersonService {

	@Autowired
	PersonRespository personRespository;
	
	public Person save(Person person) {
		return personRespository.save(person);
	}
	
	public void delete(String cpf) {
		personRespository.remove(cpf);
	}
}
