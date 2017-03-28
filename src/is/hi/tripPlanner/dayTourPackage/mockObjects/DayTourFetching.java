package is.hi.tripPlanner.dayTourPackage.mockObjects;

import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;

public interface DayTourFetching {
    public Trip[] findResults(SearchModel s);
}
