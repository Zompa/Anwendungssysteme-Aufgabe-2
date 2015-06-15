package com.example;

import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.auctionInformation.Auction;
import com.example.auctionInformation.AuctionDetails;
import com.example.auctionInformation.AuctionListEntry;
import com.example.auctionInformation.AuctionRESTRuntimeException;
import com.example.auctionInformation.Bid;

/**
 * Singleton class that contains all information about all auctions.
 * 
 * @author Daniel
 *
 */
public class AuctionManager {

	private Collection<Auction> auctions = new LinkedList<>();
	private static AuctionManager instance = new AuctionManager();

	public AuctionManager() {

		Logger.getAnonymousLogger().info("test");
		System.out.println("TEST!!!");

		//System.out.println(AuctionManager.getInstance().getAuction(Integer.parseInt("666")).getHighestBid());
		System.out.println("init");

		AuctionDetails details = new AuctionDetails("Ein altes Fahrrad", "Der Zustand ist den Bildern zu entnehmen. Das Rad ist fahrbereit, lediglich das Licht m�sste kontrolliert werden. Die Reifen halten die Luft. Das Bremsgummi der Stempelbremse ist neu. Wittkoppsattel, originale Klingel, originaler Dynamo, originale Werkzeugtasche. Lohmannn Lampe, Speichenschloss mit Schl�ssel. Schutznetze, originaler Kettenschutz usw.", "http://www.beautys.de/var/albums/altes%20fahrrad%20herrenrad.jpg?m=1401375651", 1434366241);
		Auction auction = new Auction(details, 666);
		Bid bid = new Bid("Manfred", 43);
		auction.makeBid(bid);
		auctions.add(auction);
	}
	
	public static AuctionManager getInstance() {
		return instance;
	}

	public Auction getAuction(int ID) {
		synchronized (auctions) {
			for (Auction auction : auctions) {
				if (auction.getAuctionID() == ID)
					return auction;
			}
			return null;
		}
	}

	public void addAuction(Auction auction) {
		if (getAuction(auction.getAuctionID()) != null)
			throw new AuctionRESTRuntimeException(
					"There is already an auction with that ID: "
							+ auction.getAuctionID());
		auctions.add(auction);
	}

	public boolean deleteAuction(int ID) {
		Auction auction = getAuction(ID);
		synchronized (auctions) {
			return auctions.remove(auction);
		}
	}
	
	public AuctionListEntry[] getCurrentAuctions() {
		AuctionListEntry[] result = new AuctionListEntry[auctions.size()];
		int i=0;
		for (Auction a: auctions) {
			result[i] = new AuctionListEntry(a);
			i++;
		}
		return result;
	}

}
