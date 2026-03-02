
package Modelos;

public class Usuario {
   
    private String nombre;
    private Rol rol;
    private int id;
    private int id_sucursal;

    public Usuario(int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public Usuario() {
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
    
    
    
    
    
}
