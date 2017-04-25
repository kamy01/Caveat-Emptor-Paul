package repositories.login;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.login.User;

@Remote(UserRepositoryInterface.class)
@Stateless
public class UserRepository implements UserRepositoryInterface {
	private EntityManager entityManager;

	@Override
	public void create(User user, EntityManager entityManager) {
		setEntityManager(entityManager);
		entityManager.persist(user);
	}

	@Override
	public User read(int id, EntityManager entityManager) {
		User user;
		setEntityManager(entityManager);
		user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public void delete(User user, EntityManager entityManager) {
		setEntityManager(entityManager);
		entityManager.remove(user);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean verifyUsername(String accountName, EntityManager entityManager) {
		setEntityManager(entityManager);
		User user;
		Query query = entityManager.createNamedQuery(User.FIND_BY_USERNAME);
		query.setParameter("account", accountName);
		user = (User) query.getSingleResult();
		if (user == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean verifyUsernameAndPassword(String accountName, String password, EntityManager entityManager) {
		Query query = entityManager.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD);
		query.setParameter("account", accountName);
		query.setParameter("password", password);
		try {
			User user = (User) query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}
