package com.example.baitapcontentprovider.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tên: " + name + "\nPhone: " + phoneNumber;
    }
}
