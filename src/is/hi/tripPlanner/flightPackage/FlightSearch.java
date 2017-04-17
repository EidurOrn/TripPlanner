package is.hi.tripPlanner.flightPackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alexander on 7.4.2017.
 */
public class FlightSearch {


    tengingVidGagnagrunn t = new tengingVidGagnagrunn();
    ArrayList<String> fra = t.getFrom();
    ArrayList<String> til = t.getTo();
    ArrayList<String> data = t.getDate();
    ArrayList<String> time = t.getTime();
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
        for(int i = 0; i<fra.size();i++){
            String Str = time.get(0).substring(0,2);
            int tim = Integer.parseInt(Str);
            if ((fra.get(i)).equalsIgnoreCase(departure)&& (til.get(i)).equalsIgnoreCase(arrival)
                    && (data.get(i)).equals(date)){
                fraTil.add(departure +" "+ arrival + " " + data.get(i) + " " + time.get(i));
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

}
