package repositories.login.implementation;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.login.User;
import exceptions.user.UserException;
import repositories.login.interfaces.UserRepository;

@Remote(UserRepository.class)
@Stateless
public class UserRepositoryImp implements UserRepository {

	@Override
	public void create(User user, EntityManager entityManager) {
		entityManager.persist(user);
	}

	@Override
	public User read(int id, EntityManager entityManager) {

		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public void delete(User user, EntityManager entityManager) {

		entityManager.remove(user);
	}

	@Override
	public User findUserByUsernameAndPassword(String accountName, String password, EntityManager entityManager) {
		Query query = entityManager.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD);
		query.setParameter("account", accountName);
		query.setParameter("password", password);

		return (User) query.getSingleResult();
	}

	@Override
	public void verifyUsername(String accountName, EntityManager entityManager) throws UserException {

		Query query = entityManager.createNamedQuery(User.FIND_BY_USERNAME);
		query.setParameter("account", accountName);
		try {
			query.getSingleResult();
		} catch (PersistenceException e) {
			throw new UserException();
		}
	}

	@Override
	public User verifyUsernameAndPassword(String accountName, String password, EntityManager entityManager)
			throws UserException {
		Query query = entityManager.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD);
		query.setParameter("account", accountName);
		query.setParameter("password", password);
		try {
			return (User) query.getSingleResult();

		} catch (PersistenceException e) {
			throw new UserException();
		}
	}

}
