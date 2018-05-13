/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static java.awt.image.ImageObserver.HEIGHT;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import static model.AES_ENC.encrypt;
import static model.Check_Correct_Form.Email_Check;
import static model.Check_Correct_Form.Name_Check;
import static model.Check_Correct_Form.PID_Phone_Check;
import model.Client_Client;

/**
 *
 * @author ismael
 */
public class registerController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private Button register;

    @FXML
    private PasswordField confirm_password;

    @FXML
    void register(ActionEvent event) {
        String Name = name.getText();
        String Email = email.getText();
        String Pass = password.getText();
        String Confirm_Pass = confirm_password.getText();
        String Confirm_Password = Confirm_Pass.toString();
        String Password = Pass.toString();
        System.out.println(" confirm =" + Confirm_Password);
        System.out.println("pass = " + Password);
        boolean check_email = Email_Check(Email);
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(Name);
        boolean check_name = m.matches();

        if (check_email == false) {
            JOptionPane.showMessageDialog(null, "Please select real eamil", "Eamil not valid", HEIGHT);
        } else if (check_name == false) {
            JOptionPane.showMessageDialog(null, "Please select your real name , note that name must be only letters ", "Name not valid", HEIGHT);

        } else if (Confirm_Password == null ? Password != null : !Confirm_Password.equals(Password)) {
            System.out.println(Confirm_Password == Password);
            JOptionPane.showMessageDialog(null, "The two passwords not matches", "Not match", HEIGHT);
        } else {

            System.out.println("done");

            String information = "," + Name + "," + Email + "," + Password;
            System.out.println(information);

        Client_Client c = new Client_Client();
        String data = encrypt("Bar12345Bar12345", "RandomInitVector", information);
        String final_data = "register" + data;
        c.run(final_data);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ok 
    }

}
