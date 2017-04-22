package is.hi.tripPlanner.tripPlannerPackage.view;

import is.hi.tripPlanner.dayTourPackage.model.DayTourSearch;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.flightPackage.FlightSearch;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.tripPlannerPackage.controller.Book;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.storage.Purchaser;
import is.hi.tripPlanner.tripPlannerPackage.storage.Package;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    String chosenLocation;
    String chosenDestination;
    private ArrayList<Flight> flightResults;
    private Flight chosenFlight;

    DefaultTableModel flightTableModel = new DefaultTableModel();

    // hotel tab
    private JComboBox hotelNameCB;
    private JComboBox hotelLocationCB;
    private JTable hotelTable;
    private JButton hotelSearchBtn;
    private JButton hotelCancelBtn;
    private JButton hotelBookBtn;
    private JTextField hotelAvailableFrom;
    private JComboBox hotelNrNightCB;
    private JComboBox hoteStarsCB;
    private JComboBox hotelTypeCB;

    DefaultTableModel hotelTableModel = new DefaultTableModel();



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
// GUI

        // Flight
        flightTable.setAutoCreateRowSorter(true);
        flightTable.setFillsViewportHeight(true);
        flightTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        flightTableModel.addColumn("Location");
        flightTableModel.addColumn("Destination");
        flightTableModel.addColumn("Date");
        flightTableModel.addColumn("Price");
        flightTable.setModel(flightTableModel);
        flightTable.getSelectionModel().addListSelectionListener(new TableListener(this, 1));

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

        String chosenHotelName;
        String chosenHotelLocation;
        String chosenHotelDate;
        int nrOfNights;
        String chosenType;
        String chosenStars;

        Set<String> allHotels =  new HashSet<>();
        //allHotels.addAll( m.getHotelSearchObject().HotelSearch(""));
        System.out.println(allLocations.toString());
        //allLocations.remove("test");
        //cbDestination.setModel(new DefaultComboBoxModel(allLocations.toArray()));




//// Listeners

    // Booking tab
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

    // Flight tab
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


    // Hotel tab


    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("TPGUI");
        frame.setContentPane(new TPGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
