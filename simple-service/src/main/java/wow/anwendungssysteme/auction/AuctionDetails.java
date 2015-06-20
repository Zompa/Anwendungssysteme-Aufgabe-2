package wow.anwendungssysteme.auction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuctionDetails {

	public String title;
	public String description;
	public String imageURL;
	public long endDate;
	
	public AuctionDetails() {
		
	}
	
	public AuctionDetails(String title, String description, String imageURL, long endDate) {
		this.title = title;
		this.description = description;
		this.imageURL = imageURL;
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public long getEndDate() {
		return endDate;
	}
	
	@Override
	public String toString(){
		return "Title: " + this.title  + " Description: " + this.description + " ImageUrl: " + this.imageURL + " EndDate: " + this.endDate;
	}
	
	
	

}
