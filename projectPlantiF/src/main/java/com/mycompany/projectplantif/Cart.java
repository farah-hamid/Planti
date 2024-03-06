/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author farah
 */
public class Cart {

     // static because the cart needs to be accessed in difftrent classes
     private static Cart CART;

     /**
      * create a new cart if there isn't one yet or return the existing cart
      *
      * @return static CART
      */
     public static Cart getCart() {
          if (CART == null) {
               CART = new Cart();
          }
          return CART;
     }

     private ArrayList<CartItem> cartItems;

     /**
      * Class constructor
      */
     public Cart() {
          this.cartItems = new ArrayList<>();
     }

     /**
      *
      * @return ArrayList of cartItem
      */
     public ArrayList<CartItem> getCartItems() {
          return cartItems;
     }

     /**
      * this method adds the specified product to the list
      *
      * @param product
      */
     public void addProduct(Product product) {
          int index = this.getIndex(product);
          if (index == -1) { // create a new product
               CartItem cartItem = new CartItem(product, 1);
               cartItems.add(cartItem);
          } else {
               this.cartItems.get(index).incrementQuantity();
          }

     }

     /**
      * this method removes the specified product from the list
      *
      * @param product
      */
     public void deleteProduct(Product product) {
          int index = this.getIndex(product);
          if (index != -1) { // if equles -1 it means the product is not in the list 
               this.cartItems.remove(this.cartItems.get(index));
          }

     }

     /**
      * this method uses the getQuantity method from the CartItem class to get
      * the number of product quantity
      *
      * @param product
      * @return number of product quantity
      */
     public int getQuantity(Product product) {
          int index = this.getIndex(product);
          if (index != -1) { // if equles -1 it means the product is not in the list 
               return this.cartItems.get(this.getIndex(product)).getQuantity();
          } else {
               return 0;
          }

     }

     /**
      * this method uses the setQuantity method from the CartItem class to set
      * the number of product quantity
      *
      * @param product
      * @param quantity
      */
     public void setQuantity(Product product, int quantity) {
          int index = this.getIndex(product);
          if (index != -1) {
               this.cartItems.get(this.getIndex(product)).setQuantity(quantity);
          }

     }

     /**
      * this method uses the getPrice method from the product class to get the
      * product price and the getQuantity from the CartItem class to calculate
      * the total price
      *
      * @return the total of products price
      */
     public float calculateTotal() {
          float total = 0;
          for (int i = 0; i < this.cartItems.size(); i++) {
               total += (this.cartItems.get(i).getProduct().getPrice()) * this.cartItems.get(i).getQuantity();
          }
          return total;
     }

     /**
      * this method search for the specified product in the ArrayList return the
      * index of the product if it is in the list or -1 if it is not
      *
      * @param product
      * @return index of the product if found, if not return -1
      * @ return index of the product
      */
     public int getIndex(Product product) {
          for (int i = 0; i < this.cartItems.size(); i++) {
               if (product.getName().equalsIgnoreCase(this.cartItems.get(i).getProduct().getName())) {
                    return i;
               }
          }
          return -1;
     }

     /**
      *
      * this method sort the arrayList based on the user preference
      *
      * @param sortType
      */
     public void sortList(String sortType) {
          // https://springjavatutorial.medium.com/sorting-arraylist-of-objects-in-java-b021d93ebbfe
          Comparator<CartItem> compareByName = Comparator.comparing(CartItem::getProductName);
          Comparator<CartItem> compareByPrice = Comparator.comparing(CartItem::getProductPriceInCart);

          switch (sortType) {

               case "A-Z":
                    cartItems.sort(compareByName);
                    break;
               case "Lowest Price":
                    cartItems.sort(compareByPrice);
                    break;
               case "Z-A":
                    cartItems.sort(compareByName);
                    Collections.reverse(cartItems);
                    break;
               case "Highest Price":
                    cartItems.sort(compareByPrice);
                    Collections.reverse(cartItems);
                    break;
               default:
                    break;
          }
     }



     @Override
     public String toString() {
          String s = "";
          for (int i = 0; i < cartItems.size(); i++) {
               s = s +"\n" +"product:"+ cartItems.get(i).toString();
          }
          return s;
     }

}
