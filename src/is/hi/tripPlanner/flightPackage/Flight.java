package is.hi.tripPlanner.flightPackage;

import java.util.Date;

public class Flight {
    private int flightId;
    private Date departure;
    private Date arrival;
    private String location;
    private int availableSeats;
    private String destination;
    private int numbPeople;
    private int price;

    public Flight(String location, String destination, Date departure, Date arrival){
        this.location = location;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumbPeople() {
        return numbPeople;
    }

    public void setNumbPeople(int numbPeople) {
        this.numbPeople = numbPeople;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
