package repositories.item.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import entities.category.Category;
import entities.item.Item;
import entities.login.User;

public interface ItemRepository {

	public void create(Item item, EntityManager entityManager);

	public Item read(long id, EntityManager entityManager);

	public void delete(Item item, EntityManager entityManager);

	public void update(Item item, EntityManager entityManager);

	public List<Item> findItemsByUser(User user , EntityManager entityManager);
	
	public List<Item> findItemsByCategory(Category category , EntityManager entityManager);

}
