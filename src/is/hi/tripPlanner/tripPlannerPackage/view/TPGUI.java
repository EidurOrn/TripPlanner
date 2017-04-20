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

    public TPGUI(){
        DefaultTableModel model = new DefaultTableModel();

        flightTable.setAutoCreateRowSorter(true);
        flightTable.setFillsViewportHeight(true);
        flightTable.setPreferredScrollableViewportSize(new Dimension(550, 200));
        model.addColumn("Location");
        model.addColumn("Destination");
        model.addColumn("Date");
        model.addColumn("Price");
        flightTable.setModel(model);

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
