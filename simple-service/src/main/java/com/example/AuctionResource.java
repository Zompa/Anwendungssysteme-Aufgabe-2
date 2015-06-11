package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.auctionInformation.Auction;
import com.example.auctionInformation.AuctionDetails;
import com.example.auctionInformation.Bid;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("auction")
public class AuctionResource {

	public AuctionResource() {
	}
	
	
//    /**
//     * Method handling HTTP GET requests. The returned object will be sent
//     * to the client as "text/plain" media type.
//     *
//     * @return String that will be returned as a text/plain response.
//     */
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//        return "Got it!";
//    }
    
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String numberOfAuctions() {
//		return auctions.entrySet().size()+"";
//	}

    @GET
    @Path("/{auctionID}")
    @Produces(MediaType.APPLICATION_XML)
	public Auction getAuction(@PathParam("auctionID") String auctionID) {
    	
    	return AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID));
	}
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
	public void createAuction(Auction auction) {
    	
    	AuctionManager.getInstance().addAuction(auction);
	}
    
    @POST
    @Path("/{auctionID}/highestBid")
    @Consumes(MediaType.APPLICATION_XML)
    public void makeBid(@PathParam("auctionID") String auctionID, Bid bid) {
    	AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).makeBid(bid);
    }

    @GET
    @Path("/{auctionID}/highestBid")
    @Produces(MediaType.APPLICATION_XML)
    
    public Bid getHighestBid(@PathParam("auctionID") String auctionID) {
    	return AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).getHighestBid();
    }
    
    @GET
    @Path("/{auctionID}/auctionDetails")
    @Produces(MediaType.APPLICATION_XML)
    public AuctionDetails getAuctionDetails(@PathParam("auctionID") String auctionID) {
    	return AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).getAuctionDetails();
    }
      
    @PUT
    @Path("/{auctionID}/auctionDetails")
    @Consumes(MediaType.APPLICATION_XML)
    public void setAuctionDetails(@PathParam("auctionID") String auctionID, AuctionDetails auctionDetails) {
    	AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).setAuctionDetails(auctionDetails);;
    }

}
