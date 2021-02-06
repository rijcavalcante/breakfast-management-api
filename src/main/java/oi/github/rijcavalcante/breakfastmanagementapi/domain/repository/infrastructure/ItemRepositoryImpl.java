package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Item;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.ItemRepository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	@Transactional
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		manager.merge(item);
	}
}
