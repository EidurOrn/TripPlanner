package is.hi.tripPlanner.tripPlannerPackage.controller;

import is.hi.tripPlanner.tripPlannerPackage.storage.Package;

public class Book {
    private Package packageToBeBooked;
    private static int bookingNr;
    private int flightBookingNr;
    private int hotelBookingNr;
    private int tripBookingNr;

    public Book(Package p) {
        this.packageToBeBooked = p;
        this.flightBookingNr = p.getBookedFlight().getFlightId();
        this.hotelBookingNr = p.getBookedHotel().getHotelId();
        this.tripBookingNr = p.getBookedTrip().getTripdId();
    }

    // Package booking, connect to other groups and book on their system. If everything
    // was successful then put this booking order in our db and return true.
    public boolean bookPackage(){
        // Code to be made
        return true;
    }
}
