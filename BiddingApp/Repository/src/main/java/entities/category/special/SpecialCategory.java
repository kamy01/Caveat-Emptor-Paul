package entities.category.special;

import java.util.List;

import entities.category.Category;

public class SpecialCategory {
	private Long id;
	private String text;
	private String description;
	private List<SpecialCategory> nodes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SpecialCategory> getCategories() {
		return nodes;
	}

	public void setCategories(List<SpecialCategory> categories) {
		this.nodes = categories;
	}



}
