package is.hi.tripPlanner.dayTourPackage.model;

import java.util.Date;

public class Trip {
	
	private String tripName;
	private Date dateBegin;
	private Date dateEnd;
	private String description;
	private int maxPeople;
	private int minPeople;
	private String location;
	private int price;
	private int tripId;
	private int numBooking;
	
	public Trip(String tripName, Date dateBegin, Date dateEnd, String description, int maxPeople, int minPeople, String location, int price, int tripId, int numBooking) {
		this.tripName = tripName;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.description = description;
		this.maxPeople = maxPeople;
		this.minPeople = minPeople;
		this.location = location;
		this.price = price;
		this.tripId = tripId;
		this.numBooking = numBooking;
	}
	
	public String getTripName() {
		return tripName;
	}
	
	public Date getDateBegin() {
		return dateBegin;
	}
	
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getMaxPeople() {
		return maxPeople;
	}
	
	public int getMinPeople() {
		return minPeople;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getTripId() {
		return tripId;
	}
	
	public int getNumBooking() {
		return numBooking;
	}
}