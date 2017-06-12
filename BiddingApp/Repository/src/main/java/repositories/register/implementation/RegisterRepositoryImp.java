package repositories.register.implementation;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.register.Register;
import exceptions.user.UserException;
import repositories.register.interfaces.RegisterRepository;

@Remote(RegisterRepository.class)
@Stateless
public class RegisterRepositoryImp implements RegisterRepository {

	@Override
	public void create(Register register, EntityManager entityManager) {
		entityManager.persist(register);
	}

	@Override
	public Register read(int id, EntityManager entityManager) {
		return entityManager.find(Register.class, id);

	}

	@Override
	public void delete(Register register, EntityManager entityManager) {
		entityManager.remove(register);
	}

	public Register getRegisterByActivationCode(String validationCode, EntityManager entityManager) throws UserException {
		Query query = entityManager.createNamedQuery(Register.FIND_REGISTER_BY_ACTIVATION_KEY);
		query.setParameter("validationCode", validationCode);
		try {
			return (Register) query.getSingleResult();

		} catch (PersistenceException e) {
			throw new UserException();
		}
	}

}
