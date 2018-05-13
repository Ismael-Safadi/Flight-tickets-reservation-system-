/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static java.awt.image.ImageObserver.HEIGHT;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import main.Main;
import static model.AES_ENC.encrypt;
import static model.Check_Correct_Form.Email_Check;
import static model.Check_Correct_Form.Name_Check;
import static model.Check_Correct_Form.PID_Phone_Check;
import model.Client_Client;

/**
 *
 * @author ismael
 */
public class reservationController implements Initializable {

    ObservableList place_of_birth = FXCollections.observableArrayList();
    ////////
    ObservableList track_from = FXCollections.observableArrayList();
    ////////
    ObservableList track_to = FXCollections.observableArrayList();
    //

    @FXML
    private ChoiceBox<String> POB;

    @FXML
    private Button submit;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField pid;

    @FXML
    private TextField phone;

    @FXML
    private DatePicker DOB;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> from;

    @FXML
    private ChoiceBox<String> to;

    @FXML
    private TextField email;

    @FXML
    void handleButtonAction() throws IOException {

        LocalDate localDate = DOB.getValue();
        String final_date = localDate.toString();
        System.out.println(final_date);
String type = null;
        try {
            
            Toggle x = gender.getSelectedToggle();

            String choice = x.toString();

            if (choice.contains("Normal")) {
                type = "Normal";
            } else if (choice.contains("Business")) {
                type = "Business";
            }

            System.out.println(type);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select your ticket type", "Empty Filed", HEIGHT);

        }
        String pob = POB.getValue();
        System.out.println("POB = " + pob);

        String From = from.getValue();
        String To = to.getValue();
        System.out.println("From  =" + From);
        System.out.println("To = " + To);
        System.out.println("Date OF birth = " + final_date);
        String Full_Name = name.getText();
        String Passport_ID = pid.getText();
        String Eamil = email.getText();
        String Phone = phone.getText();
        
        
         if (Full_Name == null || Passport_ID == null || Eamil == null || Phone == null || From == null || To == null || pob == null){
                    JOptionPane.showMessageDialog(null, "Please select your information", "Empty Filed", HEIGHT);

        }
        boolean check_email = Email_Check(Eamil);
        boolean check_pid = PID_Phone_Check(Passport_ID);
        boolean check_phone = PID_Phone_Check(Phone);
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(Full_Name);
        boolean check_name = m.matches();

        if (check_email == false) {
            JOptionPane.showMessageDialog(null, "Please select real eamil", "Eamil not valid", HEIGHT);
        } else if (check_name == false) {
            JOptionPane.showMessageDialog(null, "Please select your real name , note that name must be only letters ", "Name not valid", HEIGHT);
        } else if (check_phone == false) {
            JOptionPane.showMessageDialog(null, "Please select your real phone number , note that the phone number must be an digets", "Phone number not valid ", HEIGHT);
        } else if (check_pid == false) {
            JOptionPane.showMessageDialog(null, "Please select your real pid , the pid must be an intger", "PID not valid", HEIGHT);
        } else if (pob == null) {
            JOptionPane.showMessageDialog(null, "Please select your place of birth ", "Empty Filed", HEIGHT);
        } else if (From == null) {
            JOptionPane.showMessageDialog(null, "Please select your track (From)", "Empty Filed", HEIGHT);
        } else if (To == null) {
            JOptionPane.showMessageDialog(null, "Please select your track (To)", "Empty Filed", HEIGHT);
        } else if (DOB == null) {
            JOptionPane.showMessageDialog(null, "Please select your date of birth", "Empty Filed", HEIGHT);
        } 
       
        
        
        else {
            // send data use socket 
            // add_data_to_database("isamel","1234567890", "isamel@gmail.com", "0591263449", "gaza", "qatar", "1/1/1999", "qatar", "iojer09fwop");

            String reservation_data = "," + Full_Name + "," + Passport_ID + "," + Eamil + "," + Phone + "," + From + "," + To + "," + final_date + "," + pob+","+type;
            System.out.println(reservation_data);
            String data = encrypt("Bar12345Bar12345", "RandomInitVector", reservation_data);
            String final_data = "reservation" + data;
            Client_Client c = new Client_Client();
            c.run(final_data);
            System.out.println("done");
            String con = c.getConfirm();
            if ("confirm".contains(con)){
            
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/confirm.fxml"));
            Parent contant = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(contant);
            primaryStage.setTitle("Client");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            }
            
            
            else {
            
                System.out.println("");
            }
            
        }

    }

    public void loadPOB() {
        place_of_birth.removeAll(place_of_birth);
        //, , 
        String c1 = "Palestine";
        String c2 = "Qatar";
        String c3 = "USA";
        place_of_birth.addAll(c1, c2, c3);
        POB.getItems().addAll(place_of_birth);

    }

    ////
    public void loadFrom() {
        track_from.removeAll(track_from);
        //, , "Palestine", , "USA"
        String c1 = "Palestine";
        String c2 = "Jordan";
        String c3 = "USA";
        track_from.addAll(c1, c2, c3);
        from.getItems().addAll(track_from);

    }

    public void loadTo() {
        track_to.removeAll(track_to);
        //, , 
        String c1 = "Qatar";
        String c2 = "Holand";
        String c3 = "Germany";
        track_to.addAll(c1, c2, c3);
        to.getItems().addAll(track_to);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadFrom();
        loadPOB();
        loadTo();
        // ok 
    }
}
