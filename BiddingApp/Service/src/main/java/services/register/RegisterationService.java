 package services.register;

import entities.login.User;
import exceptions.user.UserException;

public interface RegisterationService {

	public void createUserWithRegistration(User user) throws UserException;
	
	
}
