package beans.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PostConstruct
	public void init() {
		setItemsDTO(new ArrayList<>());
		setItemsDTO(itemService.getItemsForUser(user));
	}

	public List<ItemDTO> getItemsDTO() {
		return itemsDTO;
	}

	public void setItemsDTO(List<ItemDTO> itemsDTO) {
		this.itemsDTO = itemsDTO;
	}
	
	

}
