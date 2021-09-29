/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.*;
/**
 *
 * @author Admin
 */
public class Conexion {
    
    
    public Connection getConexion(){
        JSONParser parser = new JSONParser();
        Connection conn = null;
        
    try {
            String credentials_path = System.getProperty("user.dir") + "/src/modelo/db_credentials.json";
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(credentials_path));
            
            String host     = (String)jsonObject.get("db_ip");
            String port     = (String)jsonObject.get("dp_port");
            String username = (String)jsonObject.get("db_user");
            String password = (String)jsonObject.get("db_pssword");
            String dbURL = "jdbc:mysql://"+host+":"+port+"/videomatch" ;
            
            conn = DriverManager.getConnection(dbURL, username, password);
            //if( conn != null ) 
            //    System.out.println ( "Conectado" );
        } 
        catch( SQLException ex ) {
            System.err.println(ex);
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return conn;
        
    }
}
