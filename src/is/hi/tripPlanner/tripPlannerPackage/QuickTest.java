package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.model.SearchModel;
import is.hi.tripPlanner.dayTourPackage.model.Trip;
import is.hi.tripPlanner.dayTourPackage.controller.BookingController;

import is.hi.tripPlanner.flightPackage.Flight;

//import is.hi.tripPlanner.hotelPackage.HotelBookings.Main;

import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;

import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.storage.Package;
import is.hi.tripPlanner.tripPlannerPackage.storage.Purchaser;

import java.util.ArrayList;

import static is.hi.tripPlanner.tripPlannerPackage.controller.Database.*;


public class QuickTest {

    public static void main(String[] args) throws Exception{

// þarf að setja upp gagnagrunninn fyrir hótelin áður en forritið er keyrt í fyrsta skipti:
        //is.hi.tripPlanner.hotelPackage.HotelBookings.Main.setUpDatabase();
// ! bara einu sinni

      Package pakki = new Package(); //

        String searchParam[] = {"", "", "", "", "8000"};
        SearchModel dayTourSearchTest  = new SearchModel(searchParam);


        MetaSearch m = new MetaSearch(dayTourSearchTest, new HotelSearch());


        // user info:
        String email = "vae111@hi.is";
        String ssn = "0908922319";
        Purchaser buyer = new Purchaser("Vladek", email, "7744270",ssn);
        // Vladek ætlar að kaupa fyrir sig og barnabarnið sitt
        buyer.setTripNumPeople("3");



        //// day tours:


        // search for trips :
        Trip[] trips = m.getDayTourInfo(dayTourSearchTest);
        //a = m.sortByDate_Trip(a,true);
        trips = m.sortByPrice_Trip(trips,true);
        int ferdirBokadar = 0; // bókum bara eina dagsferð í alpha útgáfu forritsins

        for (Trip trip : trips) {
            System.out.println("trip name " + trip.getTripName());
            // book:
                // in dayTour
            String[] bookingString =  { ""+trip.getTripId(), email, buyer.getTripNumPeople() , ssn };
            String ferdBokud =  BookingController.bookTrip(bookingString);

                // in tripPlanner
            if(ferdBokud.equals("Booking successful") && ferdirBokadar<1){
                pakki.setBookedDayTour(trip);
                ferdirBokadar++;
            }
        }

        System.out.println("Bókuð í trip plannerinn: + " + pakki.getBookedDayTour().getTripName());






        //// hotels:
        ArrayList<HotelRoom> hl = m.getHotelInfo("","Sudurland","","","suite","","");
        for(HotelRoom h : hl){
            System.out.println("hotel name + " + h.getHotelName());
        }
        insertPurchaser("froskur@gmail.com", "Friki", "6969699");
        insertPurchaser("kuntakinte@gmail.com", "Tobias", "6969699");
        insertBooking("A678","H098","F999","D943", "froskur@gmail.com");
        insertBooking("A111","H323","F323","D232", "kuntakinte@gmail.com");
        insertBooking("A120","H109","F919","D333", "mummi@visir.is");
        getBookings();
        removeBooking("A678");
        removeBooking("A111");
        removeBooking("A120");
        System.out.println("\nSome bookings removed...");
        getBookings();

        // flights:

        ArrayList<Flight> flights = m.getFlightInfo("Keflavík", "Alicante", "2017-04-16");

        for(Flight flight : m.sortByDeparture_Flight( flights.toArray(new Flight[0]),false)){
            System.out.println(flight.getDeparture());
        }

        Flight chosenFlight = flights.get(0);
        pakki.setBookedFlight(chosenFlight);
        // raðar ekki











    }
    /**
     * returns word number nr in the 3 word sentence sent
     * 1 <= n <= 3
     */
    public static String nthWord(String sent, int nr){
        int fyrstaBil = sent.indexOf(' ');
        if(nr == 1){
            return sent.substring(0,fyrstaBil);
        }

        int annadBil = fyrstaBil + sent.substring(fyrstaBil+1).indexOf(' ');

        if(nr==2) {
            return sent.substring(fyrstaBil + 1, annadBil+1);
        }

        return  sent.substring(annadBil+2,sent.length());
    }



}
