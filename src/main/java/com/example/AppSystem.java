package com.example;

import java.io.FileNotFoundException;

public class AppSystem extends TheSystem {
    AppSystem() throws FileNotFoundException {
    }

    @Override
    public void display() throws FileNotFoundException {
        // takes no parameter and displays every item in App system
        // your output should be formatted as follows:

        // header "AppSystem Inventory:" followed by the flowing columns.
        // 1. Name: itemName, displayed as a left-justified string
        //          within a 20-char field
        // 2. Description: itemDesc, displayed as a left-justified
        //          string within 20-char field
        // 3. Price: itemPrice, displayed as left-justified
        //          floating-point # w/ 2 decimal places in 10-char field
        // 4. Available Quantity: availableQuantity, displayed
        //          as decimal # within 10-char field.
        // each column must be properly labeled, please see example below.

        System.out.println("AppSystem Inventory:");
        System.out.printf("%-20s %-20s %-10s %-10s%n", "Name", "Description", "Price", "Available Quantity");

        getItemCollection().entrySet().forEach(entry -> {
            System.out.format("%-20s %-20s %-10.2f %-10d%n", entry.getValue().getItemName(),
                    entry.getValue().getItemDesc(), entry.getValue().getItemPrice(),
                    entry.getValue().getAvailableQuantity());
        });

    }

    @Override      // this overwrites the parents class add method 
    public Boolean add(Item item) {
        // takes item object as a parameter, checks:
        // 1. if item is null, then returns false
        if (item == null) {
            return false;
        }
        if (this.itemCollection.containsKey(item.getItemName())) {
            // 2. if already in collection, displays a message:
            // "[item name] is already in the App system" and returns false
            System.out.println(item.getItemName() + " is already in the App system");
            return false;
        } else if (!this.itemCollection.containsKey((item.getItemName()))) {
            // 3. if item not in collection, method adds
            //          item to collection & returns true
            item.setQuantity(1);
            getItemCollection().put(item.getItemName(), item);
            return true;
        }
        return false;
    }

    public Item reduceAvailableQuantity(String item_name) {
        if (getItemCollection().containsKey(item_name)) {
            Item item = getItemCollection().get(item_name);
            item.setAvailableQuantity(item.getAvailableQuantity() - 1);

            if (item.getAvailableQuantity() == 0) {
                remove(item_name);
            }
            return item;
        } else if (!getItemCollection().containsKey(item_name)) {
            return null;
        }
        // then decreases available quantity of item in
        //          system by 1 and returns item object.
        // if item is not in collection, returns null
        return null;
    }
}
