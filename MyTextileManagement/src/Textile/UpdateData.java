package Textile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateData extends JFrame implements ActionListener {
    private JButton agentButton, productButton, orderButton, customerButton;

    public UpdateData() {
        super("Update Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set size to cover slightly less than the whole screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.5);
        int height = (int) (screenSize.getHeight() * 0.5);
        setSize(width, height);
        setLocationRelativeTo(null);

        agentButton = new JButton("Agent");
        productButton = new JButton("Product");
        orderButton = new JButton("Order");
        customerButton = new JButton("Customer");

        agentButton.addActionListener(this);
        productButton.addActionListener(this);
        orderButton.addActionListener(this);
        customerButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(agentButton);
        panel.add(productButton);
        panel.add(orderButton);
        panel.add(customerButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == agentButton) {
            new Show_Agent().setVisible(true);
        } else if (e.getSource() == productButton) {
            new Show_Product().setVisible(true);
        } else if (e.getSource() == orderButton) {
            new Show_Orders().setVisible(true);
        } else if (e.getSource() == customerButton) {
            new Show_Customers().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateData());
    }
}
