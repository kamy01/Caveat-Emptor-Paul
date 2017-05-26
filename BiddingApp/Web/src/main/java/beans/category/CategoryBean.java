package beans.category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.category.CategoryService;

@ManagedBean(name = "category")
@SessionScoped
public class CategoryBean {
	@EJB
	private CategoryService categoryService;
	private String categories;

	private String layout;

	@PostConstruct
	public void init() {
		categories = categoryService.getRootAsJson();
	}

	public String getCategories() {	
		categories = categoryService.getRootAsJson();
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getLayout() {
		setLayout("defaultLayout.xhtml");
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

}
