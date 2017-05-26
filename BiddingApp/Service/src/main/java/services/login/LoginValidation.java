package services.login;

import entities.login.User;
import exceptions.user.UserException;

public interface LoginValidation {
	public boolean validateUser(String accountName, String password) throws UserException;

	public User getUser(String accountName, String password);
}
