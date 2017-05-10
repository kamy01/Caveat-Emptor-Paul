package beans.category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.category.interfaces.CategoryService;

@ManagedBean(name = "category")
@SessionScoped
public class CategoryBean {
	@EJB
	private	CategoryService categoryService;
	private String categories;

	@PostConstruct
	public void postConstruct() {
		setCategories(categoryService.getRootAsJson());
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}
	
}
