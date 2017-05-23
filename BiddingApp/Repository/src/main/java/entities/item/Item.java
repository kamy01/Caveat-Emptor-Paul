package entities.item;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import entities.category.Category;
import entities.login.User;

@Entity
@NamedQueries(value = { @NamedQuery(name = "findItemsByUser", query = "SELECT i FROM Item i WHERE i.user = :user"),
		@NamedQuery(name = "findItemsByCateogry", query = "SELECT i FROM Item i WHERE i.category = :category") })

@Table(name = "item")

public class Item implements Serializable {
	public static final String FIND_ITEMS_BY_USER = "findItemsByUser";
	public static final String FIND_ITEMS_BY_CATEGORY = "findItemsByCateogry";
	public static final String GET_ALL_ITEMS = "Item.getAllItems";
	private static final long serialVersionUID = -3319507180659185256L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "initial_price")
	private Double initialPrice;

	@Column(name = "best_bid")
	private Double bestBid;

	@Column
	private Long bids;

	@Column(name = "opening_date")
	private Timestamp openingDate;

	@Column(name = "closing_date")
	private Timestamp closingDate;

	@Column
	private String status;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Double getBestBid() {
		return bestBid;
	}

	public void setBestBid(Double bestBid) {
		this.bestBid = bestBid;
	}

	public Long getBids() {
		return bids;
	}

	public void setBids(Long bids) {
		this.bids = bids;
	}

	public Timestamp getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Timestamp openingDate) {
		this.openingDate = openingDate;
	}

	public Timestamp getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Timestamp closingDate) {
		this.closingDate = closingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
