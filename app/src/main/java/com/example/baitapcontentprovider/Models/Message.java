package com.example.baitapcontentprovider.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Message implements Serializable {
    private String name;
    private  String messenger;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public Message(String name, String messenger) {
        this.name = name;
        this.messenger = messenger;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tên:" + name + "\nMessenger: " + messenger;
    }
}
