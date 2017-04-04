package is.hi.tripPlanner.tripPlannerPackage.controller;

import java.sql.*;

public class Database {
    public static void insertBooking(String bookingNr, String hotelNr, String flightNr, String daytourNr) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tripPlanner.db");
            PreparedStatement putNewBookingStmt = connection.prepareStatement("INSERT INTO"
                    + "(bookingNr, hotelBookingNr, flightBookingNr, tripBookingNr) "
                    + "VALUES (?, ?, ?, ?);");
            putNewBookingStmt.setString(1, bookingNr);
            putNewBookingStmt.setString(2, hotelNr);
            putNewBookingStmt.setString(3, flightNr);
            putNewBookingStmt.setString(4, daytourNr);
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                System.err.println(e);
            }
        }
    }
    public static void removeBooking(String bookingNr) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tripPlanner.db");
            PreparedStatement removeBookingStmt = connection.prepareStatement("DELETE FROM bookings"
                    + "WHERE bookingNr = ?;");
            removeBookingStmt .setString(1, bookingNr);

        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                System.err.println(e);
            }
        }

    }
}
