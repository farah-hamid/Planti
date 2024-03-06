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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author shahad Bandar
 */
public class PaymentFXMLController implements Initializable {

     @FXML
     private Button bukebutton3;
     @FXML
     private MenuButton menu2;
     @FXML
     private MenuItem homepage2;
     @FXML
     private MenuItem profile2;
     @FXML
     private MenuItem cart2;
     @FXML
     private Label tcost;
     @FXML
     private Button button2;
     @FXML
     private TextField name;
     @FXML
     private TextField cardnum;
     @FXML
     private TextField cvv;
//     @FXML
//     private TextField endofexpires;
     // @FXML
     // private Parent root;
     // @FXML
     // private Stage stage;
     @FXML
     private Label errorLabel;
     @FXML
     private ChoiceBox<String> choiceBox;
     @FXML
     private DatePicker datePicker;

     private Scene scene;
     private Stage stage;
     private Parent root;


     /**
      * Initializes the controller class.
      */
     @Override
     public void initialize(URL url, ResourceBundle rb) {
          choiceBox.getItems().addAll("Home", "Profile", "Cart", "Log Out");

          choiceBox.setStyle("-fx-font-family: 'Niramit SemiBold';");

          choiceBox.setOnAction(e -> {
               if (choiceBox.getValue().equals("Profile")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    try {
                         root = loader.load();
                    } catch (IOException ex) {
                         ex.printStackTrace();
                    }
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               } else if (choiceBox.getValue().equals("Log Out")) {
                    // to clear the cart
                    Cart cart = Cart.getCart();
                    cart.getCartItems().clear();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("onBoardingScreen.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    try {
                         root = loader.load();
                    } catch (IOException ex) {
                         ex.printStackTrace();
                    }
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

               } else if (choiceBox.getValue().equals("Cart")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    try {
                         root = loader.load();
                    } catch (IOException ex) {
                         ex.printStackTrace();
                    }
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               } else if (choiceBox.getValue().equals("Home")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    try {
                         root = loader.load();
                    } catch (IOException ex) {
                         ex.printStackTrace();
                    }
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               }

          });
          Cart cart = Cart.getCart();
          double deliveryCost = 10.0;
          double subtotal = cart.calculateTotal();
          double finalTotal = subtotal + deliveryCost;
          tcost.setText("Total Cost: $" + finalTotal);
          tcost.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold'; ");

     }

     @FXML
     private void back2(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderFXML.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     /*    @FXML
    private void menu2(ActionEvent event) throws IOException {
        if (event.getSource() == homepage2 || event.getSource() == profile2 || event.getSource() == cart2) {
            navigateTo2("PaymentFXML.fxml");
        }
    }*/
 /*private void navigateTo2(String fxmlPath) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) menu2.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Order");
        stage.setScene(scene);
        stage.show();
}*/
     // @FXML
     /* private void paynow(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentVerificationFXML.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     
    }*/
     @FXML
     private void textname(ActionEvent event) {
     }

     @FXML
     private void textcardnum(ActionEvent event) {
     }

     @FXML
     private void textcvv(ActionEvent event) {
     }

     @FXML
     private void textendodexpires(ActionEvent event) {
     }

     public void setTcost(String value) {
          tcost.setText(value);
     }

     @FXML
     private void paynow(ActionEvent event) throws IOException {
          
          
          // Validate name
          String nameValue = name.getText().trim();
          // Validate card number
         String  cardNumberValue = cardnum.getText().trim();
          // Validate CVV
          String cvvValue = cvv.getText().trim();
          // Validate expiry date
         LocalDate expiryDateValue = datePicker.getValue();
          // Validate input before proceeding
          if (validateInput(nameValue,cardNumberValue,cvvValue,expiryDateValue)) {

               
               Payment payment = new Payment();
               // payment.setId(0);
               payment.setName(nameValue);
               payment.setCardNumber(cardNumberValue);
               payment.setCvv(cvvValue);
               payment.setExpiryDate(expiryDateValue.toString());

               Session session = HibernateUtil.getSessionFactory().openSession();
               Transaction tx = session.beginTransaction();
//insert new student
               session = HibernateUtil.getSessionFactory().openSession();
               tx = session.beginTransaction();
               int sId2 = (Integer) session.save(payment);
               tx.commit();
               session.close();

               FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentVerificationFXML.fxml"));
               root = loader.load();

               stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
          }
     }

     
     @FXML
     private boolean validateInput(String nameValue,String cardNumberValue,String cvvValue,LocalDate expiryDateValue) {
          // Reset error label
          errorLabel.setText("");


          LocalDate now = LocalDate.now();

          if (nameValue.isEmpty() || !nameValue.matches("[a-zA-Z]+")) {
               errorLabel.setText("Please enter a valid name with letters only.");
               return false;
          } 
          if (cardNumberValue.isEmpty() || !cardNumberValue.matches("\\d{16}")) {
               errorLabel.setText("Please enter a valid 16-digit card number.");
                return false;
          } 
          if (cvvValue.isEmpty() || !cvvValue.matches("\\d{3}")) {
               errorLabel.setText("Please enter a valid 3-digit CVV.");
                return false;
          }  if (expiryDateValue == null || expiryDateValue.isBefore(now)) {
               errorLabel.setText("Please enter a valid expiry date in MM/YY format.");
                return false;
          } else {
               return true;
          }

//          // Additional validation rules can be added here
//          if (!errorLabel.getText().isEmpty()) {
//               // If there is an error, show and enable the label
//               errorLabel.setDisable(false);
//               errorLabel.setVisible(true);
//               return false;
//          }
//
//          // If no error, hide and disable the label
//          errorLabel.setDisable(true);
//          errorLabel.setVisible(false);
//
//          return true; // Input is valid
     }
}
