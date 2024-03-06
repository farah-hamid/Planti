/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author farah
 */
public class ProfileController {

     @FXML
     private VBox mainContainer;

     @FXML
     private BorderPane borderPane;

     @FXML
     private ChoiceBox<String> choiceBox;

     private Scene scene;
     private Stage stage;
     private Parent root;

     @FXML
     public void initialize() throws IOException {
          // Main layout container

          choiceBox.getItems().addAll("Home", "Profile", "Cart", "Log Out");

          choiceBox.setValue("Profile");

          choiceBox.setStyle("-fx-font-family: 'Niramit SemiBold';");

          choiceBox.setOnAction(e -> {
               if (choiceBox.getValue().equals("Home")) {
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

          User user = User.getUser();

          mainContainer.setSpacing(30);
          mainContainer.setAlignment(Pos.CENTER);
          mainContainer.setPadding(new Insets(20)); // 20px padding all around

          // Profile picture placeholder with edit button
          StackPane profileImageStack = new StackPane();
          //Circle profileCircle = new Circle(50, Color.LIGHTGRAY);
          Image image = new Image("file:src/main/resources/com/mycompany/projectplantif/profile.png");
          ImageView imageView = new ImageView(image);
          imageView.setFitWidth(180);
          imageView.setFitHeight(180);
          VBox imageVBox = new VBox(imageView);
          imageVBox.setAlignment(Pos.CENTER);

          Button editButton = new Button("\u270E"); // Unicode 
          editButton.setStyle("-fx-background-color:#A6C39C; -fx-text-fill: white;-fx-font-size: 20px; -fx-border-radius: 20;-fx-background-radius:20");
          editButton.setAlignment(Pos.CENTER);
          editButton.setTranslateX(40);
          editButton.setTranslateY(-10);
          profileImageStack.getChildren().addAll(imageVBox, editButton);
          StackPane.setAlignment(editButton, Pos.BOTTOM_CENTER);

          Label errorLabel = new Label();
          // Name TextField
          HBox Namehbox = new HBox(5);
          TextField nameTextField = new TextField();
          nameTextField.setText(user.getName());
          nameTextField.setStyle("-fx-background-color:  -fx-control-inner-background; -fx-border-radius: 15;-fx-background-radius:15");
          nameTextField.setPrefWidth(234);
          nameTextField.setPrefHeight(38);

          Namehbox.getChildren().addAll(new Button("\uD83D\uDC64"), nameTextField);
 
          // Email TextField within HBox
          HBox emailHBox = new HBox(5);
          TextField emailTextField = new TextField(user.getEmail());
          emailTextField.setStyle("-fx-background-color:  -fx-control-inner-background; -fx-border-radius: 15;-fx-background-radius:15");
          emailTextField.setPrefWidth(234);
          emailTextField.setPrefHeight(38);

          emailHBox.getChildren().addAll(new Button("\uD83D\uDCE7"), emailTextField);


          // PasswordField within HBox
          HBox passwordHBox = new HBox(5);
          PasswordField passwordField = new PasswordField();
          passwordField.setText(user.getPassword());
          passwordField.setStyle("-fx-background-color:  -fx-control-inner-background; -fx-border-radius: 15;-fx-background-radius:15");
          passwordField.setPrefWidth(234);
          passwordField.setPrefHeight(38);
          passwordHBox.getChildren().addAll(new Button("\uD83D\uDD12"), passwordField);

          // Update Profile Button
          Button updateProfileButton = new Button("Update");

          updateProfileButton.setOnAction(e -> {

               String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
               if (nameTextField.getText().isEmpty() || !nameTextField.getText().matches("[a-zA-Z]+")) {
                    errorLabel.setText("Please enter a valid name with letters only.");
               } else if (!emailTextField.getText().matches(emailRegex)) // Invalid email format
               {
                    errorLabel.setText("please make to enter valid Email");
               } else if (passwordField.getText().length() < 8) // Password is too short
               {
                    errorLabel.setText("Password must be 8 digits or more ");
               } else {

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
                         if (sList.get(i).getName().equals(user.getName())) {
                              index = i;
                         }
                    }

                    Session session2 = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx2 = session2.beginTransaction();
                    User Supdate = null;
                    int sid = user.getUserId();
                    Supdate = (User) session2.get(User.class, sid);
                    Supdate.setName(nameTextField.getText());
                    Supdate.setEmail(emailTextField.getText());
                    Supdate.setPassword(passwordField.getText());
                    user.setName(nameTextField.getText());
                    user.setEmail(emailTextField.getText());
                    user.setPassword(passwordField.getText());
                    session2.update(Supdate);
                    tx2.commit();
                    session2.close();

                    errorLabel.setText("Update done");
               }

          });
//        updateProfileButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;");

          updateProfileButton.setStyle(
                  "-fx-background-color: #A6C39C; "
                  + "-fx-text-fill: white; "
                  + "-fx-font-size: 17px; "
                  + "-fx-max-width: 300px; "
                  + "-fx-background-radius: 10px; "
                  + "-fx-font-family: 'Niramit SemiBold';" // Set the font family
          );

          updateProfileButton.setMaxWidth(200);

          // Adding all elements to VBox
          mainContainer.getChildren().addAll(
                  profileImageStack,
                  Namehbox,
                  emailHBox,
                  passwordHBox,
                  errorLabel,
                  updateProfileButton
          );

          borderPane.setCenter(mainContainer);

     }

     @FXML

     private void switchScene(ActionEvent event) throws IOException {

          FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
          root = loader.load();
          // root = FXMLLoader.load(getClass().getResource("singleProdcut"));
          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();

          // App.setRoot(loader.getLocation().getPath());
     }

}
