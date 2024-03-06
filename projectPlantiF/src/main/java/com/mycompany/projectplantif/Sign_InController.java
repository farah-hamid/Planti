/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author pro
 */
public class Sign_InController {

     private Scene scene;
     private Stage stage;
     private Parent root;

     @FXML
     private TextField Namefield;

     @FXML
     private TextField EmailFeild;

     @FXML
     private TextField passWordFiled;

     @FXML
     private DatePicker DOB_fieled;
     @FXML
     private Label msglable;

     @FXML
     private void handleGoToLoginButtonClick(ActionEvent event) {

          String name = Namefield.getText();
          String email = EmailFeild.getText();
          String password = passWordFiled.getText();
          LocalDate date = DOB_fieled.getValue();
          
          
         

          if (validateInputs(name, email, password, date)) {

               User user = new User();
               
               user.setName(name);
               user.setEmail(email);
               user.setPassword(password);
               user.setDateOfBirth(date);

               Session session = HibernateUtil.getSessionFactory().openSession();
               Transaction tx = session.beginTransaction();
//insert new student
               session = HibernateUtil.getSessionFactory().openSession();
               tx = session.beginTransaction();
               int sId2 = (Integer) session.save(user);
               tx.commit();
               session.close();

               FXMLLoader loader = new FXMLLoader(getClass().getResource("Log_In.fxml"));
               stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               try {
                    root = loader.load();
               } catch (IOException ex) {
                    ex.printStackTrace();
               }
               scene = new Scene(root);
               stage.setScene(scene);
               stage.show();
          } else {
               // Display an error message or handle invalid inputs
          }

     }

     @FXML
     private void handleShowSignUpButtonClick(ActionEvent event) {

//           try {
//            App.setRoot("Sign_In");
//        } catch (IOException e) {
//            // Handle the exception appropriately
//        }
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Log_In.fxml"));
          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          try {
               root = loader.load();
          } catch (IOException ex) {
               ex.printStackTrace();
          }
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
     }

     private boolean validateInputs(String name, String email, String password, LocalDate date) {
          if (name.isEmpty() || email.isEmpty() || password.isEmpty() || date == null) {
               // Fields are empty
               msglable.setText("please make sure to fill all information");
               return false;
          }

          // Validate email format
          String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
          if (!email.matches(emailRegex)) {
               // Invalid email format
               msglable.setText("please make to enter valid Email");
               return false;
          }

          // Validate password criteria
          if (password.length() < 8) {
               // Password is too short
               msglable.setText("Password must be 8 digits or more ");
               return false;
          }

          // Perform additional validation as needed
          return true; // Inputs are valid
     }
}
