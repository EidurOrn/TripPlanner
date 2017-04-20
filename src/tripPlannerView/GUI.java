package tripPlannerView;

import javax.swing.*;


import java.util.ArrayList;

/**
 * Created by Notandi on 19-Apr-17.
 */
public class GUI {
    private JTabbedPane tabbedPane1;
    private JPanel panelMain;
    private JComboBox CBLocation;
    private JButton button1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    //ArrayList<String> fra = leit.
    private ArrayList<String> flightLocation;






    private void initGui() {
    //    flightLocation = leit.searchForFlight("Keflavik", "Alicante", "2017-04-16");
        DefaultComboBoxModel locationCB;
        System.out.print(flightLocation.toString());
        //locationCB = new DefaultComboBoxModel<String>(flightLocation.toArray());
      // CBLocation.setModel(locationCB);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
