package org.example.services;

import org.example.Book;
import org.example.Catalog;
import org.example.enums.Genre;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogService {
    public CatalogService() {}
    public Catalog getCatalogFromStorage() {
        Catalog catalog = new Catalog();
        try (Scanner scanner = new Scanner(new FileInputStream("storage/catalog.txt"))) {
            while (scanner.hasNextLine()) {
                String bookLine = scanner.nextLine();
                String[] bookInfo = bookLine.split(", ");
                Genre genre = Genre.valueOf(bookInfo[2]);
                Book book = new Book(bookInfo[0], bookInfo[1], genre, Double.parseDouble(bookInfo[3]));
                catalog.addBook(book);
            }
        } catch (FileNotFoundException exception) {
            Logger.getLogger(CatalogService.class.getName()).log(Level.SEVERE, null, exception);
        }
        return catalog;
    }
}
