package Textile;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField tf1;
    JPasswordField pf1, pf2; // Added another password field for confirmation
    JButton bt1, bt2;
    JPanel p1, p2;
    Font f1, f2;

    SignUp() {
        super("Sign Up");
        setLocation(100, 100);
        setSize(600, 300); // Increased size
        setResizable(false);

        f1 = new Font("Arial", Font.BOLD, 24); // Increased font size
        f2 = new Font("Arial", Font.BOLD, 18); // Increased font size

        l1 = new JLabel("Sign Up");
        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        l4 = new JLabel("Confirm Password");

        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);
        l4.setFont(f2);

        l1.setForeground(Color.RED);
        l2.setForeground(Color.BLUE);
        l3.setForeground(Color.BLUE);
        l4.setForeground(Color.BLUE);

        tf1 = new JTextField(20);
        pf1 = new JPasswordField(20);
        pf2 = new JPasswordField(20);

        tf1.setFont(f2);
        pf1.setFont(f2);
        pf2.setFont(f2);

        bt1 = new JButton("Submit");
        bt2 = new JButton("Cancel");

        bt1.setFont(f2);
        bt2.setFont(f2);

        p1 = new JPanel(new GridLayout(4, 2));
        p1.add(l2);
        p1.add(tf1);
        p1.add(l3);
        p1.add(pf1);
        p1.add(l4);
        p1.add(pf2); // Changed from tf2 to pf2 for password confirmation

        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        p2.add(bt1);
        p2.add(bt2);

        // Add space between border and contents
        p1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        p2.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        setLayout(new BorderLayout(30, 20));

        add(l1, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf1.getText();
            String password = new String(pf1.getPassword());
            String confirmPassword = new String(pf2.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ConnectionClass obj = new ConnectionClass();
                    String q = "INSERT INTO login (username, password) VALUES (?, ?)";
                    
                    PreparedStatement pstmt = obj.con.prepareStatement(q);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to register user!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Cancel",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        SignUp signUp = new SignUp();
        signUp.setVisible(true);
    }
}
