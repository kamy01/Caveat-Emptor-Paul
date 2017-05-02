package services.register;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.login.User;
import entities.register.Register;
import exceptions.user.UserException;
import repositories.login.interfaces.IUserRepository;
import repositories.register.interfaces.IRegisterRepository;
import services.register.interfaces.IActivationService;

@Remote(IActivationService.class)
@Stateless
public class ActivationService implements IActivationService {
	@PersistenceContext(unitName = "bidding-unit")
	private EntityManager entityManager;
	@EJB
	private IUserRepository userRepository;
	@EJB
	private IRegisterRepository registerRepository;

	@Override
	public void activateUser(String activationCode) throws UserException {
		Register register = registerRepository.getRegisterByActivationCode(activationCode, entityManager);
		if (isActivationCodeExpired(register.getValidationTime())) {
			throw new UserException();
		}
		User user = register.getUser();
		user.setValid(true);
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
