
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import static model.AES_ENC.encrypt;
import model.Client_Client;

public class loginController implements Initializable{
    
    
     @FXML
    private PasswordField password;

    @FXML
    private TextField name;

    @FXML
    private Button login;

    @FXML
    void login(ActionEvent event) throws InterruptedException, IOException {
        

        String Email = name.getText();
        String Password  = password.getText();
        String login_data = ","+Email+","+Password;
         String data = encrypt("Bar12345Bar12345", "RandomInitVector", login_data);
     String final_data = "login"+data;
       Client_Client c = new  Client_Client();
       c.run(final_data);
       
       String login_info = c.getData();
        System.out.println("login info = =  "+login_info);
       if ("correct_password".equals(login_info)){
           
           
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/Reservation.fxml"));
            Parent contant = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(contant);
            primaryStage.setTitle("Client");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Stage stage = (Stage) login.getScene().getWindow();
    stage.close();
       
       
       }
    }

    @FXML
    void register(ActionEvent event) {
         try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/register.fxml"));
            Parent contant = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(contant);
            primaryStage.setTitle("Client");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ok 
    }
}
