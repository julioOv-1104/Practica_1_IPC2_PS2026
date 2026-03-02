
package DAOs;

import Modelos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import paquete.practica1_ipc2_pizzatycoon.ConexionDB;

public class SucursalDAO {
    
    private ConexionDB conexion = new ConexionDB();

    public boolean crearSucursal(String nombre) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO sucursal (nombre_sucursal) VALUES (?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nombre);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR NUEVA SUCURSAL");
            e.getMessage();
        }
        return false;
    }
    
    public ArrayList<Sucursal> obtenerSucursales(){
        
        ArrayList<Sucursal> sucursales = new ArrayList<>();
    
        try (Connection conn = conexion.conectar()) {
            

            String sql = "SELECT * FROM sucursal";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo
               
                Sucursal nueva = new Sucursal(rs.getInt("id_sucursal"),rs.getString("nombre_sucursal"));
                sucursales.add(nueva);
                //guarda los sucursales en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER SUCURSALES");
            e.printStackTrace();
        }
        
        return sucursales;
        
    }
    
    public boolean cambiarAdminSucursal(int id_sucursal, int id_usuario) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE usuario SET id_sucursal = ? WHERE id_usuario = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id_sucursal);
            stm.setInt(2, id_usuario);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CAMBIAR ADMIN PARA SUCURSAL CON ID: "+id_sucursal);
            e.getMessage();
        }
        return false;
    }
    
    
     public boolean cambiarNombreSucursal(String nombre_sucursal, int id_sucursal) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE sucursal SET nombre_sucursal = ? WHERE id_sucursal = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nombre_sucursal);
            stm.setInt(2, id_sucursal);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CAMBIAR NOMBRE DE SUCURSAL CON ID: "+id_sucursal);
            e.getMessage();
        }
        return false;
    }
}
