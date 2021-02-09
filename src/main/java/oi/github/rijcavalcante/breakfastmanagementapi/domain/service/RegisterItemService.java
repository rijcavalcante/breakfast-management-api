package oi.github.rijcavalcante.breakfastmanagementapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Item;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.model.Participant;
import oi.github.rijcavalcante.breakfastmanagementapi.domain.repository.ItemRepository;

@Service
public class RegisterItemService {

	@Autowired
	ItemRepository repository;

	public void save(Participant participant, List<Item> itens) {
		List<Item> itensBase = repository.queryItens();

		for (Item item : itens) {

			if (itensBase.contains(item)) {// throw Exception}

				item.setParticipant(participant);
				repository.addItem(item);
			}
		}
	}

	public void update(Participant participant, List<Item> itens) {

		List<Item> itensBase = repository.queryItens();

		for (Item item : itens) {

			if (itensBase.contains(item) 
					&& !item.getParticipant().getCpf().equals(participant.getCpf())) {
				// throw Exception
			}

			repository.addItem(item);
		}

	}

}
