package Textile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Agent extends JFrame implements ActionListener {
    private JLabel l1, l2, l3, l4, l5; // Added l5 declaration
    private JTextField tf1, tf2, tf3, tf4; // Added tf4 declaration
    private JButton bt1, bt2;

    public Agent() {
        super("Agent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.5);
        int height = (int) (screenSize.getHeight() * 0.8);
        setSize(width, height);
        setLocationRelativeTo(null);

        try {
            ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Textile/resource/m.jpg"));
            JLabel backgroundLabel = new JLabel(backgroundImage);
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(backgroundLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Font font = new Font("Arial", Font.BOLD, 25);

        l1 = new JLabel("Enter Customer Details");
        l1.setFont(new Font("Arial", Font.BOLD, 40));
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("Agent ID");
        l3 = new JLabel("Agent Name");
        l4 = new JLabel("Agent Location");
        l5 = new JLabel("Order ID"); // Added label for order id

        tf1 = new JTextField(20);
        tf2 = new JTextField(20);
        tf3 = new JTextField(20);
        tf4 = new JTextField(20); // Added text field for order id

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
        addFormField(formPanel, gbc, l5, tf4); // Added field for order id

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
                int agentId = Integer.parseInt(tf1.getText());
                String agentName = tf2.getText();
                String agentLocation = tf3.getText();
                int orderId = Integer.parseInt(tf4.getText()); // Updated variable name
                
                // Validate input
                if (agentId <= 0 || orderId <= 0) {
                    JOptionPane.showMessageDialog(null, "Agent ID and Order ID must be positive integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Database connection and insertion code
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile2", "root", "Mk8618245968");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO agent (agent_id, agent_name, agent_location, order_id) VALUES (?, ?, ?, ?)")) {
                    stmt.setInt(1, agentId);
                    stmt.setString(2, agentName);
                    stmt.setString(3, agentLocation);
                    stmt.setInt(4, orderId);
                    stmt.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Successfully Inserted");
                setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Agent ID and Order ID must be integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == bt2) {
            JOptionPane.showMessageDialog(null, "Cancelled.....");
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Agent();
    }
}
