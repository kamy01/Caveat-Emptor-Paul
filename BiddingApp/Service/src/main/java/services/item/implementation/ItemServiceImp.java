package services.item.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.ItemDTO;
import entities.category.Category;
import entities.item.Item;
import entities.login.User;
import mappers.ItemMapper;
import repositories.item.interfaces.ItemRepository;
import services.item.interfaces.ItemService;

@Remote(ItemService.class)
@Stateless
public class ItemServiceImp implements ItemService {
	@EJB
	ItemRepository itemRepository;
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@Override
	public List<ItemDTO> getItemsForUser(User user) {
		List<Item> items = itemRepository.findItemsByUser(user, entityManager);
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item item : items) {
			ItemDTO itemDTO = ItemMapper.mapToItemDTO(item);
			itemsDTO.add(itemDTO);
		}
		return itemsDTO;
	}

	@Override
	public List<ItemDTO> getItemsForCategory(Category category) {
		List<Item> items = itemRepository.findItemsByCategory(category, entityManager);
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item item : items) {
			ItemDTO itemDTO = ItemMapper.mapToItemDTO(item);
			itemsDTO.add(itemDTO);
		}
		return itemsDTO;
	}

	@Override
	public void removeItem(ItemDTO itemDTO) {
		Item item = ItemMapper.mapToItem(itemDTO);
		itemRepository.delete(item, entityManager);
	}

	@Override
	public void editItem(ItemDTO itemDTO) {
		Item item = ItemMapper.mapToItem(itemDTO);
		itemRepository.update(item, entityManager);
	}

	@Override
	public void addItem(ItemDTO itemDTO) {
		Item item = ItemMapper.mapToItem(itemDTO);
		itemRepository.create(item, entityManager);
	}

}
