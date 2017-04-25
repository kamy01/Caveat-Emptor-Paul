package repositories.login;

import javax.persistence.EntityManager;

import entities.login.User;

public interface UserRepositoryInterface {

	public void create(User user, EntityManager entityManager);

	public User read(int id, EntityManager entityManager);

	public void delete(User user, EntityManager entityManager);

	public boolean verifyUsername(String username, EntityManager entityManager);

	public boolean verifyUsernameAndPassword(String username, String password, EntityManager entityManager);

	public void setEntityManager(EntityManager entityManager);
	
}