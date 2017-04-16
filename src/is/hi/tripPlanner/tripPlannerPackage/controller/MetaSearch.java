package is.hi.tripPlanner.tripPlannerPackage.controller;


import is.hi.tripPlanner.dayTourPackage.model.SearchModel;
import is.hi.tripPlanner.dayTourPackage.model.Trip;
import is.hi.tripPlanner.dayTourPackage.controller.SearchController;
import is.hi.tripPlanner.dayTourPackage.controller.DatabaseRetrival;
//import is.hi.tripPlanner.dayTourPackage.mockObjects.DayTourFetching;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.hotelPackage.*;
import is.hi.tripPlanner.hotelPackage.JFrames.Search;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MetaSearch{
    private SearchModel dayTourSearchObject;
    private Search hotelSearchObject;

    DatabaseRetrival dayTourDBRetrieval = new DatabaseRetrival();

    public MetaSearch(SearchModel d, Search h){
        dayTourSearchObject = d;
        hotelSearchObject = h;
    }

    public Trip[] getDayTourInfo(SearchModel s){
        // Check if end date occurs before the start date.
        if(s.getDateBegin() != null) {
            if(s.getDateEnd() != null){
                if (s.getDateEnd().before(s.getDateBegin())) {
                    return new Trip[0];
                }
            }
        }
        // Check if the price is negative (user being payed).
        if(s.getPrice() < 0){
            return new Trip[0];
        }

        return dayTourDBRetrieval.queryTrip(s);
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

        if(!hotelName.equals("")) {
            l = hotelSearchObject.HotelSearch(hotelName); // Makes a list with the first search results.
            if(l.isEmpty()) return l; // There was a search attempt but it turned up empty, no other condition can change that.
        }
        if(!location.equals("")) {
            if(l.isEmpty()) l = hotelSearchObject.LocationSearch(location); // Makes a list with the first search results.
                                                                            // (If no prev result has been given, empty is a result).
            else l.retainAll(hotelSearchObject.LocationSearch(location)); // Retains only the same list elements between the two lists.

            if(l.isEmpty()) return l;
        }
        if(!fromAvailability.equals("")) {
            if(l.isEmpty()) l = hotelSearchObject.FromAvailabilitySearch(fromAvailability);
            else l.retainAll(hotelSearchObject.FromAvailabilitySearch(fromAvailability));

            if(l.isEmpty()) return l;
        }
        if(!toAvailability.equals("")) {
            if(l.isEmpty()) l = hotelSearchObject.ToAvailabilitySearch(toAvailability);
            else l.retainAll(hotelSearchObject.ToAvailabilitySearch(toAvailability));

            if(l.isEmpty()) return l;
        }
        if(!type.equals("")) {
            if(l.isEmpty()) l = hotelSearchObject.TypeSearch(type);
            else l.retainAll(hotelSearchObject.TypeSearch(type));

            if(l.isEmpty()) return l;
        }
        if(!theme.equals("")) {
            if(l.isEmpty()) l = hotelSearchObject.ThemeSearch(theme);
            else l.retainAll(hotelSearchObject.ThemeSearch(theme));

            if(l.isEmpty()) return l;
        }
        if(!quality.equals("")) {
            if(l.isEmpty()) l = hotelSearchObject.QualitySearch(quality);
            else l.retainAll(hotelSearchObject.QualitySearch(quality));

            if(l.isEmpty()) return l;
        }
        return l;
    }


    /**
     *
     * @param searchFlight  search object from flight program
     * @return
     */
    public Flight[] getFlightInfo(Search searchFlight){
        // skv. domain modelinu er hjá 7F virðist tilviksbreytan availableFlightList geyma leitarniðurstöðurnar
        return new Flight[0];
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
}
