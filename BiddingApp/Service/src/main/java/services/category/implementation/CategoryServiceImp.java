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
import repositories.category.implementation.CategoryRepositoryImp;
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
	public void addCategory(Category category,Long parentID) {
		Category parent =categoryRepository.read(parentID, entityManager);
		category.setParent(parent);
		categoryRepository.create(category, entityManager);
	}

}
