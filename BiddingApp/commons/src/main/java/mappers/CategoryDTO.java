package mappers;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 2664131984740112466L;

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

	public List<CategoryDTO> getNodes() {
		return nodes;
	}

	public void setNodes(List<CategoryDTO> nodes) {
		this.nodes = nodes;
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
