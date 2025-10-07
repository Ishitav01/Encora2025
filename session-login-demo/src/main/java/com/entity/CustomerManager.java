package com.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_manager")
public class CustomerManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerid;

    private String name;

    private String username;

    private String password;

    private int loggedin; // 0 = logged out, 1 = logged in

    // --- Getters & Setters ---
    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(int loggedin) {
        this.loggedin = loggedin;
    }

    // equals & hashCode
    @Override
    public int hashCode() {
        return Objects.hash(customerid, name, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CustomerManager other)) return false;
        return customerid == other.customerid &&
               Objects.equals(name, other.name) &&
               Objects.equals(username, other.username);
    }
}
