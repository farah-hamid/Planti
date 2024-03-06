/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shahad Bandar
 */
public class PaymentVerificationFXMLController implements Initializable {

     @FXML
     private Button backbutton2;
     @FXML
     private Button button3;
     @FXML
     private Parent root;
     @FXML
     private Stage stage;
     @FXML
     private Button exportBt;

     private Scene scene;

     /**
      * Initializes the controller class.
      */
     @Override
     public void initialize(URL url, ResourceBundle rb) {

     }

     @FXML
     private void backbutton2(ActionEvent event) throws IOException {
//       root = FXMLLoader.load(getClass().getResource("/Screens/PaymentVerificationFXML.fxml"));  
//      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentFXML.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
     }

     @FXML
     private void backtohome(ActionEvent event) throws IOException {
//          root = FXMLLoader.load(getClass().getResource("/Screens/PaymentVerificationFXML.fxml"));
//          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

          Cart cart = Cart.getCart();
          cart.getCartItems().clear();

          FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
     }

//     private void exportToFile(String content) {
//          FileChooser fileChooser = new FileChooser();
//     
//
//          // Set extension filter
//          FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
//          fileChooser.getExtensionFilters().add(extFilter);
//
//          // Show save file dialog
//          File file = fileChooser.showSaveDialog(null);
//
//          if (file != null) {
//               try (PrintWriter writer = new PrintWriter(file)) {
//                    writer.write(content);
//                    System.out.println("File exported successfully!");
//               } catch (IOException ex) {
//                    System.err.println("Error exporting file: " + ex.getMessage());
//               }
//          }
//     }
     private void exportToFile(String content) throws FileNotFoundException {
          FileChooser fileChooser = new FileChooser();

           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.txt");
          fileChooser.getExtensionFilters().add(extFilter);
          // Show save file dialog
          File file = fileChooser.showSaveDialog(null);

         try (PrintWriter writer = new PrintWriter(file)) {
                    writer.write(content);
                 
               } catch (IOException ex) {
                
               }

     }

     private String userDetails() {
          User user = User.getUser();
          Cart cart = Cart.getCart();

          return "User: \n" + user.getName() + "\nOrder: " + cart.toString() + "\nThank You";
     }

     @FXML
     private void clickBtAction(ActionEvent e) throws FileNotFoundException {
          exportToFile(userDetails());
     }
}
