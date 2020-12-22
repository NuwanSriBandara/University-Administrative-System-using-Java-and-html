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
import static school.School.M;
import static school.School.Y;
import static school.School.d;
import static school.School.h;
import static school.School.m;
import static school.School.s;

/**
 *
 * @author HP
 */
public class Salary extends javax.swing.JFrame {
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
    interface calculationot{
        double cal(double x,double y);
        calculationot doot=(double ot, double otperhr)->{
          double allot=ot*otperhr;
          return allot;
        };
        
    }
    interface calculationgross{
        double cal(double x,double y,double z,double a);
        calculationgross dogross=(double x,double y,double z,double a)->{
            double gross=x+y+z+a;
            return gross;
        };
    }
    interface calculationdaily{
        double cal(double x);
        calculationdaily dodaily=(double x)->{
           double daily=x/30;
           return daily;
        };
    }
    interface calculationleave{
        double cal(double x,double y);
        calculationot doot=(double ot, double otperhr)->{
          double leave=ot*otperhr;
          return leave;
        };
        
    }
    interface calculationnet{
        double cal(double x,double y,double z,double a);
        calculationgross dogross=(double x,double y,double z,double a)->{
            double net=x-y-x*z/100-x*a/100;
            return net;
        };
    }

    /**
     * Creates new form Salary
     */
    public Salary() {
        getContentPane().setBackground(new Color(204,255,255));
        initComponents();
        t.start();
        username.setText(School.USERNAME);
        userphoto.setIcon(School.image1);
         userphoto.setIcon(School.image1);
         getdetails();
         searchlabel.setVisible(false);
         jScrollPane2.setVisible(false);
    }
    public void getdetails(){
         try{
            Conn.Database.getDatabaseConnection();
            PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from user where username=?" );
            ps.setString(1,username.getText());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                UID.setText(rs.getString("uid"));
                Username.setText(rs.getString("username"));
                Fullname.setText(rs.getString("fullname"));
                HRtype.setText(rs.getString("hrtype"));
                email.setText(rs.getString("email"));
                
                PreparedStatement ps1=Conn.Database.con.prepareStatement("Select * from salary where username=?" );
                ps1.setString(1, username.getText());
                ResultSet rs1=ps1.executeQuery();
                if(rs1.next()){
                    basic.setText(rs1.getString("basic"));
                      double basicnew=Double.parseDouble(basic.getText());
                    ot.setText(rs1.getString("othrs"));
                      double otnew=Double.parseDouble(ot.getText());
                    performance.setText(rs1.getString("performance"));
                      double performancenew=Double.parseDouble(performance.getText());
                    bonus.setText(rs1.getString("bonus"));
                      double bonusnew=Double.parseDouble(bonus.getText());
                    leavedays.setText(rs1.getString("leavedays"));
                      double leavenew=Double.parseDouble(leavedays.getText());
                    tax.setText(rs1.getString("tax"));
                      double taxnew=Double.parseDouble(tax.getText());
                    contribution.setText(rs1.getString("contribution"));
                      double contributionnew=Double.parseDouble(contribution.getText());
                    otperhr.setText(rs1.getString("otperhr"));
                      double otperhrnew=Double.parseDouble(otperhr.getText());
                    paymentdate.setText(rs1.getDate("paymentdate").toString());
                    fromtime.setText(rs1.getDate("fromtime").toString());
                    totime.setText(rs1.getDate("totime").toString());
                    allot.setText(String.valueOf(calculationot.doot.cal(otnew, otperhrnew)));
                    double allotnew=calculationot.doot.cal(otnew, otperhrnew);
                    gross.setText(String.valueOf(calculationgross.dogross.cal(basicnew, allotnew, performancenew, bonusnew)));
                    double rate1=calculationgross.dogross.cal(basicnew, allotnew, performancenew, bonusnew);
                    rate.setText(String.valueOf((int)calculationdaily.dodaily.cal(rate1)));
                    rate2.setText(rate.getText());
                    double leaveper=calculationdaily.dodaily.cal(rate1);
                    leaveall.setText(String.valueOf((int)calculationleave.doot.cal(leavenew, leaveper)));
                    double leaveallnew=calculationleave.doot.cal(leavenew, leaveper);
                    net.setText(String.valueOf(((int)calculationnet.dogross.cal(rate1, leaveallnew, taxnew, contributionnew))));
                      
                      
                }else{
                   // JOptionPane.showMessageDialog(this, "Please refresh the System application !   ");
                }
            }else{
                //JOptionPane.showMessageDialog(this, "Please refresh the System application !   ");
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

        jScrollPane5 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        Searchbutton = new javax.swing.JButton();
        userphoto = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        Logout8 = new javax.swing.JButton();
        Logout9 = new javax.swing.JButton();
        Logout10 = new javax.swing.JButton();
        clock = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        inquiry = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Logout6 = new javax.swing.JButton();
        Logout7 = new javax.swing.JButton();
        examlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        net = new javax.swing.JTextField();
        UID = new javax.swing.JTextField();
        HRtype = new javax.swing.JTextField();
        Username = new javax.swing.JTextField();
        Fullname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        paymentdate = new javax.swing.JTextField();
        ot = new javax.swing.JTextField();
        performance = new javax.swing.JTextField();
        bonus = new javax.swing.JTextField();
        gross = new javax.swing.JTextField();
        rate = new javax.swing.JTextField();
        allot = new javax.swing.JTextField();
        tax = new javax.swing.JTextField();
        contribution = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        totime = new javax.swing.JTextField();
        leavedays = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        rate2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        leaveall = new javax.swing.JTextField();
        basic = new javax.swing.JTextField();
        otperhr = new javax.swing.JTextField();
        fromtime = new javax.swing.JTextField();
        searchlabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchresults = new javax.swing.JTextArea();
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

        jScrollPane5.setViewportView(jTree2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Get your Payments from UoM");
        setMaximumSize(new java.awt.Dimension(1800, 1000));
        setMinimumSize(new java.awt.Dimension(1800, 1000));
        setPreferredSize(new java.awt.Dimension(1800, 1000));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("University of Moratuwa");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 50, 350, 60));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Wisdom is all wealth");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 100, -1, 20));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/University_of_Moratuwa_logo.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 10, -1, -1));

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

        clock.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        clock.setText("00:00:00");
        getContentPane().add(clock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 850, 60, -1));

        date.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        date.setText("Date");
        getContentPane().add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1698, 875, 80, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(" All Rights Reserved by UoM");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 900, 160, -1));

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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gif 1.gif"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 166, 240, 540));

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

        examlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        examlabel.setText("Monthly Salary Details");
        getContentPane().add(examlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 250, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Last Payment Details");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 190, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("UID");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 160, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("HR type");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 160, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("User name");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 160, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Full name");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 160, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Net Payment LKR");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 740, 230, 30));

        net.setEditable(false);
        net.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        net.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netActionPerformed(evt);
            }
        });
        getContentPane().add(net, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 740, 400, 25));

        UID.setEditable(false);
        UID.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        UID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UIDActionPerformed(evt);
            }
        });
        getContentPane().add(UID, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 400, 25));

        HRtype.setEditable(false);
        HRtype.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        HRtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HRtypeActionPerformed(evt);
            }
        });
        getContentPane().add(HRtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 400, 25));

        Username.setEditable(false);
        Username.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        getContentPane().add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 400, 25));

        Fullname.setEditable(false);
        Fullname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Fullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullnameActionPerformed(evt);
            }
        });
        getContentPane().add(Fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 400, 25));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Email Address");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 160, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Date of Payment");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, 160, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Basic Salary LKR");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 160, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Overtime Pay LKR");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 160, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Performance Related Pay LKR");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 230, 20));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Employer Superannuation Contribution %");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 695, 300, 20));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Bonus LKR");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 230, 20));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Daily Rate LKR/Day");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 605, 230, 20));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Leave Deduction LKR");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 635, 230, 20));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Taxation %");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 665, 230, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Gross Payment LKR");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, 230, 30));

        email.setEditable(false);
        email.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 400, -1));

        paymentdate.setEditable(false);
        paymentdate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        paymentdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentdateActionPerformed(evt);
            }
        });
        getContentPane().add(paymentdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 400, 25));

        ot.setEditable(false);
        ot.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        ot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otActionPerformed(evt);
            }
        });
        getContentPane().add(ot, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, 80, 25));

        performance.setEditable(false);
        performance.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        performance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performanceActionPerformed(evt);
            }
        });
        getContentPane().add(performance, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 400, 25));

        bonus.setEditable(false);
        bonus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        bonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bonusActionPerformed(evt);
            }
        });
        getContentPane().add(bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 400, 25));

        gross.setEditable(false);
        gross.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        gross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossActionPerformed(evt);
            }
        });
        getContentPane().add(gross, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 570, 400, 25));

        rate.setEditable(false);
        rate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateActionPerformed(evt);
            }
        });
        getContentPane().add(rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 600, 400, 25));

        allot.setEditable(false);
        allot.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        allot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allotActionPerformed(evt);
            }
        });
        getContentPane().add(allot, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 470, 190, 25));

        tax.setEditable(false);
        tax.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxActionPerformed(evt);
            }
        });
        getContentPane().add(tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 660, 400, 25));

        contribution.setEditable(false);
        contribution.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        contribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contributionActionPerformed(evt);
            }
        });
        getContentPane().add(contribution, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 690, 400, 25));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setText("Pay Period");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 160, 20));

        totime.setEditable(false);
        totime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        totime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totimeActionPerformed(evt);
            }
        });
        getContentPane().add(totime, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 410, 150, 25));

        leavedays.setEditable(false);
        leavedays.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        leavedays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leavedaysActionPerformed(evt);
            }
        });
        getContentPane().add(leavedays, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 630, 80, 25));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel27.setText("To");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 30, 20));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("*");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 470, 20, 20));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("*");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 630, 20, 20));

        rate2.setEditable(false);
        rate2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rate2ActionPerformed(evt);
            }
        });
        getContentPane().add(rate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 630, 80, 25));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("=");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, 20, 20));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("=");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 630, 20, 20));

        leaveall.setEditable(false);
        leaveall.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        leaveall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveallActionPerformed(evt);
            }
        });
        getContentPane().add(leaveall, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 630, 190, 25));

        basic.setEditable(false);
        basic.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        basic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicActionPerformed(evt);
            }
        });
        getContentPane().add(basic, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 400, 25));

        otperhr.setEditable(false);
        otperhr.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        otperhr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otperhrActionPerformed(evt);
            }
        });
        getContentPane().add(otperhr, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, 80, 25));

        fromtime.setEditable(false);
        fromtime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        fromtime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromtimeActionPerformed(evt);
            }
        });
        getContentPane().add(fromtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 150, 25));

        searchlabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchlabel.setText("Search Results");
        getContentPane().add(searchlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 775, 140, 30));

        searchresults.setEditable(false);
        searchresults.setColumns(20);
        searchresults.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        searchresults.setRows(5);
        jScrollPane2.setViewportView(searchresults);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 810, 700, 70));

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

    private void SearchbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchbuttonActionPerformed
        if(Search.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "You have to enter specific keyword to get the results    \n Check and Try again !      ");
          }else{
           try{
             Conn.Database.getDatabaseConnection();
             PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from user where fullname like '%"+Search.getText()+"%' or uid like '%"+Search.getText()+"%'  or username = '"+Search.getText()+"' " );
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 searchresults.setText("");
                 searchresults.setText("Username   : "+rs.getString("username") + "\n" + "Fullname    : "+ rs.getString("Fullname")+"   "+"HR Type     : "+rs.getString("hrtype")+"\n"
                 +"Faculty       : "+rs.getString("faculty")+"   "+"Department : "+rs.getString("department"));
                 jScrollPane2.setVisible(true);
                 searchlabel.setVisible(true);
                 searchresults.requestFocus();
             }
             if(searchresults.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "No Search Results found for the Entered keyword !           ");
             }
            
          }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
          }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
          }
      }
    }//GEN-LAST:event_SearchbuttonActionPerformed

    private void Logout8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logout8ActionPerformed

    private void Logout9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logout9ActionPerformed

    private void Logout10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logout10ActionPerformed

    private void inquiryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiryActionPerformed
        Inquiry i=new Inquiry();
        i.setVisible(true);
        i.setLocation(300,200);
    }//GEN-LAST:event_inquiryActionPerformed

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
    private void netActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netActionPerformed
        //no part
    }//GEN-LAST:event_netActionPerformed

    private void UIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UIDActionPerformed

    private void HRtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HRtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HRtypeActionPerformed

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void FullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FullnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void paymentdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentdateActionPerformed

    private void otActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otActionPerformed

    private void performanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_performanceActionPerformed

    private void bonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bonusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bonusActionPerformed

    private void grossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossActionPerformed

    private void rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateActionPerformed

    private void allotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allotActionPerformed

    private void taxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxActionPerformed

    private void contributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contributionActionPerformed

    private void leavedaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leavedaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leavedaysActionPerformed

    private void totimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totimeActionPerformed

    private void rate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rate2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rate2ActionPerformed

    private void leaveallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveallActionPerformed

    private void basicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_basicActionPerformed

    private void otperhrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otperhrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otperhrActionPerformed

    private void fromtimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromtimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromtimeActionPerformed

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
        Salary b=new Salary();
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
          JOptionPane.showMessageDialog(this, "You have to enter specific keyword to get the results    \n Check and Try again !      ");
          }else{
           try{
             Conn.Database.getDatabaseConnection();
             PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from user where fullname like '%"+Search.getText()+"%' or uid like '%"+Search.getText()+"%'  or username = '"+Search.getText()+"' " );
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 searchresults.setText("");
                 searchresults.setText("Username   : "+rs.getString("username") + "\n" + "Fullname    : "+ rs.getString("Fullname")+"   "+"HR Type     : "+rs.getString("hrtype")+"\n"
                 +"Faculty       : "+rs.getString("faculty")+"   "+"Department : "+rs.getString("department"));
                 jScrollPane2.setVisible(true);
                 searchlabel.setVisible(true);
                 searchresults.requestFocus();
             }
             if(searchresults.getText().isEmpty()){
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
        searchresults.setText("");
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
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Fullname;
    private javax.swing.JTextField HRtype;
    private javax.swing.JButton Logout10;
    private javax.swing.JButton Logout6;
    private javax.swing.JButton Logout7;
    private javax.swing.JButton Logout8;
    private javax.swing.JButton Logout9;
    private javax.swing.JTextField Search;
    private javax.swing.JButton Searchbutton;
    private javax.swing.JTextField UID;
    private javax.swing.JTextField Username;
    private javax.swing.JTextField allot;
    private javax.swing.JTextField basic;
    private javax.swing.JTextField bonus;
    private javax.swing.JLabel clock;
    private javax.swing.JTextField contribution;
    private javax.swing.JLabel date;
    private javax.swing.JTextField email;
    private javax.swing.JLabel examlabel;
    private javax.swing.JTextField fromtime;
    private javax.swing.JTextField gross;
    private javax.swing.JButton inquiry;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTree jTree2;
    private javax.swing.JTextField leaveall;
    private javax.swing.JTextField leavedays;
    private javax.swing.JTextField net;
    private javax.swing.JTextField ot;
    private javax.swing.JTextField otperhr;
    private javax.swing.JTextField paymentdate;
    private javax.swing.JTextField performance;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField rate2;
    private javax.swing.JLabel searchlabel;
    private javax.swing.JTextArea searchresults;
    private javax.swing.JTextField tax;
    private javax.swing.JTextField totime;
    private javax.swing.JLabel username;
    private javax.swing.JLabel userphoto;
    // End of variables declaration//GEN-END:variables
}
