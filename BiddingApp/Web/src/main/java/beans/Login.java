package beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import services.login.interfaces.ILoginValidation;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	@EJB
	ILoginValidation loginValidation;
	private String username;
	private String password;
	

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
			externalContext.redirect("login.jsf");

		}

	}

	public void redirectToRegistrationPage() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("register.jsf");
	}

}
