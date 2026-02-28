
package DAOs;
import java.sql.*;
import paquete.practica1_ipc2_pizzatycoon.ConexionDB;

public class DAO {
    
     private ConexionDB conexion = new ConexionDB();

    public ConexionDB getConexion() {
        return conexion;
    }

    public void setConexion(ConexionDB conexion) {
        this.conexion = conexion;
    }
     
     
     
    
}
