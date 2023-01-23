    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efficient.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ramu Maloth
 */
public class DBConnection {
    private static Connection con = null;
    
   public static Connection getConnection(){
       try {
           DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Efficient","root","root");
           if(con!=null){
           return con;
           }else{
           return null;
           }
       } catch (Exception e) {
           System.out.println("Error at DBConnection "+e.getMessage());
       }
       return con;
   }
}
