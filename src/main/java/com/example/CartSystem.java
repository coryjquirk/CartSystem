package com.example;

import java.io.FileNotFoundException;
import java.util.Set;

public class CartSystem extends TheSystem {
    CartSystem() throws FileNotFoundException {
    }
    @Override
    public void display() throws FileNotFoundException {
        // takes no parameter, displays every item in cart system,
        // along with the sub-total, tax, and total.

        Set<String> cartKeys = shoppingCart.keySet();

        System.out.println("Cart:");
        System.out.printf("%-20s %-20s %-10s %-10s %-10s%n", "Name", "Description", "Price", "Quantity", "Sub Total");
        Double preTaxTotal = 0.0;
        Double itemPrice = 0.0;
        Double itemPriceOps = 0.0;
        Double tax = 0.0;
        Double total = 0.0;
        for ( String key : cartKeys ) {
            Item itemOps = shoppingCart.get(key);
            itemPrice += itemOps.getItemPrice();
            System.out.printf("%-20s %-20s %-10.2f %-10d %-10.2f%n", shoppingCart.get(key).getItemName(),
                    shoppingCart.get(key).getItemDesc(), itemPrice, cartQuantity.get(key), subTotals.get(key));
            preTaxTotal += itemPrice;
        }
        System.out.printf("%-20s %-20.2f%n", "Pre-tax Total", preTaxTotal);
        tax = preTaxTotal*0.05;
        System.out.printf("%-20s %-20.2f%n", "Tax", tax);
        total = (preTaxTotal+tax);
        System.out.printf("%-20s %-20.2f%n", "Total", total);

    }
    @Override
    public Boolean add(Item item){

        if (item != null){
            String item_name = item.getItemName();
            //add that item to cart object.
            //if item is already in cart, add
            if (shoppingCart.containsKey(item_name)) {
                System.out.println("adding item to cart when it's already in there");
                subTotals.put(item_name, (subTotals.get(item_name) + item.getItemPrice()));
                cartQuantity.put(item_name, (cartQuantity.get(item_name) + 1));
                item.setQuantity(item.getQuantity()+1);
                System.out.println("item.getQuantity() after cart-add op "+item.getQuantity());
            } else {
                System.out.println("item to cart for first time");
                shoppingCart.put(item_name, item);
                subTotals.put(item_name, item.getItemPrice());
                //don't need to use setter because default item.quantity is 1
                cartQuantity.put(item_name, 1);
            }
            return true;
        }
        return false;
    }
    @Override
    public Item remove(String item_name){
        if (shoppingCart.containsKey(item_name)){
            Item itemForRemoval = itemCollection.get(item_name);
            shoppingCart.remove(item_name);
            subTotals.remove(item_name);
            return itemForRemoval;
        } else {
            return null;
        }
    }
}
