/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.*;

/**
 *
 * @author Adeesha
 */
public class DbConnect {
    public static Connection connectDB()
    {
        Connection con = null;
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
        }
        
        catch (Exception e)
        {
            System.out.println(e);
        }
        return con;
    }
}

