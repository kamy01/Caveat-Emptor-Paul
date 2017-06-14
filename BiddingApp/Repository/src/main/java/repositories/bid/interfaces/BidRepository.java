package repositories.bid.interfaces;

import javax.persistence.EntityManager;

import entities.bid.Bid;

public interface BidRepository {

	public void create(Bid bid, EntityManager entityManager);

	public Bid read(long id, EntityManager entityManager);

	public void delete(Bid bid, EntityManager entityManager);

	public void update(Bid bid, EntityManager entityManager);
}
