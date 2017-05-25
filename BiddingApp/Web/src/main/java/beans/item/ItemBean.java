package beans.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dto.ItemDTO;
import entities.login.User;
import services.item.interfaces.ItemService;

@ManagedBean(name = "item")
@RequestScoped
public class ItemBean {

	@EJB
	private ItemService itemService;

	private List<ItemDTO> itemsDTO;
	@ManagedProperty(value = "#{login.user}")
	private User user;

	@PostConstruct
	public void init() {
		setItemsDTO(new ArrayList<>());
		setItemsDTO(itemService.getItemsForUser(user));
	}

	public void saveAction() throws IOException {
		for (ItemDTO item : itemsDTO) {
			if (item.getEditable() == true) {
				editItem(item);
				item.setEditable(false);
			}
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath() + "/" + "items/items.xhtml");
	}

	public void editAction(ItemDTO item) {
		item.setEditable(true);
	}

	public void removeItem(ItemDTO itemDTO) {
		itemService.removeItem(itemDTO);
	}

	public void editItem(ItemDTO itemDTO) {
		itemService.editItem(itemDTO);
	}

	public void addItem(ItemDTO itemDTO) {
		itemService.addItem(itemDTO);
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

}
