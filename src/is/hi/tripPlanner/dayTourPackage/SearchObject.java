package is.hi.tripPlanner.dayTourPackage;

import java.util.Date;

public class SearchObject {
    String tripName;
    Date dateBegin;
    Date dateEnd;
    String location;
    int price;

    public SearchObject(String tripName, Date dateBegin, Date dateEnd, String location, int price){
        this.tripName = tripName;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.location = location;
        this.price = price;
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
}
