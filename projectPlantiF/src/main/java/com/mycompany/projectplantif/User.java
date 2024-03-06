/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class User implements java.io.Serializable {

     private static User USER;

     public static User getUser() {
          if (USER == null) {
               USER = new User();
          }

          return USER;
     }

     @Id
     @Column(name = "UserID")
     private int userId;

     @Column(name = "Name")
     private String name;

     @Column(name = "Email")
     private String email;

     @Column(name = "Password")
     private String password;

     @Column(name = "DateOfBirth")
     private LocalDate dateOfBirth;

     // Constructor without parameters
     public User() {
     }

     // Constructor with parameters
     public User(int userId, String name, String email, String password, LocalDate dateOfBirth) {
          this.userId = userId;
          this.name = name;
          this.email = email;
          this.password = password;
          this.dateOfBirth = dateOfBirth;
     }

     // Getters and Setters
     public int getUserId() {
          return userId;
     }

     public void setUserId(int userId) {
          this.userId = userId;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public LocalDate getDateOfBirth() {
          return dateOfBirth;
     }

     public void setDateOfBirth(LocalDate dateOfBirth) {
          this.dateOfBirth = dateOfBirth;
     }

     // Override toString method for easy printing of User object details
     @Override
     public String toString() {
          return "User{"
                  + "userId=" + userId
                  + ", name='" + name + '\''
                  + ", email='" + email + '\''
                  + ", password='" + password + '\''
                  + ", dateOfBirth=" + dateOfBirth
                  + '}';
     }
}
