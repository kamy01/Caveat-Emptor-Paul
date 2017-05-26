package beans.category;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dto.CategoryDTO;
import services.category.CategoryService;

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
		categoryService.addCategory(category, id);
		redirect();
	}

	public void addNewRootCategoryToDatabase() throws IOException {
		categoryService.addNewRootCategory(category);
		redirect();
	}

	public void removeCategoryFromDatabase() {
		category.setId(id);
		categoryService.removeCategory(category);
		redirect();
	}

	public void updateCategory() {
		category.setId(id);
		categoryService.updateCategory(category);
		redirect();
	}

	public void redirect() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/" + "categories/categories.xhtml");
		} catch (IOException e) {
		}
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
