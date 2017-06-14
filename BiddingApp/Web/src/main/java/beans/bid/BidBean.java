package beans.bid;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dto.BidDTO;
import services.bid.BidService;

@ManagedBean(name = "bid")
@SessionScoped
public class BidBean {

	@EJB
	private BidService bidService;
	private BidDTO bidDTO;

	public BidDTO getBidDTO() {
		return bidDTO;
	}

	public void setBidDTO(BidDTO bidDTO) {
		this.bidDTO = bidDTO;
	}
}
