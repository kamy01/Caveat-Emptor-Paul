package beans.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dto.ItemDTO;
import entities.login.User;
import services.item.ItemService;

@ManagedBean(name = "item")
@SessionScoped
public class ItemBean {

	@EJB
	private ItemService itemService;
	private List<ItemDTO> itemsDTO;
	
	@ManagedProperty(value = "#{login.user}")
	private User user;
	private ItemDTO itemDTO;
	private String openingDate;
	private String closingDate;
	private Long categoryID;

	@PostConstruct
	public void init() {
		itemDTO = new ItemDTO();
		setItemsDTO(new ArrayList<>());
		setItemsDTO(itemService.getItemsForUser(user));
	}

	public void saveAction()  {
		for (ItemDTO item : itemsDTO) {
			if (item.getEditable() == true) {
				editItem(item);
				item.setEditable(false);
			}
		}
		refreshPage();

	}

	public void refreshPage()  {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/" + "items/items.xhtml");
		} catch (IOException e) {
		}
	}

	public void editAction(ItemDTO item) {
		item.setEditable(true);
	}

	public void removeItem(ItemDTO itemDTO) {
		itemService.removeItem(itemDTO);
		refreshPage();
	}

	public void editItem(ItemDTO itemDTO) {
		itemService.editItem(itemDTO);
	}

	public void addItem() {
		itemDTO.setUser(user);
		itemDTO.setBestBid(0.0);
		itemDTO.setBids((long)0);

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ItemDTO> getItemsDTO() {
		return itemsDTO;
	}

	public void setItemsDTO(List<ItemDTO> itemsDTO) {
		this.itemsDTO = itemsDTO;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

}
