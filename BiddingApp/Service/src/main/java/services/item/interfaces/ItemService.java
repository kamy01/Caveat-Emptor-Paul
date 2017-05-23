package services.item.interfaces;

import java.util.List;

import dto.ItemDTO;
import entities.category.Category;
import entities.login.User;

public interface ItemService {
	public List<ItemDTO> getItemsForUser(User user);
	
	public List<ItemDTO> getItemsForCategory(Category category);
}
