package is.hi.tripPlanner.dayTourPackage.model;

import java.sql.Date;

public class DayTourSearch {
	
	private String tripName;
	private Date dateBegin;
	private Date dateEnd;
	private String location;
	private int price;
	
	public DayTourSearch(String[] searchParam) {
		String tripName;
		Date dateBegin;
		Date dateEnd;
		String location;
		int price;
				
		if("".equals(searchParam[0])) {
			tripName = ".*";
		} else {
			tripName = searchParam[0];
		}
		
		try {
			dateBegin = Date.valueOf(searchParam[1]);
		} catch(Exception e) {
			dateBegin = Date.valueOf("2017-01-01"); // Format: "yyyy-[m]m-[d]d"
		}
		
		try {
			dateEnd = Date.valueOf(searchParam[2]);
		} catch(Exception e) {
			dateEnd = Date.valueOf("2030-12-24");
		}
		
		if(searchParam[3] == "") {
			location = ".*";
		} else {
			location = searchParam[3];
		}
		
		if(searchParam[4] == "") {
			price = Integer.MAX_VALUE;
		} else {
			try {
				price = Integer.parseInt(searchParam[4]);
			} catch(Exception e) {
				price = Integer.MAX_VALUE;
			}
		}
		
		this.tripName = tripName;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.setLocation(location);
		this.price = price;		
	}

	public String getTripName() {
		return tripName;
	}
	
	public Date getDateBegin() {
		return dateBegin;
	}
	
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		String print = "";
		print = "Tripname: " + tripName 
				+ "\nDate Begin: " + dateBegin 
				+ "\nDate End: " + dateEnd 
				+ "\nLocation: " + getLocation()
				+ "\nPrice: " + price;
		
		return print;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
