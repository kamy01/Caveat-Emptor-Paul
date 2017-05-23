package dto;

import java.io.Serializable;
import java.sql.Timestamp;

import entities.category.Category;
import entities.login.User;

public class ItemDTO implements Serializable {

	private static final long serialVersionUID = -9132327261704187679L;

	private String name;
	private String description;
	private Long id;
	private Category category;
	private Double initialPrice;
	private Double bestBid;
	private Long bids;
	private Timestamp openingDate;
	private Timestamp closingDate;
	private String status;
	private User user;

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


	public Double getBestBid() {
		return bestBid;
	}

	public void setBestBid(Double bestBid) {
		this.bestBid = bestBid;
	}

	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
