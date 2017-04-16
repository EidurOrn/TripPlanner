package is.hi.tripPlanner.dayTourPackage.controller;

import is.hi.tripPlanner.dayTourPackage.model.BookingModel;
import is.hi.tripPlanner.dayTourPackage.model.Trip;

import java.sql.*;

public class DatabaseUpdater {
	private Connection connection = null;
	private String URL = "jdbc:postgresql://kuldbinstance.czsqr6wtrtap.us-west-2.rds.amazonaws.com:5432/kuldb";
	private String USER = "kuluser";
	private String PASS = "kulpassword";
	private DatabaseRetrival dbR = new DatabaseRetrival();
	
	public DatabaseUpdater() {

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;

		}
		
		try {

			connection = DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
			return;

		}
	}
		
	//Function that adds a booking to the table BOOKINGS
	public int insertBooking(BookingModel booking) { // 0 t�knar b�kun t�kst, 1 t�knar ekki n�g pl�ss, 2 t�knar villa kom upp
		String insertTableSQL = "INSERT INTO BOOKING"
				+ "(tripId, bookerEmail, numPeople, bookerSSN) VALUES"
				+ "(?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			Trip[] tripList = dbR.queryTripInfo(booking.getTripId());
			
			if(booking.getNumPeople() + tripList[0].getNumBooking() <= tripList[0].getMaxPeople()) {
				preparedStatement = connection.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1, booking.getTripId());
				preparedStatement.setString(2, booking.getBookerEmail());
				preparedStatement.setInt(3, booking.getNumPeople());
				preparedStatement.setInt(4, booking.getBookerSSN());
				//execute insert SQL statement
				preparedStatement.executeUpdate();
				
				
				String updateTableSQL = "UPDATE TRIP SET numBooking = numBooking + ?";
				preparedStatement = connection.prepareStatement(updateTableSQL);
				preparedStatement.setInt(1, booking.getNumPeople());
				preparedStatement.executeUpdate();
				return 0;
			}
			else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
	}
	
	//Function that adds a booking to the table BOOKINGS
	public void insertTrip(Trip trip) {
		String insertTableSQL = "INSERT INTO TRIP"
				+ "(tripName, dateBegin, dateEnd, description, maxPeople, minPeople, location, price) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, trip.getTripName());
			preparedStatement.setDate(2, (Date) trip.getDateBegin());
			preparedStatement.setDate(3, (Date) trip.getDateEnd());
			preparedStatement.setString(4, trip.getDescription());
			preparedStatement.setInt(5, trip.getMaxPeople());
			preparedStatement.setInt(6, trip.getMinPeople());
			preparedStatement.setString(7, trip.getLocation());
			preparedStatement.setInt(8, trip.getPrice());
			//execute insert SQL statement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertAdmin(String username, byte[] password, byte[] salt){
		String insertTableSQL = "INSERT INTO ADMIN"
				+ "(adminId, adminPassword, salt) VALUES"
				+ "(?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setBytes(2, password);
			preparedStatement.setBytes(3, salt);
			//execute insert SQL statement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createTables() {
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			
			String dropBooking = "DROP TABLE BOOKING";
			stmt.executeUpdate(dropBooking);
			
			String dropTrip = "DROP TABLE TRIP";
			stmt.executeUpdate(dropTrip);
			
			String dropAdmin = "DROP TABLE ADMIN";
			stmt.executeUpdate(dropAdmin);
			
		    String createTrip = "CREATE TABLE TRIP " +
		                   	"(tripId SERIAL PRIMARY KEY, " +
		                   	" tripName VARCHAR(256) NOT NUll, " +
		                   	" dateBegin DATE NOT NULL, " +
		                   	" dateEnd DATE NOT NULL, " +
		                   	" description VARCHAR(2000), " +
		                   	" maxPeople INT NOT NULL, " +
		                   	" minPeople INT NOT NULL, " +
		                   	" location VARCHAR(256) NOT NULL, " +
		                   	" price INT NOT NULL, " +
		                   	" numBooking INT DEFAULT 0)";
		                   
		      stmt.executeUpdate(createTrip);
		           
		      String createBooking = "CREATE TABLE BOOKING " +
		    		  		"(bookingId SERIAL PRIMARY KEY, " +
		    		  		"tripId INT NOT NUll, " +
		    		  		"bookerEmail VARCHAR(256) NOT NULL, " + 
		    		  		"numPeople INT NOT NULL, " +
		    		  		"bookerSSN INT NOT NULL);";
		      
		      stmt.executeUpdate(createBooking);
		      
		      String createAdmin = "CREATE TABLE ADMIN " +
         		"(adminId VARCHAR(256) PRIMARY KEY, " +
         		" adminPassword VARCHAR(256) NOT NUll, " +
         		" salt VARCHAR(256) NOT NULL)";

				stmt.executeUpdate(createAdmin);
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		}
	}
}
