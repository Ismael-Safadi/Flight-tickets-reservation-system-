/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static model.DB_Connect.add_user_to_database;
import static model.DB_Connect.SearchPassword;
import static model.DB_Connect.SearchSecret;
import static model.DB_Connect.add_data_to_database;
import static model.DB_Connect.add_ready_to_database;
import static model.DB_Connect.Update_Ticket;
import static model.DB_Connect.Delete_Ticket;
import static model.DB_Connect.SearchPID;
import static model.DB_Connect.SearchPhone;
import static model.DB_Connect.SearchName;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DB_Connect.hash256;
import model.Send_Email;
import model.SMS_API;
import model.AES_ENC;

/**
 *
 * @author isamel
 */
import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Server_Server {

    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message= null ;

    Server_Server() {
    }

    void run() throws InterruptedException, NoSuchAlgorithmException {
        try {
            //1. creating a server socket
            providerSocket = new ServerSocket(5050, 10);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            System.out.println("connection success");
            //4. The two parts communicate via the input and output streams
            do {
                try{
                    
             
                    String message = (String) in.readObject();
                    System.out.println(message);

                    if (message.equals("bye")) {
                        sendMessage("bye");}
                    // for login check 
                    else if (message.contains("login")){
                        String new_message = message.replace("login", "");
                     
                        System.out.println(new_message);
                        String login_data = AES_ENC.decrypt("Bar12345Bar12345", "RandomInitVector", new_message);
                    
                        String[] parts = login_data.split(",");
                        String email = parts[1];
                        String password = parts[2];
                        System.out.println(email);
                        System.out.println(password);
                        String hash_pass = hash256(password);
                        String email_search_login = SearchName(email);
                        String pass = SearchPassword(email);// pass == the hash from database 
                        System.out.println("password from database = "+pass);
                        if (pass == null ? hash_pass == null : pass.equals(hash_pass)){
                            System.out.println("correct_password");
                        sendMessage("correct_password");
                        }
                        else if ("Name_Not_Exists".equals(email_search_login)){
                        
                        sendMessage("this_account_not_exists");
                        }
                        else{
                            System.out.println("incorrect_password");
                        sendMessage("incorrect_password");
                        }
                    System.out.println("");}
                    // check for register 
                    else if (message.contains("register")){
                        
                         String new_message = message.replace("register", "");
                     
                        System.out.println(new_message);
                        String login_data = AES_ENC.decrypt("Bar12345Bar12345", "RandomInitVector", new_message);
                    
                        String[] parts = login_data.split(",");
                        String name = parts[1];
                        String email = parts[2];
                        String password = parts[3];
                        String hash_pass = hash256(password);
                        System.out.println("1 :name "+name);
                        System.out.println("2:email "+email);
                        System.out.println("3:password "+password);
                        String search = SearchPassword(email);
                        String name_search = SearchName(email);
                        if ("doesnt found".equals(search) && "Name_Not_Exists".equals(name_search)){
                            TimeUnit.SECONDS.sleep(1);
                            add_user_to_database(name, email, hash_pass);
                            sendMessage("register_new_account done");
                            sendMessage("bye");
                        }
                       
                        else{
                            sendMessage("already exists");
                        }
                    
                        System.out.println("");
                    }
                    
                    else if(message.contains("reservation")){
                        String reservation_data = message.replace("reservation", "");
                        String data = AES_ENC.decrypt("Bar12345Bar12345", "RandomInitVector", reservation_data);
                        System.out.println(data);
                        String[] parts = data.split(",");
                        String Name = parts[1];
                        String ID = parts[2]; // 034556
                        String Email = parts[3];
                        String Phone = parts[4];
                        String From = parts[5];
                        String To = parts[6];
                        String DB = parts[7];
                        String PB = parts[8];
                        String Type = parts[9];
                        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
                        StringBuilder salt = new StringBuilder();
                        Random rnd = new Random();
                        while (salt.length() < 8) { // length of the random string.
                            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                            salt.append(SALTCHARS.charAt(index));
                        }
                        String saltStr = salt.toString();

                        add_data_to_database(Name, ID, Email, Phone, From, To, DB, PB, Type,saltStr);
                        // send to email 
                        Send_Email.send_mail(saltStr, Email);
                        System.out.println("send email done");
                        sendMessage("reservation done");
                     
                    
                    
                    }
                    else if (message.contains("secret")){
                    
                    String Sec = message.replace("secret", "");
                        System.out.println("this is the first data ");
                        System.out.println(Sec);
                        String data = AES_ENC.decrypt("Bar12345Bar12345", "RandomInitVector", Sec);
                        String[] parts = data.split(",");
                        String code = parts[1];
                        String pid = parts[2];
                        
                        Date dt = new Date();
                        Calendar c = Calendar.getInstance(); 
                        c.setTime(dt); 
                        c.add(Calendar.DATE, 3);
                        dt = c.getTime();
                        String DATE=dt.toString();
                                System.out.println(dt);
                        System.out.println(code);
                        System.out.println(pid);
                        String secret = SearchSecret(pid);
                        System.out.println(secret);
                        if (secret.equals(code)) {
                        System.out.println("Done ^__^"); // send sms  
                        String SALTCHARS = "1234567890";
                        StringBuilder salt = new StringBuilder();
                        Random rnd = new Random();
                        while (salt.length() < 12) { // length of the random string.
                            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                            salt.append(SALTCHARS.charAt(index));
                        }
                        String ticketNumber = salt.toString();
                            add_ready_to_database(pid, DATE, ticketNumber);
                            String phone_number = SearchPhone(pid);
                            String SMS_Message = String.format("Hello our customer , your ticket ID is %s and"
                                    + " your travel Flight will be in %s",ticketNumber,DATE);
                            
                            // add to ready data base 
                            System.out.println(SMS_Message);
                            // send sms to phone number 
                            sendMessage("check your phone");
                            SMS_API.send_sms(SMS_Message,phone_number);
                            // send sms 
                    
                    } 
                        else {
                         sendMessage("wrong secret");
                        }
                    
                    }
                    
                    
                    
                    ///// check pid existance
                    
                    
                else if (message.equals("pid_check")){
                        String new_message = message.replace("pid_check", "");
                        String check_pid = new_message.replace("pid_check", "");
                        System.out.println(check_pid);                        
                        String check = SearchPID(check_pid);
                        System.out.println(check);
                        sendMessage(check);
                    
                    
                }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
           
                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received in unknown format");
                }
            } while (!message.contains("bye"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            
        } 
        catch (NullPointerException ex){
            System.out.println(ex);
        
        }
        
        
        finally {
            //4: Closing connection
            try {
                in.close();
                out.close();
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException, NoSuchAlgorithmException {
        Server_Server server = new Server_Server();
        while (true) {
            server.run();
        }
    }

   
}