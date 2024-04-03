package org.example;

import org.example.enums.Role;

public class Admin extends User {
    public Admin() {
        super("admin", "12312301", Role.ADMIN);
    }

    public void addBook(Book book, Catalog catalog) {
        catalog.addBook(book);
    }

    public void editBook(Book book, String newTitle, String newAuthor, double newPrice) {
        book.setPrice(newPrice);
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
    }
}
