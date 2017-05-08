package services.category.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.Gson;

import entities.category.Category;
import entities.category.special.SpecialCategory;
import entities.category.special.SpecialCategory;
import entities.category.special.SpecialCategoryConverter;
import repositories.category.implementation.CategoryRepositoryImp;
import repositories.category.interfaces.CategoryRepository;
import services.category.interfaces.CategoryService;

@Remote(CategoryService.class)
@Stateless
public class CategoryServiceImp implements CategoryService {
	@EJB
	CategoryRepository categoryRepository = new CategoryRepositoryImp();
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@Override
	public String getRootAsJson() {

		Category category = categoryRepository.read(1l, entityManager);
		SpecialCategory specialCategory = SpecialCategoryConverter.convertToSpecialCategory(category);
		Gson gson = new Gson();
		return gson.toJson(specialCategory);
	}

}
