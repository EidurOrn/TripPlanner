package is.hi.tripPlanner.tripPlannerPackage.view;

import is.hi.tripPlanner.dayTourPackage.model.DayTourSearch;
import is.hi.tripPlanner.dayTourPackage.model.Trip;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.flightPackage.FlightSearch;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;
import is.hi.tripPlanner.tripPlannerPackage.controller.Book;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.storage.Purchaser;
import is.hi.tripPlanner.tripPlannerPackage.storage.Package;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Notandi on 20-Apr-17.
 */
public class TPGUI {


    private JTabbedPane tabbedPane2;
    private JPanel panelMain;
    private JTextField enterDate;

// Booking tab
    private JPanel purchaser;
    private JPanel thePackage;
    private JTextField yourPackage;
    private JTextField customerInformation;
    private JTextField yourFlight;
    private JTextField yourHotel;
    private JPanel packageContents;
    private JTextField yourDayTour;
    private JPanel customerInfo;
    private JTextField name;
    private JTextField email;
    private JTextField phoneNumber;
    private JTextField socialSecurityNumber;
    private JPanel bookingTab;
    private JButton viewPackage;
    private JButton bookPackage;
    private JButton confirmInfo;


// Flight tab
    private JComboBox cbLocation;
    private JComboBox cbDestination;
    private JTable flightTable;
    private JList list1;
    private JButton searchButton;
    private JButton bookButton;
    private JButton cancelButton;

    // chosen search parameters
    String chosenLocation;
    String chosenDestination;
    String chosenDate;

    /**
     * search results
     */
    private ArrayList<Flight> flightResults;
    /**
     * selected flight, from flightTable
     */
    private Flight chosenFlight;

    ArrayList<String> fra;
    ArrayList<String> til;
    ArrayList<String> dates;

    DefaultTableModel flightTableModel = new DefaultTableModel();

    // hotel tab
    private JComboBox hotelNameCB;
    private JComboBox hotelLocationCB;
    private JTable hotelTable;
    private JButton hotelSearchBtn;
    private JButton hotelCancelBtn;
    private JButton hotelBookBtn;
    private JTextField hotelCheckIn;
    private JComboBox hotelStarsCB;
    private JComboBox hotelTypeCB;
    private JComboBox hotelThemeCB;
    private JTextField nrNightsText;
    private JLabel hotelThemeLabel;
    private JButton showAllHotels;

    // chosen search parameters
    String chosenHotelName;
    String chosenHotelLocation;
    String chosenHotelDate;
    int nrOfNights;
    String chosenHotelType;
    String chosenHotelTheme;
    String chosenHotelStars;

    /**
     * list of all hotel rooms
     */
    ArrayList<HotelRoom> allHotelRooms;
    private ArrayList<HotelRoom> hotelResults;
    private HotelRoom chosenHotel;


    DefaultTableModel hotelTableModel = new DefaultTableModel();



    // Day trip tab
    private JTable dtTable;
    private JPanel DTTablePanel;
    private JComboBox DTTripCB;
    private JComboBox DTLocationCB;
    private JTextField DTTripStartTxtField;
    private JTextField DTTripEndTxtField;
    private JComboBox DTNrPeopleCB;
    private JPanel DTValuePanel;
    private JButton DTCancelBtn;
    private JButton DTBookBtn;
    private JButton DTSrcBtn;
    private JPanel flightPanelValue;
    private JPanel flightPanelTable;
    private JTextField packagePrice;
    private JComboBox cbDate;
    private JTextField tripMaxPrice;
    private JLabel tripPrice;
    private JButton dtShowAll;
    private JLabel enterName;
    private JLabel enterSSN;
    private JLabel enterPhone;
    private JLabel enterEmail;
    private JLabel flight;
    private JLabel hotel;
    private JLabel daytour;
    private JLabel totalPrice;

    Trip[] allTrips;
    private ArrayList<Trip> dayTourResults;
    private Trip chosenTrip;

    // chosen search parameters
    String chosenDTName;
    String chosenDTLocation;
    String chosenDTDateBegin = "";
    String chosenDTDateEnd = "";
    String chosenMaxPrice = "10000000";
    String chosenNrOfPeople;

    DefaultTableModel dayTripTableModel = new DefaultTableModel();



    //// non-GUI objects


    private Package pakki;
    Purchaser buyer ;
    MetaSearch m;






    public TPGUI(){

//// non-GUI
        // set up search, package and purchaser to use and add to
        buyer = new Purchaser("","","","");
        // package with empty flight, hotel and daytour, not used  // pakki = new Package(new Flight("","",new Date()) ,  new HotelRoom(0, "",0,"","","","",0,""), new Trip("", new Date(), new Date(), "", 0, 0, "", 0,0,0) , buyer);
        pakki = new Package();
        String[] searchParam = {"", "", "", "", ""};
        m = new MetaSearch(new DayTourSearch(searchParam), new HotelSearch(), new FlightSearch());
        allHotelRooms = new HotelSearch().LocationSearch("");

// GUI
        panelMain.setPreferredSize(new Dimension(1150, 550));
        //revalidate();
    // Flight
        flightPanelValue.setMinimumSize(new Dimension(-1, 20));
        flightPanelValue.setMaximumSize(new Dimension(-1,60));
        flightPanelTable.setMinimumSize(new Dimension(-1, 400));
        flightPanelTable.setMaximumSize(new Dimension(-1,600));
        flightTable.setAutoCreateRowSorter(true);
        flightTable.setFillsViewportHeight(true);
        flightTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        flightTableModel.addColumn("Location");
        flightTableModel.addColumn("Destination");
        flightTableModel.addColumn("Date");
        flightTableModel.addColumn("Price");
        flightTable.setModel(flightTableModel);
        flightTable.getSelectionModel().addListSelectionListener(new TableListener(this,1));

        fra = m.getFlightSearchObject().getFra();
        Set<String> allLocations =  new HashSet<>();
        allLocations.addAll( fra );
        allLocations.remove("test");
        cbLocation.setModel(new DefaultComboBoxModel(allLocations.toArray()));
        chosenLocation  = (String) allLocations.toArray()[0];

        til = m.getFlightSearchObject().getTil();
        Set<String> allDestinations =  new HashSet<>();
        allDestinations.addAll( til);
        allDestinations.remove("test");
        cbDestination.setModel(new DefaultComboBoxModel(allDestinations.toArray()));
        chosenDestination = (String) allDestinations.toArray()[0];

        dates  = m.getFlightSearchObject().getDates();
        Set<String> allDates =  new HashSet<>();
        allDates.addAll( dates);
        cbDate.setModel(new DefaultComboBoxModel(allDates.toArray()));
        // comboboxes have been set up



    // Hotel
        getHotelTable().setAutoCreateRowSorter(true);
        getHotelTable().setFillsViewportHeight(true);
        getHotelTable().setPreferredScrollableViewportSize(new Dimension(550, 200));
        hotelTableModel.addColumn("Name");
        hotelTableModel.addColumn("Location");
        hotelTableModel.addColumn("Price");
        hotelTableModel.addColumn("Quality");
        hotelTableModel.addColumn("Type");
        hotelTableModel.addColumn("Theme");
        getHotelTable().setModel(hotelTableModel);
        getHotelTable().getSelectionModel().addListSelectionListener(new TableListener(this,2));




        Set<String> allHotelNames =  new HashSet<>();
        Set<String> allHotelLocations =  new HashSet<>();
        Set<String> allHotelStars =  new HashSet<>();
        Set<String> allHotelTypes =  new HashSet<>();
        Set<String> allHotelThemes =  new HashSet<>();

        allHotelNames.add("");
        allHotelLocations.add("");
        allHotelStars.add("");
        allHotelTypes.add("");
        allHotelThemes.add("");

        for(HotelRoom hotel : allHotelRooms){
            allHotelNames.add(hotel.getHotelName());
            allHotelLocations.add(hotel.getLocation());
            allHotelStars.add("" + hotel.getQuality());
            allHotelTypes.add(hotel.getType());
            allHotelThemes.add(hotel.getTheme());
        }

        hotelNameCB.setModel(new DefaultComboBoxModel(allHotelNames.toArray()));
        hotelLocationCB.setModel(new DefaultComboBoxModel(allHotelLocations.toArray()));
        hotelStarsCB.setModel(new DefaultComboBoxModel(allHotelStars.toArray()));
        hotelTypeCB.setModel(new DefaultComboBoxModel(allHotelTypes.toArray()));
        hotelThemeCB.setModel(new DefaultComboBoxModel(allHotelThemes.toArray()));






    // DayTour tab
        getDtTable().setAutoCreateRowSorter(true);
        getDtTable().setFillsViewportHeight(true);
        getDtTable().setPreferredScrollableViewportSize(new Dimension(550, 200));
        dayTripTableModel.addColumn("Name");
        dayTripTableModel.addColumn("Location");
        dayTripTableModel.addColumn("Starting Date");
        dayTripTableModel.addColumn("Ending Date");
        dayTripTableModel.addColumn("MinPeople");
        dayTripTableModel.addColumn("MaxPeople");
        dayTripTableModel.addColumn("Price");
        getDtTable().setModel(dayTripTableModel);
        getDtTable().getSelectionModel().addListSelectionListener(new TableListener(this,3));

        // Comboboxes:

        // fetch all trips:

        allTrips = m.getDayTourInfo(new DayTourSearch(searchParam));
        Set<String> allDayTourNames =  new HashSet<>();
        Set<String> allDayTourLocations =  new HashSet<>();

        // remove repetition if there are any:
        allDayTourNames.add("");
        allDayTourLocations.add("");
        int maxNrOfPeople = 0;
        for(Trip trip : allTrips){

            allDayTourNames.add(trip.getTripName());
            allDayTourLocations.add(trip.getLocation());

            if(trip.getMaxPeople() > maxNrOfPeople){
                maxNrOfPeople = trip.getMaxPeople();
            }

        }

        String[] nrOfPeople = new String[maxNrOfPeople];
        for(int i = 1; i<=maxNrOfPeople; i++){
            nrOfPeople[i-1] = Integer.toString(i);
        }
        DTTripCB.setModel(new DefaultComboBoxModel(allDayTourNames.toArray()));
        DTLocationCB.setModel(new DefaultComboBoxModel(allDayTourLocations.toArray()));
        DTNrPeopleCB.setModel(new DefaultComboBoxModel(nrOfPeople));

        // Search input has been put to combo boxes




//// Listeners

        /**
         * listener for when tabs are changed
         * puts
         */
        tabbedPane2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {

                DateFormat format = new SimpleDateFormat("y-MM-d", Locale.ENGLISH);
                // if switched to Hotel Tab, put date of bookedFlight into check-in date
                int index =  tabbedPane2.getSelectedIndex();
                if(index == 1){
                    if(chosenFlight != null) {
                        hotelCheckIn.setText(format.format(chosenFlight.getDeparture()));
                    }
                }

                if(index == 2){
                    // stilli location sem selected location

                    if(pakki.getBookedHotel() != null){
                        String hotelLocation = pakki.getBookedHotel().getLocation();
                        if(allDayTourLocations.contains(hotelLocation)){

                            DTLocationCB.setSelectedItem(hotelLocation);
                            // only show those trips that are in chosenDTLocation

                            // for trips in allTrips,  if tripName is in DTTripCB, if tripLocation is chosenDTLocation, add to model

                            showTripsOnLocation();;


                        }
                    }



                    //DTLocationCB.setSelectedIndex(0);
                }

            }
        });

        // Booking tab listeners
        /**
         * button pressed to view package contents
         */
        viewPackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String flightText = "";
                String hotelText = "";
                String dayTourText = "";
                int totalPrice = 0;
                if( pakki.getBookedFlight() != null) {
                    // assume that if a flight is booked, then it has a location, destination and departure
                     flightText = "Flight from " + pakki.getBookedFlight().getLocation() + " to " + pakki.getBookedFlight().getDestination()
                            + " on " + pakki.getBookedFlight().getDeparture() + " that costs " + pakki.getBookedFlight().getPrice() + "kr.";
                     totalPrice += pakki.getBookedFlight().getPrice();

                }
                if( pakki.getBookedHotel() != null){
                    hotelText = "Hotelroom at " + pakki.getBookedHotel().getHotelName() + " located in " + pakki.getBookedHotel().getLocation() + " that costs " + pakki.getBookedHotel().getPrice() + "kr.";
                    totalPrice+=pakki.getBookedHotel().getPrice();
                }
                if( pakki.getBookedDayTour() != null) {
                    dayTourText = "Day tour " + pakki.getBookedDayTour().getTripName() + " located in " + pakki.getBookedDayTour().getLocation() + " that costs " + pakki.getBookedDayTour().getPrice() + "kr.";
                    totalPrice += pakki.getBookedDayTour().getPrice();
                }


                yourFlight.setText(flightText);
                yourHotel.setText(hotelText);
                yourDayTour.setText(dayTourText);
                packagePrice.setText("" + totalPrice + "kr.");
            }
        });


        /**
         * books the package into the Trip Planner database and the day tour database (the hotel and daytour programs don't book into the db)
         */
        bookPackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if( true) {
                    // pakki.getBookedDayTour() != null  && pakki.getBookedHotel() != null && pakki.getBookedDayTour() != null && pakki.getPurchaser() != null
                    // package has been filled and purchaser has registered his info
                    Book booking = new Book(pakki);

                    boolean[] bookingCheck = booking.bookPackage();

                    if(bookingCheck[0]){
                        System.out.println("Flight booked!");
                        // dialog gluggi
                    }
                    if(bookingCheck[1]){
                        System.out.println("Hotel booked!");
                        // dialog gluggi
                    }
                    if(bookingCheck[2]){
                        System.out.println("Day tour booked!");
                        // dialog gluggi
                    }

                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Package booked!" );
                }
            }
        });
        /**
         * puts the customer info written into Purchaser
         */
        confirmInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buyer.setSsn(socialSecurityNumber.getText());
                buyer.setEmail(email.getText());
                buyer.setName(name.getText());
                buyer.setPhone(phoneNumber.getText());
                pakki.setPurchaser(buyer);

            }
        });

        // Flight tab listeners

        /**
         * Combobox that selects date
         */
        cbDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenDate = (String)cb.getSelectedItem();
            }
        });
        cbDate.setSelectedIndex(0);

        /** Combobox that selects location
         *
         */
        cbLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenLocation = (String)cb.getSelectedItem();
                showDates(chosenLocation, chosenDestination);

            }
        });
        cbLocation.setSelectedIndex(0);
        /**
         * Combobox that selects destination
         */
        cbDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenDestination = (String)cb.getSelectedItem();
                showDates(chosenLocation, chosenDestination);
            }
        });
        cbDestination.setSelectedIndex(0);



        /** button that gets available flights and puts into flightTable
         *
         */
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // empty flightTable
                flightTableModel.setRowCount(0);
                // get flights
                 setFlightResults(m.getFlightInfo(chosenLocation, chosenDestination, chosenDate));

                // add to flightTable
                for(Flight flight : getFlightResults()){

                    String[] flightInfo = {flight.getLocation(), flight.getDestination(), flight.getDeparture().toString().substring(0,16)};
                    flightTableModel.addRow(flightInfo);
                }

            }
        });
        /**
         * books flight which is selected in flightTable to package
         */
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(chosenFlight != null) {
                    pakki.setBookedFlight(chosenFlight);
                }
            }
        });

        /** removes flight booking
         *
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pakki.setBookedFlight(null);
            }
        });


        // Hotel tab listeners

        /**
         * selector for combo box with hotelname search parameter
         */
        hotelNameCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenHotelName = (String)cb.getSelectedItem();
            }
        });
        hotelNameCB.setSelectedIndex(0);

        /**
         * selector for combo box with location search parameter
         */
        hotelLocationCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenHotelLocation = (String)cb.getSelectedItem();
            }
        });
        hotelLocationCB.setSelectedIndex(0);

        /**
         * selector for combo box with quality search parameter
         */
        hotelStarsCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenHotelStars = (String)cb.getSelectedItem();
            }
        });
        hotelStarsCB.setSelectedIndex(0);

        /**
         * selector for combo box with type search parameter
         */
        hotelTypeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenHotelType = (String)cb.getSelectedItem();
            }
        });
        hotelTypeCB.setSelectedIndex(0);
        /**
         * selector for combo box with theme search parameter
         */
        hotelThemeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenHotelTheme = (String)cb.getSelectedItem();
            }
        });
        hotelThemeCB.setSelectedIndex(0);


        /**
         * Searches for hotels that fit search parameters
         */
        hotelSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                hotelTableModel.setRowCount(0);
                // table is empty
                String checkInDate = hotelCheckIn.getText();
                String nrOfNights = nrNightsText.getText();
                int nrNights = 0;
                ArrayList<HotelRoom> hResults = m.getHotelInfo(chosenHotelName, chosenHotelLocation, "", "", chosenHotelType, chosenHotelTheme, chosenHotelStars);
                // hResults contains hotelrooms that fit search criteria
                if(  checkInDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d"))  {


                    if( nrOfNights.matches("\\d")) {
                        nrNights = Integer.parseInt(nrOfNights);
                    }

                    setHotelResults(availableRooms(hResults, nrNights, checkInDate));
                    // hotelResults contain all results that are available

                }else if ((checkInDate.equals("")|| checkInDate.equals("YYYY-DD-MM"))){
                     setHotelResults(hResults);
                    // hResults contains hotelrooms that fit search criteria

                }

                for(HotelRoom h : getHotelResults()){
                    String[] hotelInfo = {h.getHotelName(), h.getLocation(), "" + h.getPrice(), "" + h.getQuality(), h.getType(), h.getTheme() };
                    hotelTableModel.addRow(hotelInfo);
                }

                // hotelResults is displayed in hotelTable



            }
        });
        /**
         * adds hotel selected in hotelTable to package
         */
        hotelBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(chosenHotel != null){
                    pakki.setBookedHotel(chosenHotel);
                }
            }
        });


        showAllHotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hotelTableModel.setRowCount(0);
                hotelResults = allHotelRooms;
                for(HotelRoom h : allHotelRooms){
                    String[] hotelInfo = {h.getHotelName(), h.getLocation(), "" + h.getPrice(), "" + h.getQuality(), h.getType(), h.getTheme() };
                    hotelTableModel.addRow(hotelInfo);
                }
            }
        });



    // Day Tours

        /**
         * selector for combo box with name of day tour
         */
        DTTripCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenDTName = (String)cb.getSelectedItem();
            }
        });
        DTTripCB.setSelectedIndex(0);

        /**
         * selector for combo box with location of day tour
         */
        DTLocationCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenDTLocation = (String)cb.getSelectedItem();
                showTripsOnLocation();
            }
        });
        DTLocationCB.setSelectedIndex(0);

        /**
         * selector for combo box with number of people going on day tour
         */
        DTNrPeopleCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenNrOfPeople = (String)cb.getSelectedItem();
            }
        });
        DTNrPeopleCB.setSelectedIndex(0);

        /**
         *  shows day trips that fit search paramteres in dtTable and add to dayTourResults
         */
        DTSrcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                dayTripTableModel.setRowCount(0);
                // table has been emptied

                if(tripMaxPrice.getText().matches("\\d+")){
                    chosenMaxPrice = tripMaxPrice.getText();
                }else{
                    chosenMaxPrice = "";
                }

                String[] searchDT = {chosenDTName,chosenDTDateBegin, chosenDTDateEnd, chosenDTLocation, chosenMaxPrice};

                m.setDayTourSearchObject(new DayTourSearch(searchDT));
                //DayTourSearch searchParameters = new DayTourSearch(searchDT);

                ArrayList<Trip> dtResults = new ArrayList<>(Arrays.asList(m.getDayTourInfo(m.getDayTourSearchObject())));

                // tours that fit search parameters are in dResults

                setDayTourResults(dtResults);
                // tours that fit search parameters have been saved to dayTourResults
                for(Trip t: getDayTourResults()){
                    String[] dayTourInfo = {t.getTripName(), t.getLocation(), t.getDateBegin().toString(), t.getDateEnd().toString(), "" + t.getMinPeople(), "" + t.getMaxPeople(), "" + t.getPrice()};
                    dayTripTableModel.addRow(dayTourInfo);
                }
                // dayTourResults has been put to table
            }
        });

        /**
         * adds chosen daytour to package
         */
        DTBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(chosenTrip != null){


                    if((Integer.parseInt(chosenNrOfPeople) < chosenTrip.getMaxPeople()) && (Integer.parseInt(chosenNrOfPeople) > chosenTrip.getMinPeople())) {
                        pakki.setBookedDayTour(chosenTrip);
                        buyer.setTripNumPeople(chosenNrOfPeople);
                    }
                }


            }
        });
        dtShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dayTripTableModel.setRowCount(0);
                dayTourResults = new ArrayList<Trip>(Arrays.asList(allTrips));

                for(Trip t : allTrips){
                    String[] dayTourInfo = {t.getTripName(), t.getLocation(), t.getDateBegin().toString(), t.getDateEnd().toString(), "" + t.getMinPeople(), "" + t.getMaxPeople(), "" + t.getPrice()};
                    dayTripTableModel.addRow(dayTourInfo);
                }
            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("TPGUI");
        frame.setContentPane(new TPGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * shows only names of daytours that are on chosenDTLocation
     */
    public void showTripsOnLocation(){
        Set<String>  dayTourNames = new HashSet<>();
        dayTourNames.add("");
        for(Trip trip : allTrips){
            if(trip.getLocation().equals(chosenDTLocation)){
                dayTourNames.add(trip.getTripName());
            }
        }
        DTTripCB.setModel(new DefaultComboBoxModel(dayTourNames.toArray()));
    }

    /**
     * Shows in cbDate only the dates for which it's flown from location to destination
     * @param location
     * @param destination
     */
    public void showDates(String location, String destination){


        ArrayList<String> validDates = new ArrayList<String>();
        for (int i = 0; i < fra.size(); i++){

            if (location.equals(fra.get(i)) && destination.equals(til.get(i))){
                validDates.add(dates.get(i));
            }
        }

        // validDates contains the dates when it's flown from location to destination

        cbDate.setModel(new DefaultComboBoxModel( (new HashSet<>(validDates)).toArray() ) );

    }


    /**
     * returns a list of the HotelRooms in hotelRooms that are available from fromDate for at least numDays days
     * @param fromDate "2016-05-30"
     * @param numDays number of days
     */
    private ArrayList<HotelRoom> availableRooms(ArrayList<HotelRoom> hotelRooms,int numDays, String fromDate){


        ArrayList<HotelRoom> availableHotelRooms = new ArrayList<HotelRoom>();
        for(HotelRoom h : hotelRooms){
            String availableFrom =  h.getFromAvailability();
            String availableTo =  h.getToAvailability();
            if(earlier(availableFrom,fromDate) && earlier(daysLater(fromDate,numDays), availableTo)){
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

    public ArrayList<Flight> getFlightResults() {
        return flightResults;
    }

    public void setFlightResults(ArrayList<Flight> flightResults) {
        this.flightResults = flightResults;
    }

    public Flight getChosenFlight() {
        return chosenFlight;
    }

    public void setChosenFlight(Flight chosenFlight) {
        this.chosenFlight = chosenFlight;
    }

    /**
     * selected hotel, from hotelTable
     */
    public HotelRoom getChosenHotel() {
        return chosenHotel;
    }

    public void setChosenHotel(HotelRoom chosenHotel) {
        this.chosenHotel = chosenHotel;
    }

    /**
     * hotel search results
     */
    public ArrayList<HotelRoom> getHotelResults() {
        return hotelResults;
    }

    public void setHotelResults(ArrayList<HotelRoom> hotelResults) {
        this.hotelResults = hotelResults;
    }

    public ArrayList<Trip> getDayTourResults() {
        return dayTourResults;
    }

    public void setDayTourResults(ArrayList<Trip> dayTourResults) {
        this.dayTourResults = dayTourResults;
    }

    public Trip getChosenTrip() {
        return chosenTrip;
    }

    public void setChosenTrip(Trip chosenTrip) {
        this.chosenTrip = chosenTrip;
    }

    public JTable getHotelTable() {
        return hotelTable;
    }

    public void setHotelTable(JTable hotelTable) {
        this.hotelTable = hotelTable;
    }

    public JTable getDtTable() {
        return dtTable;
    }

    public void setDtTable(JTable dtTable) {
        this.dtTable = dtTable;
    }
}
