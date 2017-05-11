package services.category.interfaces;

import entities.category.Category;
import entities.category.DTO.CategoryDTO;

public interface CategoryService {
	public String getRootAsJson();

	public void addCategory(CategoryDTO categoryDTO);
}
