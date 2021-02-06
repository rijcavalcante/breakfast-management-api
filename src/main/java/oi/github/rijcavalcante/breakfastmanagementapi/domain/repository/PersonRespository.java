package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository;

import java.util.List;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Person;

public interface PersonRespository {

	public List<Person> queryPeople();
	public Person queryPersonForCpf(String cpf);
	public Person save(Person person);
	public void remove(String cpf);
}
