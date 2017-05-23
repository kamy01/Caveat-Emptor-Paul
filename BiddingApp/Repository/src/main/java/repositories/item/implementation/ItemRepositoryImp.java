package repositories.item.implementation;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.category.Category;
import entities.item.Item;
import entities.login.User;
import repositories.item.interfaces.ItemRepository;

@Remote(ItemRepository.class)
@Stateless
public class ItemRepositoryImp implements ItemRepository {

	@Override
	public void create(Item item, EntityManager entityManager) {
		entityManager.persist(item);
	}

	@Override
	public Item read(long id, EntityManager entityManager) {
		return entityManager.find(Item.class, id);
	}

	@Override
	public void delete(Item item, EntityManager entityManager) {
		entityManager.remove(item);
	}

	@Override
	public void update(Item item, EntityManager entityManager) {
		entityManager.merge(item);
	}

	@Override
	public List<Item> findItemsByUser(User user, EntityManager entityManager) {
		Query query = entityManager.createNamedQuery(Item.FIND_ITEMS_BY_USER);
		query.setParameter("user", user);
		List<Item> list = (List<Item>) query.getResultList();
		return list;
	}

	@Override
	public List<Item> findItemsByCategory(Category category, EntityManager entityManager) {
		Query query = entityManager.createNamedQuery(Item.FIND_ITEMS_BY_CATEGORY);
		query.setParameter("category", category);
		List<Item> list = (List<Item>) query.getResultList();
		return list;
	}

}
