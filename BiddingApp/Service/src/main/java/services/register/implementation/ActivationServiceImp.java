package services.register.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.login.User;
import entities.register.Register;
import exceptions.user.UserException;
import repositories.login.interfaces.UserRepository;
import repositories.register.interfaces.RegisterRepository;
import services.register.interfaces.ActivationService;

@Remote(ActivationService.class)
@Stateless
public class ActivationServiceImp implements ActivationService {
	@PersistenceContext(unitName = "bidding-unit")
	private EntityManager entityManager;
	@EJB
	private UserRepository userRepository;
	@EJB
	private RegisterRepository registerRepository;

	@Override
	public void activateUser(String activationCode) throws UserException {
		Register register = registerRepository.getRegisterByActivationCode(activationCode, entityManager);
		if (isActivationCodeExpired(register.getValidationTime())) {
			throw new UserException();
		}
		User user = register.getUser();
		user.setActivated(true);
		entityManager.merge(user);

	}

	public boolean isActivationCodeExpired(Long expirationTime) {
		if (System.currentTimeMillis() > expirationTime) {
			return true;
		} else {
			return false;
		}
	}

}
