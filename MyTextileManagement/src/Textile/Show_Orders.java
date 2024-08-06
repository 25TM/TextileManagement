package Textile;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Show_Orders extends JFrame {
    JButton bt1; // Corrected variable name
    JPanel p1, p2, p3;
    JLabel l1, l2;
    JTextField tf1;
    Font f1, f2;
    String x[] = {"Order ID", "Order Location", "Order Date", "Agent ID"}; // Updated column names
    String y[][] = new String[20][4]; // Adjusted column count
    int i = 0;

    Show_Orders() {
        super("Show Orders"); // Updated title
        setLocation(50, 50);

       
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
            String q = "select * from orders"; // Updated table name to `order`
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                y[i][0] = rest.getString("order_id"); // Updated column index
                y[i][1] = rest.getString("order_location"); // Updated column index
                y[i][2] = rest.getString("order_date"); // Updated column index
                y[i][3] = rest.getString("agent_id"); // Updated column index
                i++;
            }
            JTable t = new JTable(y, x);
            t.setFont(f1);
            t.setBackground(Color.WHITE);
            t.setForeground(Color.BLUE);
            JScrollPane sp = new JScrollPane(t);
            add(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Show_Orders().setVisible(true);
    }
}
