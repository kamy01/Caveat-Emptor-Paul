package beans.register;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import exceptions.user.UserException;
import services.register.interfaces.ActivationService;

@ManagedBean(name = "activation")
@RequestScoped
public class ActivationBean {
	@EJB
	ActivationService activationService;

	@ManagedProperty(value = "#{param.key}")
	private String activationKey;

	public void activateAccount() throws IOException {
		try {
			activationService.activateUser(activationKey);
		} catch (UserException e) {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("registrationExpired.xhtml");
		}
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

}
