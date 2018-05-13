# Flight-tickets-reservation-system-
Client and Server Flight-tickets-reservation-system using java 
<br>
About
=
- An Desktop application for Flight tickets reservation using java 
- Client and server over local network using socket 
- All data that transferred  will be encrypted using AES algorithm
- Send email using SMTP protocol
- Send SMS using Twilio API .
- Login and registration in data base Sqlite
- Admin control panel , the admin can view , modify and delete the tickets

<br>

Client Side 
=
- The first window when open the client main class ,welcome window.
![image](https://user-images.githubusercontent.com/31775833/39970531-aa4dea64-56f5-11e8-82b2-c72911e9ff9a.png)

- Then the next window is login .
<br>

![image](https://user-images.githubusercontent.com/31775833/39970175-d34fb72c-56ef-11e8-8db6-40a6f3ea956d.png)
- How it works , if you have an account you can login using email and password , the controller class will send the data to the server using socket then the server will search in database login table and if the tow hashes are equivalents the reservation window will open and if the password is incorrect  the system tell the client is the password is not correct by show alert but if the account doesn’t exists  

![image](https://user-images.githubusercontent.com/31775833/39970201-44caec32-56f0-11e8-82b0-1cb36e0170c1.png)
- If account doesn’t exists the system will ask the client to register new account and the data will send as encrypted data to the server then the server will store to into login table 
- Note that the password will   store as sha256 hash 
- Validation : if the account already exists the system tell user this account already exists .
- The next window is 
![image](https://user-images.githubusercontent.com/31775833/39970214-71db732c-56f0-11e8-9cb4-a23958102598.png)
- Reservation window , the client will fill all the fields with the correct information if any field is empty or any field fill with wrong information the system show alert tell user the wrong information 
- When submit the correct data and send it to the server , the server will send secret code to the email that user entered , and show confirm window >>
![image](https://user-images.githubusercontent.com/31775833/39970222-8d6c2316-56f0-11e8-9fcb-fc8fd6ee5e70.png)
- Confirm window ask the user to select the secret code that he received on his email
- If the secret code the system will send to the client  SMS to his phone .
- The SMS contains the ticket ID and the date of flight 
- If the secret incorrect the system show wrong message to user ,then store this information into Ready table in database 

Server Side
=
- The server side contains the server class and 
- DB connect , SMTP to send email , SMS API to send SMS , and 3 windows 
- The first window Admin control panel 
- This window show the ready tickets with there information 
- And the admin can modify and delete the tickets  but must enter the admin password 
![image](https://user-images.githubusercontent.com/31775833/39970231-cf54821e-56f0-11e8-9ecf-29aa5be1e00d.png)
- Delete window 
![image](https://user-images.githubusercontent.com/31775833/39970254-f3533070-56f0-11e8-88c1-491434e77044.png)
- Delete window , if the ticket doesn’t exists the system tell user by alert ,The deleting process done by passport id 
![image](https://user-images.githubusercontent.com/31775833/39970263-099207bc-56f1-11e8-93bb-5b05c3bbacfd.png)
- On modify window the admin and modify the date of flight 
Notes
=
- Admin password is 123456
- you need to make make an account in twilio website and fill the api information as following 
- 1 you need to change line 15 to your ACCOUNT_SID  in [server > model > SMS_API.java ]
- 2 and change line 17 to your AUTH_TOKEN  in [server > model > SMS_API.java ]
- you need to make gmail account and enable gmail enable less secure apps 
- and change line 37 and put your email and passowrd also in line 44  in server , model ,  Send_Email class 
Using 
=
- The first class you should to run is Server_Server[ server > model > Server_Server.java].
- then on the client side run [Client\src\main\Main.java]
- click login then the new window will open , select your email and password 
- if this is the first time for you click register and create new account 
- then continue .
- On the Admin side run [Server\src\main\Main.java]
-  view ,modify and delete the tickets .
Contact
=
- Facebook :https://www.facebook.com/ismael.alsafadi

- Email : ismaelalsafadi@protonmail.com
<br>
<h2>Thank You</h2>



