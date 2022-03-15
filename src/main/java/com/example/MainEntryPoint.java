package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainEntryPoint {
    public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        AppSystem app = new AppSystem();
        CartSystem cart = new CartSystem();
        TheSystem sys = new TheSystem() {
            @Override
            public void display() throws FileNotFoundException {

            }
        };
        Integer choice = 0;
        while (choice != 7) {
            menu(); // Choose an action:
            choice = reader.nextInt();
            switch (choice) {
                case 1: // 1. Add item to System
                    Item item = new Item();
                    reader.nextLine();
                    System.out.print("\nEnter the item name:\n");
                    item.setItemName(reader.nextLine());
                    System.out.print("\nEnter a description for the item:\n");
                    item.setItemDesc(reader.nextLine());
                    System.out.print("\nEnter the item's price:\n");
                    item.setItemPrice(reader.nextDouble());
                    System.out.print("\nEnter the quantity available in the System:\n");
                    item.setAvailableQuantity(reader.nextInt());
                    System.out.println(item.getItemName());
                    if (app.add(item)) {
                        System.out.println("Item successfully added");
                    } else {
                        System.out.println("Try Again");
                    }
                    break;
                case 2: // 2. Add item to Cart
                    app.display();
                    System.out.println("Enter the name of the item");
                    reader.nextLine();
                    String item_name = reader.nextLine();
                    //in this case item_name is the key element in HashMap itemCollection
                    //HashMap.get() targets a key element
                    Item item1 = app.getItemCollection().get(item_name);
                    if (app.checkAvailability(item1))
                        if (cart.add(item1)) {
                            app.reduceAvailableQuantity(item_name);
                            System.out.println("Item successfully added");
                        } else {
                            System.out.println("Invalid or Unavailable Item, Please Try Again");
                        }
                    break;
                case 3: // 3. Display Cart
                    cart.display();
                    break;
                case 4: // 4. Display System
                    app.display();
                    break;
                case 5: // 5. Remove item from Cart
                    cart.display();
                    System.out.println("Enter the name of the item");
                    reader.nextLine();
                    item_name = reader.nextLine();
                    if (cart.remove(item_name) != null) {
                        System.out.println(item_name + " was removed from the cart");
                    } else {
                        System.out.println("Invalid Item, Please Try Again");
                    }
                    break;
                case 6: // 6. Remove item from System
                    app.display();
                    System.out.println("Enter the name of the item");
                    reader.nextLine();
                    item_name = reader.nextLine();
                    if (app.remove(item_name) != null) {
                        System.out.println(item_name + " was removed from the System");
                        if (cart.remove(item_name) != null) {
                            System.out.println(item_name + " was also removed from the cart");
                        }
                    } else {
                        System.out.println("Invalid Item, Please Try Again");
                    }

                    break;
                case 7: // 7. Quit
                    System.out.println("\nByyyeee!!");
                    break;
            }
        }
        reader.close();
    }

    public static void menu() {
        System.out.println("Choose an action:");
        System.out.println("1. Add item to System");
        System.out.println("2. Add item to Cart");
        System.out.println("3. Display Cart");
        System.out.println("4. Display System");
        System.out.println("5. Remove item from Cart");
        System.out.println("6. Remove item from System");
        System.out.println("7. Quit");
    }
}
