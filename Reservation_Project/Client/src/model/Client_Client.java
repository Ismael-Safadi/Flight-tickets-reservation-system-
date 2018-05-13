package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.glass.ui.Cursor;
import controllers.reservationController;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client_Client {
    private String login_data ;
    private String confirm;
    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;

    public Client_Client() {
    }

    public void run(String msg) {
        try {
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", 5050);
            System.out.println("Connected to localhost in port 8080");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server
            sendMessage(msg);
            do {
                try {
                    String message = (String) in.readObject();
                    if (message.equals("bye")) {
                        sendMessage("bye");
                    } else if (message.equals("correct_password")) {
                        System.out.println("login done ");
                        // corrct pass 
                      login_data = "correct_password";
                        
                        System.out.println("correct_password");
                    } else if (message.equals("incorrect_password")) {

                        JOptionPane.showMessageDialog(null, "Incorrect password  , please tye again", "Incorrect password", HEIGHT);

                        System.out.println("incorrect_password");
                        // alleart 
                    } else if (message.contains("already exists")) {
                        // cant register new account  already exists 
                        JOptionPane.showMessageDialog(null, "this account is already exists , try with another email or user name ", "already exists", HEIGHT);

                        System.out.println("already exists");
                    } else if (message.contains("register_new_account done")) {
                        JOptionPane.showMessageDialog(null, "Registration Done , now loing into your account");

                        System.out.println("register new account done ^__^");
                        // register done

                    } else if (message.contains("not exists")) {

                        JOptionPane.showMessageDialog(null, "Incorrect Passport ID, please tye again", "Incorrect Passport ID", HEIGHT);

                        // alleart 
                    } else if (message.contains("reservation done")) {
                        confirm = "confirm";
                        JOptionPane.showMessageDialog(null, "reservation done , please check your email we send to you the secret code");
                        // reservation done 
                        
                    } else if (message.contains("this_account_not_exists")) {

                        JOptionPane.showMessageDialog(null, "This account not found in our database , please register new account", "Account not found", HEIGHT);

                    } else if (message.contains("wrong secret")) {
                        JOptionPane.showMessageDialog(null, "Incorrect secret  , please tye again", "Incorrect secret", HEIGHT);

                        // try agin with true secret 
                        System.out.println("incorrect secret");
                    } else if (message.contains("check your phone")) {
                        JOptionPane.showMessageDialog(null, "check your phone , we sent to your phone a date of flight and the ticket id as an SMS");

                        // allert check your phone
                        
                        
                        
                    }
                    
                    else {
                        System.out.println(message);
                        sendMessage(msg);
                        message = "bye";
                        sendMessage(message);
                    }
                } catch (ClassNotFoundException classNot) {
                    System.err.println("data received in unknown format");
                }
            } while (!message.contains("bye"));

        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (NullPointerException ex) {

            System.out.println(ex);
        } finally {
            //4: Closing connection
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

   public String getData() {
        return login_data;
    }
   
   public String getConfirm(){
   return  confirm;
   
   }
    public static void main(String args[]) {
//        Client_Client client = new Client_Client();
//        client.run("hello ");
    }
}
