package is.hi.tripPlanner.dayTourPackage.controller;

import is.hi.tripPlanner.dayTourPackage.model.SearchModel;
import is.hi.tripPlanner.dayTourPackage.model.Trip;

import java.text.ParseException;

public class SearchController {

	private static DatabaseRetrival dbRetrival = new DatabaseRetrival();

	public static Trip[] findResults(String[] searchParam) throws ParseException {
		
		SearchModel search = new SearchModel(searchParam);
				
		Trip[] tripList = dbRetrival.queryTrip(search);
	
		return tripList;
	}
}