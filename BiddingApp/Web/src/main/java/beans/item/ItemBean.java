package beans.item;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import constants.ItemStatus;
import dto.ItemDTO;
import entities.login.User;
import services.category.CategoryService;
import services.item.ItemService;
import utils.DateParser;

@ManagedBean(name = "item")
@SessionScoped
public class ItemBean {

	@EJB
	private ItemService itemService;
	@EJB
	private CategoryService categoryService;

	@ManagedProperty(value = "#{login.user}")
	private User user;
	private List<ItemDTO> itemsToBuy;
	private List<ItemDTO> itemsToSell;

	private ItemDTO itemDTO;
	private Long categoryID;

	private Double initialPrice;

	private Boolean tableType;

	private String openingDate;
	private String closingDate;
	private String openingTime;
	private String closingTime;

	public Double getPrice() {
		return initialPrice;
	}

	public void setPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	@PostConstruct
	public void init() {
		itemDTO = new ItemDTO();
		setItemsToBuy(itemService.getItemsToBuy(user));
		setItemsToSell(itemService.getItemsToSell(user));
	}

	public void saveAction() {
		for (ItemDTO item : itemsToSell) {
			if (item.getEditable() == true) {
				item.setEditable(false);
				editItem(item);
			}
		}
		setItemsToSell(itemService.getItemsToSell(user));
	}

	public void refreshPage() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/" + "items/items.xhtml");
		} catch (IOException e) {
		}
	}

	public void editAction(ItemDTO item) {
		item.setEditable(true);
	}

	public Boolean statusCheck(ItemDTO item) {
		if (item.getStatus().equals(ItemStatus.CLOSED)) {
			return true;
		}
		return false;
	}

	public void editItem(ItemDTO itemDTO) {
		itemService.editItem(itemDTO);
	}

	public void addItem() {
		if (itemDTO.getName() != null && itemDTO.getInitialPrice() != null) {

			itemDTO.setCategory(categoryService.getCategoryById(categoryID));
			itemDTO.setUser(user);

			itemDTO.setBestBid(0.0);
			itemDTO.setOpeningDate(DateParser.getTimestamp(openingDate + " " + openingTime, "yyyy/mm/dd hh:mm a"));
			itemDTO.setClosingDate(DateParser.getTimestamp(closingDate + " " + closingTime, "yyyy/mm/dd hh:mm a"));
			itemDTO.setStatus(generateStatus());
			itemService.addItem(itemDTO);

			init();
		}
	}

	public void validatePrice(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Double initialPrice = (Double) value;
		if (initialPrice == null || initialPrice <= 1) {
			FacesMessage facesMessage = new FacesMessage("Initial price must be greater than or equal to 1$.");
			throw new ValidatorException(facesMessage);
		}

	}

	public void validateCateogry(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Long categoryID = (Long) value;
		if (categoryID == null) {
			FacesMessage facesMessage = new FacesMessage("Please select a category.");
			throw new ValidatorException(facesMessage);
		}
	}

	public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String name = (String) value;
		if (name.length() <= 1 || name == null) {
			FacesMessage facesMessage = new FacesMessage("Name, minimum 2 characters!");
			throw new ValidatorException(facesMessage);
		}

	}

	public void validateDescription(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String description = (String) value;
		if (description.length() <= 1 || description == null) {
			FacesMessage facesMessage = new FacesMessage("Description, minimum 2 characters!");
			throw new ValidatorException(facesMessage);
		}
	}

	public void validateDate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String date = (String) value;
		if (date == null || date.length() < 8) {
			FacesMessage facesMessage = new FacesMessage("Date empty or invalid.");
			throw new ValidatorException(facesMessage);
		}
	}

	public String generateStatus() {
		Timestamp opening = (DateParser.getTimestamp(openingDate + " " + openingTime, "yyyy/mm/dd hh:mm a"));
		Timestamp closing = (DateParser.getTimestamp(closingDate + " " + closingTime, "yyyy/mm/dd hh:mm a"));
		if (opening.after(closing)) {
			return ItemStatus.CLOSED;
		}
		if (!opening.after(closing)) {
			return ItemStatus.OPEN;
		}
		return ItemStatus.NOT_YET_OPEN;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public void notEditable(ItemDTO itemDTO) {
		itemDTO.setEditable(false);
	}

	public void showSellTable() {
		setTableTpye(false);
	}

	public void showBuyTable() {
		setTableTpye(true);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public List<ItemDTO> getItemsToBuy() {
		return itemsToBuy;
	}

	public void setItemsToBuy(List<ItemDTO> itemsToBuy) {
		this.itemsToBuy = itemsToBuy;
	}

	public List<ItemDTO> getItemsToSell() {
		return itemsToSell;
	}

	public void setItemsToSell(List<ItemDTO> itemsToSell) {
		this.itemsToSell = itemsToSell;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public Boolean getTableTpye() {
		return tableType;
	}

	public void setTableTpye(Boolean tableTpye) {
		this.tableType = tableTpye;
	}

}
