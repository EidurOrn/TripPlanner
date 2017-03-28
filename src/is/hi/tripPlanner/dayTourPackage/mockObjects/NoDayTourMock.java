package is.hi.tripPlanner.dayTourPackage.mockObjects;

import is.hi.tripPlanner.dayTourPackage.SearchObject;
import is.hi.tripPlanner.dayTourPackage.Trip;

public class NoDayTourMock implements DayTourFetching {
    public Trip[] findResults(SearchObject s) {
        return new Trip[0];
    }
}
