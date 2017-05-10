package entities.category.DTO;

import java.util.ArrayList;
import java.util.List;

import entities.category.Category;

public class CategoryDTOConverter {

	public static CategoryDTO convertToCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(category.getDescription());
		categoryDTO.setId(category.getId());
		categoryDTO.setText(category.getText());
		if (category.getParent() != null) {
			categoryDTO.setParentID(category.getParent().getId());
		}
		List<CategoryDTO> listDTO = new ArrayList<>();
		for (Category cat : category.getCategories()) {
			CategoryDTO dto = convertToCategoryDTO(cat);
			listDTO.add(dto);
		}
 
		categoryDTO.setCategories(listDTO);
		return categoryDTO;
	}          

//	public static Category convertToCategory(CategoryDTO categoryDTO) {
//		Category category = new Category();
//		category.setDescription(categoryDTO.getDescription());
//		category.setId(categoryDTO.getId());
//		category.setText(categoryDTO.getText());
//		if(categoryDTO.getParentID()!=null){
//			category.setParent(categoryDTO.getParentID());
//		}
//		List<Category> categoryList=new ArrayList<>();
//		for(CategoryDTO dto: categoryDTO.getCategories()){
//			Category cat= convertToCategory(dto);
//			categoryList.add(cat);
//		}
//		category.setCategories(categoryList);
//		return category;
//	}

}
