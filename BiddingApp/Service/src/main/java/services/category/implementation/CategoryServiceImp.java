package services.category.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.Gson;

import entities.category.Category;
import entities.category.DTO.CategoryDTO;
import entities.category.DTO.CategoryDTOConverter;
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
		CategoryDTO specialCategory = CategoryDTOConverter.convertToCategoryDTO(category);
		Gson gson = new Gson();
		return gson.toJson(specialCategory);
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO) {

		Long parentId = categoryDTO.getParentID();
		Category parent = categoryRepository.read(parentId, entityManager);
		Category category = CategoryDTOConverter.convertToCategory(categoryDTO);
		category.setParent(parent);
		categoryRepository.add(category, entityManager);
	}

	@Override
	public void removeCategory(CategoryDTO categoryDTO) {
		Long parentId = categoryDTO.getParentID();
		Category parent = categoryRepository.read(parentId, entityManager);
		Category category = CategoryDTOConverter.convertToCategory(categoryDTO);
		parent.getCategories().addAll(category.getCategories());
		categoryRepository.update(parent, entityManager);
		categoryRepository.delete(entityManager.merge(category), entityManager);
	}

}
