/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.tripPlanner.hotelPackage.HotelBookings;

import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author drifasoley
 */
public class SQLiteJDBC
{
    public static String hotelRoomTableName = "HotelRooms"; 
    
    
    
    private static Connection getConnection() throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //Class.forName("com.mysql.jdbc.Driver")
            conn = DriverManager.getConnection("jdbc:sqlite:hoteldatabase.db");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static void createHotelRooms() {
        Connection c = null;
        Statement stmt = null;
        try {
          c = getConnection();
          System.out.println("createHotelRooms: Opened database successfully");

          stmt = c.createStatement();

          String sql = "CREATE TABLE " + SQLiteJDBC.hotelRoomTableName +
                       " (id INT PRIMARY KEY NOT NULL," +
                       " hotelName TEXT NOT NULL, " + 
                       " fromAvailability DATE NOT NULL, " +
                       " toAvailability DATE NOT NULL, " +
                       " price INT NOT NULL, " + 
                       " type TEXT NOT NULL, " +
                       " theme TEXT NOT NULL, " +
                       " quality INT NOT NULL, " +
                       " location TEXT NOT NULL);";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Table created successfully");
    }
    
    public static void insertIntoHotelRooms(int id, String hotelName, String fromAvailability, String toAvailability, int price, String type, String theme, int quality, String location) {
        Connection c = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            String sql = String.format("INSERT INTO %s VALUES(%d, '%s', '%s', '%s', %d, '%s', '%s', %d, '%s');",
                                        /*table name: */ SQLiteJDBC.hotelRoomTableName,
                                        /*value field 0:*/ id,
                                        /*value field 1:*/ hotelName,
                                        /*value field 2:*/ fromAvailability,
                                        /*value field 3:*/ toAvailability,
                                        /*value field 4:*/ price,
                                        /*value field 5:*/ type,
                                        /*value field 6:*/ theme,
                                        /*value field 7:*/ quality,
                                        /*value field 8:*/ location);
            
            stmt.executeUpdate(sql);
            
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.out.println("INSERT ERROR: " + e.getMessage());
            return;
        }
        System.out.println("Successfully inserted hotel with id: " + id);
    }
    
    public static HotelRoom getHotelForId(int identifier) {
        HotelRoom hotel = null;
        Connection c = null;

        Statement stmt = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("getHotelForId: Opened database successfully");

            stmt = c.createStatement();
            String query = String.format("SELECT * FROM %s WHERE id = '%d';",
                                                SQLiteJDBC.hotelRoomTableName,
                                                identifier); // breyta þessari skipun með LIKE í staðin fyrir = og í staðin fyrir ID nota hotelname, taka á móti streng í staðin fyrir id
            ResultSet rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String hotelName = rs.getString("hotelName");
                int price = rs.getInt("price");
                String fromAvailability = rs.getString("fromAvailability");
                String toAvailability = rs.getString("toAvailability");
                String type = rs.getString("type");
                String theme = rs.getString("theme");
                int quality = rs.getInt("quality");
                String location = rs.getString("location");
                
                hotel = new HotelRoom(id, hotelName, price, fromAvailability, toAvailability, type, theme, quality, location);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.out.println("SELECT ERROR:");
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return null;
        }
        System.out.println("Operation done successfully");
        
        return hotel;
    }
}
