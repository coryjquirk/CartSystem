package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class TheSystem {
   File file = new File("resources/sample.txt");
    String item_name;
    HashMap<String, Item> itemCollection = new HashMap<>();
    HashMap<String, Item> shoppingCart = new HashMap<>();
    HashMap<String, Double> subTotals = new HashMap<>();
    HashMap<String, Integer> cartQuantity = new HashMap<>();
    TheSystem() throws FileNotFoundException {
        // Your code here
        if (getClass().getSimpleName().equals("AppSystem")){
            Scanner sc = new Scanner(file);
            String delimiters = "  ";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                //splits up line into strings where there are two spaces.
                String[] strArray = line.split(delimiters);
                Item newItem = new Item();
                item_name = strArray[0];
                //fills out blank newItem's values w/ the arrays of
                //strings by index for each line of the .txt file
                newItem.setItemName(strArray[0]);
                newItem.setItemDesc(strArray[1]);
                newItem.setItemPrice(Double.parseDouble(strArray[2]));
                newItem.setAvailableQuantity(parseInt(strArray[3]));
                itemCollection.put(item_name, newItem);
            }
            sc.close();
        }
        //initializes the itemCollection variable with empty HashMap.
        //then checks if AppSystem is invoking constructor
    }

    public void setItemCollection(HashMap<String, Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

        //item name is key, value is corresponding item object
    public HashMap<String, Item> getItemCollection() {
        // Your code here
//        System.out.println("TheSystem.getItemCollection() is called");

        // provides the list of items in the system or the cart
        // depending on which class initiates
        return this.itemCollection;
    }
    
    public Boolean checkAvailability(Item item) {
        // takes item object as parameter, then checks if item.getQuantity() is
        // greater than or equal to item.getAvailableQuantity
        System.out.println("item.getAvailableQuantity() "+item.getAvailableQuantity());
        if (item.getQuantity() >= item.getAvailableQuantity()){
            System.out.println("Item doesn't exist in our system.");
        } else {
            return true;
        }
        return false;
    }
    
    public Boolean add(Item item) {
        // This method takes item object as a parameter checks
        // If item is null then false
        if (item == null) {
            return false;
        }
        if (this.itemCollection.containsKey(item.getItemName())) {
            // 2. if already in collection, displays a message:
            // "[item name] is already in the App system" and returns false
            item.setQuantity(item.getQuantity() + 1);
            System.out.println(item.getItemName() + " is already in the App system");
            return true;
        } else if (!this.itemCollection.containsKey((item.getItemName()))){
            // 3. if item not in collection, method adds
            //          item to collection & returns true
            item.setQuantity(1);
            getItemCollection().put(item.getItemName(), item);
            return true;
        }
        return false;
    }

    public Item remove(String itemName) {
        // Your code here
        // This method takes String itemName as a parameter,
        if (itemCollection.containsKey(itemName)){
            // checks if the item is in the collection,
            // if it is then removes it and Returns the
            // item object being removed.

            Item itemForRemoval = itemCollection.get(itemName);
            itemCollection.remove(itemName);
            return itemForRemoval;
        }
        // If it is not in the collection then returns null.
        return null;
    }

    public abstract void display() throws FileNotFoundException;
}
