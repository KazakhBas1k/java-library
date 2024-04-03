package org.example.components;

import org.example.Customer;
import org.example.User;
import org.example.services.UserService;

import javax.swing.*;

public class LoginWindow extends JFrame {
    private UserService userService;
    public LoginWindow() {
        this.userService = new UserService();
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Write name:");
        JTextField nameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Write password:");
        JTextField passwordField = new JTextField(15);
        JButton loginButton = new JButton("Login");
        JButton goToRegister = new JButton("No account?");
        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(goToRegister);

        goToRegister.addActionListener(e-> {
            RegistrationWindow registrationWindow = new RegistrationWindow();
            registrationWindow.setVisible(true);
            dispose();
        });
        loginButton.addActionListener(e -> {
            String username = nameField.getText();
            String password = passwordField.getText();
            User userByUsername = this.userService.getUserByUsername(username);
            if (userByUsername.getPassword().equals(password)) {
                Customer customer = new Customer(username, password);
                JOptionPane.showMessageDialog(null, "User " + username + " welcome!");
                CatalogWindow catalogWindow = new CatalogWindow(customer);
                catalogWindow.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Password");
            }
        });

        add(panel);
    }
}
