package is.hi.tripPlanner.dayTourPackage.mockObjects;

import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;

public class NoDayTourMock implements DayTourFetching {
    public Trip[] findResults(SearchModel s) {
        return new Trip[0];
    }
}
