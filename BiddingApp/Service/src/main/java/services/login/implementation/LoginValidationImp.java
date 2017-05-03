package services.login.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.login.User;
import exceptions.user.UserException;
import repositories.login.interfaces.UserRepository;
import services.login.interfaces.LoginValidation;

@Remote(LoginValidation.class)
@Stateless
public class LoginValidationImp implements LoginValidation {
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@EJB
	private UserRepository userRepository;

	public boolean validateUser(String accountName, String password) throws UserException {
		User user = userRepository.verifyUsernameAndPassword(accountName, password, entityManager);
		return user.isActivated();

	}

}
