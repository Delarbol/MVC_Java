
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Pelicula;
import modelo.PeliculaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.frmvideopro;

/**
 *
 * @author Admin
 */
public class Ctrlvideopro implements ActionListener,MouseListener,KeyListener{
    private Pelicula pmod;
    private PeliculaDAO pcon;
    private Usuario umod;
    private UsuarioDAO ucon;
    private frmvideopro frm;
    
    
    public Ctrlvideopro ( Pelicula pmod,
                           PeliculaDAO pcon,
                           Usuario umod,
                           UsuarioDAO ucon,
                           frmvideopro frm)
    {
        this.pmod = pmod;
        this.pcon = pcon;
        this.umod = umod;
        this.ucon = ucon;
        
        this.frm = frm;
        this.frm.jbtnAdd.addActionListener(this);
        this.frm.jbtnClean.addActionListener(this);
        this.frm.jbtnDelete.addActionListener(this);
        this.frm.jbtnExit.addActionListener(this);
        this.frm.jbtnUpdate.addActionListener(this);
        this.frm.jTable1.addMouseListener(this);
        this.frm.jTable2.addMouseListener(this);
        this.frm.jtxtBuscar.addKeyListener(this);

        
    }
    public void iniciar()
    {
        frm.setTitle("4chan Video Amazing DB manager");
        frm.setLocationRelativeTo(null);
        try {
            URL resource = frm.getClass().getResource("/img/icon.png");
            BufferedImage image = ImageIO.read(resource);
            frm.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace(); 
            }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmvideopro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmvideopro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmvideopro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmvideopro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        actualizaPeliculas();
        actualizaUsuarios();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        // Para el botón de agregar película
        if(e.getSource() == frm.jbtnAdd){
            pmod.setTitulo(frm.jtxtTitle.getText());
            pmod.setResumen(frm.jtxtResumen.getText());
            pmod.setAño(frm.jtxtAnio.getText());
            pmod.setDirector(frm.jtxtDirector.getText());
            pmod.setId(null);
            pcon.insertarPelicula(pmod);
            

            actualizaPeliculas();
            limpiar();
            
        }
        // Para el botón de limpiar
        if(e.getSource() == frm.jbtnClean){
            limpiar();
            }
        //Para el botón de actualizar
        if(e.getSource() == frm.jbtnUpdate){
            DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable1.getModel();
                
            int SelectedRows = frm.jTable1.getSelectedRow();
            String id = RecordTable.getValueAt(SelectedRows, 0).toString();
            
            
            pmod.setTitulo(frm.jtxtTitle.getText());
            pmod.setResumen(frm.jtxtResumen.getText());
            pmod.setAño(frm.jtxtAnio.getText());
            pmod.setDirector(frm.jtxtDirector.getText());
            pmod.setId(id);
            
            pcon.actualizaPelicula(pmod);

            actualizaPeliculas();
     
        }
        if(e.getSource() == frm.jbtnDelete){
            DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable1.getModel();
            int SelectedRows = frm.jTable1.getSelectedRow();
            String id = RecordTable.getValueAt(SelectedRows, 0).toString();
            int deleteItem = JOptionPane.showConfirmDialog(null,"Estás seguro de querer borrar?",
            "Advertencia",JOptionPane.YES_NO_OPTION);
            if (deleteItem ==JOptionPane.YES_OPTION ) 
             {
             pcon.borrarPelicula(id);
             }


            actualizaPeliculas();
            limpiar();
     
        }
        
        
    }
    
    public void limpiar(){
        frm.jtxtTitle.setText("");
        frm.jtxtResumen.setText("");
        frm.jtxtAnio.setText("");
        frm.jtxtDirector.setText("");
    }
    
    public void actualizaPeliculas(){
        DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable1.getModel();
        RecordTable.setRowCount(0);
        ArrayList<Pelicula> peliculas = pcon.getPeliculas();
        for(int i=0; i<peliculas.size(); i++)
            {
            RecordTable.addRow(peliculas.get(i).toArray());
            }
        }    
        
    
    public void actualizaUsuarios(){
        DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable2.getModel();
        DefaultTableModel RecordTable2 = (DefaultTableModel)frm.jTable3.getModel();
        RecordTable.setRowCount(0);
        RecordTable2.setRowCount(0);
        ArrayList<Usuario> usuarios = ucon.getUsuarios();
        for(int i=0; i<usuarios.size(); i++){
            RecordTable.addRow(usuarios.get(i).toArrayBasic());
            RecordTable2.addRow(usuarios.get(i).toArrayDetail());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent m){
    
        if (m.getSource() == frm.jTable1){
                DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable1.getModel();
                int SelectedRows = frm.jTable1.getSelectedRow();
                frm.jtxtTitle.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
                frm.jtxtResumen.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
                frm.jtxtAnio.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
                frm.jtxtDirector.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
        }
        if (m.getSource() == frm.jTable2){
                DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable2.getModel();
                DefaultTableModel RecordTable2 = (DefaultTableModel)frm.jTable3.getModel();
                int SelectedRows = frm.jTable2.getSelectedRow();
                frm.jtxtAlias.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
                frm.jtxtNombre.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
                frm.jtxtApellido.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
                frm.jtxtEmail.setText(RecordTable2.getValueAt(SelectedRows, 0).toString());
                frm.jtxtCelular.setText(RecordTable2.getValueAt(SelectedRows, 1).toString());
                frm.jtxtDOB.setText(RecordTable2.getValueAt(SelectedRows, 2).toString());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == frm.jtxtBuscar){
            ArrayList<Pelicula> peliculas = pcon.filtraPelicula(frm.jtxtBuscar.getText());
            DefaultTableModel RecordTable = (DefaultTableModel)frm.jTable1.getModel();
            RecordTable.setRowCount(0);
            for(int i=0; i<peliculas.size(); i++)
            {
            RecordTable.addRow(peliculas.get(i).toArray());
            }
        }
    }

    

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        }

    @Override
    public void keyPressed(KeyEvent e) {
         }

    
}
