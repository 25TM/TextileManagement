package Textile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Orders extends JFrame implements ActionListener {
    private JLabel l1, l2, l3, l4, l5; // Added l5 declaration
    private JTextField tf1, tf2, tf3, tf4; // Added tf4 declaration
    private JButton bt1, bt2;

    public Orders() {
        super("Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set size to cover slightly less than the whole screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.5);
        int height = (int) (screenSize.getHeight() * 0.8);
        setSize(width, height);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.BOLD, 25);

        l1 = new JLabel("Enter Order Details");
        l1.setFont(new Font("Arial", Font.BOLD, 40));
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("Order ID");
        l3 = new JLabel("Order Location");
        l4 = new JLabel("Order Date");
        l5 = new JLabel("Agent ID"); // Added label for Agent ID

        tf1 = new JTextField(20);
        tf2 = new JTextField(20);
        tf3 = new JTextField(20);
        tf4 = new JTextField(20); // Added text field for Agent ID

        bt1 = new JButton("Save");
        bt2 = new JButton("Cancel");

        l2.setFont(font);
        l3.setFont(font);
        l4.setFont(font);
        l5.setFont(font);

        bt1.setFont(font);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);

        bt2.setFont(font);
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.WHITE);

        tf1.setFont(font);
        tf2.setFont(font);
        tf3.setFont(font);
        tf4.setFont(font);

        l1.setForeground(Color.RED);
        l2.setForeground(Color.BLUE);
        l3.setForeground(Color.BLUE);
        l4.setForeground(Color.BLUE);
        l5.setForeground(Color.BLUE);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel formPanel = new JPanel(new GridBagLayout());

        addFormField(formPanel, gbc, l2, tf1);
        addFormField(formPanel, gbc, l3, tf2);
        addFormField(formPanel, gbc, l4, tf3);
        addFormField(formPanel, gbc, l5, tf4); // Added field for Agent ID

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(l1, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(formPanel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(bt1);
        buttonPanel.add(bt2);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        panel.add(textField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            try {
                int id = Integer.parseInt(tf1.getText());
                String location = tf2.getText();
                String date = tf3.getText();
                int agentId = Integer.parseInt(tf4.getText()); // Parse Agent ID

                // Database connection and insertion code
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile2", "root", "Mk8618245968");
                     Statement stmt = conn.createStatement()) {
                    String query = "INSERT INTO orders (order_id, order_location, order_date, agent_id) VALUES ('" + id + "', '" + location + "', '" + date + "', '" + agentId + "')";

                    stmt.executeUpdate(query);
                }

                JOptionPane.showMessageDialog(null, "Successfully Inserted");
                setVisible(false);
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == bt2) {
            JOptionPane.showMessageDialog(null, "Cancelled.....");
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Orders();
    }
} 