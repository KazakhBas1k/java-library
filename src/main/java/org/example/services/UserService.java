package org.example.services;

import org.example.Book;
import org.example.Customer;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    private final String storage = "storage/users.txt";
    public UserService() {}
    public void createCustomer(Customer customer) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(storage, true))) {
            printWriter.println(this.getStringedCustomer(customer));
        } catch (FileNotFoundException exception) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    public Customer getUserByUsername(String username) {
        List<Customer> users = this.getAllCustomers();
        int index = users.size() + 1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                index = i;
            }
        }
        return users.get(index);
    }

    public boolean isNameValid(String username) {
        List<Customer> users = this.getAllCustomers();
        boolean isValid = true;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                isValid = false;
            }
        }
        return isValid;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(storage))) {
            String userLine = scanner.nextLine();
            String[] userInfo = userLine.split(", ");
            Customer user = new Customer(userInfo[0], userInfo[1]);
            users.add(user);
        } catch (FileNotFoundException exception) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, exception);
        }
        return users;
    }

    public void setBookToCart(Customer customer, Book book) {
        if (!Customer.cart.getBooks().contains(book)) {
            Customer.cart.addBook(book);
        } else {
            JOptionPane.showMessageDialog(null, "This book is already added!");
        }
    }

    public void removeBookFromCart(Customer customer, Book book) {
        Customer.cart.removeBook(book);
    }

    public String getStringedCustomer(Customer customer) {
        return customer.getUsername() + ", " + customer.getPassword() + ", " + customer.getRole();
    }
}
