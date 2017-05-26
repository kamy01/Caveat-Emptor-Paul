package services.category;

import dto.CategoryDTO;

public interface CategoryService {
	public String getRootAsJson();

	public void addCategory(CategoryDTO categoryDTO, Long id);

	public void removeCategory(CategoryDTO categoryDTO);

	public void addNewRootCategory(CategoryDTO categoryDTO);
	
	public void updateCategory(CategoryDTO categoryDTO);
}
