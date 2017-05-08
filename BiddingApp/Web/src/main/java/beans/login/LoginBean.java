package beans.login;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import exceptions.user.UserException;
import services.login.interfaces.LoginValidation;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {
	@EJB
	LoginValidation loginValidation;
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
		try {
			if (loginValidation.validateUser(username, password)) {
				externalContext.redirect(externalContext.getRequestContextPath() + "/" + "categories/categories.xhtml");
			} else {
				FacesMessage facesMessage = new FacesMessage("Account not activated,check your email.");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("form", facesMessage);
			}
		} catch (UserException e) {
			FacesMessage facesMessage = new FacesMessage("Wrong username or password.");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("form", facesMessage);
		}

	}

	public void redirectToRegistrationPage() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath() + "/" + "register/register.xhtml");
	}

}
