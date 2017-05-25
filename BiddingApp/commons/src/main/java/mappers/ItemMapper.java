package mappers;

import dto.ItemDTO;
import entities.item.Item;

public class ItemMapper {

	public static ItemDTO mapToItemDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setEditable(false);
		itemDTO.setName(item.getName());
		itemDTO.setDescription(item.getDescription());
		itemDTO.setBestBid(item.getBestBid());
		itemDTO.setBids(item.getBids());
		itemDTO.setCategory(item.getCategory());
		itemDTO.setClosingDate(item.getClosingDate());
		itemDTO.setId(item.getId());
		itemDTO.setInitialPrice(item.getInitialPrice());
		itemDTO.setOpeningDate(item.getOpeningDate());
		itemDTO.setStatus(item.getStatus());
		return itemDTO;
	}

	public static Item mapToItem(ItemDTO itemDTO) {
		Item item = new Item();
		item.setName(itemDTO.getName());
		item.setDescription(item.getDescription());
		item.setBestBid(itemDTO.getBestBid());
		item.setBids(itemDTO.getBids());
		item.setCategory(itemDTO.getCategory());
		item.setClosingDate(itemDTO.getClosingDate());
		item.setId(itemDTO.getId());
		item.setInitialPrice(itemDTO.getInitialPrice());
		item.setOpeningDate(itemDTO.getOpeningDate());
		item.setStatus(itemDTO.getStatus());
		return item;
	}

}
