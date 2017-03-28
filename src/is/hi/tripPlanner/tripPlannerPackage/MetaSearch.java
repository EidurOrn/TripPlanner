package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.mockObjects.DayTourFetching;
import is.hi.tripPlanner.dayTourPackage.SearchObject;
import is.hi.tripPlanner.dayTourPackage.Trip;

public class MetaSearch{
    private DayTourFetching dayTourSearchObject;

    public MetaSearch(DayTourFetching d){
        dayTourSearchObject = d;
    }

    public Trip[] getDayTourInfo(SearchObject s){
        return dayTourSearchObject.findResults(s);
    }

}
