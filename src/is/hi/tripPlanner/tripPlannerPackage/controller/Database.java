package is.hi.tripPlanner.tripPlannerPackage.controller;

import java.sql.*;

public class Database {
    public static void insertPurchaser(String email, String name, String phone) throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException eString) {
            System.err.println("Could not init JDBC driver - driver not found");
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tripPlanner.db");
            //Athugum hvort að user sé nú þegar til í töflu, ef ekki þá bætum við honum við
            PreparedStatement checkIfUserExists = connection.prepareStatement("SELECT email FROM Purchaser "
                    + "WHERE email = ? "
                    + "COLLATE NOCASE;");
            checkIfUserExists.setString(1, email);
            ResultSet rs = checkIfUserExists.executeQuery();
            if (rs.next()){
                System.out.println("Notandi er til");
            } else {
                //bætum notenda sem vantaði við
            PreparedStatement putNewUserStmt = connection.prepareStatement("INSERT INTO Purchaser"
                    + " (email, name, phone) "
                    + " VALUES (?, ?, ?)");
            putNewUserStmt.setString(1, email);
            putNewUserStmt.setString(2, name);
            putNewUserStmt.setString(3, phone);

            putNewUserStmt.executeUpdate();
            }
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


    public static void insertBooking(String bookingNr, String hotelNr, String flightNr, String daytourNr, String purchaser_email) throws ClassNotFoundException {
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
                    + " (bookingNr, hotelBookingNr, flightBookingNr, tripBookingNr, purchaser_email) "
                    + " VALUES (?, ?, ?, ?, ?)");
            putNewBookingStmt.setString(1, bookingNr);
            putNewBookingStmt.setString(2, hotelNr);
            putNewBookingStmt.setString(3, flightNr);
            putNewBookingStmt.setString(4, daytourNr);
            putNewBookingStmt.setString(5, purchaser_email);

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
                    System.out.println(rs.getString("bookingNr")+" "+ rs.getString("purchaser_email"));
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
