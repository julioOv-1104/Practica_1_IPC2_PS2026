
package Modelos;

public class Producto {
    
    private int id_producto;
    private String nombre_producto;

    public Producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    

    public Producto(int id_producto, String nombre_producto) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
    
    @Override
    public String toString(){
    return id_producto+"--------------------"+nombre_producto;
    }
    
    
    
}
