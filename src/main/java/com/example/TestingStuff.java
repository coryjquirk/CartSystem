package com.example;

import java.io.FileNotFoundException;

public class TestingStuff {

    public static void main(String[] args) throws FileNotFoundException {
        AppSystem app = new AppSystem();
        CartSystem cart = new CartSystem();
//        Item item1 = app.getItemCollection().get(item_name);
        //this prints the array of objectjumblestrings
        System.out.println(app.getItemCollection().toString());
        //this prints key/value pair for each entry.
        app.itemCollection.entrySet().forEach(entry -> {
            System.out.println("key: "+entry.getKey() + " value: " + entry.getValue().getItemName());
        });
        app.display();
    }
}
