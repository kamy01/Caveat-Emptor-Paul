package mappers;

import dto.BidDTO;
import entities.bid.Bid;

public class BidMapper {

	public static BidDTO mapToBidDTO(Bid bid) {
		BidDTO bidDTO = new BidDTO();
		bidDTO.setId(bid.getId());
		bidDTO.setItemDTO(ItemMapper.mapToItemDTO(bid.getItem()));
		bidDTO.setUser(bid.getUser());
		bidDTO.setValue(bid.getValue());
		return bidDTO;
	}

	public static Bid mapToBid(BidDTO bidDTO) {
		Bid bid = new Bid();
		bid.setId(bidDTO.getId());
		bid.setItem(ItemMapper.mapToItem(bidDTO.getItemDTO()));
		bid.setUser(bidDTO.getUser());
		bid.setValue(bidDTO.getValue());
		return bid;
	}
}
