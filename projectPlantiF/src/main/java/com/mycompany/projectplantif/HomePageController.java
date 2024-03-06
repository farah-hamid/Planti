/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author farah
 */
public class HomePageController {

     @FXML
     private TextField searchLbl;
     @FXML
     private BorderPane HomePageProductBorderPane;
     @FXML
     private VBox vbox;
     @FXML
     private Product productChosen;
     @FXML
     private ScrollPane scrollPane;
     @FXML
     private ChoiceBox<String> choiceBox;


     private Scene scene;
     private Stage stage;
     private Parent root;

     @FXML
     public void initialize() throws IOException {

          choiceBox.getItems().addAll("Home", "Profile", "Cart", "Log Out");

          choiceBox.setValue("Home");

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
               }

          });

          searchLbl.setFocusTraversable(false);

          Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction tx = session.beginTransaction();
          List<Product> productList = null;
          String queryStr = "from Product";
          Query query = session.createQuery(queryStr);
          productList = query.getResultList();
          tx.commit();
          session.close();

          HBox product1 = new HBox(productViewMenu(productList.get(0) /*new Product("BAMBOO", "bambooPlant.png", 15)*/));
          product1.setAlignment(Pos.CENTER);
          HBox product2 = new HBox(productViewMenu(productList.get(1)));
          product2.setAlignment(Pos.CENTER);

          HBox product3 = new HBox(productViewMenu(productList.get(2)));
          product3.setAlignment(Pos.CENTER);

          HBox product4 = new HBox(productViewMenu(productList.get(3)));
          product4.setAlignment(Pos.CENTER);

          HBox product5 = new HBox(productViewMenu(productList.get(4)));
          product5.setAlignment(Pos.CENTER);

          HBox product6 = new HBox(productViewMenu(productList.get(5)));
          product6.setAlignment(Pos.CENTER);

          vbox.getChildren().addAll(product1, product2, product3, product4, product5, product6);
          scrollPane.setContent(vbox);

          vbox.setAlignment(Pos.TOP_CENTER);
          HomePageProductBorderPane.setCenter(scrollPane);

     }

     @FXML
     private StackPane productViewMenu(Product product) throws IOException {
          HBox hbox = new HBox(30);

          Rectangle backgroundR = new Rectangle(270, 100);
          backgroundR.setStyle("-fx-fill:#A6C39C;-fx-arc-height:20px;-fx-arc-width:20px;");

          Image image = new Image("file:src/main/resources/com/mycompany/projectplantif/" + product.getImage());
          ImageView imageView = new ImageView(image);
          imageView.setFitWidth(130);
          imageView.setFitHeight(130);
          VBox imageVBox = new VBox(imageView);
          imageVBox.setPadding(new Insets(0, 0, 20, 0));

          Label productNameLabel = new Label(product.getName());
          productNameLabel.setStyle("-fx-font-family: Adelle Sans Regular;-fx-font-size: 15px;-fx-text-fill: black;-fx-font-family: 'Niramit SemiBold'; ");
          Button addBt = new Button("+");

          addBt.setOnAction(e -> {

               stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
               System.out.println("ADDED TO CART");
               Cart cart = Cart.getCart();
               cart.addProduct(product);

          });
          DropShadow shadow = new DropShadow();

          addBt.setOnMousePressed(e -> {
               addBt.setEffect(shadow);
          });

          addBt.setOnMouseReleased(e -> {
               addBt.setEffect(null);
          });

          addBt.setStyle(" -fx-background-radius: 20;-fx-background-color: #3D601E; -fx-text-fill: white;    -fx-font-size: 15px;-fx-font-weight: bold");

          Button viewBt = new Button("view");
          viewBt.setOnAction(e -> {
               soundEffect(e);

               this.productChosen = product;
               player.setOnEndOfMedia(() -> {
                    try {
                         this.switchScene(e);
                    } catch (IOException ex) {
                         ex.printStackTrace();
                    }
               });
          });

          viewBt.setStyle(" -fx-background-color: transparent; -fx-text-fill: white;-fx-font-size: 15px;-fx-font-family: 'Niramit SemiBold'; ");
          viewBt.setPadding(new Insets(0, 0, 0, 10));

          Label productPriceLabel = new Label(String.valueOf("$" + product.getPrice()));
          productPriceLabel.setStyle("-fx-font-family: Adelle Sans Regular;-fx-font-size: 15px;-fx-text-fill: black;-fx-font-family: 'Niramit SemiBold';");
          VBox btVBox = new VBox(5, addBt, viewBt, productPriceLabel);
          btVBox.setAlignment(Pos.CENTER_RIGHT);

          btVBox.setPadding(new Insets(5, 5, 5, 5));
          hbox.getChildren().addAll(productNameLabel);
          hbox.setAlignment(Pos.CENTER);

          StackPane stackView = new StackPane(backgroundR, imageVBox, hbox, btVBox);

          return stackView;
     }

     @FXML

     private void switchScene(Event event) throws IOException {

          FXMLLoader loader = new FXMLLoader(getClass().getResource("singleProdcut.fxml"));
          root = loader.load();

          ProductController productController = loader.getController();
          productController.setProduct(this.productChosen);
          productController.initialize();

          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();

     }

     @FXML
     private MediaView mediaView;
     private MediaPlayer player;

     @FXML
     private void soundEffect(Event event) {

          //https://www.youtube.com/watch?v=J07HiaaYwis
          if (mediaView.getMediaPlayer() == null) {
               try {
                    String fileName = getClass().getResource("mouse.mp3").toURI().toString();
                    Media media = new Media(fileName);
                    player = new MediaPlayer(media);
                    mediaView.setMediaPlayer(player);
               } catch (URISyntaxException e) {
                    e.printStackTrace();
               }

          }
          mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getStartTime());
          mediaView.getMediaPlayer().play();
     }

}
