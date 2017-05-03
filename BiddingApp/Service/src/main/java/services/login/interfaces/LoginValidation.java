package services.login.interfaces;

import exceptions.user.UserException;

public interface LoginValidation {
	public boolean validateUser(String accountName, String password) throws UserException;

}
