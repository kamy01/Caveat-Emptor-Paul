package services.bid.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.BidDTO;
import dto.ItemDTO;
import entities.bid.Bid;
import entities.category.Category;
import entities.item.Item;
import mappers.BidMapper;
import mappers.ItemMapper;
import repositories.bid.interfaces.BidRepository;
import repositories.item.interfaces.ItemRepository;
import services.bid.BidService;

@Remote(BidService.class)
@Stateless
public class BidServiceImp implements BidService {
	@EJB
	BidRepository bidRepository;
	@EJB 
	ItemRepository itemRepository;
	
	
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;


	@Override
	public List<ItemDTO> getItemsForCategory(Category category) {
		List<Item> items = itemRepository.findItemsByCategory(category, entityManager);
		List<ItemDTO>itemsDTO = new ArrayList<ItemDTO>();
		for(Item item : items){
			itemsDTO.add(ItemMapper.mapToItemDTO(item));
		}
		return itemsDTO;
		
	}


	@Override
	public void saveBid(BidDTO bidDTO) {
		bidRepository.create(BidMapper.mapToBid(bidDTO), entityManager);
	}
	@Override
	public void updateBid(BidDTO bidDTO){
		
	}
	
	
}
