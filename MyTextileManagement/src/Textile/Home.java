package Textile;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {

    JLabel l1;

    Home() {
        super("Welcome to Textile System");

        // Set the window to fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("textile/icon/M.gif"));
        Image i3 = i2.getImage().getScaledInstance(1600, 690, Image.SCALE_DEFAULT);
        ImageIcon icc = new ImageIcon(i3);

        l1 = new JLabel(icc);
        add(l1);

        JMenuBar mb = new JMenuBar();
        Font font = new Font("Arial", Font.BOLD, 25);

        JMenu master = new JMenu("Orders");
        master.setForeground(Color.BLUE);
        master.setFont(font);

        JMenuItem addOrder = new JMenuItem("Add Order");
        addOrder.addActionListener(this);
        addOrder.setFont(font);

        JMenuItem deleteOrder = new JMenuItem("Delete");
        deleteOrder.addActionListener(this);
        deleteOrder.setFont(font);

        JMenuItem showOrders = new JMenuItem("Orders"); // Updated text
        showOrders.addActionListener(this);
        showOrders.setFont(font);

        master.add(addOrder);
        master.add(deleteOrder);
        master.add(showOrders);

        JMenu productMenu = new JMenu("Product");
        productMenu.setForeground(Color.BLUE);
        productMenu.setFont(font);

        JMenuItem addProduct = new JMenuItem("Add Product");
        addProduct.addActionListener(this);
        addProduct.setFont(font);

        JMenuItem showProduct = new JMenuItem("Products"); // Updated text
        showProduct.addActionListener(this);
        showProduct.setFont(font);

        productMenu.add(addProduct);
        productMenu.add(showProduct);

        JMenu customerMenu = new JMenu("Customer");
        customerMenu.setForeground(Color.BLUE);
        customerMenu.setFont(font);

        JMenuItem addCustomer = new JMenuItem("Add Customer");
        addCustomer.addActionListener(this);
        addCustomer.setFont(font);

        JMenuItem showCustomer = new JMenuItem("Customers"); // Updated text
        showCustomer.addActionListener(this);
        showCustomer.setFont(font);

        customerMenu.add(addCustomer);
        customerMenu.add(showCustomer);

        JMenu agentMenu = new JMenu("Agent");
        agentMenu.setForeground(Color.BLUE);
        agentMenu.setFont(font);

        JMenuItem addAgent = new JMenuItem("Add Agent");
        addAgent.addActionListener(this);
        addAgent.setFont(font);

        JMenuItem showAgent = new JMenuItem("Agents"); // Updated text
        showAgent.addActionListener(this);
        showAgent.setFont(font);

        agentMenu.add(addAgent);
        agentMenu.add(showAgent);

        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.BLUE);
        exit.setFont(font);

        JMenuItem ex = new JMenuItem("Exit");
        ex.addActionListener(this);
        ex.setFont(font);

        exit.add(ex);

        mb.add(master);
        mb.add(productMenu);
        mb.add(customerMenu);
        mb.add(agentMenu);
        mb.add(exit);

        setJMenuBar(mb);
        add(l1);
    }

    public void actionPerformed(ActionEvent e) {
        String comnd = e.getActionCommand();

        if (comnd.equals("Add Order")) {
            new Orders().setVisible(true);
        } else if (comnd.equals("Delete")) {
            new Delete_order().setVisible(true);
        } else if (comnd.equals("Orders")) { // Updated command
            new Show_Orders().setVisible(true);
        } else if (comnd.equals("Add Product")) {
            new Product().setVisible(true);
        } else if (comnd.equals("Products")) { // Updated command
            new Show_Product().setVisible(true);
        } else if (comnd.equals("Add Customer")) {
            new Customer().setVisible(true);
        } else if (comnd.equals("Customers")) { // Updated command
            new Show_Customers().setVisible(true);
        } else if (comnd.equals("Add Agent")) {
            new Agent().setVisible(true);
        } else if (comnd.equals("Agents")) { // Updated command
            new Show_Agent().setVisible(true);
        } else if (comnd.equals("Exit")) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, comnd + " clicked.");
        }
    }

    public static void main(String args[]) {
        new Home().setVisible(true);
    }
}
