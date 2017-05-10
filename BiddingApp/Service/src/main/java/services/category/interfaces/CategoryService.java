package services.category.interfaces;

import entities.category.Category;

public interface CategoryService {
	public String getRootAsJson();


	public void addCategory(Category categoryDTO, Long parentID);
}
