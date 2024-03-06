/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author farah
 */
public class cartController {

     @FXML
     ChoiceBox<String> choiceBox;

     @FXML
     ChoiceBox<String> sortBox;
     @FXML
     private VBox cartVbox;

     @FXML
     private Button bottomBt;

     private Scene scene;
     private Stage stage;
     private Parent root;

     @FXML
     public void initialize() throws IOException {
          ///////////////////////////////////////////////

          choiceBox.getItems().addAll("Home", "Profile", "Cart", "Log Out");

          choiceBox.setValue("Cart");
          choiceBox.setStyle("-fx-font-family: 'Niramit SemiBold';");

          choiceBox.setOnAction(e -> {
               if (choiceBox.getValue().equals("Profile")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    try {
                         root = loader.load();
                    } catch (IOException ex) {
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
                    }
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               } else if (choiceBox.getValue().equals("Home")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    try {
                         root = loader.load();
                    } catch (IOException ex) {
                    }
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               }

          });

          ///////////////////////////////////////////
          List<CartItem> cartItems = Cart.getCart().getCartItems();

          //////////////////////////////////////////
          //cartVbox.getChildren().clear();
          if (cartItems.isEmpty()) {
               cartVbox.getChildren().add(this.emptyItemCartView());
          } else {

               for (int i = 0; i < cartItems.size(); i++) {

                    HBox hbox = this.itemCartView(cartItems.get(i).getProduct());
                    cartVbox.getChildren().add(hbox);
               }

          }

          ///////////////////////////////////////////
          sortBox.getItems().addAll("Sort", "A-Z", "Z-A", "Lowest Price", "Highest Price");
          sortBox.setValue("Sort");
          sortBox.setStyle("-fx-font-family: 'Niramit SemiBold';-fx-text-align: center;");
          sortBox.setOnAction(e -> {

               if (!cartItems.isEmpty()/*do only if the cart is not empty*/) {
                    Cart.getCart().sortList(sortBox.getValue());
                    cartVbox.getChildren().clear();

                    for (int i = 0; i < cartItems.size(); i++) {

                         HBox hbox = this.itemCartView(cartItems.get(i).getProduct());
                         cartVbox.getChildren().add(hbox);
                    }
               }
          });

          //https://stackoverflow.com/questions/62559353/where-to-assign-keyevent-in-controller-class-in-javafx
          EventHandler<KeyEvent> keyPressListener = e -> keyD(e);

          cartVbox.sceneProperty().addListener((obs, oldScene, newScene) -> {
               if (oldScene != null) {
                    oldScene.removeEventHandler(KeyEvent.KEY_PRESSED, keyPressListener);
               }
               if (newScene != null) {
                    newScene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressListener);
               }
          });

     }

     @FXML
     public void keyD(KeyEvent e) {
          if (e.getCode() == KeyCode.C) {
               List<CartItem> cartItems = Cart.getCart().getCartItems();
               cartItems.clear();
               cartVbox.getChildren().clear();
               cartVbox.getChildren().add(emptyItemCartView());
          }
     }

     public HBox itemCartView(Product product) {

          HBox hbox = new HBox(50);

          Image image = new Image("file:src/main/resources/com/mycompany/projectplantif/" + product.getImage());
          ImageView imageView = new ImageView(image);
          imageView.setFitWidth(110);
          imageView.setFitHeight(110);

          Label productnameLabel = new Label(product.getName());
          productnameLabel.setStyle("-fx-font-size: 14px;-fx-font-family: 'Niramit SemiBold'; ");

          Button delBt = new Button("Delete");
          delBt.setStyle(" -fx-background-color: transparent; -fx-text-fill: DarkGray; -fx-font-size: 14px;-fx-font-family: 'Niramit SemiBold';");
          delBt.setOnAction(e -> {
               System.out.println("Removed");
               Cart cart = Cart.getCart();

               //to delete it from the list 
               cart.deleteProduct(product);

               // to delet it from the scene 
               cartVbox.getChildren().remove(hbox);

               if (cart.getCartItems().isEmpty()) {
                    cartVbox.getChildren().add(this.emptyItemCartView());

               }
          });

          VBox vbox = new VBox(20, productnameLabel, delBt);
          vbox.setAlignment(Pos.BOTTOM_CENTER);

          Cart cart = Cart.getCart();

          Label priceLabel = new Label("$" + (product.getPrice() * cart.getQuantity(product)));
          priceLabel.setStyle("-fx-font-size: 15px;-fx-font-family: 'Niramit SemiBold'; ");

          Label noProduct = new Label(String.valueOf(cart.getQuantity(product)));
          noProduct.setStyle("-fx-font-size: 15px;-fx-font-family: 'Niramit SemiBold'; ");

          Button addBt = new Button("+");
          addBt.setOnAction(e -> {
               System.out.println("ADDED TO CART");
               int quantity = Integer.parseInt(noProduct.getText()) + 1;
               noProduct.setText(String.valueOf(quantity));
               cart.setQuantity(product, quantity);
               priceLabel.setText("$" + (product.getPrice() * cart.getQuantity(product)));
          });

          addBt.setStyle(" -fx-background-radius: 19;-fx-background-color:  #A6C39C; -fx-text-fill: white;    -fx-font-size: 15px;-fx-font-weight: bold");
//
          Button removeBt = new Button("- ");
          removeBt.setOnAction(e -> {
               System.out.println("reduced TO CART");

               int quantity = Integer.parseInt(noProduct.getText()) - 1;

               if (quantity <= 0) {
                    noProduct.setText(String.valueOf(1));
                    cart.setQuantity(product, 1);
               } else {
                    noProduct.setText(String.valueOf(quantity));
                    cart.setQuantity(product, quantity);
               }
               priceLabel.setText("$" + (product.getPrice() * cart.getQuantity(product)));

          });

          removeBt.setStyle(" -fx-background-radius: 19;-fx-background-color:  #A6C39C; -fx-text-fill: white;    -fx-font-size: 15px;-fx-font-weight: bold");

          HBox quantityHBox = new HBox(10, addBt, noProduct, removeBt);
          quantityHBox.setAlignment(Pos.CENTER);

          VBox vbox2 = new VBox(20, quantityHBox, priceLabel);
          vbox2.setAlignment(Pos.BOTTOM_CENTER);

          hbox.getChildren().addAll(imageView, vbox, vbox2);
          hbox.setAlignment(Pos.BOTTOM_CENTER);

          bottomBt.setText("ORDER NOW");

          return hbox;

     }

     public VBox emptyItemCartView() {

          Image ghost = new Image("file:src/main/resources/com/mycompany/projectplantif/ghost.png");
          ImageView imageView2 = new ImageView(ghost);
          imageView2.setFitWidth(200);
          imageView2.setFitHeight(200);

          VBox ghostBox = new VBox(imageView2);
          ghostBox.setPadding(new Insets(0, 0, 0, 0));
          ghostBox.setAlignment(Pos.CENTER);

          //https://www.youtube.com/watch?v=UdGiuDDi7Rg
          TranslateTransition translateGhost = new TranslateTransition();
          translateGhost.setNode(ghostBox);
          translateGhost.setDuration(Duration.millis(1000));
          translateGhost.setCycleCount(TranslateTransition.INDEFINITE);
          translateGhost.setByX(10);
          translateGhost.setByY(10);
          translateGhost.setByZ(10);
          translateGhost.setAutoReverse(true);
          translateGhost.play();

          Label booLabel = new Label("BOO!");

          booLabel.setStyle("-fx-font-size: 40px;-fx-font-family: 'Niramit SemiBold'; ");
          Label nothinglLabel = new Label("Nothing Here, But Me");
          nothinglLabel.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold'; ");
          Label dontLabel = new Label("You don't have any Item in the cart.");
          dontLabel.setStyle("-fx-font-size: 20px;-fx-font-family: 'Niramit SemiBold'; ");

          VBox labelBox = new VBox(booLabel, nothinglLabel, dontLabel);
          labelBox.setAlignment(Pos.CENTER);

          StackPane stackPane = new StackPane(labelBox);

          VBox imageVBox = new VBox(ghostBox, stackPane);
          imageVBox.setAlignment(Pos.CENTER);

          bottomBt.setText("SHOP NOW");
          return imageVBox;
     }

     @FXML
     private void switchSceneBottomBt(ActionEvent event) throws IOException {

          if (bottomBt.getText().equalsIgnoreCase("shop now")) {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
               root = loader.load();

               stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
          } else {
               //order page 
               FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderFXML.fxml"));
               root = loader.load();

               stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
          }

     }

     @FXML
     private void switchScene(ActionEvent event) throws IOException {

          FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
          root = loader.load();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();

     }

     @FXML
     public void dropDownShadow() {

          this.bottomBt.setEffect(new DropShadow());
     }

     @FXML
     public void RdropDownShadow() {

          this.bottomBt.setEffect(null);
     }

}
