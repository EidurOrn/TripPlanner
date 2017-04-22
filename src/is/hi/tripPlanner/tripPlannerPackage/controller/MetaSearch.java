package is.hi.tripPlanner.tripPlannerPackage.controller;


import is.hi.tripPlanner.dayTourPackage.model.DayTourSearch;
import is.hi.tripPlanner.dayTourPackage.model.Trip;
import is.hi.tripPlanner.dayTourPackage.controller.DatabaseRetrival;
//import is.hi.tripPlanner.dayTourPackage.mockObjects.DayTourFetching;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.flightPackage.FlightSearch;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MetaSearch{
    private DayTourSearch dayTourSearchObject; // leitarskilyrðin eru sett í þennan object, dayTourSearchObject.setLocation("")
    private HotelSearch hotelSearchObject;
    private FlightSearch flightSearchObject;

    DatabaseRetrival dayTourDBRetrieval = new DatabaseRetrival();

    public MetaSearch(DayTourSearch d, HotelSearch h, FlightSearch f){
        setDayTourSearchObject(d);
        setHotelSearchObject(h);
        setFlightSearchObject(f);

    }

    /**
     * called from dayTourUI, fetches a list of day tours that fit search conditions listed in search
     * @param search search parameter object, contains tripName, dateBegin, dateEnd, location and price
     * @return
     */
    public Trip[] getDayTourInfo(DayTourSearch search){
        // Check if end date occurs before the start date.
        if(search.getDateBegin() != null) {
            if(search.getDateEnd() != null){
                if (search.getDateEnd().before(search.getDateBegin())) {
                    return new Trip[0];
                }
            }
        }
        // Check if the price is negative (user being payed).
        if(search.getPrice() < 0){
            return new Trip[0];
        }

        return dayTourDBRetrieval.queryTrip(search);
    }

    /**
     * called from HotelUI,  fetches a list of hotels that fit search conditions from hotel program
     * @param hotelName name of hotel to be searched for, if "" then this param is not used.
     * @param location location to be searched for, if "" then this param is not used.
     * @param fromAvailability when the hotel has an available room search condition, if "" then this param is not used.
     * @param toAvailability when the user has to check out of the hotel, if "" then this param is not used.
     * @param type type of hotel, if "" then this param is not used.
     * @param theme theme of the hotel, if "" then this param is not used.
     * @param quality quality of the hotel, if "" then this param is not used.
     * @return
     */
    public ArrayList<HotelRoom> getHotelInfo(String hotelName, String location, String fromAvailability,
                                             String toAvailability, String type, String theme, String quality){
        ArrayList<HotelRoom> l = new ArrayList<HotelRoom>();

        if(hotelName.equals("") && location.equals("") && fromAvailability.equals("") && toAvailability.equals("") && type.equals("") && theme.equals("") && quality.equals("") ){
            // all searchParams are empty string, not used
            return getHotelSearchObject().HotelSearch("");
        }
        if(!hotelName.equals("")) {
            l = getHotelSearchObject().HotelSearch(hotelName); // Makes a list with the first search results.
            if(l.isEmpty()) return l; // There was a search attempt but it turned up empty, no other condition can change that.
        }
        if(!location.equals("")) {
            if(l.isEmpty()) l = getHotelSearchObject().LocationSearch(location); // Makes a list with the first search results.
                                                                            // (If no prev result has been given, empty is a result).
            else l.retainAll(getHotelSearchObject().LocationSearch(location)); // Retains only the same list elements between the two lists.

            if(l.isEmpty()) return l;
        }
        if(!fromAvailability.equals("")) {
            if(l.isEmpty()) l = getHotelSearchObject().FromAvailabilitySearch(fromAvailability);
            else l.retainAll(getHotelSearchObject().FromAvailabilitySearch(fromAvailability));

            if(l.isEmpty()) return l;
        }
        if(!toAvailability.equals("")) {
            if(l.isEmpty()) l = getHotelSearchObject().ToAvailabilitySearch(toAvailability);
            else l.retainAll(getHotelSearchObject().ToAvailabilitySearch(toAvailability));

            if(l.isEmpty()) return l;
        }
        if(!type.equals("")) {
            if(l.isEmpty()) l = getHotelSearchObject().TypeSearch(type);
            else l.retainAll(getHotelSearchObject().TypeSearch(type));

            if(l.isEmpty()) return l;
        }
        if(!theme.equals("")) {
            if(l.isEmpty()) l = getHotelSearchObject().ThemeSearch(theme);
            else l.retainAll(getHotelSearchObject().ThemeSearch(theme));

            if(l.isEmpty()) return l;
        }
        if(!quality.equals("")) {
            if(l.isEmpty()) l = getHotelSearchObject().QualitySearch(quality);
            else l.retainAll(getHotelSearchObject().QualitySearch(quality));

            if(l.isEmpty()) return l;
        }
        return l;
    }


    /**
     *
     * @param location where you fly from
     * @param destination where you fly to
     * @param date when you fly, format "yyyy-MM-dd"
     * @return returns list of Flight objects from the database that have the departure, arrival and date
     */
    public ArrayList<Flight> getFlightInfo(String location, String destination, String date){
        // skv. domain modelinu er hjá 7F virðist tilviksbreytan availableFlightList geyma leitarniðurstöðurnar


        ArrayList<Flight> f = new ArrayList<Flight>();

        ArrayList<String> flightResults =  flightSearchObject.searchForFlight(location, destination, date);

        // flug sem fara á sömu mínútu eru sömu flug
        DateFormat format = new SimpleDateFormat("y-M-d HH:mm", Locale.ENGLISH);
        Date departure;

        // the elements of flightResults are strings "location destination departure-time"
        // e.g. Keflavík Alicante 2017-04-16 21:58:37
        for(String flight : flightResults){
            try {
                departure = format.parse(nthWord(flight, 3));
                f.add(new Flight(location, destination, departure));
            }
            catch(ParseException ex){
                //
            }


        }


        // fyrir return flight má kalla á flightS.searchForFlight(destination, location , returnDate)
        return f;
    }

    /* --------------------------------------------------------- */
    /*                          Sort                             */
    /* --------------------------------------------------------- */

    // ======================   Flight   =======================
    // These functions are made but I don't seem to be able to find object of type "Flight" on their GitHub so this might be obsolete.

    // Sorts array f by departure time in descending order if desc = true, otherwise in ascending.
    public Flight[] sortByDeparture_Flight(Flight[] f, boolean desc){
        if(desc){
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getDeparture() == null || f2.getDeparture() == null)
                        return 0;
                    return f1.getDeparture().compareTo(f2.getDeparture());
                }
            }.reversed());
        }
        else{
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getDeparture() == null || f2.getDeparture() == null)
                        return 0;
                    return f1.getDeparture().compareTo(f2.getDeparture());
                }
            }.reversed());
        }

        return f;
    }

    // Sorts array f by arrival time in descending order if desc = true, otherwise in ascending.
    public Flight[] sortByArrival_Flight(Flight[] f, boolean desc){
        if(desc){
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getArrival() == null || f2.getArrival() == null)
                        return 0;
                    return f1.getArrival().compareTo(f2.getArrival());
                }
            }.reversed());
        }
        else{
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getArrival() == null || f2.getArrival() == null)
                        return 0;
                    return f1.getArrival().compareTo(f2.getArrival());
                }
            }.reversed());
        }

        return f;
    }

    // Sorts array f by location in descending order if desc = true, otherwise in ascending.
    public Flight[] sortByLocation_Flight(Flight[] f, boolean desc){
        if(desc){
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getLocation() == null || f2.getLocation() == null)
                        return 0;
                    return f1.getLocation().compareTo(f2.getLocation());
                }
            }.reversed());
        }
        else{
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getLocation() == null || f2.getLocation() == null)
                        return 0;
                    return f1.getLocation().compareTo(f2.getLocation());
                }
            }.reversed());
        }

        return f;
    }

    // Sorts array f by destination in descending order if desc = true, otherwise in ascending.
    public Flight[] sortByDestination_Flight(Flight[] f, boolean desc){
        if(desc){
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getDestination() == null || f2.getDestination() == null)
                        return 0;
                    return f1.getDestination().compareTo(f2.getDestination());
                }
            }.reversed());
        }
        else{
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getDestination() == null || f2.getDestination() == null)
                        return 0;
                    return f1.getDestination().compareTo(f2.getDestination());
                }
            }.reversed());
        }

        return f;
    }

    // Sorts array f by price in descending order if desc = true, otherwise in ascending.
    public Flight[] sortByPrice_Flight(Flight[] f, boolean desc){
        if(desc){
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getPrice() == f2.getPrice())
                        return 0;
                    return f1.getPrice() < f2.getPrice() ? -1 : 1;
                }
            }.reversed());
        }
        else{
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getPrice() == f2.getPrice())
                        return 0;
                    return f1.getPrice() < f2.getPrice() ? -1 : 1;
                }
            }.reversed());
        }

        return f;
    }

    // Sorts array f by numbPeople in descending order if desc = true, otherwise in ascending.
    public Flight[] sortBynumbPeople_Flight(Flight[] f, boolean desc){
        if(desc){
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getNumbPeople() == f2.getNumbPeople())
                        return 0;
                    return f1.getNumbPeople() < f2.getNumbPeople() ? -1 : 1;
                }
            }.reversed());
        }
        else{
            Arrays.sort(f, new Comparator<Flight>() {
                public int compare(Flight f1, Flight f2) {
                    if (f1.getNumbPeople() == f2.getNumbPeople())
                        return 0;
                    return f1.getNumbPeople() < f2.getNumbPeople() ? -1 : 1;
                }
            }.reversed());
        }

        return f;
    }

    // ======================   HotelRoom    =======================

    // Sorts list h by theme in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByHotelName_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getHotelName() == null || h2.getHotelName() == null)
                        return 0;
                    return h1.getHotelName().compareTo(h2.getHotelName());
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getHotelName() == null || h2.getHotelName() == null)
                        return 0;
                    return h1.getHotelName().compareTo(h2.getHotelName());
                }
            });
        }

        return h;
    }

    // Sorts list h by location in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByLocation_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getLocation() == null || h2.getLocation() == null)
                        return 0;
                    return h1.getLocation().compareTo(h2.getLocation());
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getLocation() == null || h2.getLocation() == null)
                        return 0;
                    return h1.getLocation().compareTo(h2.getLocation());
                }
            });
        }

        return h;
    }

    // Sorts list h by quality in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByQuality_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getQuality() == h2.getQuality())
                        return 0;
                    return h1.getQuality() < h2.getQuality() ? -1 : 1;
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getQuality() == h2.getQuality())
                        return 0;
                    return h1.getQuality() < h2.getQuality() ? -1 : 1;
                }
            });
        }

        return h;
    }

    // Sorts list h by fromAvailability in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByFromAvailability_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getFromAvailability() == null || h2.getFromAvailability() == null)
                        return 0;
                    return h1.getFromAvailability().compareTo(h2.getFromAvailability());
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getFromAvailability() == null || h2.getFromAvailability() == null)
                        return 0;
                    return h1.getFromAvailability().compareTo(h2.getFromAvailability());
                }
            });
        }

        return h;
    }

    // Sorts list h by toAvailability in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByToAvailability_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getToAvailability() == null || h2.getToAvailability() == null)
                        return 0;
                    return h1.getToAvailability().compareTo(h2.getToAvailability());
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getToAvailability() == null || h2.getToAvailability() == null)
                        return 0;
                    return h1.getToAvailability().compareTo(h2.getToAvailability());
                }
            });
        }

        return h;
    }

    // Sorts list h by theme in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByTheme_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getTheme() == null || h2.getTheme() == null)
                        return 0;
                    return h1.getTheme().compareTo(h2.getTheme());
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getTheme() == null || h2.getTheme() == null)
                        return 0;
                    return h1.getTheme().compareTo(h2.getTheme());
                }
            });
        }

        return h;
    }

    // Sorts list h by theme in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByType_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getType() == null || h2.getType() == null)
                        return 0;
                    return h1.getType().compareTo(h2.getType());
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getType() == null || h2.getType() == null)
                        return 0;
                    return h1.getType().compareTo(h2.getType());
                }
            });
        }

        return h;
    }

    // Sorts list h by price in descending order if desc = true, otherwise in ascending.
    public ArrayList<HotelRoom> sortByPrice_Hotel(ArrayList<HotelRoom> h, boolean desc){
        if(desc){
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getPrice() == h2.getPrice())
                        return 0;
                    return h1.getPrice() < h2.getPrice() ? -1 : 1;
                }
            }.reversed());
        }
        else{
            Collections.sort(h, new Comparator<HotelRoom>() {
                public int compare(HotelRoom h1, HotelRoom h2) {
                    if (h1.getPrice() == h2.getPrice())
                        return 0;
                    return h1.getPrice() < h2.getPrice() ? -1 : 1;
                }
            });
        }

        return h;
    }

    // =====================   Day Tour   ======================
    // Sorts array t by starting date in descending order if desc = true, otherwise in ascending.
    public Trip[] sortByDate_Trip(Trip[] t, boolean desc){
        if(desc){
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getDateBegin() == null || t2.getDateBegin() == null)
                        return 0;
                    return t1.getDateBegin().compareTo(t2.getDateBegin());
                }
            }.reversed());
        }
        else{
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getDateBegin() == null || t2.getDateBegin() == null)
                        return 0;
                    return t1.getDateBegin().compareTo(t2.getDateBegin());
                }
            });
        }

        return t;
    }

    // Sorts array t by name in descending order if desc = true, otherwise in ascending.
    public Trip[] sortByTripName_Trip(Trip[] t, boolean desc){
        if(desc){
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getTripName() == null || t2.getTripName() == null)
                        return 0;
                    return t1.getTripName().compareTo(t2.getTripName());
                }
            }.reversed());
        }
        else{
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getTripName() == null || t2.getTripName() == null)
                        return 0;
                    return t1.getTripName().compareTo(t2.getTripName());
                }
            });
        }

        return t;
    }

    // Sorts array t location in descending order if desc = true, otherwise in ascending.
    public Trip[] sortByLocation_Trip(Trip[] t, boolean desc){
        if(desc){
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getLocation() == null || t2.getLocation() == null)
                        return 0;
                    return t1.getLocation().compareTo(t2.getLocation());
                }
            }.reversed());
        }
        else{
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getLocation() == null || t2.getLocation() == null)
                        return 0;
                    return t1.getLocation().compareTo(t2.getLocation());
                }
            });
        }

        return t;
    }

    // Sorts array t price in descending order if desc = true, otherwise in ascending.
    public Trip[] sortByPrice_Trip(Trip[] t, boolean desc){
        if(desc){
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getPrice() == t2.getPrice())
                        return 0;
                    return t1.getPrice() < t2.getPrice() ? -1 : 1;
                }
            }.reversed());
        }
        else{
            Arrays.sort(t, new Comparator<Trip>() {
                public int compare(Trip t1, Trip t2) {
                    if (t1.getPrice() == t2.getPrice())
                        return 0;
                    return t1.getPrice() < t2.getPrice() ? -1 : 1;
                }
            });
        }

        return t;
    }

    /**
     * returns word number nr in the 3 word sentence sent
     * 1 <= nr <= 3
     * used to get data from flight results
     */
    public static String nthWord(String sent, int nr){
        int fyrstaBil = sent.indexOf(' ');
        if(nr == 1){
            return sent.substring(0,fyrstaBil);
        }

        int annadBil = fyrstaBil + sent.substring(fyrstaBil+1).indexOf(' ');

        if(nr==2) {
            return sent.substring(fyrstaBil + 1, annadBil+1);
        }

        return  sent.substring(annadBil+2,sent.length());
    }


    public DayTourSearch getDayTourSearchObject() {
        return dayTourSearchObject;
    }

    public void setDayTourSearchObject(DayTourSearch dayTourSearchObject) {
        this.dayTourSearchObject = dayTourSearchObject;
    }

    public HotelSearch getHotelSearchObject() {
        return hotelSearchObject;
    }

    public void setHotelSearchObject(HotelSearch hotelSearchObject) {
        this.hotelSearchObject = hotelSearchObject;
    }

    public FlightSearch getFlightSearchObject() {
        return flightSearchObject;
    }

    public void setFlightSearchObject(FlightSearch flightSearchObject) {
        this.flightSearchObject = flightSearchObject;
    }
}
