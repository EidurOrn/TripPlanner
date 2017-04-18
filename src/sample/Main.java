package sample;

import is.hi.tripPlanner.dayTourPackage.model.SearchModel;
import is.hi.tripPlanner.dayTourPackage.model.Trip;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.storage.Package;
import is.hi.tripPlanner.tripPlannerPackage.storage.Purchaser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {



        // command line bókun hér:
        System.out.println("hi");


        Package pakki = new Package(); //


        // Log in
        String email = "ss111@hi.is";
        String ssn = "0908922319";
        Purchaser buyer = new Purchaser("Stanislav", email, "7744270",ssn);
        System.out.println(" User logs in with the info needed to book: ");
        System.out.println(buyer.getName());
        System.out.println(buyer.getEmail());
        System.out.println(buyer.getPhone());
        System.out.println(buyer.getSsn() + "\n");


        // Initiate searching
        String searchParam[] = {"", "", "", "", ""};
        SearchModel dayTourSearchTest  = new SearchModel(searchParam);
        MetaSearch m = new MetaSearch(dayTourSearchTest, new HotelSearch());

    ////  Search for flights ////

        String departure = "2017-04-16";
        ArrayList<Flight> flights = m.getFlightInfo("Alicante", "Keflavík", departure);

        System.out.println("The user searches for flights by date, location and destination");
        System.out.println("and gets results:");
        for(Flight flight : flights){
            System.out.println(flight.getLocation() + "  " + flight.getDestination() + "  " + flight.getDeparture());
        }

            String departureBack = "2017-04-23";
            ArrayList<Flight> flightsBack  = m.getFlightInfo( "Keflavík", "Alicante", departureBack);

        // Choose flight
        System.out.println("\nUser chooses a flight and adds it to the package.");
        Flight chosenFlight = flights.get(0);
        pakki.setBookedFlight(chosenFlight);

    //// Search for hotels ////

        // the time period has been determined
        Date arrivalTime = pakki.getBookedFlight().getDeparture();

        // get all hotels, and pick from them the ones that are available when the user is on vacation

        String[] types =  {"double", "single", "triple", "suite", "Villa"};
        ArrayList<HotelRoom> allHotelRooms = hotelByType(types[0]);
        int j = 1;
        while(j < types.length && allHotelRooms.addAll(hotelByType(types[j]))){
            j++;
        }
        // allHotelRooms contains all hotelrooms

        ArrayList<HotelRoom> availableHotelRooms = new ArrayList<HotelRoom>();
        for(HotelRoom h : allHotelRooms){
            String availableFrom =  h.getFromAvailability();
            String availableTo =  h.getToAvailability();
            if(earlier(availableFrom,arrivalTime) && earlier(arrivalTime, daysLater(availableTo,7))){
                availableHotelRooms.add(h);
            }
        }
        // availableHotelRooms has all hotelrooms that are available for at least 1 week after the flight

        System.out.println("The user then goes into the hotel tab and sees the hotel rooms that are available when he's on vacation\n");

        for(HotelRoom h : availableHotelRooms){
            System.out.println(h.getHotelName() + "   " + h.getPrice() + " kr.   " + h.getLocation() + " " + h.getTheme());
        }
        System.out.println("\n");

        System.out.println("The user searches for hotels that are romantic: ");
        // "User" searches for a romantic hotel:
        ArrayList<HotelRoom> availableRomantic = new ArrayList<HotelRoom>();
        for(HotelRoom h : availableHotelRooms){
            if( h.getTheme().equals("Romantic")){
                availableRomantic.add(h);
            }
        }
        // availableRomantic has all hotelrooms that are available for at least 1 week after the flight
        // and are romantic

        // view them:
        for(HotelRoom h : availableRomantic){
            System.out.println(h.getHotelName() + "   " + h.getPrice() + " kr.   " + h.getLocation() + " " + h.getTheme());
        }

        // The "user" chooses a hotel
        System.out.println("\nThe user chooses a hotel room and adds it to the package");
        HotelRoom chosenHotel = availableRomantic.get(0);
        pakki.setBookedHotel(chosenHotel);
        System.out.println(chosenHotel.getHotelName());

    //// Search for daytours ////

        // the location has been determined
        String location = pakki.getBookedHotel().getLocation();
        System.out.println("\n");
        System.out.println("The user goes to the daytour tab and sees the daytours with the same location as the hotel he booked");
        // view the daytours
        String[] searchLocation = {"", "", "", location, ""};
        Trip[] dayToursOnLocation = m.getDayTourInfo( new SearchModel(searchLocation));
        for(Trip t : dayToursOnLocation){
            System.out.println(t.getTripName());
            System.out.println(t.getPrice());
        }

        System.out.println("The user searches for daytours that cost less than 10000");
        // "user" searches for daytours that cost less than 10.000

        String[] searchLocationCheap = {"", "", "", location, "10000"};
        Trip[] dayToursOnLocationCheap = m.getDayTourInfo( new SearchModel(searchLocationCheap));
        System.out.println("\n Cheap trips \n");
        for(Trip t : dayToursOnLocationCheap){
            System.out.println(t.getTripName());
            System.out.println(t.getPrice());
        }

        System.out.println("\nThe user chooses a day tour and books it for 2");
        // "user" chooses the available daytour
        pakki.setBookedDayTour(dayToursOnLocationCheap[0]);
        // he books it for two:
        buyer.setTripNumPeople("2");


        // a flight, a hotel and a daytour has been chosen

        System.out.println("\n" + "The user goes into the Trip Order tab to confirm");



        launch(args);



    }


    /**
     * returns a list of the HotelRooms that are available for at least numDays days after fromDate
     * @param fromDate "2016-05-30"
     */
    private static ArrayList<HotelRoom> availableRooms(int numDays, String fromDate){
        return new ArrayList<HotelRoom>();
    }
    /**
     * true if s1 is before s2
     * @param s1 string representing date, on format "2014-05-22"
     * @param s2
     * @return
     */
    private static boolean earlier(String s1, String s2){
        DateFormat format = new SimpleDateFormat("y-M-d", Locale.ENGLISH);
        Date date1;
        Date date2;

        try {
            date1 = format.parse(s1);
            date2 = format.parse(s2);
            return date1.compareTo(date2) <= 0;

        }
        catch(ParseException ex){
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * true if date1 is before s2
     * @param date1 date
     * @param s2 string representing date, on format "2014-05-22"
     * @return
     */
    private static boolean earlier(Date date1, String s2){
        DateFormat format = new SimpleDateFormat("y-M-d", Locale.ENGLISH);
        Date date2;

        try {
            date2 = format.parse(s2);
            return date1.compareTo(date2) <= 0;

        }
        catch(ParseException ex){
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * true if s1 is before date
     * @param s1 string representing date, on format "2014-05-22"
     * @param date2 date
     * @return
     */
    private static boolean earlier(String s1, Date date2){
        DateFormat format = new SimpleDateFormat("y-M-d", Locale.ENGLISH);
        Date date1;

        try {
            date1 = format.parse(s1);
            return date1.compareTo(date2) <= 0;

        }
        catch(ParseException ex){
            ex.printStackTrace();
        }

        return false;
    }

    /**
     *
     * @param s string representing date, on format "2014-05-22"
     * @param n int representing number of days
     * @return  string representing date n days  later, e.g. "2014-05-29"
     */
    private static String daysLater(String s, int n ){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //try {
            LocalDate date = LocalDate.parse(s); // þarf ekki formatter því þetta er default
            return date.plus(n, ChronoUnit.DAYS).toString();

//        }
//        catch(ParseException ex){
//            ex.printStackTrace();
//        }
//        return "";
    }

    private static ArrayList<HotelRoom> hotelByType(String type){
        String searchParam[] = {"", "", "", "", ""};
        SearchModel dayTourSearchTest  = new SearchModel(searchParam);
        MetaSearch m = new MetaSearch(dayTourSearchTest, new HotelSearch());

        return m.getHotelInfo("","","","", type, "", "");
    }
}
