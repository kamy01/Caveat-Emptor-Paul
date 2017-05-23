package entities.login;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entities.item.Item;

@Entity
@NamedQueries({
		@NamedQuery(name = "User.findByUsername", query = "SELECT usr FROM User usr Where usr.accountName = :account"),
		@NamedQuery(name = "User.findByUsernameAndPassword", query = "Select usr from User usr Where usr.accountName = :account and usr.password= :password"), })
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 7138048011187269909L;
	public static final String FIND_BY_USERNAME = "User.findByUsername";
	public static final String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsernameAndPassword";

	public User() {
		activated = false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String email;

	@Column(name = "account_name")
	private String accountName;

	@Column
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "activated")
	private boolean activated;

	@OneToMany(mappedBy = "user")
	private List<Item> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean isValid) {
		this.activated = isValid;
	}

}
