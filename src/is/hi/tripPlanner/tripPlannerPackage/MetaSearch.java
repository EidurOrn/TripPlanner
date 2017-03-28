package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.mockObjects.DayTourFetching;
import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;

public class MetaSearch{
    private DayTourFetching dayTourSearchObject;

    public MetaSearch(DayTourFetching d){
        dayTourSearchObject = d;
    }

    public Trip[] getDayTourInfo(SearchModel s){
        return dayTourSearchObject.findResults(s);
    }

}
