package mappers;

import dto.BidDTO;
import dto.ItemDTO;
import entities.bid.Bid;
import entities.item.Item;

public class ItemMapper {

	public static ItemDTO mapToItemDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setUser(item.getUser());
		itemDTO.setEditable(false);
		itemDTO.setName(item.getName());
		itemDTO.setDescription(item.getDescription());
		itemDTO.setBestBid(item.getBestBid());
		if (item.getBids() != null) {
			for (Bid bid : item.getBids()) {
				itemDTO.getBids().add(BidMapper.mapToBidDTO(bid));
			}
		}
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
		item.setUser(itemDTO.getUser());
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
		item.setBestBid(itemDTO.getBestBid());
		if (itemDTO.getBids() != null) {
			for (BidDTO bidDTO : itemDTO.getBids()) {
				item.getBids().add(BidMapper.mapToBid(bidDTO));
			}
		}
		item.setCategory(itemDTO.getCategory());
		item.setClosingDate(itemDTO.getClosingDate());
		item.setId(itemDTO.getId());
		item.setInitialPrice(itemDTO.getInitialPrice());
		item.setOpeningDate(itemDTO.getOpeningDate());
		item.setStatus(itemDTO.getStatus());
		return item;
	}

}
