
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Camilo DLC
 */
public class UsuarioDAO extends Conexion{
    
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
            PreparedStatement ps = null;
            Connection conn = getConexion();
            String sql = "SELECT * FROM usuarios";
            
            
        
        try {
                     
            ps = conn.prepareStatement(sql);
            ResultSet result    = ps.executeQuery(sql);
            
            while (result.next()) {
                Usuario usuario = new Usuario();
                        usuario.setAlias(result.getString(1));
                        usuario.setNombre(result.getString(2));
                        usuario.setApellido(result.getString(3));
                        usuario.setEmail(result.getString(4));
                        usuario.setCel(result.getString(5));
                        usuario.setPassword(result.getString(6));
                        usuario.setDOB(result.getString(7));

                usuarios.add( usuario );
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return usuarios;
    }
    
    
}
