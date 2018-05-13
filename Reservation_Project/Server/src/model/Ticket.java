/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
  private String PID;
    private String Date;
    private String TicketID;
    public Ticket(String PID, String Date, String TicketID) {
        this.PID = PID;
        this.Date = Date;
        this.TicketID = TicketID;
    }
  

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setTicketID(String TicketID) {
        this.TicketID = TicketID;
    }

    public String getPID() {
        return PID;
    }

    public String getDate() {
        return Date;
    }

    public String getTicketID() {
        return TicketID;
    }
    
 
    
}