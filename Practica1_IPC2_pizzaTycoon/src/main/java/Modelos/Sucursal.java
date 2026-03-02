
package Modelos;

public class Sucursal {
    
    private int id_sucursal;
    private String nombre_sucursal;

    public Sucursal(int id_sucursal, String nombre_sucursal) {
        this.id_sucursal = id_sucursal;
        this.nombre_sucursal = nombre_sucursal;
    }
    
    

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre_sucursal() {
        return nombre_sucursal;
    }

    public void setNombre_sucursal(String nombre_sucursal) {
        this.nombre_sucursal = nombre_sucursal;
    }
    
    
}
