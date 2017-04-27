package services.register;

import java.math.BigInteger;
import java.util.Random;

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
import services.register.interfaces.IRegisterationService;

@Remote(IRegisterationService.class)
@Stateless
public class RegisterationService implements IRegisterationService {
	@PersistenceContext(unitName = "bidding-unit")
	private EntityManager entityManager;
	@EJB
	private IUserRepository userRepository;
	@EJB
	private IRegisterRepository registerRepository;

	public boolean registerUser(User user) throws UserException {
		userRepository.verifyUsername(user.getAccountName(), entityManager);
		Register register = new Register();
		register.setValidationCode(generateValidationCode());
		register.setUser(user);
		register.setValidationTime(generateValidationTime());
		registerRepository.create(register, entityManager);
		return true;

	}

	public String generateValidationCode() {
		return new BigInteger(200, new Random()).toString(32);

	}

	public Long generateValidationTime() {
		return System.currentTimeMillis() + constants.Register.DAY;
	}

}
