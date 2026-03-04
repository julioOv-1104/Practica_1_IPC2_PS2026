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

    public ArrayList<Producto> obtenerProductos() {

        ArrayList<Producto> productos = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM producto";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                Producto nuevo = new Producto(rs.getInt("id_producto"), rs.getString("nombre_producto"));
                productos.add(nuevo);
                //guarda los productos en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PRODUCTOS");
            e.printStackTrace();
        }

        return productos;

    }

    public ArrayList<Producto> obtenerProductosPorSucursal(int id) {//recibe el id de la sucursal

        ArrayList<Producto> productos = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            //busca todos los productos que esten activos en una sucursal
            String sql = "SELECT p.nombre_producto, sp.estado_activo "
                    + "FROM sucursal_producto sp "
                    + "JOIN producto p ON sp.id_producto = p.id_producto "
                    + "WHERE sp.id_sucursal = ? AND estado_activo = true";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                Producto nuevo = new Producto(rs.getString("nombre_producto"));
                productos.add(nuevo);
                //guarda los productos en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PRODUCTOS DE LA SUCURSAL: " + id);
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

    public boolean revisarExistenciaDeProducto(int id, String nombre) {

        try (Connection conn = conexion.conectar()) {

            //busca si el producto ya existe en la sucursal
            String sql = "SELECT 1 "
                    + "    FROM sucursal_producto sp "
                    + "    JOIN producto p ON sp.id_producto = p.id_producto "
                    + "    WHERE sp.id_sucursal = ? "
                    + "    AND p.nombre_producto = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, nombre);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {//si si encuentra algo

                return asignarDesasignarProductos(id, nombre);//si si existe cambia el estado de activo

            } else {
                return cambiarEstadoProducto(id, nombre);//si no existe lo agrega

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER EL PRODUCTO " + nombre + " DE LA SUCURSAL: " + id);
            e.printStackTrace();
        }

        return false;
    }

    private boolean asignarDesasignarProductos(int id, String nombre) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE sucursal_producto sp "
                    + "JOIN producto p ON sp.id_producto = p.id_producto "
                    + "SET sp.estado_activo = NOT sp.estado_activo "
                    + "WHERE sp.id_sucursal = ? "
                    + "AND p.nombre_producto = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, nombre);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL ACTIVAR O DESACTIVAR PRODUCTO");
            e.getMessage();
        }

        return false;
    }

    private boolean cambiarEstadoProducto(int id, String nombre) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO sucursal_producto (id_sucursal, id_producto) "
                    + "SELECT ?, id_producto "
                    + "FROM producto "
                    + "WHERE nombre_producto = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, nombre);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL AGREGAR PRODUCTO CON SUCURSAL");
            e.getMessage();
        }
        return false;

    }

}
