/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static school.School.M;
import static school.School.Y;
import static school.School.d;
import static school.School.h;
import static school.School.m;
import static school.School.s;

/**
 *
 * @author STUDENT10
 */
public class Exams extends javax.swing.JFrame {
    interface Settingzerosh{
       StringBuilder ifelse(int h);
       Settingzerosh doifelseh=(int h) -> {
           StringBuilder HS=new StringBuilder(String.valueOf(h));
           if(HS.length()==1){
               HS.insert(0, "0");
               return HS;
           }else{
              return HS; 
           }
       };
}
 interface Settingzerosm{
       StringBuilder ifelse(int m);
       Settingzerosm doifelsem=(int m) -> {
           StringBuilder MS=new StringBuilder(String.valueOf(m));
           if(MS.length()==1){
               MS.insert(0, "0");
               return MS;
           }else{
              return MS; 
           }
       };
} 
 interface Settingzeross{
       StringBuilder ifelse(int s);
       Settingzeross doifelses=(int s) -> {
           StringBuilder SS=new StringBuilder(String.valueOf(s));
           if(SS.length()==1){
               SS.insert(0, "0");
               return SS;
           }else{
              return SS; 
           }
       };
}
    javax.swing.Timer t=new javax.swing.Timer(1000, new ClockListener());
    class ClockListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                Calendar now= Calendar.getInstance();
                d=now.get(Calendar.DAY_OF_MONTH);
                M=now.get(Calendar.MONTH);++M;
                Y=now.get(Calendar.YEAR);
                h=now.get(Calendar.HOUR_OF_DAY);
                m=now.get(Calendar.MINUTE);
                s=now.get(Calendar.SECOND);
                StringBuilder HS=Settingzerosh.doifelseh.ifelse(h);
                StringBuilder MS=Settingzerosm.doifelsem.ifelse(m);
                StringBuilder SS=Settingzeross.doifelses.ifelse(s);
                clock.setText(""+HS+":"+MS+":"+SS);
                date.setText(""+d+"/"+M+"/"+Y);
    
    }
    }

    /**
     * Creates new form Exams
     */
    public Exams() {
        getContentPane().setBackground(new Color(204,255,255));
        initComponents();
        Search.requestFocus();
        t.start();
        username.setText(School.USERNAME);
         userphoto.setIcon(School.image1);
         getexamtable();
         getCATtable();
         getProjectTable();
         jScrollPane5.setVisible(false);
         searchlabel.setVisible(false);
    }
    public void getexamtable(){
        try{
            DefaultTableModel dtm=(DefaultTableModel)Examtable.getModel();
            dtm.setRowCount(0);
            Conn.Database.getDatabaseConnection();
            PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from exam where username=?" );
            ps.setString(1,username.getText());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
            Object dataRecord[]={rs.getString("ExamID"),rs.getString("Examname"),rs.getString("ExamCode"),rs.getString("DateTime"),rs.getString("Timeduration"),rs.getString("Venue"),rs.getDouble("GPA")};
            dtm.addRow(dataRecord);
            }
            if(Examtable.getRowCount()<1){
              //JOptionPane.showMessageDialog(this, "No Search Results Found for Exams !  ");
           }
            Conn.Database.disconnectDatabase();
        }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
        }
            
        }
    public void getCATtable(){
        try{
            DefaultTableModel dtm=(DefaultTableModel)CATtable.getModel();
            dtm.setRowCount(0);
            Conn.Database.getDatabaseConnection();
            PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from CAT where username=?" );
            ps.setString(1,username.getText());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
            Object dataRecord[]={rs.getString("CATID"),rs.getString("CATname"),rs.getString("CATCode"),rs.getString("DateTime"),rs.getString("Timeduration"),rs.getString("Venue"),rs.getDouble("GPA")};
            dtm.addRow(dataRecord);
            }
            if(CATtable.getRowCount()<1){
              //JOptionPane.showMessageDialog(this, "No Search Results Found for Assessments !  ");
           }
            Conn.Database.disconnectDatabase();
        }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
        }
    }
    public void getProjectTable(){
        try{
            DefaultTableModel dtm=(DefaultTableModel)Projecttable.getModel();
            dtm.setRowCount(0);
            Conn.Database.getDatabaseConnection();
            PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from Projects where username=?" );
            ps.setString(1,username.getText());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
            Object dataRecord[]={rs.getString("ProjectID"),rs.getString("Projectname"),rs.getString("ProjectCode"),rs.getString("DateTime"),rs.getString("Timeduration"),rs.getString("Venue"),rs.getDouble("GPA")};
            dtm.addRow(dataRecord);
            }
            if(CATtable.getRowCount()<1){
              //JOptionPane.showMessageDialog(this, "No Search Results Found for Projects !  ");
           }
            Conn.Database.disconnectDatabase();
        }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        examlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Examtable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Projecttable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        CATtable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Logout6 = new javax.swing.JButton();
        Logout7 = new javax.swing.JButton();
        Logout8 = new javax.swing.JButton();
        Logout9 = new javax.swing.JButton();
        Logout10 = new javax.swing.JButton();
        Search = new javax.swing.JTextField();
        Searchbutton = new javax.swing.JButton();
        userphoto = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        inquiry = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        searchlabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Searchtable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/university-of-moratuwa.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Faculty-of-Archetecture-University-Moratuwa.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Department-of-Civil-Engineering-University-Moratuwa.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/elg0p.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Faculty-of-Archetecture-University-Moratuwa.jpg"))); // NOI18N
        jLabel9.setText("jLabel9");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/image.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Details of Examinations");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1800, 1000));
        setMinimumSize(new java.awt.Dimension(1800, 1000));
        setPreferredSize(new java.awt.Dimension(1800, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Projects");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 550, 250, 30));

        examlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        examlabel.setText("Examinations");
        getContentPane().add(examlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 250, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Continuous Assesment Tests ( CAT )");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 400, 30));

        Examtable.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        Examtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Exam ID", "Exam Name", "Exam Code", "Date/time", "Time Duration", "Venue", "GPA Fraction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Examtable.setEnabled(false);
        jScrollPane2.setViewportView(Examtable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 1000, 90));

        Projecttable.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        Projecttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Project ID", "Project Name", "Project Code", "Date/Time", "Time Duration", "Venue", "GPA Fraction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Projecttable.setEnabled(false);
        jScrollPane3.setViewportView(Projecttable);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 620, 1000, 90));

        CATtable.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        CATtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CAT ID", "CAT Name", "CAT Code", "Date/Time", "Time Duration", "Venue", "GPA Fraction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        CATtable.setEnabled(false);
        jScrollPane4.setViewportView(CATtable);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 1000, 90));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/University_of_Moratuwa_logo.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("University of Moratuwa");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 50, 350, 60));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Wisdom is all wealth");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 100, -1, 20));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(" All Rights Reserved by UoM");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 900, 160, -1));

        Logout6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Logout6.setForeground(new java.awt.Color(51, 51, 255));
        Logout6.setText("Back");
        Logout6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout6.setMaximumSize(new java.awt.Dimension(100, 50));
        Logout6.setMinimumSize(new java.awt.Dimension(100, 50));
        Logout6.setPreferredSize(new java.awt.Dimension(100, 50));
        Logout6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout6ActionPerformed(evt);
            }
        });
        getContentPane().add(Logout6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 750, 120, 40));

        Logout7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Logout7.setForeground(new java.awt.Color(51, 51, 255));
        Logout7.setText("Logout");
        Logout7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout7.setMaximumSize(new java.awt.Dimension(100, 50));
        Logout7.setMinimumSize(new java.awt.Dimension(100, 50));
        Logout7.setPreferredSize(new java.awt.Dimension(100, 50));
        Logout7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout7ActionPerformed(evt);
            }
        });
        getContentPane().add(Logout7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 800, 120, 40));

        Logout8.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        Logout8.setForeground(new java.awt.Color(51, 51, 51));
        Logout8.setText("Add");
        Logout8.setBorder(null);
        Logout8.setBorderPainted(false);
        Logout8.setContentAreaFilled(false);
        Logout8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout8.setEnabled(false);
        Logout8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout8ActionPerformed(evt);
            }
        });
        getContentPane().add(Logout8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 700, 120, 30));

        Logout9.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        Logout9.setForeground(new java.awt.Color(51, 51, 51));
        Logout9.setText("Update");
        Logout9.setBorder(null);
        Logout9.setBorderPainted(false);
        Logout9.setContentAreaFilled(false);
        Logout9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout9.setEnabled(false);
        Logout9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout9ActionPerformed(evt);
            }
        });
        getContentPane().add(Logout9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 750, 120, 30));

        Logout10.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        Logout10.setForeground(new java.awt.Color(51, 51, 51));
        Logout10.setText("Delete");
        Logout10.setBorder(null);
        Logout10.setBorderPainted(false);
        Logout10.setContentAreaFilled(false);
        Logout10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout10.setEnabled(false);
        Logout10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout10ActionPerformed(evt);
            }
        });
        getContentPane().add(Logout10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 800, 120, 30));

        Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchFocusGained(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchKeyPressed(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 350, 250, 25));

        Searchbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Searchbutton.setForeground(new java.awt.Color(51, 51, 255));
        Searchbutton.setText("Search");
        Searchbutton.setBorder(null);
        Searchbutton.setBorderPainted(false);
        Searchbutton.setContentAreaFilled(false);
        Searchbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Searchbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(Searchbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 350, 70, 25));

        userphoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userphoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(userphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 400, 190, 200));

        username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        username.setForeground(new java.awt.Color(51, 51, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("username");
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 605, 220, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7tjJ.gif"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 166, 240, 540));

        clock.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        clock.setText("00:00:00");
        getContentPane().add(clock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 850, 60, -1));

        date.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        date.setText("Date");
        getContentPane().add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1698, 875, 80, -1));

        inquiry.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        inquiry.setForeground(new java.awt.Color(51, 51, 255));
        inquiry.setText("Request Inquiry");
        inquiry.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inquiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inquiryActionPerformed(evt);
            }
        });
        getContentPane().add(inquiry, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 115, 160, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setText("   and note that each Semester has a SAME weigh regarding the final GPA.");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 755, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setText("* Note that Examinations are typically started at 9.00 am");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setText("* Note that CATs are typically done as per the plannings of Lecturers");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setText("* Note that the Project periods are based on specific dates ");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 590, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel21.setText("* Here, GPA Fractions are based on SEMESTER-Wise, that means Addition of GPA Fractions of all the Examinations, CAT and/or Projects within one semester equals to 4.0  ");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 735, -1, -1));

        searchlabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchlabel.setText("Search Results");
        getContentPane().add(searchlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 780, 140, 30));

        jScrollPane5.setEnabled(false);

        Searchtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Code", "Date", "Time Duration", "Venue", "GPA Fraction"
            }
        ));
        jScrollPane5.setViewportView(Searchtable);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 820, 1000, 80));

        jMenuBar1.setBorder(null);
        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.setName("Rules"); // NOI18N

        jMenu1.setText("File");

        jMenu5.setText("General Map");

        jMenuItem6.setText("Structural Map");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem6MouseClicked(evt);
            }
        });
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Satellite Map");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem7MouseClicked(evt);
            }
        });
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem8.setText("Library");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem8MouseClicked(evt);
            }
        });
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuItem12.setText("Medical Centre");
        jMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem12MouseClicked(evt);
            }
        });
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenuItem13.setText("Sports Ground");
        jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem13MouseClicked(evt);
            }
        });
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenu1.add(jMenu5);

        jMenu8.setText("Faculties");

        jMenuItem9.setText("Faculty of Engineering");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseClicked(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem9);

        jMenuItem10.setText("Faculty of Architecture");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseClicked(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem10);

        jMenuItem16.setText("Faculty of Information Technology");
        jMenuItem16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem16MouseClicked(evt);
            }
        });
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenu1.add(jMenu8);

        jMenuItem1.setText("Inquiry Request Form");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem14.setText("Help");
        jMenuItem14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem14MouseClicked(evt);
            }
        });
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem14);

        jMenuItem15.setText("Logout");
        jMenuItem15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem15MouseClicked(evt);
            }
        });
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Home");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu7.setText("Edit");

        jMenu3.setText("Inquiry - Quick Access");

        jMenuItem2.setText("Basic Details");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Your Marks");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Your Examinations");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseClicked(evt);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Funds");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem17.setText("Payments");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem17);

        jMenu7.add(jMenu3);

        jMenuItem11.setText("Extra Curricular Activities");
        jMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem11MouseClicked(evt);
            }
        });
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        jMenuBar1.add(jMenu7);

        jMenu4.setText("Refresh");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu6.setText("Help");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Logout6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout6ActionPerformed

        Welcome w=new Welcome();
        w.setVisible(true);
        w.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_Logout6ActionPerformed

    private void Logout7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout7ActionPerformed
        int option=JOptionPane.showConfirmDialog(this, "Are you sure to log out? ","Confirm your Decision ",JOptionPane.YES_NO_CANCEL_OPTION);
        if(option==JOptionPane.YES_OPTION){
        Login log=new Login();
        log.setVisible(true);
        log.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_Logout7ActionPerformed
    }
    private void Logout8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logout8ActionPerformed

    private void Logout9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logout9ActionPerformed

    private void Logout10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logout10ActionPerformed

    private void SearchbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchbuttonActionPerformed

      if(Search.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "You have to enter specific keyword to get the results    \nCheck and Try again !      ");
      }else{
          try{
             DefaultTableModel dtm=(DefaultTableModel)Searchtable.getModel();
             dtm.setRowCount(0); 
             Conn.Database.getDatabaseConnection();
             PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from exam where (examname like '%"+Search.getText()+"%' or examcode like '%"+Search.getText()+"%'  or datetime like '%"+Search.getText()+"%' or venue like '%"+Search.getText()+"%' or gpa like '%"+Search.getText()+"%') and username=? " );
             ps.setString(1, username.getText());
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 Object dataRecord[]={rs.getString("examid"),rs.getString("examname"),rs.getString("examcode"),rs.getString("datetime"),rs.getString("timeduration"),rs.getString("venue"),rs.getString("gpa")};
                 dtm.addRow(dataRecord);
             }
             PreparedStatement ps1=Conn.Database.con.prepareStatement("Select * from cat where (catname like '%"+Search.getText()+"%' or catcode like '%"+Search.getText()+"%'  or datetime like '%"+Search.getText()+"%' or venue like '%"+Search.getText()+"%' or gpa like '%"+Search.getText()+"%') and username=? " );
             ps1.setString(1, username.getText());
             ResultSet rs1=ps1.executeQuery();
             while(rs1.next()){
                 Object dataRecord1[]={rs1.getString("CATid"),rs1.getString("catname"),rs1.getString("catcode"),rs1.getString("datetime"),rs1.getString("timeduration"),rs1.getString("venue"),rs1.getString("gpa")};
                 dtm.addRow(dataRecord1);
             }
             PreparedStatement ps2=Conn.Database.con.prepareStatement("Select * from projects where (projectname like '%"+Search.getText()+"%' or projectcode like '%"+Search.getText()+"%'  or datetime like '%"+Search.getText()+"%' or venue like '%"+Search.getText()+"%' or gpa like '%"+Search.getText()+"%') and username=? " );
             ps2.setString(1, username.getText());
              ResultSet rs2=ps2.executeQuery();
              while(rs2.next()){
                  Object dataRecord2[]={rs2.getString("projectid"),rs2.getString("projectname"),rs2.getString("projectcode"),rs2.getString("datetime"),rs2.getString("timeduration"),rs2.getString("venue"),rs2.getString("gpa")};
                  dtm.addRow(dataRecord2);
              }
              jScrollPane5.setVisible(true);
              searchlabel.setVisible(true);
              rs.close();rs1.close();rs2.close();
             if(Searchtable.getRowCount()<1){
                 jScrollPane5.setVisible(false);
                 searchlabel.setVisible(false);
                 JOptionPane.showMessageDialog(this, "No Search Results found for the Entered keyword !           ");
             }
            
          }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
          }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
          }
      }
      
    }//GEN-LAST:event_SearchbuttonActionPerformed

    private void inquiryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiryActionPerformed
       Inquiry i=new Inquiry();
       i.setVisible(true);
       i.setLocation(300,200);
    }//GEN-LAST:event_inquiryActionPerformed

    private void jMenuItem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseClicked
        SiteMap s=new SiteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        SiteMap s=new SiteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseClicked
        SatelliteMap s=new SatelliteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7MouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        SatelliteMap s=new SatelliteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseClicked
        library l=new library();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem8MouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        library l=new library();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MouseClicked
        medicalcentre m=new medicalcentre();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem12MouseClicked

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        medicalcentre m=new medicalcentre();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MouseClicked
        sportsground s=new sportsground();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem13MouseClicked

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        sportsground s=new sportsground();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked
        engineering e=new engineering();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        engineering e=new engineering();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseClicked
        architecture a=new architecture();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem10MouseClicked

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        architecture a=new architecture();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem16MouseClicked
        IT i=new IT();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem16MouseClicked

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        IT i=new IT();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        Inquiry i= new Inquiry();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Inquiry i= new Inquiry();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem14MouseClicked
        Help h=new Help();
        h.setVisible(true);
        h.setLocation(300,80);
    }//GEN-LAST:event_jMenuItem14MouseClicked

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        Help h=new Help();
        h.setVisible(true);
        h.setLocation(300,80);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem15MouseClicked
        int option=JOptionPane.showConfirmDialog(this, "Are you sure to Logout ?         ","Confirm your Decision  ",JOptionPane.YES_NO_CANCEL_OPTION);
        if(option==JOptionPane.YES_OPTION){
            Login l=new Login();
            l.setVisible(true);
            l.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem15MouseClicked

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        int option=JOptionPane.showConfirmDialog(this, "Are you sure to Logout ?         ","Confirm your Decision  ",JOptionPane.YES_NO_CANCEL_OPTION);
        if(option==JOptionPane.YES_OPTION){
            Login l=new Login();
            l.setVisible(true);
            l.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        Welcome w=new Welcome();
        w.setVisible(true);
        w.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        Basics b=new Basics();
        b.setVisible(true);
        b.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Basics b=new Basics();
        b.setVisible(true);
        b.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        Marks m=new Marks();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Marks m=new Marks();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseClicked
        Exams e=new Exams();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Exams e=new Exams();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
        Funds f=new Funds();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Funds f=new Funds();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        Funds f=new Funds();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MouseClicked
        Extra x=new Extra();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11MouseClicked

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        Extra x=new Extra();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        Exams b=new Exams();
        b.setVisible(true);
        b.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Help h=new Help();
        h.setVisible(true);
        h.setLocation(300,80);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           if(Search.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "You have to enter specific keyword to get the results    \nCheck and Try again !      ");
      }else{
          try{
             DefaultTableModel dtm=(DefaultTableModel)Searchtable.getModel();
             dtm.setRowCount(0); 
             Conn.Database.getDatabaseConnection();
             PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from exam where (examname like '%"+Search.getText()+"%' or examcode like '%"+Search.getText()+"%'  or datetime like '%"+Search.getText()+"%' or venue like '%"+Search.getText()+"%' or gpa like '%"+Search.getText()+"%') and username=? " );
             ps.setString(1, username.getText());
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 Object dataRecord[]={rs.getString("examid"),rs.getString("examname"),rs.getString("examcode"),rs.getString("datetime"),rs.getString("timeduration"),rs.getString("venue"),rs.getString("gpa")};
                 dtm.addRow(dataRecord);
             }
             PreparedStatement ps1=Conn.Database.con.prepareStatement("Select * from cat where (catname like '%"+Search.getText()+"%' or catcode like '%"+Search.getText()+"%'  or datetime like '%"+Search.getText()+"%' or venue like '%"+Search.getText()+"%' or gpa like '%"+Search.getText()+"%') and username=? " );
             ps1.setString(1, username.getText());
             ResultSet rs1=ps1.executeQuery();
             while(rs1.next()){
                 Object dataRecord1[]={rs1.getString("CATid"),rs1.getString("catname"),rs1.getString("catcode"),rs1.getString("datetime"),rs1.getString("timeduration"),rs1.getString("venue"),rs1.getString("gpa")};
                 dtm.addRow(dataRecord1);
             }
             PreparedStatement ps2=Conn.Database.con.prepareStatement("Select * from projects where (projectname like '%"+Search.getText()+"%' or projectcode like '%"+Search.getText()+"%'  or datetime like '%"+Search.getText()+"%' or venue like '%"+Search.getText()+"%' or gpa like '%"+Search.getText()+"%') and username=? " );
             ps2.setString(1, username.getText());
              ResultSet rs2=ps2.executeQuery();
              while(rs2.next()){
                  Object dataRecord2[]={rs2.getString("projectid"),rs2.getString("projectname"),rs2.getString("projectcode"),rs2.getString("datetime"),rs2.getString("timeduration"),rs2.getString("venue"),rs2.getString("gpa")};
                  dtm.addRow(dataRecord2);
              }
              jScrollPane5.setVisible(true);
              searchlabel.setVisible(true);
              rs.close();rs1.close();rs2.close();
             if(Searchtable.getRowCount()<1){
                 jScrollPane5.setVisible(false);
                 searchlabel.setVisible(false);
                 JOptionPane.showMessageDialog(this, "No Search Results found for the Entered keyword !           ");
                 
             }
            
          }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
          }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
          }
      }
      }
    }//GEN-LAST:event_SearchKeyPressed

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
        DefaultTableModel dtm=(DefaultTableModel)Searchtable.getModel();
       dtm.setRowCount(0);
    }//GEN-LAST:event_SearchFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exams.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exams().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CATtable;
    private javax.swing.JTable Examtable;
    private javax.swing.JButton Logout10;
    private javax.swing.JButton Logout6;
    private javax.swing.JButton Logout7;
    private javax.swing.JButton Logout8;
    private javax.swing.JButton Logout9;
    private javax.swing.JTable Projecttable;
    private javax.swing.JTextField Search;
    private javax.swing.JButton Searchbutton;
    private javax.swing.JTable Searchtable;
    private javax.swing.JLabel clock;
    private javax.swing.JLabel date;
    private javax.swing.JLabel examlabel;
    private javax.swing.JButton inquiry;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel searchlabel;
    private javax.swing.JLabel username;
    private javax.swing.JLabel userphoto;
    // End of variables declaration//GEN-END:variables
}
