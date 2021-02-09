package oi.github.rijcavalcante.breakfastmanagementapi.domain.repository;

import java.util.List;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Item;

public interface ItemRepository {

	public List<Item> queryItens();
	public void addItem(Item item);
}
