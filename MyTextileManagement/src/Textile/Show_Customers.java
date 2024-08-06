package Textile;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Show_Customers extends JFrame {
    String x[] = { "Customer ID", "Customer Name", "Product ID", "Quantity", "Bill Amount" }; // Column names
    JPanel p1, p2, p3;
    Font f1;
    String y[][] = new String[20][5]; // Array size adjusted
    int i = 0, j = 0;
    JTable t;

    Show_Customers() {
        super("Show Customers");
        // Set size to half the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.8);
        int height = (int) (screenSize.getHeight() * 0.5);
        setSize(width, height);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        f1 = new Font("MS UI Gothic", Font.BOLD, 20);

        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT customer_id, customer_name, product_id, quantity, bill_amount FROM customers"; // Updated SQL query
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                y[i][j++] = rest.getString("customer_id");
                y[i][j++] = rest.getString("customer_name");
                y[i][j++] = rest.getString("product_id");
                y[i][j++] = rest.getString("quantity");
                y[i][j++] = rest.getString("bill_amount"); // Added column for bill amount
                i++;
                j = 0;
            }
            t = new JTable(y, x);
            t.setFont(f1);
            t.setBackground(Color.white);
            t.setForeground(Color.BLUE); // Set foreground color for text
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(t);
        add(sp);
    }

    public static void main(String args[]) {
        new Show_Customers().setVisible(true);
    }
}
