/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpoa;

import java.sql.*;
/**
 *
 * @author valentinruiz
 */
public class DBConnection {
    
    public static Connection connect(){
        
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/OpenGestion","root","");
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return con;
    }
    
}
