/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DB_Connect.ShowAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import model.Ticket;

/**
 *
 * @author ismael
 */
public class ShowController implements Initializable{
    
  
    @FXML private TableView<Ticket> Tickets;

        
    @FXML private TableColumn<Ticket, String>PID;
 
    @FXML private TableColumn<Ticket, String>Date;

    @FXML private TableColumn<Ticket, String>TicketID;
    
    

   public static ObservableList<Ticket>data = FXCollections.observableArrayList();
    
    
    
    
    
    
    
    
    
    @FXML void onDeleteClick(ActionEvent event){
     try {
               
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Delete.fxml"));
            
            Parent contant = loader.load();
            Scene scene = new Scene(contant);
            
            Stage addPersonStage = new Stage();
            addPersonStage.setTitle("Delete Ticket");
            addPersonStage.setScene(scene);
            addPersonStage.initModality(Modality.APPLICATION_MODAL);
            addPersonStage.setResizable(false);
            addPersonStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
/////
      
    @FXML void onModifyClick(ActionEvent event){
     try {
               
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Modify.fxml"));
            
            Parent contant = loader.load();
            Scene scene = new Scene(contant);
            
            Stage addPersonStage = new Stage();
            addPersonStage.setTitle("Modify Ticket");
            addPersonStage.setScene(scene);
            addPersonStage.setResizable(false);
            addPersonStage.initModality(Modality.APPLICATION_MODAL);
            addPersonStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    @FXML void onExitClick(ActionEvent event){
    System.exit(0);
    
    }
    
    
    
    @FXML void refresh(ActionEvent event) throws SQLException{
        data.clear();
        ShowAll();
  
    }
    
    
    
    
    
    
    
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowAll();
        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    //data.add(new Ticket("98289498","1/1/1000","7777"));
   PID.setCellValueFactory(new PropertyValueFactory<>("PID"));
   Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
   TicketID.setCellValueFactory(new PropertyValueFactory<>("TicketID"));
   Tickets.setItems(data);
   }//To change body of generated methods, choose Tools | Templates.
    }
    
    
    

