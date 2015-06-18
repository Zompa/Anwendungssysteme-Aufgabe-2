package wow.anwendungssysteme.auction;

import javax.xml.bind.annotation.XmlRootElement;

import wow.anwendungssysteme.user.User;
import wow.anwendungssysteme.user.UserManager;

@XmlRootElement
public class Bid {

	public int value;
	public int bidderId;
	
	public Bid() {
		
	}
	
	public Bid(int bidderId, int value) {
		this.bidderId = bidderId;
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public User getBidder() {
		return UserManager.getInstance().getUser(bidderId);
	}
	
	public boolean higherThan(Bid anotherBid) {
		if (anotherBid == null) return true;
		return this.value > anotherBid.getValue();
	}
}
