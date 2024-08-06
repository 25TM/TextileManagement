package Textile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Delete_order extends JFrame implements ActionListener {
    private JLabel l1, l2;
    private JTextField tf1;
    private JButton bt1;

    public Delete_order() {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.4);
        int height = (int) (screenSize.getHeight() * 0.4);
        setSize(width, height);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.BOLD, 25);

        l1 = new JLabel("Enter Order ID to Delete");
        l1.setFont(new Font("Arial", Font.BOLD, 40));
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("");
        l2.setFont(font);
        l2.setHorizontalAlignment(JLabel.CENTER);

        tf1 = new JTextField(20);
        tf1.setFont(font);
        tf1.setHorizontalAlignment(JTextField.CENTER);
        tf1.setPreferredSize(new Dimension(150, 40)); // Adjusted size

        bt1 = new JButton("Delete");
        bt1.setFont(font);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.addActionListener(this);

        l1.setForeground(Color.RED);
        l2.setForeground(Color.BLUE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(l1, gbc);

        JPanel formPanel = new JPanel(new GridBagLayout());
        addFormField(formPanel, gbc, l2, tf1);

        gbc.gridy++;
        panel.add(formPanel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(bt1);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, JLabel label, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            try {
                int id = Integer.parseInt(tf1.getText());

                // Database connection and deletion code
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile2", "root", "Mk8618245968");
                     Statement stmt = conn.createStatement()) {
                    String query = "DELETE FROM orders WHERE order_id=" + id;

                     stmt.executeUpdate(query);
                }

                JOptionPane.showMessageDialog(null, "Successfully Deleted");
                setVisible(false);
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Delete_order();
    }
}
