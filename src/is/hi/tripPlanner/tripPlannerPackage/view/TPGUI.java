package is.hi.tripPlanner.tripPlannerPackage.view;

import is.hi.tripPlanner.dayTourPackage.model.DayTourSearch;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.flightPackage.FlightSearch;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;
import is.hi.tripPlanner.tripPlannerPackage.controller.Book;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.storage.Purchaser;
import is.hi.tripPlanner.tripPlannerPackage.storage.Package;


import javax.swing.*;
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

    /**
     * search results
     */
    private ArrayList<Flight> flightResults;
    /**
     * selected flight, from flightTable
     */
    private Flight chosenFlight;

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
    /**
     * hotel search results
     */
    ArrayList<HotelRoom> hotelResults;
    /**
     * selected hotel, from hotelTable
     */
    HotelRoom chosenHotel;





    DefaultTableModel hotelTableModel = new DefaultTableModel();

    // Day trip tab
    private JTable DTTable;
    private JPanel DTTablePanel;
    private JComboBox DTTripCb;
    private JComboBox DTLocationCB;
    private JTextField DTTripStartTxtField;
    private JTextField DTTripEndTxtField;
    private JComboBox DTTypeCB;
    private JComboBox DTNrPeoplCB;
    private JPanel DTValuePanel;
    private JButton DTCancelBtn;
    private JButton DTBookBtn;
    private JButton DTSrcBtn;
    private JPanel flightPanelValue;
    private JPanel flightPanelTable;
    private JTextField nrNightsText;
    private JLabel hotelThemeLabel;
    private JButton showAllHotels;

    DefaultTableModel DayTRipTableModel = new DefaultTableModel();

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
        panelMain.setPreferredSize(new Dimension(750, 800));
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

        Set<String> allLocations =  new HashSet<>();
        allLocations.addAll( m.getFlightSearchObject().getFra());
        System.out.println(allLocations.toString());
        allLocations.remove("test");
        cbDestination.setModel(new DefaultComboBoxModel(allLocations.toArray()));


        Set<String> allDestinations =  new HashSet<>();
        allDestinations.addAll( m.getFlightSearchObject().getTil());
        System.out.println(allLocations.toString());
        allDestinations.remove("test");
        cbLocation.setModel(new DefaultComboBoxModel(allDestinations.toArray()));

        // comboboxes have been set up




        hotelTable.setAutoCreateRowSorter(true);
        hotelTable.setFillsViewportHeight(true);
        hotelTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        hotelTableModel.addColumn("Name");
        hotelTableModel.addColumn("Location");
        hotelTableModel.addColumn("Price");
        hotelTableModel.addColumn("Quality");
        hotelTableModel.addColumn("Type");
        hotelTableModel.addColumn("Theme");
        hotelTable.setModel(hotelTableModel);


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
        DTTable.setAutoCreateRowSorter(true);
        DTTable.setFillsViewportHeight(true);
        DTTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        DayTRipTableModel.addColumn("Name");
        DayTRipTableModel.addColumn("Location");
        DayTRipTableModel.addColumn("Starting Date");
        DayTRipTableModel.addColumn("Ending Date");
        DayTRipTableModel.addColumn("MinPeople");
        DayTRipTableModel.addColumn("MaxPeople");
        DayTRipTableModel.addColumn("Description");
        DayTRipTableModel.addColumn("Price");
        DTTable.setModel(DayTRipTableModel);




//// Listeners

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

                if( pakki.getBookedFlight() != null) {
                    // assume that if a flight is booked, then it has a location, destination and departure
                     flightText = "Flight from " + pakki.getBookedFlight().getLocation() + " to " + pakki.getBookedFlight().getDestination()
                            + " on " + pakki.getBookedFlight().getDeparture();
                }
                if( pakki.getBookedHotel() != null){
                    hotelText = "Hotelroom at " + pakki.getBookedHotel().getHotelName() + " located in " + pakki.getBookedHotel().getLocation();
                }
                if( pakki.getBookedDayTour() != null) {
                    dayTourText = "Day tour " + pakki.getBookedDayTour().getTripName() + " located in " + pakki.getBookedDayTour().getLocation() + " that costs " + pakki.getBookedDayTour().getPrice();
                }

                yourFlight.setText(flightText);
                yourHotel.setText(hotelText);
                yourDayTour.setText(dayTourText);
            }
        });




        // Flight tab listeners

        /**
         * books the package into the Trip Planner database and the day tour database (the hotel and daytour programs don't book into the db)
         */
        bookPackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if( pakki.getBookedDayTour() != null  && pakki.getBookedHotel() != null && pakki.getBookedDayTour() != null && pakki.getPurchaser() != null) {
                    // package has been filled and purchaser has registered his info
                    Book booking = new Book(pakki);
                    booking.bookPackage();
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

            }
        });

        /** Combobox that selects location
         *
         */
        cbLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenLocation = (String)cb.getSelectedItem();
            }
        });
        cbLocation.setSelectedIndex(0);

        cbDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox cb = (JComboBox)actionEvent.getSource();
                chosenDestination = (String)cb.getSelectedItem();
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
                 setFlightResults(m.getFlightInfo(chosenLocation, chosenDestination, enterDate.getText()));

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
                if(checkInDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")){
                    hotelResults = availableRooms(m.getHotelInfo(chosenHotelName, chosenHotelLocation, "", "", chosenHotelType, chosenHotelTheme, chosenHotelStars), Integer.parseInt(nrNightsText.getText()), checkInDate );
                    // hotelResults contain all results that are available
                    for(HotelRoom h : hotelResults){
                        System.out.println(h.getHotelName() + "   " + h.getFromAvailability()   +  "   "  + h.getToAvailability());
                        System.out.println("\n" + daysLater(h.getFromAvailability(),Integer.parseInt(nrNightsText.getText())) + "\n");
                    }
                    System.out.println("Fromdate: " + checkInDate);
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
     * returns a list of the HotelRooms in hotelRooms that are available for at least numDays days after fromDate
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
}
