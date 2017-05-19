package repositories.item.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import entities.item.Item;

public interface ItemRepository {

	public void create(Item item, EntityManager entityManager);

	public Item read(long id, EntityManager entityManager);

	public void delete(Item item, EntityManager entityManager);

	public void update(Item item, EntityManager entityManager);

	public List<Item> findItemsByUser(Item item , EntityManager entityManager);

}
