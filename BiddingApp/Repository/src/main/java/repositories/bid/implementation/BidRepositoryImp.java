package repositories.bid.implementation;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import entities.bid.Bid;
import repositories.bid.interfaces.BidRepository;

@Remote(BidRepository.class)
@Stateless
public class BidRepositoryImp implements BidRepository {

	@Override
	public void create(Bid bid, EntityManager entityManager) {
		entityManager.persist(bid);
	}

	@Override
	public Bid read(long id, EntityManager entityManager) {
		return entityManager.find(Bid.class, id);
	}

	@Override
	public void delete(Bid bid, EntityManager entityManager) {
		entityManager.remove(bid);
	}

	@Override
	public void update(Bid bid, EntityManager entityManager) {
		entityManager.merge(bid);
	}

}
