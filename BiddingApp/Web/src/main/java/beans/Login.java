package beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import services.LoginValidationInterface;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	@EJB
	LoginValidationInterface loginValidation;
	private String username;
	private String password;
	private String status = "Status";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validateLogin() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		if (loginValidation.validateUser(username, password)) {
			externalContext.redirect("http://www.ebay.com/sch/Computer-Processors-CPUs/164/bn_661751/i.html");
		} else {
			status = "Username does not exist";
			externalContext.redirect("index.jsf");

		}

	}

	public void redirectToRegistrationPage() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
