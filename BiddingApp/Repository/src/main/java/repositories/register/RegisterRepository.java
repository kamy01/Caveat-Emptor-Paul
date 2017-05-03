package repositories.register;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.register.Register;
import repositories.register.interfaces.IRegisterRepository;

@Remote(IRegisterRepository.class)
@Stateless
public class RegisterRepository implements IRegisterRepository {

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

	public Register getRegisterByActivationCode(String validationCode, EntityManager entityManager)
			{
		Query query = entityManager.createNamedQuery(Register.FIND_REGISTER_BY_ACTIVATION_KEY);
		query.setParameter("validationCode", validationCode);
		return (Register) query.getSingleResult();

	}

}
