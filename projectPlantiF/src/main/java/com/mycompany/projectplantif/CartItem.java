/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

/**
 *
 * @author farah
 */
public class CartItem {

     private Product product;
     private int quantity;

     /**
      * Class constructor
      */
     public CartItem() {
     }

     /**
      * Class constructor
      *
      * @param product
      * @param quantity
      */
     public CartItem(Product product, int quantity) {
          this.product = product;
          this.quantity = quantity;
     }

     /**
      * set the product
      *
      * @param product
      */
     public void setProduct(Product product) {
          this.product = product;
     }

     /**
      * set the quantity
      *
      * @param quantity
      */
     public void setQuantity(int quantity) {
          this.quantity = quantity;
     }

     /**
      *
      * @return product
      */
     public Product getProduct() {
          return product;
     }

     /**
      *
      * @return number of quantity
      */
     public int getQuantity() {
          return quantity;
     }

     /**
      * this method increment the number of product quantity
      */
     public void incrementQuantity() {
          this.quantity++;
     }

     /**
      * this method decrement the number of product quantity
      */
     public void decrementQuantity() {
          if (this.quantity > 0) //check if quantity is bigger than one to avoid negative quantity value 
          {
               this.quantity--;
          }
     }

     /**
      * this method is used in the Cart class to help with sorting the array
      * based on the product name
      *
      * @return the name of the product
      */
     public String getProductName() {
          return this.product.getName();
     }

     /**
      * this method is used in the Cart class to help with sorting the array
      * based on the product price
      *
      * @return the price of the product in the cart
      */
     public float getProductPriceInCart() {
          return this.product.getPrice() * this.getQuantity();
     }

     @Override
     public String toString() {

          return "product: " + product.getName() + " , quantity: " + quantity + " , price: " + getProductPriceInCart();
     }

}
