package entities.register;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import entities.login.User;

@Entity
@NamedQuery(name = "FindRegisterByActivationKey", query = "SELECT registr FROM Register registr Where registr.validationCode = :validationCode")
@Table(name = "register")
public class Register implements Serializable {

	public static final String FIND_REGISTER_BY_ACTIVATION_KEY = "FindRegisterByActivationKey";
	private static final long serialVersionUID = 4619115082007560268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "validation_time")
	private Long validationTime;

	@Column(name = "validation_code")
	private String validationCode;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValidationTime() {
		return validationTime;
	}

	public void setValidationTime(Long validationTime) {
		this.validationTime = validationTime;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
