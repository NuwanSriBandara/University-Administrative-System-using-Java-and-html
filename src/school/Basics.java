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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
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
public class Basics extends javax.swing.JFrame {
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
     * Creates new form Basics
     */
    public Basics() {
        getContentPane().setBackground(new Color(204,255,255));
       
        initComponents();
        t.start();
        Search.requestFocus();
         username.setText(School.USERNAME);
         userphoto.setIcon(School.image1);
         getdetails();
         getGPA();
        searchlabel.setVisible(false);
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
         
    }
    public void getdetails(){
        try{
            Conn.Database.getDatabaseConnection();
            PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from user where username=?" );
            ps.setString(1,username.getText());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                uid.setText(rs.getString("uid"));
                faculty.setText(rs.getString("faculty"));
                department.setText(rs.getString("department"));
                hrtype.setText(rs.getString("hrtype"));
                course.setText(rs.getString("department"));
                email.setText(rs.getString("email"));
                note.setText("Mobile Number : +94 "+rs.getString("mobile"));
                String datestring=rs.getString("recorddate");
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
               try {
                     Date date=format.parse(datestring);
                     registrationdate.setDate(date);
               } catch (ParseException ex) {
                      JOptionPane.showMessageDialog(this, "Error: \n"+ex);
            
              }
                
            }
            
         }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
        }
    }
    
    public void getGPA(){
        try{
                PreparedStatement ps1=Conn.Database.con.prepareStatement("Select gotgpa from exammarks where username=?" );
                ps1.setString(1,username.getText());
                ResultSet rs1=ps1.executeQuery();
                DecimalFormat df =new DecimalFormat(".0000");
                if(rs1.next()){
                   LinkedList<Double> GPA1=new LinkedList<>();
                   GPA1.add(Double.valueOf(rs1.getString("gotGPA")));
                   PreparedStatement ps2=Conn.Database.con.prepareStatement("Select gotgpa from catmarks where username=?" );
                   ps2.setString(1,username.getText());
                   ResultSet rs2=ps2.executeQuery();
                   if(rs2.next()){
                       LinkedList<Double> GPA2=new LinkedList<>();
                       GPA2.add(Double.valueOf(rs2.getString("gotGPA")));
                       PreparedStatement ps3=Conn.Database.con.prepareStatement("Select gotgpa from projectsmarks where username=?" );
                       ps3.setString(1,username.getText());
                       ResultSet rs3=ps3.executeQuery();
                       if(rs3.next()){
                           LinkedList<Double> GPA3=new LinkedList<>();
                           GPA3.add(Double.valueOf(rs3.getString("gotGPA")));
                           LinkedList<Double> GPA=new LinkedList<>();
                           GPA.addAll(GPA1);
                           GPA.addAll(GPA2);
                           GPA.addAll(GPA3);
                           int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                           String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                       }else{
                           LinkedList<Double> GPA=new LinkedList<>();
                           GPA.addAll(0,GPA1);GPA.addAll(1,GPA2);int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                          String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                       }
                   }else{
                       PreparedStatement ps3=Conn.Database.con.prepareStatement("Select gotgpa from projectsmarks where username=?" );
                       ps3.setString(1,username.getText());
                       ResultSet rs3=ps3.executeQuery();
                       if(rs3.next()){
                           LinkedList<Double> GPA3=new LinkedList<>();
                           GPA3.add(Double.valueOf(rs3.getString("gotGPA")));
                           LinkedList<Double> GPA=new LinkedList<>();
                           GPA.addAll(0,GPA1);
                           GPA.addAll(1,GPA3);
                           int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                           String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                          }else{
                           LinkedList<Double> GPA=new LinkedList<>();
                           GPA.addAll(0,GPA1);int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                           String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                   }
                }
                }else{
                    PreparedStatement ps2=Conn.Database.con.prepareStatement("Select gotgpa from catmarks where username=?" );
                   ps2.setString(1,username.getText());
                   ResultSet rs2=ps2.executeQuery();
                   if(rs2.next()){
                       LinkedList<Double> GPA2=new LinkedList<>();
                       GPA2.add(Double.valueOf(rs2.getString("gotGPA")));
                       PreparedStatement ps3=Conn.Database.con.prepareStatement("Select gotgpa from projectsmarks where username=?" );
                       ps3.setString(1,username.getText());
                       ResultSet rs3=ps3.executeQuery();
                       if(rs3.next()){
                           LinkedList<Double> GPA3=new LinkedList<>();
                           GPA3.add(Double.valueOf(rs3.getString("gotGPA")));
                           LinkedList<Double> GPA=new LinkedList<>();
                           GPA.addAll(0,GPA2);
                           GPA.addAll(1,GPA3);
                           int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                           String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                       }else{
                           LinkedList<Double> GPA=new LinkedList<>();
                         GPA.addAll(0,GPA2);int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                           String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                       }
                   }else{
                       PreparedStatement ps3=Conn.Database.con.prepareStatement("Select gotgpa from projectsmarks where username=?" );
                       ps3.setString(1,username.getText());
                       ResultSet rs3=ps3.executeQuery();
                       if(rs3.next()){
                           LinkedList<Double> GPA3=new LinkedList<>();
                           GPA3.add(Double.valueOf(rs3.getString("gotGPA")));
                           LinkedList<Double> GPA=new LinkedList<>();
                           GPA.addAll(1,GPA3);
                           int i;double sum=0;
                           for(i = 0; i < GPA.size(); i++)
                               sum += GPA.get(i);
                           String sum1 = df.format(sum);
                           currentgpa.setText(sum1);
                          }else{
                          //no part
                   }
                }
                }
                Conn.Database.disconnectDatabase();
        }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
        }
        }
    
    
    //public void setuserpass(String userpass){this.username.setText(userpass);}
    //public String getuserpass(){return this.username.getText();}
    /*public void setuid(String uidreg){this.uid.setText(uidreg);}
    public String getuid(){return this.uid.getText();}
    
    public void setfaculty(String facultyreg){this.faculty.setText(facultyreg);}
    public String getfaculty(){return this.faculty.getText();}
    
    public void setdepartment(String departmentreg){this.department.setText(departmentreg);}
    public String getdepartment(){return this.department.getText();}
    
    public void setusername(String userreg){this.username.setText(userreg);}
    public String getusername(){return this.username.getText();}*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jTextField8 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jColorChooser1 = new javax.swing.JColorChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        course = new javax.swing.JTextField();
        faculty = new javax.swing.JTextField();
        department = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        currentgpa = new javax.swing.JTextField();
        registrationdate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        note = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Logout6 = new javax.swing.JButton();
        Logout7 = new javax.swing.JButton();
        Logout8 = new javax.swing.JButton();
        Logout9 = new javax.swing.JButton();
        Logout10 = new javax.swing.JButton();
        Search = new javax.swing.JTextField();
        Logout11 = new javax.swing.JButton();
        changeprofile = new javax.swing.JButton();
        userphoto = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        uid = new javax.swing.JTextField();
        hrtype = new javax.swing.JTextField();
        examsquick = new javax.swing.JButton();
        marksquick = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchresults = new javax.swing.JTextArea();
        searchlabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        searchlist = new javax.swing.JList<>();
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/University-of-Moratuwa-Sumanadasa-Building.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel15.setText("jLabel15");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Your Basic Details ");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1800, 1000));
        setMinimumSize(new java.awt.Dimension(1800, 1000));
        setPreferredSize(new java.awt.Dimension(1800, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Basic Details");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 280, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("UID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 160, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("HR Type");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 160, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Faculty");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 160, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Department");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 160, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Course");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 160, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Email Address");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 160, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Current GPA");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 160, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Registration Date");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 125, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Special Notes");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, 160, 20));

        course.setEditable(false);
        course.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseActionPerformed(evt);
            }
        });
        getContentPane().add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 400, 25));

        faculty.setEditable(false);
        faculty.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        getContentPane().add(faculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 400, 25));

        department.setEditable(false);
        department.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        getContentPane().add(department, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 400, 25));

        email.setEditable(false);
        email.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 350, 400, 25));

        currentgpa.setEditable(false);
        currentgpa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        currentgpa.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(currentgpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 400, 25));
        getContentPane().add(registrationdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 150, -1));

        note.setEditable(false);
        note.setColumns(20);
        note.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        note.setLineWrap(true);
        note.setRows(5);
        note.setBorder(null);
        jScrollPane1.setViewportView(note);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 400, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/University_of_Moratuwa_logo.png"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 10, -1, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("University of Moratuwa");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 50, 350, 60));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Wisdom is all wealth");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 100, -1, 20));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(" All Rights Reserved by UoM");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 900, 160, -1));

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

        Logout11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Logout11.setForeground(new java.awt.Color(51, 51, 255));
        Logout11.setText("Search");
        Logout11.setBorder(null);
        Logout11.setBorderPainted(false);
        Logout11.setContentAreaFilled(false);
        Logout11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout11ActionPerformed(evt);
            }
        });
        getContentPane().add(Logout11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 350, 70, 25));

        changeprofile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changeprofile.setText("Change your Profile credentials");
        changeprofile.setBorderPainted(false);
        changeprofile.setContentAreaFilled(false);
        changeprofile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changeprofile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeprofileActionPerformed(evt);
            }
        });
        getContentPane().add(changeprofile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 650, 290, 35));

        userphoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userphoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(userphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 400, 190, 200));

        username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        username.setForeground(new java.awt.Color(51, 51, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("username");
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 605, 220, 40));

        clock.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        clock.setText("00:00:00");
        getContentPane().add(clock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 850, 60, -1));

        date.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        date.setText("Date");
        getContentPane().add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1698, 875, 80, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gif 3.gif"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 166, 240, 540));

        uid.setEditable(false);
        uid.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });
        getContentPane().add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 400, 25));

        hrtype.setEditable(false);
        hrtype.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        hrtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrtypeActionPerformed(evt);
            }
        });
        getContentPane().add(hrtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 400, 25));

        examsquick.setBorderPainted(false);
        examsquick.setContentAreaFilled(false);
        examsquick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        examsquick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examsquickActionPerformed(evt);
            }
        });
        getContentPane().add(examsquick, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 55, 25));

        marksquick.setBorderPainted(false);
        marksquick.setContentAreaFilled(false);
        marksquick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        marksquick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marksquickActionPerformed(evt);
            }
        });
        getContentPane().add(marksquick, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 660, 55, 25));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel23.setText("   For more details, go to  EXAMS and/or MARKS");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 660, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel24.setText("* Here, Current GPA represents the GPA of yours in the CURRENT Semester, Note that the FINAL GPA will be displayed in the Special Note Field  ");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel13.setText("(to this application)");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 460, -1, -1));

        searchresults.setEditable(false);
        searchresults.setColumns(20);
        searchresults.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        searchresults.setRows(5);
        jScrollPane2.setViewportView(searchresults);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 740, 700, 110));

        searchlabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        searchlabel.setText("Search Results");
        getContentPane().add(searchlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 700, 140, 30));

        searchlist.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jScrollPane3.setViewportView(searchlist);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 380, 250, 110));

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
        Inquiry i=new Inquiry();
        i.setVisible(true);
        i.setLocation(300, 200);
    }//GEN-LAST:event_Logout8ActionPerformed

    private void Logout9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout9ActionPerformed
        Inquiry i=new Inquiry();
        i.setVisible(true);
        i.setLocation(300, 200);
    }//GEN-LAST:event_Logout9ActionPerformed

    private void Logout10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout10ActionPerformed
        Inquiry i=new Inquiry();
        i.setVisible(true);
        i.setLocation(300, 200);
    }//GEN-LAST:event_Logout10ActionPerformed

    private void Logout11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout11ActionPerformed
      if(Search.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "You have to enter specific keyword to get the results    \n Check and Try again !      ");
      }else{
          try{
             Conn.Database.getDatabaseConnection();
             PreparedStatement ps=Conn.Database.con.prepareStatement("Select * from user where fullname like '%"+Search.getText()+"%' or uid like '%"+Search.getText()+"%'  or username = '"+Search.getText()+"' " );
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 searchresults.setText("");
                 //searchlist.add(rs.getString("username"));
                 //searchresults.setText("Username   : "+rs.getString("username") + "\n" + "Fullname    : "+ rs.getString("Fullname")+"\n"+"HR Type     : "+rs.getString("hrtype")+"\n"
                 //+"Faculty       : "+rs.getString("faculty")+"\n"+"Department : "+rs.getString("department"));
                 //jScrollPane2.setVisible(true);
                 //searchlabel.setVisible(true);
             }
             if(searchresults.getText().isEmpty()){
                 jScrollPane2.setVisible(false);
                 searchlabel.setVisible(false);
                 JOptionPane.showMessageDialog(this, "No Search Results found for the Entered keyword !           ");
             }
            
          }catch(HeadlessException x){
               JOptionPane.showMessageDialog(this, "Error: \n"+x);
          }catch(SQLException ex){
               JOptionPane.showMessageDialog(this, "Error In SQL: \n"+ex);
          }
      }
    }//GEN-LAST:event_Logout11ActionPerformed

    private void courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseActionPerformed
        //no part
    }//GEN-LAST:event_courseActionPerformed

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void hrtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrtypeActionPerformed

    private void jMenuItem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseClicked
        SiteMap s=new SiteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6MouseClicked

    private void jMenuItem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseClicked
        SatelliteMap s=new SatelliteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7MouseClicked

    private void jMenuItem8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseClicked
        library l=new library();
        l.setVisible(true);
        l.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem8MouseClicked

    private void jMenuItem12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MouseClicked
        medicalcentre m=new medicalcentre();
        m.setVisible(true);
        m.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem12MouseClicked

    private void jMenuItem13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MouseClicked
        sportsground s=new sportsground();
        s.setVisible(true);
        s.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem13MouseClicked

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked
        engineering e=new engineering();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseClicked
        architecture a=new architecture();
        a.setVisible(true);
        a.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem10MouseClicked

    private void jMenuItem16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem16MouseClicked
        IT i=new IT();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem16MouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        Inquiry i= new Inquiry();
        i.setVisible(true);
        i.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem14MouseClicked
        Help h=new Help();
        h.setVisible(true);
        h.setLocation(300,80);
    }//GEN-LAST:event_jMenuItem14MouseClicked

    private void jMenuItem15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem15MouseClicked
        int option=JOptionPane.showConfirmDialog(this, "Are you sure to Logout ?         ","Confirm your Decision  ",JOptionPane.YES_NO_CANCEL_OPTION);
        if(option==JOptionPane.YES_OPTION){
            Login l=new Login();
            l.setVisible(true);
            l.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem15MouseClicked

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

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        Marks m=new Marks();
        m.setVisible(true);
        m.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3MouseClicked

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

    private void jMenuItem11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MouseClicked
        Extra x=new Extra();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Help h=new Help();
        h.setVisible(true);
        h.setLocation(300,80);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
       Basics b=new Basics();
       b.setVisible(true);
       b.setLocationRelativeTo(null);
       this.dispose();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void examsquickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examsquickActionPerformed
       Exams e=new Exams();
       e.setVisible(true);
       e.setLocationRelativeTo(null);
       this.dispose();
    }//GEN-LAST:event_examsquickActionPerformed

    private void marksquickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marksquickActionPerformed
      Marks m=new Marks();
      m.setVisible(true);
      m.setLocationRelativeTo(null);
      this.dispose();
    }//GEN-LAST:event_marksquickActionPerformed

    private void changeprofileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeprofileActionPerformed
       Changeprofileframe c=new Changeprofileframe();
       c.setVisible(true);
       c.setLocationRelativeTo(null);
    }//GEN-LAST:event_changeprofileActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
       int option=JOptionPane.showConfirmDialog(this, "Are you sure to Logout ?         ","Confirm your Decision  ",JOptionPane.YES_NO_CANCEL_OPTION);
        if(option==JOptionPane.YES_OPTION){
            Login l=new Login();
            l.setVisible(true);
            l.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
         Help h=new Help();
        h.setVisible(true);
        h.setLocation(300,80);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       Inquiry i= new Inquiry();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
         engineering e=new engineering();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
      architecture a=new architecture();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
         IT i=new IT();
        i.setVisible(true);
        i.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        SiteMap s=new SiteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
          SatelliteMap s=new SatelliteMap();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        library l=new library();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        medicalcentre m=new medicalcentre();
        m.setVisible(true);
        m.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        sportsground s=new sportsground();
        s.setVisible(true);
        s.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        Extra x=new Extra();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Basics b=new Basics();
       b.setVisible(true);
       b.setLocationRelativeTo(null);
       this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Marks m=new Marks();
      m.setVisible(true);
      m.setLocationRelativeTo(null);
      this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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

    private void SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchFocusGained
       searchresults.setText("");
    }//GEN-LAST:event_SearchFocusGained

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
                 searchresults.setText("Username   : "+rs.getString("username") + "\n" + "Fullname    : "+ rs.getString("Fullname")+"\n"+"HR Type     : "+rs.getString("hrtype")+"\n"
                 +"Faculty       : "+rs.getString("faculty")+"\n"+"Department : "+rs.getString("department"));
                 jScrollPane2.setVisible(true);
                 searchlabel.setVisible(true);
                 searchresults.requestFocus();
             }
             if(searchresults.getText().isEmpty()){
                 jScrollPane2.setVisible(false);
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
            java.util.logging.Logger.getLogger(Basics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Basics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Basics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Basics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Basics().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Logout10;
    private javax.swing.JButton Logout11;
    private javax.swing.JButton Logout6;
    private javax.swing.JButton Logout7;
    private javax.swing.JButton Logout8;
    private javax.swing.JButton Logout9;
    private javax.swing.JTextField Search;
    private javax.swing.JButton changeprofile;
    private javax.swing.JLabel clock;
    private javax.swing.JTextField course;
    private javax.swing.JTextField currentgpa;
    private javax.swing.JLabel date;
    private javax.swing.JTextField department;
    private javax.swing.JTextField email;
    private javax.swing.JButton examsquick;
    private javax.swing.JTextField faculty;
    private javax.swing.JTextField hrtype;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JButton marksquick;
    private javax.swing.JTextArea note;
    private com.toedter.calendar.JDateChooser registrationdate;
    private javax.swing.JLabel searchlabel;
    private javax.swing.JList<String> searchlist;
    private javax.swing.JTextArea searchresults;
    private javax.swing.JTextField uid;
    private javax.swing.JLabel username;
    private javax.swing.JLabel userphoto;
    // End of variables declaration//GEN-END:variables
}
