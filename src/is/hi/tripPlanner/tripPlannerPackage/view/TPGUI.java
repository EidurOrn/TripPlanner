package is.hi.tripPlanner.tripPlannerPackage.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by Notandi on 20-Apr-17.
 */
public class TPGUI {
    private JTabbedPane tabbedPane2;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JTable flightTable;
    private JList list1;
    private JButton searchButton;
    private JButton bookButton;
    private JButton cancelButton;
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
    DefaultTableModel flightModel = new DefaultTableModel();
    DefaultTableModel hotelModel = new DefaultTableModel();

    public TPGUI(){

        flightTable.setAutoCreateRowSorter(true);
        flightTable.setFillsViewportHeight(true);
        flightTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        flightModel.addColumn("Location");
        flightModel.addColumn("Destination");
        flightModel.addColumn("Date");
        flightModel.addColumn("Price");
        flightTable.setModel(flightModel);

        hotelTable.setAutoCreateRowSorter(true);
        hotelTable.setFillsViewportHeight(true);
        hotelTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        hotelModel.addColumn("Name");
        hotelModel.addColumn("Location");
        hotelModel.addColumn("Price");
        hotelModel.addColumn("Quality");
        hotelModel.addColumn("Type");
        hotelModel.addColumn("Theme");
        hotelTable.setModel(hotelModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TPGUI");
        frame.setContentPane(new TPGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 700));
        frame.pack();
        frame.setVisible(true);
    }

}
