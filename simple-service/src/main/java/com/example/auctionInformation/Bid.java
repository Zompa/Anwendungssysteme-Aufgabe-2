package com.example.auctionInformation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bid {

	public int value;
	public String bidderName;
	
	public Bid() {
		
	}
	
	public Bid(String bidderName, int value) {
		this.bidderName = bidderName;
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public String getBidderName() {
		return bidderName;
	}
	
	public boolean higherThan(Bid anotherBid) {
		if (anotherBid == null) return true;
		return this.value > anotherBid.getValue();
	}
}
