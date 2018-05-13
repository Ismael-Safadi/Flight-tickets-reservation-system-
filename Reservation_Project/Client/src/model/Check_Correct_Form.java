/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ismael
 */
public class Check_Correct_Form {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    // Email 
    public static boolean Email_Check(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    // name return the reverse 
    public static boolean Name_Check(String name) {
        return name.matches("[a-zA-Z]+");

    }
    // 
    public static boolean PID_Phone_Check(String number){
    return number.matches("[0-9]+");
    
    }

}
