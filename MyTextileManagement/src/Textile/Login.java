package Textile;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5;
    JTextField tf1;
    JPasswordField pf1;
    JButton bt1, bt2, btSignUp; // Added signUp button
    JPanel p1, p2;
    Font f1, f2;

    Login() {
        super("Login");
        setLocation(50, 50);
        setSize(800, 400); // Increased size
        setResizable(false);

        f1 = new Font("Arial", Font.BOLD, 24); // Increased font size
        f2 = new Font("Arial", Font.BOLD, 18); // Increased font size

        l1 = new JLabel("Welcome To Textile Management System ");
        l2 = new JLabel("UserName");
        l3 = new JLabel("Password");

        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);

        l1.setForeground(Color.RED);
        l2.setForeground(Color.BLUE);
        l3.setForeground(Color.BLUE);

        tf1 = new JTextField(20); // Set preferred width
        pf1 = new JPasswordField(20); // Set preferred width

        tf1.setFont(f2);
        pf1.setFont(f2);

        bt1 = new JButton("Login");
        bt2 = new JButton("Cancel");
        btSignUp = new JButton("Sign Up"); // Added sign-up button

        // Set preferred size for buttons
        bt1.setPreferredSize(new Dimension(120, 40));
        bt2.setPreferredSize(new Dimension(120, 40));
        btSignUp.setPreferredSize(new Dimension(120, 40)); // Set preferred size for sign-up button

        bt1.setBackground(Color.BLACK);
        bt2.setBackground(Color.BLACK);
        btSignUp.setBackground(Color.BLACK); // Set background color for sign-up button

        bt1.setForeground(Color.WHITE);
        bt2.setForeground(Color.WHITE);
        btSignUp.setForeground(Color.WHITE); // Set foreground color for sign-up button
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource(""));
        Image img2 = img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);

        l4 = new JLabel(img3);
        l1.setHorizontalAlignment(JLabel.CENTER);
        
        p1 = new JPanel(new GridBagLayout()); // Using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        p1.add(l2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        p1.add(tf1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        p1.add(l3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        p1.add(pf1, gbc);

        // Use FlowLayout for button panel
        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Added gap between buttons
        p2.add(bt1);
        p2.add(bt2);
        p2.add(btSignUp); // Added sign-up button to panel

        setLayout(new BorderLayout(30, 30));

        add(l1, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH); // Add button panel to the SOUTH

        add(l4, BorderLayout.EAST);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        btSignUp.addActionListener(this); // Add action listener for sign-up button
    }

    public void actionPerformed(ActionEvent e) {
        String user = tf1.getText();
        String pass = pf1.getText();
        
        if (e.getSource() == bt1) {
            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "SELECT * FROM login WHERE username='" + user + "' AND password='" + pass + "'";

                ResultSet rest = obj.stm.executeQuery(q);
                if (rest.next()) {
                    // Open Home frame
                    Home homeFrame = new Home();
                    homeFrame.setVisible(true);
                    System.out.println("Open");
                    
                    // Close Login frame
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        }
        else if (e.getSource() == btSignUp) {
            // Open sign-up frame
            SignUp signUpFrame = new SignUp();
            signUpFrame.setVisible(true);
            // Close login frame
            this.dispose();
        }
    }

    public static void main(String args[]) {
        new Login().setVisible(true);
    }
}
