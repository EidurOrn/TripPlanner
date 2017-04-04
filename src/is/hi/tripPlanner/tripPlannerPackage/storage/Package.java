package is.hi.tripPlanner.tripPlannerPackage.storage;

import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.hotelPackage.Hotel;
import is.hi.tripPlanner.tripPlannerPackage.controller.Book;

public class Package {
    private static int packageId;
    private Flight bookedFlight;
    private Hotel bookedHotel;
    private Trip bookedTrip;
    private Purchaser purchaser;

    public Package(Flight bookedFlight, Hotel bookedHotel, Trip bookedTrip, Purchaser purchaser) {
        this.bookedFlight = bookedFlight;
        this.bookedHotel = bookedHotel;
        this.bookedTrip = bookedTrip;
        this.purchaser = purchaser;
        packageId++;
    }

    public Package() {
        packageId++;
    }

    public static int getPackageId() {
        return packageId;
    }

    public Flight getBookedFlight() {
        return bookedFlight;
    }

    public void setBookedFlight(Flight bookedFlight) {
        this.bookedFlight = bookedFlight;
    }

    public Hotel getBookedHotel() {
        return bookedHotel;
    }

    public void setBookedHotel(Hotel bookedHotel) {
        this.bookedHotel = bookedHotel;
    }

    public Trip getBookedTrip() {
        return bookedTrip;
    }

    public void setBookedTrip(Trip bookedTrip) {
        this.bookedTrip = bookedTrip;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    // Books the entire package (unfinished)
    public boolean bookPackage(){
        Book b = new Book(this);
        // This needs to be handled differently to alert the user what has failed
        // could simply return the boolean array where each index represents:
        // [flight,hotel,trip] and handle it there or handle it differently here.
        for(boolean result : b.bookPackage()){
            if(!result) return false;
        }
        return true;
    }
}
