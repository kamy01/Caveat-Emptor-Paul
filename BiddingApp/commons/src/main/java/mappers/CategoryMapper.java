package mappers;

import java.util.ArrayList;
import java.util.List;

import dto.CategoryDTO;
import entities.category.Category;

public class CategoryMapper {

	public static CategoryDTO mapToCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(category.getDescription());
		categoryDTO.setId(category.getId());
		categoryDTO.setText(category.getText());
		if (category.getParent() != null) {
			categoryDTO.setParentID(category.getParent().getId());
		}
		List<CategoryDTO> listDTO = new ArrayList<>();
		for (Category cat : category.getCategories()) {
			CategoryDTO dto = mapToCategoryDTO(cat);
			listDTO.add(dto);
		}

		categoryDTO.setCategories(listDTO);
		return categoryDTO;
	}

	public static Category mapToCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setDescription(categoryDTO.getDescription());
		category.setId(categoryDTO.getId());
		category.setText(categoryDTO.getText());

		List<Category> list = new ArrayList<>();
		if (categoryDTO.getCategories() != null) {
			for (CategoryDTO dto : categoryDTO.getCategories()) {
				Category cat = mapToCategory(dto);
				list.add(cat);
			}
		}
		category.setCategories(list);
		return category;
	}

}
