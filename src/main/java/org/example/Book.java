package org.example;

import org.example.enums.Genre;
import org.example.models.Readable;

public class Book implements Readable {
    private String title;
    private String author;
    private double price;
    private Genre genre;

    public Book(String title, String author, Genre genre, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }
    public Genre getGenre() { return genre; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return this.title + " by " + this.author + " (" + this.genre + ") - $" + this.price;
    }
}
