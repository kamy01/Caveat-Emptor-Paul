package beans.login;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import entities.login.User;
import exceptions.user.UserException;
import services.login.LoginValidation;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {
	@EJB
	private LoginValidation loginValidation;
	private String username;
	private String password;
	private User user;

	public void validateLogin() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			if (loginValidation.validateUser(username, password)) {
				externalContext.redirect(externalContext.getRequestContextPath() + "/" + "categories/categories.xhtml");
				setUser(loginValidation.getUser(username, password));
			} else {
				FacesMessage facesMessage = new FacesMessage("Account not activated,check your email.");
				addMessageToFaces(facesMessage);
			}
		} catch (UserException e) {
			FacesMessage facesMessage = new FacesMessage("Wrong username or password.");
			addMessageToFaces(facesMessage);
		}

	}

	public void addMessageToFaces(FacesMessage facesMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("form", facesMessage);
	}

	public void redirectToRegistrationPage() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath() + "/" + "register/register.xhtml");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

}
