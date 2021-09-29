
package videopro;

import controlador.Ctrlvideopro;
import modelo.Pelicula;
import modelo.PeliculaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.frmvideopro;

/**
 *
 * @author Camilo DLC
 */
public class videopro {
    

    public static void main(String[] args){
        Pelicula pmod = new Pelicula();
        PeliculaDAO pcon = new PeliculaDAO();
        Usuario umod = new Usuario();
        UsuarioDAO ucon = new UsuarioDAO();
        
        frmvideopro frm = new frmvideopro();
        

        
        Ctrlvideopro ctrl = new Ctrlvideopro(pmod, pcon, umod, ucon, frm);
        ctrl.iniciar();
        frm.setVisible(true);
    }

}
