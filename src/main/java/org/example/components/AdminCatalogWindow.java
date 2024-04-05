package org.example.components;

import org.example.Admin;
import org.example.Book;
import org.example.Catalog;
import org.example.Customer;
import org.example.enums.Action;
import org.example.enums.Genre;
import org.example.services.CatalogService;
import org.example.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.stream.Collectors;

public class AdminCatalogWindow extends JFrame {
    private JList<Book> bookList;
    public List<Book> allBooks;
    public static Admin admin;
    public DefaultListModel<Book> bookListModel;
    public JButton deleteButton;
    public JButton editButton;
    public JButton addButton;
    public AdminCatalogWindow(Admin admin) {
        AdminCatalogWindow.admin = admin;
        setTitle("Catalog");
        setSize(1100, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents(Admin.catalog.getBooks());
        setVisible(true);
    }
    private void initComponents(List<Book> books) {
        allBooks = books;
        JPanel panel = new JPanel();

        bookListModel = new DefaultListModel<>();
        books.forEach(bookListModel::addElement);
        bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);
        panel.add(scrollPane);

        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        deleteButton = new JButton("Delete book");
        editButton = new JButton("Edit Book");
        addButton = new JButton("Add book");
        deleteButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            if (selectedBook != null) {
                int option = JOptionPane.showConfirmDialog(this, "Do you want to delete " + selectedBook.getTitle() + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    bookListModel.removeElement(selectedBook);
                    admin.deleteBook(selectedBook);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to delete.");
            }
        });
        addButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            BookAddEditWindow bookAddEditWindow = new BookAddEditWindow(Action.ADD, admin, selectedBook);
            bookAddEditWindow.setVisible(true);
        });

        editButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            if (selectedBook != null) {
                int option = JOptionPane.showConfirmDialog(this, "Do you want to edit?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    BookAddEditWindow bookAddEditWindow = new BookAddEditWindow(Action.EDIT, admin, selectedBook);
                    bookAddEditWindow.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to edi.");
            }
        });

        controlPanel.add(deleteButton);
        controlPanel.add(addButton);
        controlPanel.add(editButton);

        panel.add(controlPanel);
        add(panel);
    }
}
