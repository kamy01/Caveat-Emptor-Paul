package services.item.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.ItemDTO;
import entities.item.Item;
import repositories.item.interfaces.ItemRepository;
import services.item.interfaces.ItemService;

@Remote(ItemService.class)
@Stateless
public class ItemServiceImp implements ItemService	{
	@EJB
	ItemRepository itemRepository;
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@Override
	public List<Item> getItemList() {
		List<ItemDTO> =itemRepository.findItemsByUser(item, entityManager);
	}
}
