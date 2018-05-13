/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;

/**
 *
 * @author ismael
 */
public class logoController implements Initializable {
 
 
 @FXML Button login;
 
 
 
 @FXML void login() throws IOException{
     
     
      FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/login.fxml"));
            Parent contant = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(contant);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
    Stage stage = (Stage) login.getScene().getWindow();
    stage.close();
       
 
 
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 

        // ok 
    }
}
