package Textile;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Show_Product extends JFrame {
    String x[] = { "Product ID", "Product Name", "Price", "Quantity", "Order ID" }; // Updated column names
    JPanel p1, p2, p3;
    Font f1;
    String y[][] = new String[20][5]; // Adjusted array size
    int i = 0, j = 0;
    JTable t;

    Show_Product() {
        super("Show Product");
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
            String q = "SELECT id, pname, price, qty, orderid FROM products"; // Updated SQL query
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                y[i][j++] = rest.getString("id");
                y[i][j++] = rest.getString("pname");
                y[i][j++] = rest.getString("price");
                y[i][j++] = rest.getString("qty");
                y[i][j++] = rest.getString("orderid"); // Updated column name
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
        new Show_Product().setVisible(true);
    }
}