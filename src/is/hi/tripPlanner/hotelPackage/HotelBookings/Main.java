/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.tripPlanner.hotelPackage.HotelBookings;

import is.hi.tripPlanner.hotelPackage.JFrames.AdalFrame;
import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;

/**
 *
 * @author drifasoley
 */
public class Main {
    
    public static void setUpDatabase() {
        SQLiteJDBC.createHotelRooms();
        SQLiteJDBC.insertIntoHotelRooms(1, "Hotel Bifrost","2017-01-01","2017-12-31",28000 , "double", "Family", 2, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(2, "Hotel Ísafjordur","2017-04-04","2017-08-21",34000 , "double", "Family", 3, "Vesturland");
        SQLiteJDBC.insertIntoHotelRooms(3, "Hotel Snæfell","2017-03-04","2017-09-04",21000 , "single", "Family", 2, "Austurland");
        SQLiteJDBC.insertIntoHotelRooms(4, "Hotel Snæfell","2017-03-04","2017-12-31",40000 , "double", "Family", 2, "Austurland");
        SQLiteJDBC.insertIntoHotelRooms(5, "Hotel Holt","2017-03-04","2017-08-07",90000 , "suite", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(6, "Hotel Holt","2017-03-04","2017-08-30",60000 , "double", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(7, "Hotel Holt","2017-03-04","2017-12-31",40000 , "single", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(8, "Hotel Borg","2017-02-06","2017-07-26",94000 , "suite", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(9, "Hotel Borg","2017-02-06","2017-09-04",64000 , "double", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(10, "Hotel Borg","2017-02-06","2017-09-04",44000 , "single", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(11, "Hotel Canopy","2017-02-06","2017-09-04",81647 , "suite", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(12, "Hotel Canopy","2017-02-06","2017-09-04",60389 , "double", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(13, "Hotel Canopy","2017-04-05","2017-08-15",45890 , "single", "Family", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(14, "Hotel Frón","2017-04-05","2017-09-04", 77000, "suite", "Family", 3, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(15, "Hotel Frón","2017-04-05","2017-09-04",55400 , "double", "Family", 3, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(16, "Hotel Frón","2017-04-05","2017-09-04",33500 , "single", "Family", 3, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(17, "Hotel KEA","2017-04-05","2017-10-12",45792 , "double", "Family", 4, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(18, "Hotel KEA","2017-04-05","2017-07-29",36700 , "single", "Family", 4, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(19, "Hotel Mývatn","2017-02-06","2017-12-10",26700, "double", "Family", 3, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(20, "Hotel Mývatn","2017-04-06","2017-12-01",19200 , "single", "Family", 3, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(21, "Hotel Kjarnalundur","2017-02-06","2017-12-31",39000, "double", "Family", 2, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(22,"Hotel Icelandair Akureyri","2017-02-06","2017-12-31",47000, "double", "Family", 3, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(23, "Hotel Eskifjördur","2017-01-01","2017-12-31",22000 , "double", "Family", 2, "Austurland");
        SQLiteJDBC.insertIntoHotelRooms(24, "Hotel Selfoss","2017-04-04","2017-08-21",18126 , "double", "Family", 4, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(25, "Hotel Selfoss","2017-04-04","2017-08-21",50085 , "suite", "Family", 4, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(26, "Hotel Holt","2017-03-04","2017-12-31",90000 , "suite", "Buisness", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(27, "Hotel Holt","2017-03-04","2017-12-31",60000 , "double", "Buisness", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(28, "Hotel Holt","2017-03-04","2017-12-31",40000 , "single", "Buisness", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(29, "Hotel Borg","2017-02-06","2017-07-26",94000 , "suite", "Buisness", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(30, "Hotel Borg","2017-02-06","2017-09-04",64000 , "double", "Buisness", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(31, "Hotel Icelandair Akureyri","2017-02-06","2017-10-31",47000, "double", "Buisness", 3, "Norðurland");
        SQLiteJDBC.insertIntoHotelRooms(32, "Hotel Canopy","2017-02-06","2017-09-04",81647 , "suite", "Romantic", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(33, "Hotel Canopy","2017-02-06","2017-09-04",60389 , "double", "Romantic", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(34, "Hotel Canopy","2017-04-05","2017-08-15",45890 , "single", "Romantic", 4, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(35, "Hotel Bifrost","2017-01-01","2017-12-31",28000 , "double", "Romantic", 2, "Sudurland");
        SQLiteJDBC.insertIntoHotelRooms(36, "Hotel Selfoss","2017-04-04","2017-08-21",18126 , "double", "Romantic", 4, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(37, "Litlabýli","2017-04-04","2017-08-21",13789 , "double", "Romantic", 2, "Vesturland");
        SQLiteJDBC.insertIntoHotelRooms(38, "Litlabýli","2017-04-04","2017-08-21",13789 , "double", "Family", 2, "Vesturland");
        SQLiteJDBC.insertIntoHotelRooms(39, "Hotel Eldhestar","2017-08-02","2017-11-21",16933 , "double", "Family", 2, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(40, "Hotel Grimsborgir","2017-02-02","2017-09-01",47223 , "double", "Family", 4, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(41, "Hotel Grimsborgir","2017-01-02","2017-07-08",76138 , "suite", "Family", 4, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(42, "Hotel Grimsborgir","2017-02-02","2017-09-18",32456 , "single", "Family", 4, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(43, "Hotel Edda Vík","2017-01-02","2017-11-09",26253 , "double", "Family", 3, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(44, "Hotel Edda Vík","2017-03-22","2017-10-09",21803 , "single", "Family", 3, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(45, "Hotel Edda Vík","2017-10-22","2017-12-31",36253 , "double", "Family", 3, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(46, "Puffin Hotel Vík","2017-04-08","2017-11-21",17053 , "double", "Family", 3, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(47, "Puffin Hotel Vík","2017-08-02","2017-11-21" ,33731, "triple", "Family", 3, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(48, "Sólbakki","2017-03-02","2017-08-21" ,103507, "Villa", "Family", 3, "Vesturland");
        SQLiteJDBC.insertIntoHotelRooms(49, "Tower Suites Reykjavík","2017-08-02","2017-11-21" ,198507, "suite", "Romantic", 5, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(50, "Tower Suites Reykjavík","2017-08-02","2017-11-21" ,198507, "suite", "Buisness", 5, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(51, "Hólar Private Luxury Villa","2017-04-02","2017-11-21" , 481426, "Villa", "Buisness", 5, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(52, "Hólar Private Luxury Villa","2017-04-02","2017-11-21" , 481426, "Villa", "Romantic", 5, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(53, "Hólar Private Luxury Villa","2017-04-02","2017-11-21" , 481426, "Villa", "Party", 5, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(54, "Party Hotel Danna og Gilla","2017-01-02","2017-12-29" , 999999, "suite", "Party", 5, "Suðurland");
        SQLiteJDBC.insertIntoHotelRooms(55, "Ungbarna Hotel Drífu","2017-04-02","2017-11-21" , 20000, "suite", "Family", 3, "Austurland");
        SQLiteJDBC.insertIntoHotelRooms(56, "Ungbarna Hotel Drífu","2017-04-02","2017-11-21" , 20000, "triple", "Family", 3, "Austurland");
        SQLiteJDBC.insertIntoHotelRooms(57, "Ungbarna Hotel Drífu","2017-04-02","2017-11-21" , 20000, "double", "Family", 3, "Austurland");
        SQLiteJDBC.insertIntoHotelRooms(58, "Emmuhótel","2017-04-02","2017-11-21" , 200000, "suite", "Romantic",4, "Norðurland");
    }
    
    public static void main(String args[]) {
        Main.setUpDatabase();
        //HotelRoom model = SQLiteJDBC.getHotelForId(1);
        //HotelRoom model2 = SQLiteJDBC.getHotelForId(2);
        //model.dump();
        
        AdalFrame frame = new AdalFrame();
                frame.setVisible(true);
        //model2.dump();
    }
}
