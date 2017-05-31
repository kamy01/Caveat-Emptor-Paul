package services.item;

import java.util.List;

import dto.ItemDTO;
import entities.category.Category;
import entities.login.User;

public interface ItemService {
	public List<ItemDTO> getItemsForUser(User user);

	public List<ItemDTO> getItemsForCategory(Category category);

	public void removeItem(ItemDTO itemDTO);

	public void editItem(ItemDTO itemDTO);

	public void addItem(ItemDTO itemDTO);


}
