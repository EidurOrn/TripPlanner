package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.dayTourPackage.mockObjects.*;

import java.util.Date;

public class QuickTest {
    public static void main(String[] args){
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
    }
}
