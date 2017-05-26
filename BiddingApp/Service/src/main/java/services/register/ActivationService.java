package services.register;

import exceptions.user.UserException;

public interface ActivationService {
	public void activateUser(String activationCode) throws UserException;
}
