package entities.category.special;

import java.util.ArrayList;
import java.util.List;

import entities.category.Category;

public class SpecialCategoryConverter {

	public static SpecialCategory convertToSpecialCategory(Category category) {
		SpecialCategory specialCategory = new SpecialCategory();
		specialCategory.setDescription(category.getDescription());
		specialCategory.setId(category.getId());
		specialCategory.setText(category.getText());
		List<SpecialCategory> specialList = new ArrayList<>();
		for (Category cat : category.getCategories()) {
			SpecialCategory special = convertToSpecialCategory(cat);
			specialList.add(special);
		}

		specialCategory.setCategories(specialList);
		return specialCategory;
	}

}
