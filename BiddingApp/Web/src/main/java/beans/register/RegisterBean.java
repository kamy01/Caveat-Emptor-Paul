package beans.register;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entities.login.User;
import exceptions.user.UserException;
import services.register.interfaces.IRegisterationService;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean {
	@EJB
	private IRegisterationService registerationService;

	private String accountName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;

	public User createUser() {
		User user = new User();
		user.setAccountName(accountName);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setValid(false);
		return user;

	}

	public void registerUserToDatabase() throws IOException {
		User user = createUser();
		try {
			registerationService.crateUserWithRegistration(user);
		} catch (UserException e) {
			FacesMessage facesMessage = new FacesMessage("User already exists");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("form", facesMessage);
		}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
