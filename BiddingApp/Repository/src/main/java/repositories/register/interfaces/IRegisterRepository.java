package repositories.register.interfaces;

import javax.persistence.EntityManager;

import entities.register.Register;

public interface IRegisterRepository {

	public void create(Register register, EntityManager entityManager);

	public Register read(int id, EntityManager entityManager);

	public void delete (Register register ,EntityManager entityManager);
	
	

}
