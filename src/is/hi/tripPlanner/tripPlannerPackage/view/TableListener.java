package is.hi.tripPlanner.tripPlannerPackage.view;

import is.hi.tripPlanner.flightPackage.Flight;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
/**
 * Created by Valdi on 22.4.2017.
 */
public class TableListener implements ListSelectionListener {

    private final TPGUI mainWindow;
    /**
     * = 1 if for flightTable, = 2 if for hotelTable, = 3 if for daytourTable
     */
    private int flightHotelTour;

    public TableListener(TPGUI mainWindow, int flightHotelTour){
        this.mainWindow = mainWindow;
        this.flightHotelTour = flightHotelTour;
    }


    @Override
    public void valueChanged(ListSelectionEvent event){

        ListSelectionModel lsm = (ListSelectionModel)event.getSource();
        int chosenIndex = lsm.getMinSelectionIndex();
        if(flightHotelTour == 1){
            mainWindow.setChosenFlight(mainWindow.getFlightResults().get(chosenIndex));
        }
        if(flightHotelTour == 2){
//            mainWindow.setChosenHotel(mainWindow.getHotelResults().get(chosenIndex));

            int otherIndex = mainWindow.getHotelTable().getRowSorter().convertRowIndexToModel(chosenIndex);
            // otherIndex is the index in the data, even after the table has been sorted by clicking

            mainWindow.setChosenHotel(mainWindow.getHotelResults().get(otherIndex));
            //System.out.println(mainWindow.getChosenHotel() + "\n");
                    //System.out.println( mainWindow.getHotelResults().get(mainWindow.getHotelTable().getRowSorter().convertRowIndexToModel(mainWindow.getHotelTable().getSelectedRow())).getHotelName());
        }

        if(flightHotelTour == 3){
            //mainWindow.setChosenTrip(mainWindow.getDayTourResults().get(chosenIndex));
            int otherIndex = mainWindow.getDtTable().getRowSorter().convertRowIndexToModel(chosenIndex);
            // otherIndex is the index in the data, even after the table has been sorted by clicking
            System.out.println(chosenIndex);
            System.out.println(otherIndex);
            mainWindow.setChosenTrip(mainWindow.getDayTourResults().get(otherIndex));
        }


    }
}
