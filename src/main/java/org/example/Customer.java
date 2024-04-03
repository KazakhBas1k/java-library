package org.example;

import org.example.enums.Role;

public class Customer extends User {
    public static Cart cart;
    public Customer(String username, String password) {
        super(username, password, Role.CUSTOMER);
        cart = new Cart();
    }
}
