package entities.category.DTO;

import java.util.List;

public class CategoryDTO {
	private Long id;
	private String text;
	private String description;
	
	private Long parentID;
	private List<CategoryDTO> nodes;

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

	public List<CategoryDTO> getCategories() {
		return nodes;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.nodes = categories;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}



}
