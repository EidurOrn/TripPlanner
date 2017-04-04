package is.hi.tripPlanner.dayTourPackage;

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
    private int tripdId;

    public Trip(String tripName, Date dateBegin, Date dateEnd, String description, int maxPeople, int minPeople, String location, int price, int tripdId) {
        this.tripName = tripName;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.description = description;
        this.maxPeople = maxPeople;
        this.minPeople = minPeople;
        this.location = location;
        this.price = price;
        this.tripdId = tripdId;
    }

    public Trip() {
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTripdId() {
        return tripdId;
    }

    public void setTripdId(int tripdId) {
        this.tripdId = tripdId;
    }
}

