package is.hi.tripPlanner.tripPlannerPackage.controller;

import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;
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
        this.hotelBookingNr = p.getBookedHotel().getId();
        this.tripBookingNr = p.getBookedTrip().getTripdId();
        bookingNr++;
    }

    // Incase of having to cancel booking.
    public Book() {}

    // Package booking, connect to other groups and book on their system. If everything
    // was successful then put this booking order in our db.
    // This returns a boolean array representing the success of each booking:
    // [flight,hotel,dayTour]
    public boolean[] bookPackage(){
        // TODO Finish the function to book package.

        // In case for some odd reason this function is being called when empty
        // constructor was used (which is only supposed to be used when cancelling
        // the booking of a package.
        if(packageToBeBooked == null) return new boolean[]{false,false,false};

        // Attempt to book the package content.
        boolean[] bookingSuccess = new boolean[]{
            bookFlight(packageToBeBooked.getBookedFlight()),
            bookHotel(packageToBeBooked.getBookedHotel()),
            bookDayTour(packageToBeBooked.getBookedTrip())
        };

        // Check whether every booking succeeded.
        for(boolean r : bookingSuccess)
            if(!r) return bookingSuccess;

        // Connect to our db and insert the package order.
        Database d = new Database();
        try{
            d.insertBooking(Integer.toString(bookingNr),Integer.toString(hotelBookingNr),Integer.toString(flightBookingNr),
                    Integer.toString(tripBookingNr),packageToBeBooked.getPurchaser().getEmail());
        }catch (ClassNotFoundException e){
            // What to do if it fails to book into our DB.
        }

        return bookingSuccess;
    }

    // Connects to the flight group and attempts to book, returns true if successful
    private boolean bookFlight(Flight flight){
        // TODO Implement flight booking when they have given us their assignment.
        return true;
    }

    // Connects to the hotel group and attempts to book, returns true if successful
    private boolean bookHotel(HotelRoom hotel){
        // TODO Implement hotel booking when they have completed their booking.
        return true;
    }

    // Connects to the day tour group and attempts to book, returns true if successful
    private boolean bookDayTour(Trip trip){
        // TODO Implement trip booking with our project.
        // We call bookTrip(String[] s) where s contains: [tripId,bookerEmail, NrOfPeople, bookerSSN]
        return true;
    }

    // Cancels the booking of a package that has been booked before (Doesn't seem to be implemented with other groups, obsolete).
    public boolean[] cancelBooking(int bNr){
        // Connect to our db to get the id's of booked flight, hotel and day tour.
        // Should also check whether this package is "ongoing" or already finished,
        // (as in date/time wise) don't want the system to be cheated.

        // success rate of cancelling booking for [flight,hotel,dayTour].
        return new boolean[]{true,true,true};
    }

    // Connects to the flight group and attempts to cancel given booking, returns true if successful
    private boolean cancelFlight(int fNr){
        // Code to be made
        return true;
    }

    // Connects to the hotel group and attempts to book, returns true if successful
    private boolean cancelHotel(int hNr){
        // Code to be made
        return true;
    }

    // Connects to the day tour group and attempts to book, returns true if successful
    private boolean cancelDayTour(int tNr){
        // Code to be made
        return true;
    }

}
