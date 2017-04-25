package services;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import repositories.login.UserRepositoryInterface;

@Remote(LoginValidationInterface.class)
@Stateless
public class LoginValidation implements LoginValidationInterface {
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@EJB
	private UserRepositoryInterface userRepository;

	public boolean validateUser(String accountName, String password) {
		return userRepository.verifyUsernameAndPassword(accountName, password, entityManager);

	}
}