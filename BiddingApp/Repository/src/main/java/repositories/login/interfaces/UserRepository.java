package repositories.login.interfaces;

import javax.persistence.EntityManager;

import entities.login.User;
import exceptions.user.UserException;

public interface UserRepository  {

	public void create(User user, EntityManager entityManager);

	public User read(int id, EntityManager entityManager);

	public void delete(User user, EntityManager entityManager);

	public void verifyUsername(String username, EntityManager entityManager) throws UserException;

	public User verifyUsernameAndPassword(String username, String password, EntityManager entityManager) throws UserException;

	
}