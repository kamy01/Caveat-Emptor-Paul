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
import services.item.ItemService;
import utils.DoubleRounder;

@Remote(ItemService.class)
@Stateless
public class ItemServiceImp implements ItemService {
	@EJB
	ItemRepository itemRepository;
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@Override
	public List<ItemDTO> getItemsToSell(User user) {
		List<Item> items = itemRepository.findItemsByUser(user, entityManager);
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item item : items) {
			ItemDTO itemDTO = ItemMapper.mapToItemDTO(item);
			itemsDTO.add(itemDTO);
		}
		return itemsDTO;
	}

	@Override
	public List<ItemDTO> getItemsToBuy(User user) {
		List<Item> items = itemRepository.findItemsNotByUser(user, entityManager);
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
		Item mergedItem = entityManager.merge(item);
		entityManager.remove(mergedItem);

	}

	@Override
	public void editItem(ItemDTO itemDTO) {
		Item item = ItemMapper.mapToItem(itemDTO);
		item.setInitialPrice(DoubleRounder.round(item.getInitialPrice(), 2));
		itemRepository.update(item, entityManager);
	}

	@Override
	public void addItem(ItemDTO itemDTO) {
		Item item = ItemMapper.mapToItem(itemDTO);
		item.setInitialPrice(DoubleRounder.round(item.getInitialPrice(), 2));
		itemRepository.create(item, entityManager);
	}

}
