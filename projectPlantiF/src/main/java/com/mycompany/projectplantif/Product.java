/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
/**
 *
 * @author farah
 */
@Entity
public class Product implements java.io.Serializable {

    @Id
    @Column(name = "id")
     private int id;
   
    @Column(name = "name")
     private String name;
    
    @Column(name = "image")
     private String image;
    
    @Column(name = "price")
     private float price;

     /**
      * Class constructor
      */
     public Product() {
     }

     /**
      * Class constructor
      *
      * @param name
      * @param image
      * @param price
      */
     public Product(String name, String image, float price) {
          this.name = name;
          this.image = image;
          this.price = price;
     }

     /**
      *
      * @return the id of the product
      */
     public int getId() {
          return id;
     }

     /**
      *
      * @return the image of the product
      */
     public String getImage() {
          return image;
     }

     /**
      *
      * @return the price of the product
      */
     public float getPrice() {
          return price;
     }

     /**
      *
      * @return the name of the product
      */
     public String getName() {
          return name;
     }

     /**
      * set the id
      *
      * @param id
      */
     public void setId(int id) {
          this.id = id;
     }

     /**
      * set the image
      *
      * @param image
      */
     public void setImage(String image) {
          this.image = image;
     }

     /**
      * set the price
      *
      * @param price
      */
     public void setPrice(float price) {
          this.price = price;
     }

     /**
      * set the name
      *
      * @param name
      */
     public void setName(String name) {
          this.name = name;
     }

}
