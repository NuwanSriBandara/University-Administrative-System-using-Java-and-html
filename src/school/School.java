package school;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class School {
    
    public static int UID;  public static javax.swing.JLabel clock; public static int h,m,s,d,M,Y; public static javax.swing.Timer t; public static ImageIcon image1; public static String USERNAME;
    public static String sectionname;

    public static void main(String[] args) {
       
        UIManager.put("Menu.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("MenuItem.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.messageFont",new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont",new Font("Segoe UI", Font.PLAIN, 12));
       
        Login log=new Login();
        log.setVisible(true);
        log.setLocationRelativeTo(null);
        
        
    }
     
}
