package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import wow.anwendungssysteme.auction.Auction;
import wow.anwendungssysteme.auction.AuctionDetails;
import wow.anwendungssysteme.auction.AuctionListEntry;
import wow.anwendungssysteme.auction.AuctionManager;
import wow.anwendungssysteme.auction.Bid;

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
    @Produces({"application/xml","application/json"})
	public Auction getAuction(@PathParam("auctionID") String auctionID) {
    	
    	return AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID));
	}
    
    @DELETE
    @Path("/{auctionID}")
    @Produces({"application/xml","application/json"})
	public void deleteAuction(@PathParam("auctionID") String auctionID) {
    	
    	AuctionManager.getInstance().deleteAuction(Integer.parseInt(auctionID));
	}
    
    @POST
    @Consumes({"application/xml","application/json"})
	public void createAuction(Auction auction) {
    	
    	AuctionManager.getInstance().addAuction(auction);
	}
    
    @POST
    @Path("/{auctionID}/highestBid")
    @Consumes({"application/json"})
    //@Consumes({"application/xml","application/json"})
    public void makeBid(@PathParam("auctionID") String auctionID, Bid bid) {
    	AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).makeBid(bid);
    }

    @GET
    @Path("/{auctionID}/highestBid")
   // @Produces(MediaType.APPLICATION_XML)
    @Produces({"application/xml","application/json"})
    public Bid getHighestBid(@PathParam("auctionID") String auctionID) {
    	System.out.println("Bid requested!");
    	return AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).getHighestBid();
    }
    
    @GET
    @Path("/{auctionID}/auctionDetails")
    @Produces({"application/xml","application/json"})
    public AuctionDetails getAuctionDetails(@PathParam("auctionID") String auctionID) {
    	return AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).getAuctionDetails();
    }
      
    @PUT
    @Path("/{auctionID}/auctionDetails")
    @Consumes({"application/xml","application/json"})
    public void setAuctionDetails(@PathParam("auctionID") String auctionID, AuctionDetails auctionDetails) {
    	AuctionManager.getInstance().getAuction(Integer.parseInt(auctionID)).setAuctionDetails(auctionDetails);;
    }
    
    @GET
    @Path("/auctions")
    @Produces({"application/xml","application/json"})
	public AuctionListEntry[] getCurrentAuctions() {
    	System.out.println("Auctionlist requested!");
    	return AuctionManager.getInstance().getCurrentAuctions();
	}
}
