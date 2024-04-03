package org.example.components;

import org.example.Customer;
import org.example.services.UserService;

import javax.swing.*;

public class RegistrationWindow extends JFrame {
    private UserService userService;
    public RegistrationWindow() {
        this.userService = new UserService();
        setTitle("Registration");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Write name:");
        JTextField nameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Write password:");
        JTextField passwordField = new JTextField(15);
        JButton registerButton = new JButton("Registration");
        JButton goToLogin = new JButton("Already have an account?");
        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(goToLogin);

        goToLogin.addActionListener(e-> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
            dispose();
        });

        registerButton.addActionListener(e -> {
            String username = nameField.getText();
            String password = passwordField.getText();
            if (this.userService.isNameValid(username)) {
                Customer customer = new Customer(username, password);
                this.userService.createCustomer(customer);
                JOptionPane.showMessageDialog(null, "User " + username + " welcome!");
                CatalogWindow catalogWindow = new CatalogWindow(customer);
                catalogWindow.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Username is already used!");
            }
        });

        add(panel);
    }
}
