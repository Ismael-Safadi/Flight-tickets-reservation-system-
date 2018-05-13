
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Client_Client;
import model.AES_ENC;
import static model.AES_ENC.encrypt;
public class confirmController implements Initializable {
    
      @FXML
    private TextField PID;

    @FXML
    private TextField secret;
    @FXML
    void confirm(ActionEvent event) {
        String pid = PID.getText();
        String sec = secret.getText();
        String check = "pid_check"+pid;
        Client_Client c = new Client_Client();
        c.run(check);
        String sec_data_normal = ","+sec+","+pid;
        String data = encrypt("Bar12345Bar12345", "RandomInitVector", sec_data_normal);
        try {
        String sec_num = "secret"+data;
        c.run(sec_num);
        
        }catch(Exception e){
        
            System.out.println(e);
        }
        

    }

    @Override
     public void initialize(URL url, ResourceBundle rb) {
        
      
        // TODO
    } 
}
