package is.hi.tripPlanner.tripPlannerPackage.controller;

import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.hotelPackage.Hotel;
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
    // was successful then put this booking order in our db.
    // This returns a boolean array representing the success of each booking:
    // [flight,hotel,dayTour]
    public boolean[] bookPackage(){
        // Code to be made
        boolean[] bookingSuccess = new boolean[]{
            bookFlight(packageToBeBooked.getBookedFlight()),
            bookHotel(packageToBeBooked.getBookedHotel()),
            bookDayTour(packageToBeBooked.getBookedTrip())
        };

        // Check whether every booking succeeded
        for(boolean r : bookingSuccess)
            if(!r) return bookingSuccess;

        // Connect to our db and insert the package order.

        return bookingSuccess;
    }

    // Connects to the flight group and attempts to book, returns true if successful
    private boolean bookFlight(Flight flight){
        // Code to be made
        return true;
    }

    // Connects to the hotel group and attempts to book, returns true if successful
    private boolean bookHotel(Hotel hotel){
        // Code to be made
        return true;
    }

    // Connects to the day tour group and attempts to book, returns true if successful
    private boolean bookDayTour(Trip trip){
        // Code to be made
        return true;
    }

}
