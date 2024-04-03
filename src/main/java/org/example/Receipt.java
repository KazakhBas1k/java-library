package org.example;

import java.util.List;

public class Receipt {
    private List<Book> books;

    public Receipt(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Book book : books) {
            totalPrice =  totalPrice + book.getPrice();
        }
        return totalPrice;
    }
}
