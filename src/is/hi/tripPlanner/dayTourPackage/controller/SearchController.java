package is.hi.tripPlanner.dayTourPackage.controller;

import is.hi.tripPlanner.dayTourPackage.model.DayTourSearch;
import is.hi.tripPlanner.dayTourPackage.model.Trip;

import java.text.ParseException;

public class SearchController {

	private static DatabaseRetrival dbRetrival = new DatabaseRetrival();

	public static Trip[] findResults(String[] searchParam) throws ParseException {
		
		DayTourSearch search = new DayTourSearch(searchParam);
				
		Trip[] tripList = dbRetrival.queryTrip(search);
	
		return tripList;
	}
}