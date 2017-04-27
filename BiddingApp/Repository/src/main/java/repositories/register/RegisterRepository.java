package repositories.register;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

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
		Register register = entityManager.find(Register.class, id);
		return register;

	}

	@Override
	public void delete(Register register, EntityManager entityManager) {
		entityManager.remove(register);
	}

}
 