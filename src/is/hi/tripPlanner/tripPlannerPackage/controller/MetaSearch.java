package is.hi.tripPlanner.tripPlannerPackage.controller;


import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.dayTourPackage.mockObjects.DayTourFetching;
import is.hi.tripPlanner.flightPackage.*;
import is.hi.tripPlanner.hotelPackage.*;
import java.util.ArrayList;

public class MetaSearch{
    private DayTourFetching dayTourSearchObject;

    public MetaSearch(DayTourFetching d){
        dayTourSearchObject = d;
    }

    public Trip[] getDayTourInfo(SearchModel s){
        // Check if end date occurs before the start date.
        if(s.getDateBegin() != null) {
            if(s.getDateEnd() != null){
                if (s.getDateEnd().before(s.getDateBegin())) {
                    return new Trip[0];
                }
            }
        }
        // Check if the price is negative (user being payed).
        if(s.getPrice() < 0){
            return new Trip[0];
        }

        return dayTourSearchObject.findResults(s);
    }

    /**
     * called from HotelUI,  fetches a list of hotels that fit search conditions from hotel program
     * @param searchHotel search object from hotel program,  gets hotel search results from their db
     * @return
     */
    public ArrayList<Hotel> getHotelInfo(SearchEngine searchHotel){
        // tilviksbreytan availableHotelList í SearchEngine geymir líklega  leitarniðurstöðurnar
        return new ArrayList();

    }


    /**
     *
     * @param searchFlight  search object from flight program
     * @return
     */
    public Flight[] getFlightInfo(Search searchFlight){
        // skv. domain modelinu er hjá 7F virðist tilviksbreytan availableFlightList geyma leitarniðurstöðurnar
        return new Flight[0];
    }


}
