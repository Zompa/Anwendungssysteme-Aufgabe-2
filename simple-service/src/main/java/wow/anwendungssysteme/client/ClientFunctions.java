package wow.anwendungssysteme.client;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import wow.anwendungssysteme.auction.Auction;
import wow.anwendungssysteme.auction.AuctionDetails;
import wow.anwendungssysteme.auction.Bid;
import wow.anwendungssysteme.user.User;

public class ClientFunctions {
 
  public static void main(String[] args) {
 
		Client client = ClientBuilder.newClient();
		WebTarget rootTarget = client.target("http://localhost:8080/myapp");
		int auctionID = 100;
		int userID = 50;
		
		// Targets  Auktionen betreffend				
		WebTarget auctionTarget = rootTarget.path("auction");
		WebTarget allAuctionsTarget = auctionTarget.path("auctions");
		WebTarget specificAuctionTarget = auctionTarget.path(Integer.toString(auctionID));
		WebTarget bidTarget = specificAuctionTarget.path("highestBid");
		WebTarget detailTarget = specificAuctionTarget.path("auctionDetails");
		
		// Targets User betreffend
		WebTarget userTarget = rootTarget.path("user");
		WebTarget specificUserTarget = userTarget.path(Integer.toString(userID));
		WebTarget allUsersTarget = userTarget.path("users");
		

		//REST CALLS USER BETREFFEND
		
		// REST POST um einen neuen User anzulegen
		User user = new User("Melina", userID); // User (<Name>, <ID>)		
		Response response = userTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
		System.out.println("POST User: " + response.getStatus());
		
		// REST GET um alle User abzufragen
		Invocation.Builder invocationBuilderUsers = allUsersTarget.request(MediaType.APPLICATION_JSON);				 
		response = invocationBuilderUsers.get();
		System.out.println("GET Users: " + response.getStatus());
		System.out.println(response.readEntity(String.class));
		
		// REST GET um einen bestimmten User abzufragen
		Invocation.Builder invocationBuilderUser = specificUserTarget.request(MediaType.APPLICATION_JSON);				 
		response = invocationBuilderUser.get();
		System.out.println("GET User: " + response.getStatus());
		System.out.println(response.readEntity(String.class));

		// REST DELETE um einen bestimmten User zu löschen
		response = specificUserTarget.request(MediaType.APPLICATION_JSON).delete();
		System.out.println("DELETE User: " + response.getStatus());
		
		
		
		
		// REST CALLS AUKTIONEN BETREFFEND
		
		// REST  POST um eine neue Auktion anzulegen
		// AuctionDetails(<Titel>, <Beschreibung>, <BildURL>, <Endzeitpunkt>)
		AuctionDetails details = new AuctionDetails("Titel der neuen Auktion", "Beschreibungstext der neuen Auktion", "URL des Bildes ", 1000);
		// Auction(details, <auctionID>, <creatorId>)
		Auction auction = new Auction(details, auctionID, 21);			
		response = auctionTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(auction, MediaType.APPLICATION_JSON));
		System.out.println("POST Auction: " + response.getStatus());
		
		// REST POST um ein Gebot zu einer bestimmten Auktion abzugeben		
		Bid bid = new Bid(42, auctionID); //Bid(<bidderID>, <value>)
		response = bidTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(bid, MediaType.APPLICATION_JSON));
		System.out.println("POST Bid: " + response.getStatus());
		
		// REST GET um das Höchstgebot einer bestimmten Auktion abzufragen
		Invocation.Builder invocationBuilderBid = specificAuctionTarget.request(MediaType.APPLICATION_JSON);				 
		response = invocationBuilderBid.get();
		System.out.println("GET highest Bid: " + response.getStatus());
		System.out.println(response.readEntity(String.class));

		// REST GET um alle Auktionen abzufragen
		Invocation.Builder invocationBuilderAll = allAuctionsTarget.request(MediaType.APPLICATION_JSON);				 
		response = invocationBuilderAll.get();
		System.out.println("GET all Auctions: " + response.getStatus());
		System.out.println(response.readEntity(String.class));
		
		// REST GET um Informationen über eine bestimmte Auktion abzufragen		
		Invocation.Builder invocationBuilderSpecificAuction = specificAuctionTarget.request(MediaType.APPLICATION_JSON);				 
		response = invocationBuilderSpecificAuction.get();
		System.out.println("GET Auction: " + response.getStatus());
		System.out.println(response.readEntity(String.class));				
		
		// REST PUT um die Detailinformationen einer bestimmten Auktion abzuändern
		details = new AuctionDetails("geänderter Titel der neuen Auktion", "geänderter Beschreibungstext der neuen Auktion", "geänderte URL des Bildes ", 2000);
		response = specificAuctionTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(details, MediaType.APPLICATION_JSON));
		System.out.println("PUT Auction Details: " + response.getStatus());
		
				
		// REST GET um die Detailinformationen einer bestimmten Auktion abzufragen		
		Invocation.Builder invocationBuilderDetails = detailTarget.request(MediaType.APPLICATION_JSON);				 
		response = invocationBuilderDetails.get();
		System.out.println("GET Auction Details: " + response.getStatus());
		System.out.println(response.readEntity(String.class));	
		
		// REST DELETE um eine bestimmte Auktion zu löschen			 
		response = specificAuctionTarget.request(MediaType.APPLICATION_JSON).delete();
		System.out.println("DELETE Auction: " + response.getStatus());	
		
	}
  
  public static void performAuctionCalls(){
	  
  }
  
  
}