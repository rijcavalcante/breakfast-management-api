package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.infrastructure;

import java.util.List;

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
		manager.merge(item);
	}

	@Override
	public List<Item> queryItens() {
		return manager.createQuery("from Item", Item.class).getResultList();
	}
}
