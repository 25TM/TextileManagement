package Textile;

import java.sql.*;

public class ConnectionClass {
    Connection con;
    Statement stm;
    ConnectionClass()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Corrected driver class name
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/textile2", "root", "Mk8618245968");
            stm = con.createStatement();
        } 
        catch(Exception e) 
        {
            e.printStackTrace(); // Print stack trace for any connection errors
        }
    }

    public static void main(String args[]) {
        new ConnectionClass();
    }
}