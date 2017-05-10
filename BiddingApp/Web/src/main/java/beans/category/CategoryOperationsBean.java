package beans.category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.category.Category;
import services.category.interfaces.CategoryService;

@ManagedBean(name = "categoryOperations")
@SessionScoped
public class CategoryOperationsBean {
	@EJB
	private CategoryService categoryService;
	private Category category;
	private Long parentID;

	@PostConstruct
	public void init() {
		category = new Category();
	}

	public void addCategoryToDatabase() {
		if (parentID != null) {
			categoryService.addCategory(category, parentID);
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

}
