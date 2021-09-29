
package modelo;

import java.util.Vector;

/**
 *
 * @author Camilo DLC
 */
public class Pelicula {

    private String id;
    private String titulo;
    private String resumen;
    private String año;
    private String director;    

    
//    public Pelicula(String id, String titulo, String resumen, String año, String director) {
//        this.id = id;
//        this.titulo = titulo;
//        this.resumen = resumen;
//        this.año = año;
//        this.director = director;
//    }

  
    

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Object[] toArray() {
//        public Object[] toArray(){
        Object[] data = {id, titulo, resumen, año, director};
//        Vector<?> data = {id, titulo, resumen, año, director};
        return data;
    }
    
    
    
    
}
