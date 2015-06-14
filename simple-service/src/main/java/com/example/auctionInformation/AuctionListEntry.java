package com.example.auctionInformation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuctionListEntry {

	public String name;
	public int auctionID;
	
	public AuctionListEntry() {
		
	}
	
	public AuctionListEntry(Auction auction) {
		this.name = auction.getAuctionDetails().title;
		this.auctionID =auction.auctionID;
	}
}
