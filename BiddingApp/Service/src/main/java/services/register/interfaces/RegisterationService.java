 package services.register.interfaces;

import entities.login.User;
import exceptions.user.UserException;

public interface RegisterationService {

	public void crateUserWithRegistration(User user) throws UserException;
	
	
}
