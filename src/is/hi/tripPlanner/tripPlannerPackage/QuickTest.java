package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.dayTourPackage.mockObjects.*;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.controller.Database.*;

import java.util.Date;

import static is.hi.tripPlanner.tripPlannerPackage.controller.Database.getBookings;
import static is.hi.tripPlanner.tripPlannerPackage.controller.Database.insertBooking;
import static is.hi.tripPlanner.tripPlannerPackage.controller.Database.removeBooking;


public class QuickTest {
    public static void main(String[] args) throws Exception{
        //
        // auka comment
        // 
        MetaSearch m = new MetaSearch(new ThreeDayTourMock());
        SearchModel s = new SearchModel("Fun!",new Date(2017,3,25,14,30),new Date(2017,3,25,14,30),
            "Place",5000);
        Trip[] a = m.getDayTourInfo(s);
        for (Trip trip : a) {
            System.out.println(trip.getTripName());
        }
        insertBooking("A678","H098","F999","D943");
        insertBooking("A111","H323","F323","D232");
        getBookings();
        removeBooking("A678");
        removeBooking("A111");
        System.out.println("Some bookings removed...");
        getBookings();
    }


}
