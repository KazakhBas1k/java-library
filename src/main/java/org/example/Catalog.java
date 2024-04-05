package org.example;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private static List<Book> books;

    public Catalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void edit(Book selectedBook, Book newBook) {
        int index = books.indexOf(selectedBook);
        books.set(index, newBook);
    }

    public void deleteBook(Book book) {books.remove(book);}

    public List<Book> getBooks() {
        return books;
    }
}
