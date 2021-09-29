
package modelo;


public class Usuario {
    private String alias;
    private String nombre;
    private String apellido;
    private String email;
    private String cel;
    private String password;
    private String DOB;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    
    //Para llenar únicamente las tres columnas deseadas de la tabla de usuarios
    
    public Object[] toArrayBasic() {
        Object[] data = {alias, nombre, apellido};
        return data;
    }
    
    //Este para llenar los 3 detalles en la tabla invisible, para poderlos
    //obtener luego en los respectivos campos
    public Object[] toArrayDetail() {
        Object[] data = {email, cel, DOB};
        return data;
    }
}
