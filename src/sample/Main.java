package sample;

import is.hi.tripPlanner.dayTourPackage.model.DayTourSearch;
import is.hi.tripPlanner.dayTourPackage.model.Trip;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.flightPackage.FlightSearch;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;
import is.hi.tripPlanner.tripPlannerPackage.controller.Book;
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
        Purchaser buyer = new Purchaser("","","","");




        // Initiate searching
        String searchParam[] = {"", "", "", "", ""};
        DayTourSearch dayTourSearchTest  = new DayTourSearch(searchParam);
        MetaSearch m = new MetaSearch(dayTourSearchTest, new HotelSearch(), new FlightSearch());

    ////  Search for flights ////

        String departure = "2017-04-11";
        ArrayList<Flight> flights = m.getFlightInfo("Alicante", "Keflavík", departure);

        System.out.println("The user searches for flights by date, location and destination");
        System.out.println("User input: ");
        System.out.println("Location: Alicante \n Destination: Keflavík  \n  Date: 2017-04-11");
        System.out.println("Search results:");
        for(Flight flight : flights){
            System.out.println(flight.getLocation() + "  " + flight.getDestination() + "  " + flight.getDeparture());
        }

            String departureBack = "2017-04-23";
            ArrayList<Flight> flightsBack  = m.getFlightInfo( "Keflavík", "Alicante", departureBack);

        // Choose flight
        System.out.println("\nThe user chooses a flight and adds it to the package.\n");
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

        System.out.println("The user then goes into the hotel tab and sees the hotel rooms that are available at the time\n");

        for(HotelRoom h : availableHotelRooms){
            System.out.println(h.getHotelName() + "   " + h.getPrice() + " kr.   " + h.getLocation() + "      " + h.getTheme());
        }
        System.out.println("\n");

        System.out.println("The user filters the hotels to see the ones that are romantic: \n");
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
            System.out.println(h.getHotelName() + "   " + h.getPrice() + " kr.   " + h.getLocation() + "      " + h.getTheme());
        }

        // The "user" chooses a hotel
        System.out.println("\nThe user chooses a hotel room and adds it to the package:");
        HotelRoom chosenHotel = availableRomantic.get(0);
        pakki.setBookedHotel(chosenHotel);
        System.out.println(chosenHotel.getHotelName() + "   " + chosenHotel.getPrice() + " kr.   " + chosenHotel.getLocation() + "      " + chosenHotel.getTheme());


    //// Search for daytours ////

        // the location has been determined
        String location = pakki.getBookedHotel().getLocation();
        System.out.println("\n");
        System.out.println("The user goes to the daytour tab and sees the daytours with the same location as the hotelroom he booked \n");
        // view the daytours
        String[] searchLocation = {"", "", "", location, ""};
        Trip[] dayToursOnLocation = m.getDayTourInfo( new DayTourSearch(searchLocation));
        for(Trip t : dayToursOnLocation){
            System.out.println(t.getTripName());
            //System.out.println(t.getDescription());
            System.out.println(t.getPrice());
        }

        System.out.println("\nThe user filters the daytours to see what costs less than 10000");
        // "user" searches for daytours that cost less than 10.000

        String[] searchLocationCheap = {"", "", "", location, "10000"};
        Trip[] dayToursOnLocationCheap = m.getDayTourInfo( new DayTourSearch(searchLocationCheap));
        System.out.println("\n");
        for(Trip t : dayToursOnLocationCheap){
            System.out.println("name: " + t.getTripName());
            System.out.println("price " + t.getPrice());
        }

        System.out.println("\nThe user chooses a day tour and books it for 2 persons.");
        // "user" chooses the available daytour
        pakki.setBookedDayTour(dayToursOnLocationCheap[0]);
        // he books it for two:
        buyer.setTripNumPeople("2");


        // a flight, a hotel and a daytour has been chosen

        System.out.println("\n" + "The user finally goes into the Trip Order tab to order" + "\n");


        // Print Trip contents
        System.out.println("He sees what he has booked: \n");
        System.out.println("Flight from " + pakki.getBookedFlight().getLocation() +  " to " + pakki.getBookedFlight().getDestination()
                + " on " + departure);
        System.out.println("Hotelroom at " + pakki.getBookedHotel().getHotelName() + " located in " + pakki.getBookedHotel().getLocation());
        System.out.println("Day tour " + pakki.getBookedDayTour().getTripName() + " located in " + pakki.getBookedDayTour().getLocation() + " that costs " + pakki.getBookedDayTour().getPrice());
        // Log in
        String email = "ss111@hi.is";
        String ssn = "0908922319";
        buyer.setEmail(email);
        buyer.setName("Stanislav");
        buyer.setPhone("7744270");
        buyer.setSsn(ssn);

        System.out.println("\n The user enters the info needed to book: ");
        System.out.println("Name " + buyer.getName());
        System.out.println("Email " + buyer.getEmail());
        System.out.println("Phone " + buyer.getPhone());
        System.out.println("SSN " +buyer.getSsn() + "\n");

        System.out.println("And books it (into the flight, hotel, daytour and trip databases).");

        pakki.getBookedFlight().setFlightId(42); // fake flight id
        Book bokun = new Book(pakki);


        System.out.println(m.getHotelInfo("","","","", "","Romantic","").get(0).getHotelName());
        // hægt að nota þetta sem skema til að gera UIið út frá
        System.out.println(new HotelSearch().LocationSearch("").size());

        System.out.println(availableRooms(6, pakki.getBookedFlight().getDeparture()).size());
        launch(args);



    }


    /**
     * returns a list of the HotelRooms that are available for at least numDays days after fromDate
     * @param fromDate "2016-05-30"  used with pakki.getBookedFlight().getDeparture()
     * @param numDays number of days
     */
    private static ArrayList<HotelRoom> availableRooms(int numDays, Date fromDate){



        ArrayList<HotelRoom> allHotelRooms = new HotelSearch().LocationSearch("");

        ArrayList<HotelRoom> availableHotelRooms = new ArrayList<HotelRoom>();
        for(HotelRoom h : allHotelRooms){
            String availableFrom =  h.getFromAvailability();
            String availableTo =  h.getToAvailability();
            if(earlier(availableFrom,fromDate) && earlier(fromDate, daysLater(availableTo,numDays))){
                availableHotelRooms.add(h);
            }
        }

        return availableHotelRooms;
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
        DayTourSearch dayTourSearchTest  = new DayTourSearch(searchParam);
        MetaSearch m = new MetaSearch(dayTourSearchTest, new HotelSearch(), new FlightSearch());

        return m.getHotelInfo("","","","", type, "", "");
    }
}
