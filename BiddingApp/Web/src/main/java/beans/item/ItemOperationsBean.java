package beans.item;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.ItemDTO;
import services.item.interfaces.ItemService;

@ManagedBean(name = "itemOperations")
@RequestScoped
public class ItemOperationsBean {
	@EJB
	private ItemService itemService;
	private ItemDTO itemDTO;

	public void removeItem(ItemDTO itemDTO) {
		itemService.removeItem(itemDTO);
	}

	public void editItem(ItemDTO itemDTO) {
		itemService.editItem(itemDTO);
	}

	public void addItem(ItemDTO itemDTO) {
		itemService.addItem(itemDTO);
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
}
