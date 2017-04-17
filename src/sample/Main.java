package sample;

import is.hi.tripPlanner.dayTourPackage.model.SearchModel;
import is.hi.tripPlanner.flightPackage.Flight;
import is.hi.tripPlanner.hotelPackage.JFrames.HotelSearch;
import is.hi.tripPlanner.tripPlannerPackage.controller.MetaSearch;
import is.hi.tripPlanner.tripPlannerPackage.storage.Package;
import is.hi.tripPlanner.tripPlannerPackage.storage.Purchaser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        String email = "vae111@hi.is";
        String ssn = "0908922319";
        Purchaser buyer = new Purchaser("Vladek", email, "7744270",ssn);

        // Initiate searching
        String searchParam[] = {"", "", "", "", ""};
        SearchModel dayTourSearchTest  = new SearchModel(searchParam);
        MetaSearch m = new MetaSearch(dayTourSearchTest, new HotelSearch());

        // Search for flights

        String departure = "2017-04-16";
        String arrival = "2017-04-23";
        ArrayList<Flight> flightsForth = m.getFlightInfo("Alicante", "Keflavík", departure);
        ArrayList<Flight> flightsBack = m.getFlightInfo( "Keflavík", "Alicante", arrival);

        // Select flights

        // See hotels that have fromAvailability <= departure & arrival <= toAvailability

        launch(args);



    }
}
