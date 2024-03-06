/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author farah
 */
public class ProductController {

     @FXML
     private BorderPane SingleProductPan;

     @FXML
     private VBox vbox;

     @FXML
     private Product product = new Product();

     @FXML
     private Label noquantity;

     @FXML
     private Button addtocartBt;

     @FXML
     private StackPane stackpane;

     @FXML
     public ChoiceBox<String> choiceBox;

     private Scene scene;
     private Stage stage;
     private Parent root;

     @FXML
     public void initialize() {
          //عشان ما يتكرر مرتينن
          vbox.getChildren().clear();
          choiceBox.getItems().clear();

          choiceBox.getItems().addAll("Home", "Profile", "Cart", "Log Out");

          choiceBox.setValue("");
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
               }

          });

          addtocartBt.setOnAction(e -> {
               System.out.println("ADDED TO CART");
               Cart cart = Cart.getCart();
               int quantity = Integer.parseInt(noquantity.getText()) + cart.getQuantity(product);
               cart.addProduct(product);
               cart.getCartItems().get(cart.getIndex(product)).setQuantity(quantity);

          });

          vbox = productVBoxView(product);

          stackpane.getChildren().add(vbox);

          SingleProductPan.setCenter(stackpane);

     }

     //this method to set the product in scene 
     // and get the specified product form the homePageController 
     @FXML
     public void setProduct(Product product) {
          this.product = product;
     }

     @FXML
     private VBox productVBoxView(Product product) {

          VBox Vbox = new VBox(10);
          Cart cart = Cart.getCart();

          Image image = new Image("file:src/main/resources/com/mycompany/projectplantif/" + product.getImage());
          ImageView imageView = new ImageView(image);
          imageView.setFitWidth(300);
          imageView.setFitHeight(300);

          ///////////////////////////////
          noquantity = new Label("1");
          noquantity.setStyle("-fx-font-family: 'Niramit SemiBold';-fx-font-size: 20px;-fx-text-fill: black;");

          Button addBt = new Button("+");
          addBt.setOnAction(e -> {
               System.out.println("ADDED TO CART");
               int quantity = Integer.parseInt(noquantity.getText()) + 1;
               noquantity.setText(String.valueOf(quantity));
          });

          addBt.setStyle(" -fx-background-radius: 20;-fx-background-color:  #A6C39C; -fx-text-fill: white;    -fx-font-size: 15px;-fx-font-weight: bold");
//
          Button removeBt = new Button("- ");
          removeBt.setOnAction(e -> {
               System.out.println("reduced TO CART");
               int quantity = Integer.parseInt(noquantity.getText()) - 1;
               if (quantity <= 0) {
                    noquantity.setText(String.valueOf(1));
               } else {
                    noquantity.setText(String.valueOf(quantity));
               }
          });

          removeBt.setStyle(" -fx-background-radius: 20;-fx-background-color:  #A6C39C; -fx-text-fill: white;    -fx-font-size: 15px;-fx-font-weight: bold");
/////////////////////////////

          Label productNameLabel = new Label(product.getName());
          productNameLabel.setStyle("-fx-font-family: 'Niramit SemiBold';-fx-font-size: 15px;-fx-text-fill: black;");
          Label productPriceLbl = new Label("$" + String.valueOf(product.getPrice()));
          productPriceLbl.setStyle("-fx-font-family: 'Niramit SemiBold';-fx-font-size: 25px;-fx-text-fill: black;");

          VBox productinfo = new VBox(10, productNameLabel, productPriceLbl);

          HBox btHBox = new HBox(10, addBt, noquantity, removeBt);
          btHBox.setAlignment(Pos.CENTER);

          HBox hbox = new HBox(200, productinfo, btHBox);
          hbox.setAlignment(Pos.CENTER);

          Vbox.getChildren().addAll(imageView, hbox);
          Vbox.setAlignment(Pos.CENTER);

          return Vbox;

     }

     @FXML
     private void switchScene(ActionEvent event) throws IOException {

          FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();

     }

     @FXML
     public void dropDownShadow() {

          this.addtocartBt.setEffect(new DropShadow());
     }

     @FXML
     public void RdropDownShadow() {

          this.addtocartBt.setEffect(null);
     }

}
