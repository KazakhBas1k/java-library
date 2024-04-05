package org.example.components;

import org.example.Admin;
import org.example.Book;
import org.example.Catalog;
import org.example.enums.Action;
import org.example.enums.Genre;

import javax.swing.*;

public class BookAddEditWindow extends JFrame {
    private JTextField title;
    private JTextField author;
    private JComboBox genre;
    private JTextField price;
    private JButton done;
    private static Admin admin;
    public BookAddEditWindow(Action action, Admin admin, Book selectedBook) {
        BookAddEditWindow.admin = admin;
        setTitle("Catalog");
        setSize(1100, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        title = new JTextField(15);
        author = new JTextField(15);
        genre = new JComboBox<>(Genre.values());
        price = new JTextField(15);
        done = new JButton("Done");
        title.setText(selectedBook.getTitle());
        author.setText(selectedBook.getAuthor());
        genre.setSelectedItem(selectedBook.getGenre());
        price.setText(Double.toString(selectedBook.getPrice()));

        done.addActionListener(e -> {
            String titleInput = title.getText();
            String authorInput = author.getText();
            Genre selectedGenre = (Genre) genre.getSelectedItem();
            String priceInput = price.getText();
            if (action.equals(Action.ADD)) {
                Book book = new Book(titleInput, authorInput, selectedGenre, Double.parseDouble(priceInput));
                admin.addBook(book);
                dispose();
            } else if (action.equals(Action.EDIT)) {
                Book book = new Book(titleInput, authorInput, selectedGenre, Double.parseDouble(priceInput));
                admin.editBook(selectedBook, book);
                dispose();
            }
        });

        panel.add(title);
        panel.add(author);
        panel.add(genre);
        panel.add(price);
        panel.add(done);

        add(panel);
    }
}
