package model;


/*
 * DB_Connect class 
* this class connect with database (sqlite) 
 */
import static controllers.ShowController.data;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.MessageFormat;
import org.sqlite.SQLiteConnection;

public class DB_Connect {

    /* add_user_to_database this method will add new use to database in Loin table 
    the args is : 1 : use name  
    2: email 
    3 : password will stored as hash  sha256 
    
     */

    public static String add_user_to_database(String Name, String Email, String Password) {
        System.out.println(Name);
        System.out.println(Password);

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("create table if not exists Login(ID integer primary key autoincrement,Name text,Email text,Password text)");
            System.out.println("Done 1");
            String sql = String.format("INSERT INTO Login (Name,Email,Password) "
                    + "VALUES ('%s','%s','%s');", Name, Email, Password);
            System.out.println(sql);
            //
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

        System.out.println("Records created successfully");
        return null;

    }
////////

    /*
    add_data_to_database this method will add ticket information to data base  in Data table 
    args  :NAME,PID,DB,PB,Email,Phone,FromC,ToC,secret
    1: NAME full name of customer
    2: PID passport id 
    3: DB the date of birth 
    4 : PB the palse of birth 
    5 : email the email of customer
    6 : phone number 
    7 : from > the source 
    8 : to the distenation 
    9 : secret > this secret number will sent to customer email to make aouthentication .
    
    
     */
    public static String add_data_to_database(String Name, String PID, String Email, String Phone, String From, String To, String DB, String PB, String Type , String secret) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("create table if not exists Data(ID integer primary key autoincrement,NAME text,PID text ,DB text,PB text,Email text,Phone text,FromC text,ToC text,Type text,secret text )");
            System.out.println("Done 1");
            String sql = String.format("INSERT INTO Data (NAME,PID,DB,PB,Email,Phone,FromC,ToC,Type,secret) "
                    + "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", Name, PID, DB, PB, Email, Phone, From, To, Type, secret);
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

        System.out.println("Records created successfully");
        return null;
    }
///////
/*
    add_ready_to_database
    this method will add the ready tickets to database in Ready table 
    after aouthurization this method will add the Date of flight and the thicket id to the database 
    args  :
    1: PID passport id 
    2: Date  the date of filght 
    3: Ticket  the ticket id 
    
    
     */
    public static String add_ready_to_database(String PID, String Date, String Ticket) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("create table if not exists Ready(ID integer primary key autoincrement,PID text,Date text,Ticket text)");
            System.out.println("Done 1");
            String sql = String.format("INSERT INTO Ready (PID,Date,Ticket) "
                    + "VALUES ('%s','%s','%s');", PID, Date, Ticket);
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

        System.out.println("Records created successfully");
        return null;
    }

    /*
    SearchSecret this method will return the secret code by search in databse using the PID
    
    
     */
    public static String SearchSecret(String PID) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //System.out.println("Done 1");
            String sql = String.format("SELECT * FROM Data WHERE PID='%s'", PID);

            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.getString("secret"));
            String result = rs.getString("secret");

            return result;
        } catch (Exception e) {
            return "wrong secret";
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

    }
//////
/*
    SearchPhone this method will return the phone number of customer by search using the PID 
    phone number will help us to send the ticket id and the date of filght to cutomer phone 
     */
    public static String SearchPhone(String PID) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //System.out.println("Done 1");
            String sql = String.format("SELECT * FROM Data WHERE PID='%s'", PID);

            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.getString("secret"));
            String result = rs.getString("Phone");

            return result;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

        System.out.println("Records created successfully");
        return null;
    }

///////
    public static String SearchPID(String PID) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            //System.out.println("Done 1");
            String sql = String.format("SELECT * FROM Ready WHERE PID='%s'", PID);

            ResultSet rs = c.createStatement().executeQuery(sql);
            //System.out.println(rs.getString("secret"));
            String result = rs.getString("Ticket");

            return result;
        } catch (Exception e) {
            System.out.println(e);

            return "not exist";

        } finally {
            try {

                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

    }

    ///////  
    /*
    SearchPassword this method will return the password  of customer by search in database using the email 
    this method will help us to chick if the password is correct or not when login into system 
    
     */
    public static String SearchPassword(String Email) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //System.out.println("Done 1");
            String sql = String.format("SELECT * FROM Login WHERE Email='%s'", Email);

            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.getString("secret"));
            String result = rs.getString("Password");

            return result;
        } catch (Exception e) {
            return "doesnt found";

        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

    }

    //////////////////////
    public static String SearchName(String Email) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //System.out.println("Done 1");
            String sql = String.format("SELECT * FROM Login WHERE Email='%s'", Email);

            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.getString("secret"));
            String result = rs.getString("Name");

            return result;
        } catch (Exception e) {
            return "Name_Not_Exists";

        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

    }

    /////////////////////
    /*
    hash256 this method will return the hash of string value using sha256 algorithm 
    
     */
    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

/////
    /*
        Delete this method will delete the information of  ticket from Data table if customer want to cancel the reservation 

     */
    public static String Delete(String PID) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //System.out.println("Done 1");
            String sql = String.format("DELETE FROM Data WHERE PID='%s'", PID);
            stmt.executeUpdate(sql);

            return "deleting done";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "cant delete";

        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

    }

    /*    Delete_Ticket this method will delete the Ready ticket from Ready table if customer want to cancel the reservation 
     */
    public static String Delete_Ticket(String PID) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            //System.out.println("Done 1");
            String sql = String.format("DELETE FROM Ready WHERE PID='%s'", PID);
            stmt.executeUpdate(sql);

            return "deleting done";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "cant delete";
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }
    }

    ///// update
    // UPDATE Ready SET Date = '%s' WHERE ID = '%s';
    /*
    
    Update_Ticket this method will update the Date of filght if the customer want to delay the Date of flight 
    
    
     */
    public static String Update_Ticket(String PID, String Date) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //System.out.println("Done 1");
            String sql = String.format("UPDATE Ready SET Date = '%s' WHERE PID = '%s'", Date, PID);
            stmt.executeUpdate(sql);

            return "deleting done ";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }

        System.out.println("Records created successfully");
        return null;
    }

    ////////////////
    public static Connection ShowAll() throws SQLException {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            System.out.println("Done 1");
            String sql = String.format("SELECT * FROM Ready");

            ResultSet rs = c.createStatement().executeQuery(sql);

            while (rs.next()) {

                data.add(new Ticket(rs.getString("PID"), rs.getString("Date"), rs.getString("Ticket")));
            }
//            String result = rs.getString("Password");

        } catch (Exception e) {

        } finally {
            try {

                c.commit();
                c.close();
            } catch (Exception e) {

            }
        }
        return null;
    }

    ///////////////
    public static void main(String args[]) {
        // create database
//     Connection c = null;
//    try {
//      Class.forName("org.sqlite.JDBC");
//      c = DriverManager.getConnection("jdbc:sqlite:projekt.db");
//    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
//    }
//    System.out.println("Opened database successfully");

    }
}
