package org.example.components;

import org.example.Book;
import org.example.Catalog;
import org.example.Customer;
import org.example.enums.Genre;
import org.example.services.CatalogService;
import org.example.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogWindow extends JFrame {
    private JList<Book> bookList;
    public static Customer customer;
    private final UserService userService;
    public JTextField searchField;
    public JComboBox filterComboBox;
    public JButton sortButton;
    public DefaultListModel<Book> bookListModel;
    public List<Book> allBooks;

    public CatalogWindow(Customer customer) {
        CatalogWindow.customer = customer;
        CatalogService catalogService = new CatalogService();
        Catalog catalog = catalogService.getCatalogFromStorage();
        this.userService = new UserService();
        setTitle("Catalog");
        setSize(1100, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents(catalog.getBooks());
        setVisible(true);
    }
    private void initComponents(List<Book> books) {
        allBooks = books;
        JPanel panel = new JPanel();

        bookListModel = new DefaultListModel<>();
        books.forEach(bookListModel::addElement);
        bookList = new JList<>(bookListModel);

        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        JScrollPane scrollPane = new JScrollPane(bookList);
        panel.add(scrollPane);

        JButton addToCartButton = new JButton("Add to Cart");
        JButton openCart = new JButton("Open Cart");
        JButton logout = new JButton("Logout");

        searchField = new JTextField();
        searchField.addActionListener(e -> searchBooks());
        controlPanel.add(searchField);

        filterComboBox = new JComboBox<>(Genre.values());
        filterComboBox.addActionListener(e -> filterBooks());
        controlPanel.add(filterComboBox);

        sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> sortBooks());
        controlPanel.add(sortButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        addToCartButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            if (selectedBook != null) {
                int option = JOptionPane.showConfirmDialog(this, "Add '" + selectedBook.getTitle() + "' to cart?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    this.userService.setBookToCart(customer, selectedBook);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to add to cart.");
            }
        });

        openCart.addActionListener(e -> {
            CartWindow cartWindow = new CartWindow(customer);
            cartWindow.setVisible(true);
        });

        logout.addActionListener(e -> {
            AuthorizationWindow authorizationWindow = new AuthorizationWindow();
            authorizationWindow.setVisible(true);
            dispose();
        });


        panel.add(addToCartButton);
        panel.add(openCart);
        panel.add(logout);

        add(panel);
    }
    private void searchBooks() {
        String searchText = searchField.getText().toLowerCase();
        List<Book> filteredBooks =allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchText) || book.getAuthor().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        updateBookList(filteredBooks);
    }

    private void filterBooks() {
        Genre selectedGenre = (Genre) filterComboBox.getSelectedItem();
        if (selectedGenre == Genre.ALL) {
            updateBookList(allBooks);
        } else {
            List<Book> filteredBooks =allBooks.stream()
                    .filter(book -> book.getGenre() == selectedGenre)
                    .collect(Collectors.toList());
            updateBookList(filteredBooks);
        }
    }

    private void sortBooks() {
        List<Book> sortedBooks =allBooks.stream()
                .sorted((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()))
                .collect(Collectors.toList());
        updateBookList(sortedBooks);
    }

    private void updateBookList(List<Book> books) {
        bookListModel.clear();
        books.forEach(bookListModel::addElement);
    }
}
