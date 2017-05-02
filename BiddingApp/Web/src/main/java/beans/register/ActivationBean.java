package beans.register;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import exceptions.user.UserException;
import services.register.interfaces.IActivationService;

@ManagedBean(name = "activation")
@RequestScoped
public class ActivationBean {
	@EJB
	IActivationService activationService;

	@ManagedProperty(value = "#{param.key}")
	private String activationKey;

	public void activateAccount() {
		try {
			activationService.activateUser(activationKey);
		} catch (UserException e) {

			e.printStackTrace();
		}
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

}
