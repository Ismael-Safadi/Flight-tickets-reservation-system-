/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static java.awt.image.ImageObserver.HEIGHT;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import static model.DB_Connect.Update_Ticket;
import static model.Check_Correct_Form.PID_Phone_Check;
import static model.DB_Connect.Delete_Ticket;
import static model.DB_Connect.SearchPID;
public class ModifyController implements Initializable {

    @FXML
    private TextField Admin_password;

    @FXML
    private TextField PID;

    @FXML
    private DatePicker Date;

    @FXML
    void onModifyClick(ActionEvent event) {
        LocalDate localDate = Date.getValue();

        String pid = PID.getText();
        String admin_password = Admin_password.getText();
        System.out.println(pid);
        System.out.println(localDate);
        System.out.println(admin_password);
        System.out.println("1");

        System.out.println("2");
        if (localDate == null || "".equals(admin_password) || "".equals(pid)) {

            JOptionPane.showMessageDialog(null, "Empty filed , please fill all filed", "Empty filed ", HEIGHT);

            //String lastDate = localDate.toString();
            //Update_Ticket(pid,lastDate);
        }
         else if (!"123456".equals(admin_password)){
     JOptionPane.showMessageDialog(null, "Incorrect Admin Password, Please tyr again", "Incorrect Password", HEIGHT);

     
     }
     
        else if (!PID_Phone_Check(pid)){
JOptionPane.showMessageDialog(null, "The PID must be an intger", "Incorrect PID", HEIGHT);

                    }    
            
            
         else {
            
            String exist = SearchPID(pid);
            System.out.println(exist);
            //String delete = 
//            System.out.println(delete);
            if ("not exist".equals(exist)){            JOptionPane.showMessageDialog(null, "The PID is not exists", "PID Not found", HEIGHT);

                }
           else{
                String lastDate = localDate.toString();
                Update_Ticket(pid,lastDate);
                JOptionPane.showMessageDialog(null, "Modification  done "); }

           

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
