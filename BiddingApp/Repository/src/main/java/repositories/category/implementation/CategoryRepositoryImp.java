package repositories.category.implementation;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.category.Category;
import entities.item.Item;
import repositories.category.interfaces.CategoryRepository;

@Remote(CategoryRepository.class)
@Stateless
public class CategoryRepositoryImp implements CategoryRepository {

	@Override
	public void create(Category category, EntityManager entityManager) {
		entityManager.persist(category);
	}

	@Override
	public Category read(long id, EntityManager entityManager) {
		return entityManager.find(Category.class, id);
	}

	@Override
	public void delete(Category category, EntityManager entityManager) {
		entityManager.remove(category);
	}

	@Override
	public void add(Category category, EntityManager entityManager) {
		if (category.getId() != null) {
			update(category, entityManager);
		} else {
			create(category, entityManager);
		}
	}

	@Override
	public void update(Category category, EntityManager entityManager) {
		entityManager.merge(category);
	}

}
