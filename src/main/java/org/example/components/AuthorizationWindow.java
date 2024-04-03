package org.example.components;

import org.example.Catalog;

import javax.swing.*;

public class AuthorizationWindow extends JFrame {
    public AuthorizationWindow() {
        setTitle("Authorization");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton openRegister = new JButton("Register");
        JButton openLogin = new JButton("Login");

        openRegister.addActionListener(e -> {
            RegistrationWindow registrationWindow = new RegistrationWindow();
            registrationWindow.setVisible(true);
            dispose();
        });

        openLogin.addActionListener(e -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
            dispose();
        });

        panel.add(openRegister);
        panel.add(openLogin);
        add(panel);
    }
}