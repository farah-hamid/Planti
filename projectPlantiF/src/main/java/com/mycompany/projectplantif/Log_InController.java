/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Log_InController {

     private Scene scene;
     private Stage stage;
     private Parent root;

     @FXML
     private Label errorLabel;
     @FXML
     private TextField namefield;

     @FXML
     private TextField passwordfield;

     @FXML
     private void handleGoToLoginButtonClick(ActionEvent event) {

          String name = namefield.getText();
          String password = passwordfield.getText();

          if (validateInput(name, password)) {
               // Perform sign-in logic
//               try {
//                    App.setRoot("Log_In");
//               } catch (IOException e) {
//                    // Handle the exception appropriately
//               }
               FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
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
               // Display an error message or handle invalid input
          }

     }

     @FXML
     private void handleShowSignUpButtonClick(ActionEvent event) {

//           try {
//            App.setRoot("Sign_In");
//        } catch (IOException e) {
//            // Handle the exception appropriately
//        }
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign_In.fxml"));
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

     @FXML
     private void signInButtonClicked(ActionEvent event) {
          String name = namefield.getText();
          String password = passwordfield.getText();

          if (validateInput(name, password)) {
               // Perform sign-in logic
//               try {
//                    App.setRoot("Log_In");
//               } catch (IOException e) {
//                    // Handle the exception appropriately
//               }
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
               // Display an error message or handle invalid input
          }
     }

     private boolean validateInput(String name, String password) {

          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction tx = session.beginTransaction();
          session = HibernateUtil.getSessionFactory().openSession();
          List<User> sList = null;
          String queryStr = "from User";
          Query query = session.createQuery(queryStr);
          sList = query.list();
          tx.commit();
          session.close();

          int index = -1;
          for (int i = 0; i < sList.size(); i++) {
               if (sList.get(i).getName().equals(name)) {
                    index = i;
               }
          }

          if (name.isEmpty() || password.isEmpty()) {
               // Fields are empty
               errorLabel.setText("PLease enter UserName and password");
               return false;
          } // Additional validation criteria
          else if (password.length() < 8) {
               // Password is too short
               errorLabel.setText("Password must be 8 digits or more ");
               return false;
          } // Add more validation rules as needed
          // Input is valid

          if (index == -1 || !sList.get(index).getName().equals(name) || !sList.get(index).getPassword().equals(password)) {

               errorLabel.setText("Username or Password is Incorrect  Please Try Again");
               return false;
          } else {

               User user = User.getUser();

               user.setUserId(sList.get(index).getUserId());
               user.setName(sList.get(index).getName());
               user.setEmail(sList.get(index).getEmail());
               user.setDateOfBirth(sList.get(index).getDateOfBirth());
               user.setPassword(sList.get(index).getPassword());
               errorLabel.setText("");
               return true;
          }

     }

}
