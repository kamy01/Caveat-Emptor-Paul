package services.bid;

import java.util.List;

import dto.BidDTO;
import dto.ItemDTO;
import entities.category.Category;

public interface BidService {

	List<ItemDTO> getItemsForCategory(Category category);
	
	void saveBid(BidDTO bidDTO);
	
	void updateBid(BidDTO bidDTO);
}
