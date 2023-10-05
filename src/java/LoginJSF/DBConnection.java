package LoginJSF;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benny
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
       public Connection myConnect(){
      Connection connect = null;
   
    try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    connect = DriverManager.getConnection(""
                            + "jdbc:mysql://localhost/laundry_basket"
                            + "?user=root&password=");
                    System.out.println("Connection Succesful!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cannot connect to Property DB "+e.getMessage());
        }
            return connect;
               
     }
       public static void main(String args[]){
           DBConnection test = new DBConnection();
           test.myConnect();
       }
}
