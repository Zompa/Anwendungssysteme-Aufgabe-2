package wow.anwendungssysteme.client;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import wow.anwendungssysteme.auction.Auction;
import wow.anwendungssysteme.auction.AuctionDetails;
import wow.anwendungssysteme.auction.Bid;
import wow.anwendungssysteme.user.User;

public class ClientFunctions {
 
	static Client client = ClientBuilder.newClient();
	static WebTarget rootTarget = client.target("http://localhost:8080/myapp");
	
	
	static// Targets  Auktionen betreffend				
	WebTarget auctionTarget = rootTarget.path("auction");
	static WebTarget allAuctionsTarget = auctionTarget.path("auctions");	
	static WebTarget bidTarget;
	static WebTarget detailTarget;
	static WebTarget specificAuctionTarget;
	
	// Targets User betreffend
	static WebTarget userTarget = rootTarget.path("user");
	static WebTarget allUsersTarget = userTarget.path("users");
	static WebTarget specificUserTarget;
	
	// REST POST um einen neuen User anzulegen
	public static void createUser(String name, int id){	
	User user = new User(name, id); // User (String <Name>,int <ID>)		
	Response response = userTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
	System.out.println("POST User: " + response.getStatus());
	}
	
	// REST GET um alle User abzufragen
	public static ArrayList<User> getUsers(){
				Invocation.Builder invocationBuilderUsers = allUsersTarget.request(MediaType.APPLICATION_JSON);		
				return invocationBuilderUsers.get(new GenericType<ArrayList<User>>() {});
				}
	
	// REST GET um einen bestimmten User abzufragen
	public static User getUserById(int id){
		specificUserTarget	= userTarget.path(Integer.toString(id));
				Invocation.Builder invocationBuilderUser = specificUserTarget.request(MediaType.APPLICATION_JSON);				 
				return invocationBuilderUser.get(User.class);
	}
	
	// REST DELETE um einen bestimmten User zu löschen
	public static void deleteUserById(int id){
		specificUserTarget	= userTarget.path(Integer.toString(id));
		Response response = specificUserTarget.request(MediaType.APPLICATION_JSON).delete();
		System.out.println("DELETE User: " + response.getStatus());
	}
	
	// REST  POST um eine neue Auktion anzulegen
	public static void createAuction(int id, int creatorId, String title, String description, String imgUrl, long endTime){
				// AuctionDetails(<Titel>, <Beschreibung>, <BildURL>, <Endzeitpunkt>)
				AuctionDetails details = new AuctionDetails(title, description, imgUrl, endTime);
				// Auction(details, <auctionID>, <creatorId>)
				Auction auction = new Auction(details, id, creatorId);			
				Response response = auctionTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(auction, MediaType.APPLICATION_JSON));
				System.out.println("POST Auction: " + response.getStatus());
	}
	
	// REST POST um ein Gebot zu einer bestimmten Auktion abzugeben
	public static void bid (int auctionId, int bidderId, int value){
		Bid bid = new Bid(bidderId, value); //Bid(int <bidderID>, int <value>)
		bidTarget = auctionTarget.path(Integer.toString(auctionId)).path("highestBid");
		Response response = bidTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(bid, MediaType.APPLICATION_JSON));
		System.out.println("POST Bid: " + response.getStatus());
	}
	
	// REST GET um das Höchstgebot einer bestimmten Auktion abzufragen
	public static Bid getHighestBid(int auctionId){
		specificAuctionTarget = auctionTarget.path(Integer.toString(auctionId));
		Invocation.Builder invocationBuilderBid = specificAuctionTarget.request(MediaType.APPLICATION_JSON);				 
		return invocationBuilderBid.get(Bid.class);
	}
	
	// REST GET um alle Auktionen abzufragen
	public static ArrayList<Auction> getAllAuctions(){		
		Invocation.Builder invocationBuilderAll = allAuctionsTarget.request(MediaType.APPLICATION_JSON);				 
		return invocationBuilderAll.get(new GenericType<ArrayList<Auction>>() {});
	}
	
	// REST GET um Informationen über eine bestimmte Auktion abzufragen	
	public static Auction getAuctionById(int auctionId){
		specificAuctionTarget = auctionTarget.path(Integer.toString(auctionId));
		Invocation.Builder invocationBuilderSpecificAuction = specificAuctionTarget.request(MediaType.APPLICATION_JSON);				 
		return invocationBuilderSpecificAuction.get(Auction.class);
	}

	// REST PUT um die Detailinformationen einer bestimmten Auktion abzuändern
	public static void updateAuction(int auctionId, String newTitle, String newDescription, String newImgURL, long newEnddate){
		AuctionDetails details = new AuctionDetails(newTitle, newDescription, newImgURL, newEnddate);
		specificAuctionTarget = auctionTarget.path(Integer.toString(auctionId));
		Response response = specificAuctionTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(details, MediaType.APPLICATION_JSON));
		System.out.println("PUT Auction Details: " + response.getStatus());		
	} 
	
	// REST GET um die Detailinformationen einer bestimmten Auktion abzufragen
	public static AuctionDetails getAuctionDetailsById(int auctionId){
		detailTarget = auctionTarget.path(Integer.toString(auctionId)).path("auctionDetails");
		Invocation.Builder invocationBuilderDetails = detailTarget.request(MediaType.APPLICATION_JSON);				 
		return invocationBuilderDetails.get(AuctionDetails.class);
	}
	
	// REST DELETE um eine bestimmte Auktion zu löschen
	public static void deleteAuction(int auctionId){
		specificAuctionTarget = auctionTarget.path(Integer.toString(auctionId));
		Response response = specificAuctionTarget.request(MediaType.APPLICATION_JSON).delete();
		System.out.println("DELETE Auction: " + response.getStatus());
	}
	
  public static void main(String[] args) {
	  
	 int auctionId = 100;
	 int userId = 50;

	  //REST CALLS USER BETREFFEND
	createUser("Melina", userId);
	getUsers();
	getUserById(userId);
	deleteUserById(userId);
	
	// REST CALLS AUKTIONEN BETREFFEND
	createAuction(auctionId, 21, "Titel der neuen Auktion", "Beschreibungstext der neuen Auktion", "URL des Bildes ", 1000);
	bid (auctionId,42, 100);
	getHighestBid(auctionId);
	getAllAuctions();
	getAuctionById(auctionId);
	updateAuction(auctionId , "geänderter Titel der neuen Auktion", "geänderter Beschreibungstext der neuen Auktion", "geänderte URL des Bildes ", 2000);;
	getAuctionDetailsById(auctionId);
	deleteAuction(auctionId);		
	}
  

  
}