package services.register.implementation;

import java.math.BigInteger;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import email.Email;
import entities.login.User;
import entities.register.Register;
import exceptions.user.UserException;
import repositories.login.interfaces.UserRepository;
import repositories.register.interfaces.RegisterRepository;
import services.register.RegisterationService;

@Remote(RegisterationService.class)
@Stateless
public class RegisterationServiceImp implements RegisterationService {
	Email email;
	@PersistenceContext(unitName = "bidding-unit")
	private EntityManager entityManager;
	@EJB
	private UserRepository userRepository;
	@EJB
	private RegisterRepository registerRepository;
	public static final long DAY = 86400000;

	public void createUserWithRegistration(User user) throws UserException {
		if (isAlreadyRegistered(user)) {
			throw new UserException();
		} else {
			Register register = createRegister(user);
			registerRepository.create(register, entityManager);
			try {
				sendValidationEmail(user.getEmail(), register.getValidationCode());
			} catch (MessagingException e) {
			}
		}

	}

	public Register createRegister(User user) {
		Register register = new Register();
		register.setValidationCode(generateValidationCode());
		register.setUser(user);
		register.setValidationTime(generateValidationTime());
		return register;
	}

	public boolean isAlreadyRegistered(User user) {
		try {
			userRepository.verifyUsername(user.getAccountName(), entityManager);
			return true;
		} catch (UserException e) {
			return false;
		}
	}

	private Session createEmailSession() {
		Email email = new Email();
		Session session = Session.getInstance(email.getProprieties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Email.SENDER_USERNAME, Email.SENDER_PASSWORD);
			}
		});
		return session;
	}

	private void sendValidationEmail(String emailAdress, String validationCode)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(createEmailSession());
		message.setFrom(new InternetAddress(Email.SENDER_USERNAME));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdress));
		message.setSubject(Email.SUBJECT);
		message.setText(Email.VALIDATION_LINK + "?key=" + validationCode);
		Transport.send(message);
	}

	private String generateValidationCode() {

		return new BigInteger(200, new Random()).toString(32);

	}

	private Long generateValidationTime() {
		return System.currentTimeMillis() + DAY;
	}
}
