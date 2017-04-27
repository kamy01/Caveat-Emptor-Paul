package services.login;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import repositories.login.interfaces.IUserRepository;
import services.login.interfaces.ILoginValidation;

@Remote(ILoginValidation.class)
@Stateless
public class LoginValidation implements ILoginValidation {
	@PersistenceContext(unitName = "bidding-unit")
	EntityManager entityManager;

	@EJB
	private IUserRepository userRepository;

	public boolean validateUser(String accountName, String password) {
		return userRepository.verifyUsernameAndPassword(accountName, password, entityManager);

	}
}
