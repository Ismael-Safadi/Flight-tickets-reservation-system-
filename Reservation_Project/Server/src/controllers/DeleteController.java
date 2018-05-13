/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static java.awt.image.ImageObserver.HEIGHT;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import static model.Check_Correct_Form.PID_Phone_Check;
import static model.DB_Connect.Delete_Ticket;
import static model.DB_Connect.SearchPID;

/**
 *
 * @author ismael
 */
public class DeleteController implements Initializable{
  @FXML
    private TextField Admin_Pass;

    @FXML
    private TextField PID;
    
    @FXML void onDeleteClick(ActionEvent event){
    
    String admin_password = Admin_Pass.getText();
    String pid = PID.getText();
        System.out.println(admin_password);
        System.out.println(pid);
        
        System.out.println("delete done");
     if ( "".equals(admin_password) || "".equals(pid)) {

            JOptionPane.showMessageDialog(null, "Empty filed , please fill all filed", "Empty filed ", HEIGHT);

        
        }
     else if (!"123456".equals(admin_password)){
     JOptionPane.showMessageDialog(null, "Incorrect Admin Password, Please tyr again", "Incorrect Password", HEIGHT);

     
     }
        else if (!PID_Phone_Check(pid)){
JOptionPane.showMessageDialog(null, "The PID must be an intger", "Incorrect PID", HEIGHT);

                    }    
            
            
         else {
            System.out.println("done");
            System.out.println(pid);
            String exist = SearchPID(pid);
            System.out.println(exist);
            //String delete = 
//            System.out.println(delete);
            if ("not exist".equals(exist)){            
                JOptionPane.showMessageDialog(null, "The PID is not exists", "PID Not found", HEIGHT);

                }
           else{
                try{
                Delete_Ticket(pid);}
                catch(Exception e){
                    System.out.println(e);
                
                }
                JOptionPane.showMessageDialog(null, "Deleting  done "); }


        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize");
        // TODO
    } 
    
}
