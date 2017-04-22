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


    public TableListener(TPGUI mainWindow){
        this.mainWindow = mainWindow;
    }


    @Override
    public void valueChanged(ListSelectionEvent event){

        ListSelectionModel lsm = (ListSelectionModel)event.getSource();
        int chosenIndex = lsm.getMinSelectionIndex();
        mainWindow.setChosenFlight(mainWindow.getFlightResults().get(chosenIndex));
    }
}
