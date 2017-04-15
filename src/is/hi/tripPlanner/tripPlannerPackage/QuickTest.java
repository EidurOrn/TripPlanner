package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.dayTourPackage.mockObjects.*;
import is.hi.tripPlanner.hotelPackage.JFrames.Search;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.controller.Database.*;

import java.util.ArrayList;
import java.util.Date;

import static is.hi.tripPlanner.tripPlannerPackage.controller.Database.*;


public class QuickTest {
    public static void main(String[] args) throws Exception{
        //
        // auka comment
        // 
        MetaSearch m = new MetaSearch(new ThreeDayTourMock(), new Search());
        SearchModel s = new SearchModel("Fun!",new Date(2017,3,25,14,30),new Date(2017,3,25,14,30),
            "Place",5000);
        Trip[] a = m.getDayTourInfo(s);
        //a = m.sortByDate_Trip(a,true);
        /*a = m.sortByPrice_Trip(a,true);
        for (Trip trip : a) {
            System.out.println(trip.getTripName());
        }*/
        ArrayList<HotelRoom> hl = m.getHotelInfo("","Sudurland","","","suite","","");
        for(HotelRoom h : hl){
            System.out.println(h.getHotelName());
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
    }


}
