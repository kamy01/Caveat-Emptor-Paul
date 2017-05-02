package services.register.interfaces;

import exceptions.user.UserException;

public interface IActivationService {
	public void activateUser(String activationCode) throws UserException;
}
