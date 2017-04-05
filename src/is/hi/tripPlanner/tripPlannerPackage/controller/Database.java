package is.hi.tripPlanner.tripPlannerPackage.controller;

import java.sql.*;

public class Database {
    public static void insertBooking(String bookingNr, String hotelNr, String flightNr, String daytourNr) throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException eString) {
            System.err.println("Could not init JDBC driver - driver not found");
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tripPlanner.db");
            PreparedStatement putNewBookingStmt = connection.prepareStatement("INSERT INTO Bookings"
                    + " (bookingNr, hotelBookingNr, flightBookingNr, tripBookingNr) "
                    + " VALUES (?, ?, ?, ?)");
            putNewBookingStmt.setString(1, bookingNr);
            putNewBookingStmt.setString(2, hotelNr);
            putNewBookingStmt.setString(3, flightNr);
            putNewBookingStmt.setString(4, daytourNr);

            putNewBookingStmt.executeUpdate();

            connection.close();
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
            PreparedStatement removeBookingStmt = connection.prepareStatement("DELETE FROM Bookings "
                    + "WHERE bookingNr = ?;");
            removeBookingStmt.setString(1, bookingNr);

            removeBookingStmt.setQueryTimeout(30);
            removeBookingStmt.executeUpdate();


            connection.close();
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

    public static void getBookings() {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException eString) {
            System.err.println("Could not init JDBC driver - driver not found");
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tripPlanner.db");
            PreparedStatement getAllBookingsStmt = connection.prepareStatement("SELECT *"
                    + " FROM Bookings");

            ResultSet rs = getAllBookingsStmt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("bookingNr"));
                }
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
