package beans.category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.category.DTO.CategoryDTO;
import services.category.interfaces.CategoryService;

@ManagedBean(name = "categoryOperations")
@RequestScoped
public class CategoryOperationsBean {

	@EJB
	private CategoryService categoryService;

	private CategoryDTO category;
	private Long id;

	@PostConstruct
	public void init() {
		category = new CategoryDTO();
	}

	public void addCategoryToDatabase() {
		categoryService.addCategory(category);
	}

	public void removeCategoryFromDatabase() {
		category.setId(id);
		categoryService.removeCategory(category);
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
