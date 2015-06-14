package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.auctionInformation.Auction;
import com.example.auctionInformation.AuctionDetails;
import com.example.auctionInformation.Bid;

public class TestRequestClass {

	public static void sendTestRequests(Client c) {
		AuctionDetails details = new AuctionDetails("Tollster titel", "description", "example.com", 1000);
		Auction auction = new Auction(details, 666);
		
		//create auction
        WebTarget target = c.target(Main.BASE_URI);
    	Response response = target.path("auction").request().post(Entity.entity(auction, MediaType.APPLICATION_XML));
        System.out.println(response);

		//create auction
        details.title = "afafj";
        auction = new Auction(details, 42);
    	 response = target.path("auction").request().post(Entity.entity(auction, MediaType.APPLICATION_XML));
        System.out.println(response);
        
		//create auction
        details.title = "Baum";
        auction = new Auction(details, 33);
    	 response = target.path("auction").request().post(Entity.entity(auction, MediaType.APPLICATION_XML));
        System.out.println(response);
        
        //send test bid
        Bid bid = new Bid("Christian", 300);
    	response = target.path("auction/666/highestBid").request().post(Entity.entity(bid, MediaType.APPLICATION_JSON));
        System.out.println(response);
        
        //change auction details
        details.title = "noch tollerer titel";
        response = target.path("auction/666/auctionDetails").request().put(Entity.entity(details, MediaType.APPLICATION_XML));
        System.out.println(response);
        System.out.println("schlafen");
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("t�ten");
		//delete auction
        response = target.path("auction/33").request().delete();
        System.out.println(response);
	}
}
