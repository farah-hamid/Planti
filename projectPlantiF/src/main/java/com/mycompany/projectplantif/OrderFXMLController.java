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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shahad Bandar
 */
public class OrderFXMLController implements Initializable {

     @FXML
     private Button bakebutton;

     @FXML
     private MenuButton menu;

     @FXML
     private MenuItem homepage;

     @FXML
     private MenuItem profile;

     @FXML
     private MenuItem cart;

     @FXML
     private Label labelprice1;

     @FXML
     private Button newbutton1;

     @FXML
     private Label label1;

     @FXML
     private Button newbutton2;

     @FXML
     private Label labelprice2;

     @FXML
     private Button newbutton3;

     @FXML
     private Label label2;

     @FXML
     private Button newbutton4;

     @FXML
     private Label labelprice3;

     @FXML
     private Button newbutton5;

     @FXML
     private Label label3;

     @FXML
     private Button newbutton6;

     @FXML
     private Button button1;

     //  @FXML
     // private Label subt;
     @FXML
     private Label Delivery;

     @FXML
     private Label total;

     @FXML
     private Label tatalfinal;

     @FXML
     private VBox vbox;

     @FXML
     private ChoiceBox<String> choiceBox;

     private Scene scene;
     private Stage stage;
     private Parent root;

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

          List<CartItem> cartItems = Cart.getCart().getCartItems();

          //////////////////////////////////////////
          //cartVbox.getChildren().clear();
          if (!cartItems.isEmpty()) {

               for (int i = 0; i < cartItems.size(); i++) {

                    StackPane stack = this.orderproduct(cartItems.get(i).getProduct());
                    vbox.getChildren().add(stack);
               }

          }
          Cart cart = Cart.getCart();

          // total.setText("Total: " + String.valueOf(cart.calculateTotal()));
          //total.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold'; ");
          double deliveryCost = 10.0;
          Delivery.setText("Delivery: $" + deliveryCost);
          Delivery.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold';");

          // Calculate final total
          double subtotal = cart.calculateTotal();
          double finalTotal = subtotal + deliveryCost;

          // Set total and tatalfinal labels
          total.setText("Total: $" + subtotal);
          total.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold'; ");
          tatalfinal.setText("Final Total: $" + finalTotal);
          tatalfinal.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold'; ");
     }

     @FXML
     private void pack(ActionEvent event) throws IOException {
          //navigateTo("/PaymentFXML.fxml");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
     }

     @FXML
     private void check(ActionEvent event) throws IOException {
          //navigateTo("/PaymentFXML.fxml");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentFXML.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
     }

     /*  @FXML
     private void menu(ActionEvent event) throws IOException {
          if (event.getSource() == homepage || event.getSource() == profile || event.getSource() == cart) {
               navigateTo("/Screens/PaymentFXML.fxml");
          }
     }

     @FXML
     private void navigateTo(String fxmlPath) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
          Stage stage = (Stage) menu.getScene().getWindow();
          Scene scene = new Scene(root);
          stage.setTitle("Order");
          stage.setScene(scene);
          stage.show();
     }*/

 /*@FXML
     private void updateQuantity(Label quantityLabel, Label priceLabel, int change) {
          int currentQuantity = Integer.parseInt(quantityLabel.getText());
          int newQuantity = Math.max(0, currentQuantity + change);

          quantityLabel.setText(String.valueOf(newQuantity));

          // Update the priceLabel based on the newQuantity
          int newPrice = newQuantity * PRICE_PER_UNIT;
          priceLabel.setText(String.valueOf(newPrice));
     }*/

 /*@FXML
     private void updateSubtotalAndTotal() {
          int price1 = Integer.parseInt(label1.getText()) * PRICE_PER_UNIT;
          int price2 = Integer.parseInt(label2.getText()) * PRICE_PER_UNIT;
          int price3 = Integer.parseInt(label3.getText()) * PRICE_PER_UNIT;

          int subtotal = price1 + price2 + price3;
          subt.setText(String.valueOf("$" + subtotal));

          if (subtotal == 0) {

               total.setText("$0");
          } else {

               int totalValue = subtotal + 10;
               total.setText(String.valueOf("$" + totalValue));
          }

          int deliverytValue = (subtotal == 0) ? 0 : 10;
          deliveryt.setText(String.valueOf("$" + deliverytValue));
     }*/
     private StackPane orderproduct(Product product) {
          Cart cart = Cart.getCart();

          Label productNameLabel = new Label(product.getName());
          productNameLabel.setStyle("-fx-font-size: 15px;-fx-font-family: 'Niramit SemiBold'; ");

          Label noProduct = new Label(String.valueOf(cart.getQuantity(product)));
          noProduct.setStyle("-fx-font-size: 15px;-fx-font-family: 'Niramit SemiBold'; ");

          Label priceLabel = new Label("$" + (product.getPrice() * cart.getQuantity(product)));
          priceLabel.setStyle("-fx-font-size: 15px;-fx-font-family: 'Niramit SemiBold'; ");

          Rectangle backgroundR = new Rectangle(300, 70);
          backgroundR.setStyle("-fx-fill:#A6C39C;-fx-arc-height:20px;-fx-arc-width:20px;");

          Image image = new Image("file:src/main/resources/com/mycompany/projectplantif/" + product.getImage());
          ImageView imageView = new ImageView(image);
          imageView.setFitWidth(130);
          imageView.setFitHeight(130);
          VBox imageVBox = new VBox(imageView);
          imageVBox.setPadding(new Insets(0, 0, 20, 0));

          VBox vbox = new VBox(10, productNameLabel, priceLabel);
          vbox.setAlignment(Pos.CENTER);

          HBox hbox = new HBox(50, vbox, noProduct);

          hbox.setAlignment(Pos.CENTER);
//
          StackPane stackView = new StackPane(backgroundR, imageVBox, hbox);
//
          return stackView;

     }
}
