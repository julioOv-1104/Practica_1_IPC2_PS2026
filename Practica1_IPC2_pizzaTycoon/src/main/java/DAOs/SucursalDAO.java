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

    public ArrayList<Sucursal> obtenerSucursales() {

        ArrayList<Sucursal> sucursales = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM sucursal";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                Sucursal nueva = new Sucursal(rs.getInt("id_sucursal"), rs.getString("nombre_sucursal"));
                sucursales.add(nueva);
                //guarda los sucursales en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER SUCURSALES");
            e.printStackTrace();
        }

        return sucursales;

    }

    public boolean estaSucursalOcupada(int id_sucursal) {

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT 1 FROM usuario WHERE id_sucursal = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id_sucursal);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR ADMIN PARA LA SUCURSAL CON ID: " + id_sucursal);
            e.getMessage();
        }
        return false;
    }

    public boolean cambiarAdminSucursal(int id_sucursal, String nombre) {

        try (Connection conn = conexion.conectar()) {

            String sql1 = "UPDATE usuario SET id_sucursal = ? WHERE nombre = ?";
            String sql2 = "UPDATE usuario SET id_sucursal = NULL WHERE nombre = ?";
            PreparedStatement stm = conn.prepareStatement(sql1);

            switch (id_sucursal) {//si el id = 0 es porque se quiere desvincular el admin a su sucursal
                case 0:
                    stm = conn.prepareStatement(sql2);
                    stm.setString(1, nombre);
                    break;
                default:
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, id_sucursal);
                    stm.setString(2, nombre);
            }

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CAMBIAR ADMIN PARA SUCURSAL CON ID: " + id_sucursal);
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
            System.out.println("ERROR AL CAMBIAR NOMBRE DE SUCURSAL CON ID: " + id_sucursal);
            e.getMessage();
        }
        return false;
    }
}
