package services.register.interfaces;

import entities.login.User;
import exceptions.user.UserException;

public interface IRegisterationService {
	public boolean registerUser(User user) throws UserException;
}
       