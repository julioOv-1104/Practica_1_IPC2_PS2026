
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    
}
