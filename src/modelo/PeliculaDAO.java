/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class PeliculaDAO extends Conexion{
    
    
        public void insertarPelicula(Pelicula pelicula){
            
            PreparedStatement ps = null;
            Connection conn = getConexion();
            String sql = "INSERT INTO Película(id_pelicula, título, resumen ,año ,director) VALUES"
            +"(?, ?, ?, ?, ?);";
            
        try {
            
            ps = conn.prepareStatement(sql);

            ps.setString(1, pelicula.getId()  );
            ps.setString(2, pelicula.getTitulo()  );
            ps.setString(3, pelicula.getResumen()  );
            ps.setString(4, pelicula.getAño()  );
            ps.setString(5, pelicula.getDirector()  );
            
            
            int rowsInserted = ps.executeUpdate();
            if(rowsInserted > 0) 
                JOptionPane.showMessageDialog(null, "La película fue agregada exitosamente !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
        public void actualizaPelicula(Pelicula pelicula) {
            
            PreparedStatement ps = null;
            Connection conn = getConexion();
            String sql = ("UPDATE Película SET título=?,resumen=?,año=?,director=?"
            + "WHERE id_pelicula = ?");
            
            
        try {
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getResumen());
            ps.setString(3, pelicula.getAño());
            ps.setString(4, pelicula.getDirector());
            ps.setString(5, pelicula.getId());

            
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) 
                JOptionPane.showMessageDialog(null, "La película fue actualizada exitosamente !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
        public ArrayList<Pelicula> getPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        
            PreparedStatement ps = null;
            Connection conn = getConexion();
            String sql = "SELECT * FROM película";
            
            
        
        try {
                     
            ps = conn.prepareStatement(sql);
            ResultSet result    = ps.executeQuery(sql);
            
            while (result.next()) {
                Pelicula pelicula = new Pelicula();
                        pelicula.setId(result.getString(1));
                        pelicula.setTitulo(result.getString(2));
                        pelicula.setResumen(result.getString(3));
                        pelicula.setAño(result.getString(4));
                        pelicula.setDirector(result.getString(5));
//                                                   result.getString(2),
//                                                   result.getString(3),
//                                                   result.getString(4),
//                                                   result.getString(5));
                peliculas.add( pelicula );
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return peliculas;
    }
        
        
    public ArrayList<Pelicula> filtraPelicula(String busca) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        
            PreparedStatement ps = null;
            Connection conn = getConexion();
            String sql = "SELECT * FROM película WHERE título LIKE ?" +
                            "OR resumen LIKE ? OR año LIKE ? OR director LIKE ?";
        
     try {

            String query = "%"+busca+"%";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setString(3, query);
            ps.setString(4, query);

            
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                        Pelicula pelicula = new Pelicula();
                        pelicula.setId(result.getString(1));
                        pelicula.setTitulo(result.getString(2));
                        pelicula.setResumen(result.getString(3));
                        pelicula.setAño(result.getString(4));
                        pelicula.setDirector(result.getString(5));
                peliculas.add(pelicula);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return peliculas;
    }
    
    
    public void borrarPelicula(String id) {
        PreparedStatement ps = null;
        Connection conn = getConexion();
        String sql = "DELETE FROM Película WHERE id_pelicula=?;";
        
        try {
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "La Película fue borrada exitosamente !");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
    }
    
    
    
    
    
}
