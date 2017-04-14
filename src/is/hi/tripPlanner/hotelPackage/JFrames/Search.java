/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.tripPlanner.hotelPackage.JFrames;

import is.hi.tripPlanner.hotelPackage.Models.HotelRoom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class Search extends javax.swing.JPanel {


    DefaultTableModel tableModel = new DefaultTableModel();
    HashMap idsInTable = new HashMap();
    
    HotelRoom hotelRoomQuery = new HotelRoom(0, "", -1, "", "", "", "", -1, "");
    public Search() {
        initComponents();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"hotelName","price","fromAvailability", "toAvailability", "type", "theme", "quality", "location"});
        setHotelRoomQuery();
        findHotel();
        findLocation();
        findFromAvailability();
        findToAvailability();
        findType();
        findTheme();
        findQuality();
        jDatabaseTable.setModel(tableModel);
    }
    
    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
    
    private void setHotelRoomQuery() {
        hotelRoomQuery.type = ((String) jComboBoxType.getSelectedItem()).toLowerCase().contains("select") ? "" : (String) jComboBoxType.getSelectedItem();
        int radix = 10;
        hotelRoomQuery.price = isInteger(jSearchByPrice.getText().replace(" ", ""), radix) ? Integer.parseInt(jSearchByPrice.getText(), radix) :-1;
        hotelRoomQuery.location = jSearchByLocation.getText();
        hotelRoomQuery.hotelName = jSearchByHotelName.getText();
        hotelRoomQuery.fromAvailability = jSearchByFromAvailability.getText();
        hotelRoomQuery.toAvailability = jSearchByToAvailability.getText();
        hotelRoomQuery.quality = ((String) jComboBoxQuality.getSelectedItem()).toLowerCase().contains("select") ? -1 : Integer.parseInt(((String) jComboBoxQuality.getSelectedItem()));
        hotelRoomQuery.theme = ((String) jComboBoxTheme.getSelectedItem()).toLowerCase().contains("select") ? "" : ((String) jComboBoxTheme.getSelectedItem());
    }
    
    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:hoteldatabase.db");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    

    public ArrayList<HotelRoom> HotelSearch(String ValToSearch)
    {
        ArrayList<HotelRoom> usersList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE hotelName LIKE '%"+ValToSearch+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom user;
            
            while(rs.next())
            {
                user = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                usersList.add(user);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return usersList;
    }
    
    public ArrayList<HotelRoom> LocationSearch(String  ValToSearch)
    {
        ArrayList<HotelRoom> locationList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE location LIKE '%"+ValToSearch+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom location;
            
            while(rs.next())
            {
                location = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                locationList.add(location);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return locationList;
    }
    
    public ArrayList<HotelRoom> FromAvailabilitySearch(String  ValToSearch)
    {
        ArrayList<HotelRoom> locationList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE location LIKE '%"+ValToSearch+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom location;
            
            while(rs.next())
            {
                location = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                locationList.add(location);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return locationList;
    }
    
    public ArrayList<HotelRoom> ToAvailabilitySearch(String  ValToSearch)
    {
        ArrayList<HotelRoom> locationList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE location LIKE '%"+ValToSearch+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom location;
            
            while(rs.next())
            {
                location = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                locationList.add(location);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return locationList;
    }
    
    public ArrayList<HotelRoom> TypeSearch(String  ValToSearch)
    {
        String actualValueToSearch = ValToSearch;
        
        if (actualValueToSearch.toLowerCase().contains("select")) {
            actualValueToSearch = "";
        }
        ArrayList<HotelRoom> typeList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE type LIKE '%"+actualValueToSearch+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom type;
            
            while(rs.next())
            {
                type = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                typeList.add(type);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return typeList;
    }
    
    public ArrayList<HotelRoom> ThemeSearch(String  ValToSearch)
    {
        String actualValueToSearch = ValToSearch;
        
        if (actualValueToSearch.toLowerCase().contains("select")) {
            actualValueToSearch = "";
        }
        ArrayList<HotelRoom> themeList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE theme LIKE '%"+actualValueToSearch+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom theme;
            
            while(rs.next())
            {
                theme = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                themeList.add(theme);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return themeList;
    }
    
    public ArrayList<HotelRoom> QualitySearch(String  ValToSearch)
    {
        int actualQuality = -1;
        if (ValToSearch.toLowerCase().contains("select") == false) {
            actualQuality = Integer.parseInt(ValToSearch);
        }
        
        ArrayList<HotelRoom> qualityList = new ArrayList<HotelRoom>();
        
        
            Connection c = null;
            Statement stmt = null;
            ResultSet rs;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully SEARCH FEATURE");

            stmt = c.createStatement();
            String searchQuery = "SELECT * FROM `HotelRooms` WHERE quality LIKE '%"+actualQuality+"%'";
            rs = stmt.executeQuery(searchQuery);
            
            HotelRoom quality;
            
            while(rs.next())
            {
                quality = new HotelRoom(
                                 rs.getInt("id"),
                                 rs.getString("hotelName"),
                                 rs.getInt("price"),
                                 rs.getString("fromAvailability"),
                                 rs.getString("toAvailability"),
                                 rs.getString("type"),
                                 rs.getString("theme"),
                                 rs.getInt("quality"),
                                 rs.getString("location")
                                );
                qualityList.add(quality);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return qualityList;
    }
    
    // function to display data in jtable
    private void findHotel()
    {
        ArrayList<HotelRoom> users = HotelSearch(jSearchByHotelName.getText());
        Object[] row = new Object[9];
        
        for(int i = 0; i < users.size(); i++)
        {
            
            row[0] = users.get(i).getHotelName();
            row[1] = users.get(i).getPrice();
            row[2] = users.get(i).getFromAvailability();
            row[3] = users.get(i).getToAvailability();
            row[4] = users.get(i).getType();
            row[5] = users.get(i).getTheme();
            row[6] = users.get(i).getQuality();
            row[7] = users.get(i).getLocation();
            int id = users.get(i).id;
            
            if (idsInTable.containsKey(id) == false && users.get(i).matchesQuery(hotelRoomQuery)) {
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
        }
       
    }
    
    private void findLocation(){
        ArrayList<HotelRoom> location = LocationSearch(jSearchByLocation.getText());
        Object[] row = new Object[9];
        
        for(int i = 0; i < location.size(); i++)
        {
            row[0] = location.get(i).getHotelName();
            row[1] = location.get(i).getPrice();
            row[2] = location.get(i).getFromAvailability();
            row[3] = location.get(i).getToAvailability();
            row[4] = location.get(i).getType();
            row[5] = location.get(i).getTheme();
            row[6] = location.get(i).getQuality();
            row[7] = location.get(i).getLocation();
  
            int id = location.get(i).id;
            
            if (idsInTable.containsKey(id) == false  && location.get(i).matchesQuery(hotelRoomQuery)) {
                System.out.println("Adding id " + id);
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
            
        }
    }
    
    private void findFromAvailability(){
        ArrayList<HotelRoom> fromAvailability = FromAvailabilitySearch(jSearchByFromAvailability.getText());
        Object[] row = new Object[9];
        
        for(int i = 0; i < fromAvailability.size(); i++)
        {
            row[0] = fromAvailability.get(i).getHotelName();
            row[1] = fromAvailability.get(i).getPrice();
            row[2] = fromAvailability.get(i).getFromAvailability();
            row[3] = fromAvailability.get(i).getToAvailability();
            row[4] = fromAvailability.get(i).getType();
            row[5] = fromAvailability.get(i).getTheme();
            row[6] = fromAvailability.get(i).getQuality();
            row[7] = fromAvailability.get(i).getLocation();
  
            int id = fromAvailability.get(i).id;
            
            if (idsInTable.containsKey(id) == false  && fromAvailability.get(i).matchesQuery(hotelRoomQuery)) {
                System.out.println("Adding id " + id);
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
            
        }
    }
    
    private void findToAvailability(){
        ArrayList<HotelRoom> toAvailability = ToAvailabilitySearch(jSearchByToAvailability.getText());
        Object[] row = new Object[9];
        
        for(int i = 0; i < toAvailability.size(); i++)
        {
            row[0] = toAvailability.get(i).getHotelName();
            row[1] = toAvailability.get(i).getPrice();
            row[2] = toAvailability.get(i).getFromAvailability();
            row[3] = toAvailability.get(i).getToAvailability();
            row[4] = toAvailability.get(i).getType();
            row[5] = toAvailability.get(i).getTheme();
            row[6] = toAvailability.get(i).getQuality();
            row[7] = toAvailability.get(i).getLocation();
  
            int id = toAvailability.get(i).id;
            
            if (idsInTable.containsKey(id) == false  && toAvailability.get(i).matchesQuery(hotelRoomQuery)) {
                System.out.println("Adding id " + id);
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
            
        }
    }
    
    private void findType()
    {
        String searchText = (String) jComboBoxType.getSelectedItem();
        ArrayList<HotelRoom> type = TypeSearch(searchText);
        
        Object[] row = new Object[9];
        
        for(int i = 0; i < type.size(); i++)
        {
            row[0] = type.get(i).getHotelName();
            row[1] = type.get(i).getPrice();
            row[2] = type.get(i).getFromAvailability();
            row[3] = type.get(i).getToAvailability();
            row[4] = type.get(i).getType();
            row[5] = type.get(i).getTheme();
            row[6] = type.get(i).getQuality();
            row[7] = type.get(i).getLocation();
  
            int id = type.get(i).id;
            System.out.println("Adding id " + id);
            if (idsInTable.containsKey(id) == false && type.get(i).matchesQuery(hotelRoomQuery)) {
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
        }       
    }
    
    private void findTheme()
    {
        ArrayList<HotelRoom> theme = ThemeSearch((String) jComboBoxTheme.getSelectedItem());
        
        Object[] row = new Object[9];
        
        for(int i = 0; i < theme.size(); i++)
        {
            row[0] = theme.get(i).getHotelName();
            row[1] = theme.get(i).getPrice();
            row[2] = theme.get(i).getFromAvailability();
            row[3] = theme.get(i).getToAvailability();
            row[4] = theme.get(i).getType();
            row[5] = theme.get(i).getTheme();
            row[6] = theme.get(i).getQuality();
            row[7] = theme.get(i).getLocation();
  
            int id = theme.get(i).id;
            System.out.println("Adding id " + id);
            if (idsInTable.containsKey(id) == false && theme.get(i).matchesQuery(hotelRoomQuery)) {
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
        }       
    }
    

    
    private void findQuality()
    {
        ArrayList<HotelRoom> quality = QualitySearch((String) jComboBoxQuality.getSelectedItem());
        
        tableModel.setColumnIdentifiers(new Object[]{"hotelName","price","fromAvailability", "toAvailability", "type", "theme", "quality", "location"});
        Object[] row = new Object[9];
        
        for(int i = 0; i < quality.size(); i++)
        {
            row[0] = quality.get(i).getHotelName();
            row[1] = quality.get(i).getPrice();
            row[2] = quality.get(i).getFromAvailability();
            row[3] = quality.get(i).getToAvailability();
            row[4] = quality.get(i).getType();
            row[5] = quality.get(i).getTheme();
            row[6] = quality.get(i).getQuality();
            row[7] = quality.get(i).getLocation();
  
            int id = quality.get(i).id;
            System.out.println("Adding id " + id);
            if (idsInTable.containsKey(id) == false && quality.get(i).matchesQuery(hotelRoomQuery)) {
                tableModel.addRow(row);
                idsInTable.put(id, true);
            }
        }
    }
    
    public static ArrayList selectFromTable() {
        TableModel model1 = jDatabaseTable.getModel();

        int[] indexs = jDatabaseTable.getSelectedRows();
        ArrayList retList = new ArrayList();
        Object[] row = new Object[8];
        BookingFrame bookingfrm = new BookingFrame();
        DefaultTableModel bookingtbl = (DefaultTableModel)bookingfrm.jBookingTable.getModel();

        for(int i = 0; i < indexs.length; i++)
        {
            row[0] = model1.getValueAt(indexs[i], 0);
            row[1] = model1.getValueAt(indexs[i], 1);
            row[2] = model1.getValueAt(indexs[i], 2);
            row[3] = model1.getValueAt(indexs[i], 3);
            row[4] = model1.getValueAt(indexs[i], 4);
            row[5] = model1.getValueAt(indexs[i], 5);
            row[6] = model1.getValueAt(indexs[i], 6);
            row[7] = model1.getValueAt(indexs[i], 7);
            bookingtbl.addRow(row);
            retList.add(row);
        }  
        bookingfrm.setVisible(true);
        //skilar retList sem er röðin sem maður valdi í searchinu, retList + custInfo þarf að fara saman í ArrayList/List og skilast þannig til T-hópsins líklega (hef ekkert spurt)
        //sjá BookingFrame klasann fyrir betri útskyringu
        return retList;
    }
       

    
   


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSearchByToAvailability = new javax.swing.JTextField();
        jComboBoxType = new javax.swing.JComboBox<>();
        jComboBoxTheme = new javax.swing.JComboBox<>();
        jComboBoxQuality = new javax.swing.JComboBox<>();
        jSearchByFromAvailability = new javax.swing.JTextField();
        jSearchByHotelName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDatabaseTable = new JTable();
        jSearchByPrice = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSearchByLocation = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSelectButton = new javax.swing.JButton();

        jComboBoxType.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select type", "single", "double", "triple", "villa", "suite" }));
        jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeActionPerformed(evt);
            }
        });

        jComboBoxTheme.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jComboBoxTheme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select theme", "Family", "Romantic", "Buisness", "Party" }));

        jComboBoxQuality.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jComboBoxQuality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select quality", "1", "2", "3", "4", "5" }));

        jSearchByFromAvailability.setToolTipText("");

        jDatabaseTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hotel Name", "Availability from", "Availability to", "Price", "Type", "Theme", "Quality", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jDatabaseTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jDatabaseTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jDatabaseTable);

        jSearchByPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchByPriceActionPerformed(evt);
            }
        });

        jSearchButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jSearchButton.setText("Search");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Hotel:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("date from:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("date to:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("price:");

        jSearchByLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchByLocationActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Location");

        jSelectButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jSelectButton.setText("Select");
        jSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearchByHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearchByPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSearchByToAvailability)
                                .addGap(167, 167, 167))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSearchByFromAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)))
                        .addComponent(jComboBoxTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearchByLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(jSelectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSearchByFromAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchByPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchByHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jSearchByLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSearchByToAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new Object[]{"hotelName","price","fromAvailability", "toAvailability", "type", "theme", "quality", "location"});
        idsInTable = new HashMap();
        setHotelRoomQuery();

        findHotel();
        findLocation();
        findFromAvailability();
        findToAvailability();
        findType();
        findTheme();
        findQuality();
        hotelRoomQuery.dump();
        jDatabaseTable.setModel(tableModel);
        // filter from tableModel
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    private void jSearchByPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchByPriceActionPerformed

    }//GEN-LAST:event_jSearchByPriceActionPerformed

    private void jSearchByLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchByLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchByLocationActionPerformed

    private void jDatabaseTableMousePressed(MouseEvent evt) {//GEN-FIRST:event_jDatabaseTableMousePressed

    }//GEN-LAST:event_jDatabaseTableMousePressed

    private void jSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelectButtonActionPerformed
        selectFromTable();
        this.setVisible(false);
    }//GEN-LAST:event_jSelectButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxQuality;
    private javax.swing.JComboBox<String> jComboBoxTheme;
    private javax.swing.JComboBox<String> jComboBoxType;
    public static JTable jDatabaseTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchByFromAvailability;
    private javax.swing.JTextField jSearchByHotelName;
    private javax.swing.JTextField jSearchByLocation;
    private javax.swing.JTextField jSearchByPrice;
    private javax.swing.JTextField jSearchByToAvailability;
    private javax.swing.JButton jSelectButton;
    // End of variables declaration//GEN-END:variables
}
