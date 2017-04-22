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

    }
}
