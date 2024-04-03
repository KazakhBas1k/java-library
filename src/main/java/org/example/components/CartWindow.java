package org.example.components;

import org.example.Book;
import org.example.Customer;
import org.example.services.UserService;

import javax.swing.*;
import java.util.List;

public class CartWindow extends JFrame {
    private DefaultListModel<Book> bookListModel;
    private JList<Book> bookList;
    private JButton removeButton;
    private JButton closeButton;
    private JButton buyButton;
    private static Customer customer;
    public UserService userService;

    public CartWindow(Customer customer) {
        this.userService = new UserService();
        CatalogWindow.customer = customer;

        setTitle("Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        initComponents(Customer.cart.getBooks());
        setVisible(true);
    }

    private void initComponents(List<Book> books) {
        JPanel panel = new JPanel();

        bookListModel = new DefaultListModel<>();
        books.forEach(bookListModel::addElement);
        bookList = new JList<>(bookListModel);

        JScrollPane scrollPane = new JScrollPane(bookList);
        panel.add(scrollPane);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            if (selectedBook != null) {
                bookListModel.removeElement(selectedBook);
                this.userService.removeBookFromCart(customer, selectedBook);
            }
        });
        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            dispose();
        });

        buyButton = new JButton("Buy");

        panel.add(removeButton);
        panel.add(closeButton);
        panel.add(buyButton);
        add(panel);
    }
}
