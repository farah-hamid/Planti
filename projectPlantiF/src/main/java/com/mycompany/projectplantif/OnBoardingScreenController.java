/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectplantif;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class OnBoardingScreenController {

     private Scene scene;
     private Stage stage;
     private Parent root;
     @FXML
     private MediaView mediaView;
     private MediaPlayer player;

     @FXML
     private void handleGoToLoginButtonClick(ActionEvent event) {
//        try {
//            App.setRoot("Log_In");
//        } catch (IOException e) {
//            // Handle the exception appropriately
//        }

//https://www.youtube.com/watch?v=J07HiaaYwis
          if (mediaView.getMediaPlayer() == null) {
               try {
                    String fileName = getClass().getResource("click.mp3").toURI().toString();
                    Media media = new Media(fileName);
                    player = new MediaPlayer(media);
                    mediaView.setMediaPlayer(player);
               } catch (URISyntaxException e) {
                    e.printStackTrace();
               }

          }
          mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getStartTime());
          mediaView.getMediaPlayer().play();

          
          // if audio is finished go to next page
          player.setOnEndOfMedia(() -> {
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
          });

     }

     @FXML
     private void handleShowSignUpButtonClick(ActionEvent event) {
//          try {
//               App.setRoot("Sign_In");
//          } catch (IOException e) {
//               // Handle the exception appropriately
//          }

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

}
