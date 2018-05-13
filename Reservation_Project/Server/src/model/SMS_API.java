/*
 this class required to make an account in twilio website and fill the api information as following 
1: you need to change line 15 to your ACCOUNT_SID 
2: and change line 17 to your AUTH_TOKEN 

 */
package model;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS_API {

 public static final String ACCOUNT_SID =
            "ACCOUNT_SID";
    public static final String AUTH_TOKEN =
            "AUTH_TOKEN";

    public static void send_sms(String message_value ,String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message;
     message = Message
             .creator(new PhoneNumber("+97"+number), // to
                     new PhoneNumber("+12252172717"), // from
                     message_value)
             .create();
        System.out.println(message.getSid());
    }   
}



