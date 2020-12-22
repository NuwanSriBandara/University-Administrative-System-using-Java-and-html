/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    
    public static String url="jdbc:mysql://localhost:3307/university";
    public static String user="root";
    public static String password="123";
    public static Connection con=null;
    
    public static Connection getDatabaseConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            try{
            con=DriverManager.getConnection(url,user,password);
            //JOptionPane.showMessageDialog(null,"System Connected !");
            }catch(Exception x){
                JOptionPane.showMessageDialog(null,"No Access to database !");
                System.exit(0);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"No Access to System !");
            System.exit(0);
        }
        return con;
    }
    public static void disconnectDatabase(){
        try{
            con.close();
            //JOptionPane.showMessageDialog(null,"System Disconnected !");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error: \n"+ex);
        }
    }
    
    
}
