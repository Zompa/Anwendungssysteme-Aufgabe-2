package com.example.auctionInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Daniel
 * Contains all information about one auction
 */
@XmlRootElement
public class Auction {

	private Stack<Bid> bidHistory = new Stack<>();
	private AuctionDetails auctionDetails;
	public int auctionID;
	
	public Auction() {
		
	}
	
	public Auction(AuctionDetails auctionDetails, int auctionID) {
		this.auctionDetails = auctionDetails;
		this.auctionID = auctionID;
	}

	public AuctionDetails getAuctionDetails() {
		return auctionDetails;
	}

	public void setAuctionDetails(AuctionDetails auctionDetails) {
		this.auctionDetails = auctionDetails;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public Bid getHighestBid() {
		if (bidHistory.isEmpty()) return null;
		return bidHistory.peek();
	}
	
	public void makeBid(Bid bid) {
		if (bid.higherThan(getHighestBid())) {
			bidHistory.push(bid);
			Logger.getAnonymousLogger().info("pushed bid");
		}
		
	}
	
}
