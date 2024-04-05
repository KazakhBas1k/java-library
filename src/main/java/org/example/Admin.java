package org.example;

import org.example.enums.Role;
import org.example.services.CatalogService;

public class Admin extends User {
    private CatalogService catalogService;
    public static Catalog catalog;

    public Admin() {
        super("admin", "12312301", Role.ADMIN);
        catalogService = new CatalogService();
        catalog = catalogService.getCatalogFromStorage();
    }

    public void addBook(Book book) {
        catalog.addBook(book);
        this.catalogService.updateCatalog(catalog);
    }

    public void editBook(Book selectedBook, Book newBook) {
        catalog.edit(selectedBook, newBook);
        this.catalogService.updateCatalog(catalog);
    }

    public void deleteBook(Book book) {
        catalog.deleteBook(book);
        this.catalogService.updateCatalog(catalog);
    }
}
