/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class representing payment information, mapped to the "payment_info" table in the database.
 */
@Entity
public class Payment implements Serializable {

    // Primary key for the payment_info table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Fields representing payment information
    @Column(name = "name",length = 45)
    private String name;
    
    @Column(name = "card_number",length = 16)
    private String cardNumber; 
    
    @Column(name = "cvv",length = 3)
    private String cvv;     
    
    @Column(name = "expiry_date",length = 5)    
    private String expiryDate;  

    

    /**
     * Default constructor (empty).
     */
    public Payment() {
    }

    /**
     * Parameterized constructor for creating a paymentBOJO object with provided information.
     *
     * @param name        Name on the payment card
     * @param cardNumber  Payment card number
     * @param cvv         Card Verification Value (CVV)
     * @param expiryDate  Expiry date of the payment card
     */
    public Payment(String name, String cardNumber, String cvv, String expiryDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    

    /**
     * Getter for retrieving the Payment object's ID.
     *
     * @return The ID of the Payment object.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for setting the ID of the Payment object.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for retrieving the name on the payment card.
     *
     * @return The name on the payment card.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for setting the name on the payment card.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for retrieving the payment card number.
     *
     * @return The payment card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Setter for setting the payment card number.
     *
     * @param cardNumber The card number to set.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Getter for retrieving the Card Verification Value (CVV).
     *
     * @return The CVV.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Setter for setting the Card Verification Value (CVV).
     *
     * @param cvv The CVV to set.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Getter for retrieving the expiry date of the payment card.
     *
     * @return The expiry date.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Setter for setting the expiry date of the payment card.
     *
     * @param expiryDate The expiry date to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Overriding toString method for better representation

    /**
     * Overridden toString method to represent the Payment object as a string.
     *
     * @return A string representation of the Payment object.
     */
    @Override
    public String toString() {
        return "PaymentInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}