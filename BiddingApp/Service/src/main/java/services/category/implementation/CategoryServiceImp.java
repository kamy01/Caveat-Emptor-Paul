package services.category.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.Gson;

import entities.category.Category;
import mappers.CategoryDTO;
import mappers.CategoryMapper;
import repositories.category.interfaces.CategoryRepository;
import services.category.interfaces.CategoryService;

@Remote(CategoryService.class)
@Stateless
public class CategoryServiceImp implements CategoryService {
	@EJB
	CategoryRepository categoryRepository;
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@Override
	public String getRootAsJson() {
		Category category = categoryRepository.read(1l, entityManager);
		CategoryDTO specialCategory = CategoryMapper.mapToCategoryDTO(category);
		Gson gson = new Gson();
		return gson.toJson(specialCategory);
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO, Long id) {

		Category parent = categoryRepository.read(id, entityManager);
		Category category = CategoryMapper.mapToCategory(categoryDTO);
		category.setParent(parent);
		categoryRepository.add(category, entityManager);

	}

	@Override
	public void removeCategory(CategoryDTO categoryDTO) {
		Category category = categoryRepository.read(categoryDTO.getId(), entityManager);

		for (Category child : category.getCategories()) {
			child.setParent(category.getParent());
			categoryRepository.add(child, entityManager);
		}

		Category newCategory = entityManager.merge(category);
		entityManager.remove(newCategory);
		/*
		 * categoryRepository.delete(entityManager.contains(category) ? category
		 * : entityManager.merge(category), entityManager);
		 */
	}

	@Override
	public void addNewRootCategory(CategoryDTO categoryDTO) {

		Category root = categoryRepository.read(1, entityManager);
		Category category = CategoryMapper.mapToCategory(categoryDTO);
		category.setParent(root);
		categoryRepository.add(category, entityManager);
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		if (categoryDTO.getId() != null) {
			Category parent = categoryRepository.read(categoryDTO.getParentID(), entityManager);
			Category category = CategoryMapper.mapToCategory(categoryDTO);
			category.setParent(parent);
			categoryRepository.add(category, entityManager);
		}
	}

}
