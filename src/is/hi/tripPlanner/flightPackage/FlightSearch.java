package is.hi.tripPlanner.flightPackage;

import java.util.ArrayList;

/**
 * Created by Alexander on 7.4.2017.
 */
public class FlightSearch {


    tengingVidGagnagrunn t = new tengingVidGagnagrunn();
    private ArrayList<String> fra = t.getFrom();
    private ArrayList<String> til = t.getTo();
    private ArrayList<String> dates = t.getDate();
    private ArrayList<String> time = t.getTime();
    Boolean timi1Valin = false;
    public  static Boolean timi2Valin = false;
    public static ArrayList<String> found1;
    public static ArrayList<String> found2;

    public FlightSearch(){

    }

    public ArrayList<String> searchForFlight(String departure, String arrival,String date){
        //prufa verður að hafa try og catch fyrir SQLite tengniguna
        // fer í gegn um arry-ana
        ArrayList<String> fraTil = new ArrayList<>();
        for(int i = 0; i< getFra().size(); i++){
            String Str = getTime().get(0).substring(0,2);
            int tim = Integer.parseInt(Str);
            if ((getFra().get(i)).equalsIgnoreCase(departure)&& (getTil().get(i)).equalsIgnoreCase(arrival)
                    && (dates.get(i)).equals(date)){
                String nyrLidur = departure +" "+ arrival + " " + dates.get(i) + " " + getTime().get(i);
                nyrLidur = nyrLidur.substring(0,nyrLidur.length()-2);

                if(! fraTil.contains(nyrLidur)){
                    fraTil.add(nyrLidur);
                }
            }
        }
        return fraTil;
    }

    public ArrayList<String> getFound1() {
        return found1;
    }

    public ArrayList<String> getFound2() {
        return found2;
    }

    public ArrayList<String> getFra() {
        return fra;
    }

    public void setFra(ArrayList<String> fra) {
        this.fra = fra;
    }

    public ArrayList<String> getTil() {
        return til;
    }

    public void setTil(ArrayList<String> til) {
        this.til = til;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public void setTime(ArrayList<String> time) {
        this.time = time;
    }
}
