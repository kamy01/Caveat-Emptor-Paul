package repositories.category.interfaces;

import javax.persistence.EntityManager;

import entities.category.Category;

public interface CategoryRepository {

	public void add(Category category, EntityManager entityManager);

	public void create(Category category, EntityManager entityManager);

	public Category read(long id, EntityManager entityManager);

	public void delete(Category category, EntityManager entityManager);

	public void update(Category category, EntityManager entityManager);
	

}
