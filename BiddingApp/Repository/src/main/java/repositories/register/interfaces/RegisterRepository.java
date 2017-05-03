package repositories.register.interfaces;

import javax.persistence.EntityManager;

import entities.register.Register;
import exceptions.user.UserException;

public interface RegisterRepository {

	public void create(Register register, EntityManager entityManager);

	public Register read(int id, EntityManager entityManager);

	public void delete(Register register, EntityManager entityManager);

	public Register getRegisterByActivationCode(String validationCode, EntityManager entityManager) throws UserException;

}
