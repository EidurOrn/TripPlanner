package is.hi.tripPlanner.tripPlannerPackage.view;

import javax.swing.*;

/**
 * Created by Notandi on 20-Apr-17.
 */
public class TPGUI {
    private JTabbedPane tabbedPane2;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JTextField textField1;

    public TPGUI(){


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TPGUI");
        frame.setContentPane(new TPGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
