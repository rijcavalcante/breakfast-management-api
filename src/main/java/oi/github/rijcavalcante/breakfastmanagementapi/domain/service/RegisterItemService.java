package oi.github.rijcavalcante.breakfastmanagementapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Item;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.ItemRepository;

@Service
public class RegisterItemService {

	@Autowired
	ItemRepository itemRepository;
	
	public void save(Item item) {
		itemRepository.addItem(item);
	}
}
