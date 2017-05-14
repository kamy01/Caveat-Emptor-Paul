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

		Category parent = categoryRepository.read(categoryDTO.getParentID(), entityManager);
		Category category = CategoryDTOConverter.convertToCategory(categoryDTO);
		category.setParent(parent);
		categoryRepository.add(category, entityManager);
		
	}

	@Override
	public void removeCategory(CategoryDTO categoryDTO) {
		Category category = categoryRepository.read(categoryDTO.getID(), entityManager);
		
			for (Category child : category.getCategories()) {
			child.setParent(category.getParent());
			categoryRepository.add(child, entityManager);
		}

		categoryRepository.delete(entityManager.merge(category), entityManager);
	}

}
