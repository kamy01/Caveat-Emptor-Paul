package services.category.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.Gson;

import dto.CategoryDTO;
import entities.category.Category;
import mappers.CategoryMapper;
import repositories.category.interfaces.CategoryRepository;
import services.category.CategoryService;

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
		CategoryDTO categoryDTO = CategoryMapper.mapToCategoryDTO(category);
		Gson gson = new Gson();
		return gson.toJson(categoryDTO);
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO, Long id) {
		if (categoryDTO.getText() != null && categoryDTO.getParentID() != null) {
			Category parent = categoryRepository.read(id, entityManager);
			Category category = CategoryMapper.mapToCategory(categoryDTO);
			category.setParent(parent);
			categoryRepository.add(category, entityManager);
		}
	}

	@Override
	public void removeCategory(CategoryDTO categoryDTO) {
		if (categoryDTO.getId() != null) {
			Category category = categoryRepository.read(categoryDTO.getId(), entityManager);

			for (Category child : category.getCategories()) {
				child.setParent(category.getParent());
				categoryRepository.add(child, entityManager);
			}

			Category newCategory = entityManager.merge(category);
			entityManager.remove(newCategory);
		}
	}

	@Override
	public void addNewRootCategory(CategoryDTO categoryDTO) {
		if (categoryDTO.getText().length() > 0) {
			Category root = categoryRepository.read(1, entityManager);
			Category category = CategoryMapper.mapToCategory(categoryDTO);
			category.setParent(root);
			categoryRepository.add(category, entityManager);
		}
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

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.read(id, entityManager);
	}


}
