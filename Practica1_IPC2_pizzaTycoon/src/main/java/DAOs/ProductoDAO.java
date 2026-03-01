
package DAOs;

import Modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import paquete.practica1_ipc2_pizzatycoon.ConexionDB;

public class ProductoDAO {
    
     private ConexionDB conexion = new ConexionDB();

    public boolean crearProducto(String nombre) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO producto (nombre_producto) VALUES (?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nombre);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR NUEVO PRODUCTO");
            e.getMessage();
        }
        return false;
    }
    
    public ArrayList<Producto> obtenerProductos(){
        
        ArrayList<Producto> productos = new ArrayList<>();
    
        try (Connection conn = conexion.conectar()) {
            

            String sql = "SELECT * FROM producto";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo
               
                Producto nuevo = new Producto(rs.getInt("id_producto"),rs.getString("nombre_producto"));
                productos.add(nuevo);
                //guarda los productos en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PRODUCTOS");
            e.printStackTrace();
        }
        
        return productos;
        
    }
    
    public boolean modificarProducto(String nombre, int id) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE producto SET nombre_producto = ? WHERE id_producto = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setInt(2, id);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL MODIFICAR PRODUCTO");
            e.getMessage();
        }
        return false;
    }
    
}
