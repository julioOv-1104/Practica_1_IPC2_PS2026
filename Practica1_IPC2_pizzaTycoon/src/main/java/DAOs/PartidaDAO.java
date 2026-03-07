package DAOs;

import Modelos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import paquete.practica1_ipc2_pizzatycoon.ConexionDB;

public class PartidaDAO {

    private ConexionDB conexion = new ConexionDB();

    public ArrayList<ConfigNivel> obtenerTiemposNiveles() {

        ArrayList<ConfigNivel> niveles = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM config_nivel";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                ConfigNivel nuevo = new ConfigNivel(rs.getInt("numero_nivel"), rs.getInt("tiempo_base"));
                niveles.add(nuevo);
                //guarda los tiempos en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER TIEMPOS POR NIVELES");
            e.printStackTrace();
        }

        return niveles;

    }

    public int obtenerTiemposPorNivel(int nivel) {

        int tiempo = 0;//recupera el tiempo base dependiendo del nivel actual

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT tiempo_base FROM config_nivel WHERE numero_nivel = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, nivel);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {//si si encuentra algo

                tiempo = rs.getInt("tiempo_base");
                return tiempo;

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER TIEMPO BASE DEL NIVEL: " + nivel);
            e.printStackTrace();
        }

        return tiempo;

    }

    public boolean cambiarTiempoNivel(int tiempoBase, int id_nivel) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE config_nivel SET tiempo_base = ? WHERE id_nivel = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, tiempoBase);
            stm.setInt(2, id_nivel);

            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CAMBIAR EL TIEMPO BASE");
            e.getMessage();
        }
        return false;
    }

    public int registrarPartida(Usuario usuario) {

        int id_partida = 0;

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO partida (id_usuario, id_sucursal) VALUES (?,?)";
            PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, usuario.getId());
            stm.setInt(2, usuario.getId_sucursal());

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                id_partida = rs.getInt(1);
                System.out.println("La partida se guardó con el ID: " + id_partida);
                return id_partida;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR NUEVA PARTIDA");
            e.getMessage();
        }

        return id_partida;

    }

    public int regitrarPedido(int id_partida, int tiempo) {

        int id_pedido = 0;

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO pedido (id_partida, tiempo_limite) VALUES (?,?)";
            PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, id_partida);
            stm.setInt(2, tiempo);

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                id_pedido = rs.getInt(1);
                System.out.println("El pedido se guardó con el ID: " + id_pedido);
                return id_pedido;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR NUEVO PEDIDO");
            e.getMessage();
        }

        return id_pedido;

    }

    public void regitrarDetallePedido(int id_pedido, int id_producto) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto) VALUES (?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id_pedido);
            stm.setInt(2, id_producto);

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR NUEVO DETALLE_PEDIDO");
            e.getMessage();
        }

    }

    public void cambiarEstadoDePedido(int id_estado, int id_pedido) {
        
        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE pedido SET id_estado = ? WHERE id_pedido = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id_estado);
            stm.setInt(2, id_pedido);

            stm.executeUpdate();
            

        } catch (SQLException e) {
            System.out.println("ERROR AL CAMBIAR ESTADO DE PEDIDO");
            e.getMessage();
        }
        
    }

     public void terminarPartida(int id_partida, int puntajeTotal, int nivelAlcanzado) {
         //cuando termina la partida guarda los resultados finales
        
        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE partida SET puntaje_total = ? , nivel_alcanzado = ? WHERE id_partida = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, puntajeTotal);
            stm.setInt(2, nivelAlcanzado);
            stm.setInt(3, id_partida);

            stm.executeUpdate();
            

        } catch (SQLException e) {
            System.out.println("ERROR AL TERMINAR/ACTUALIZAR PARTIDA");
            e.getMessage();
        }
        
    }
    
     
     public void regitrarHistorialPedido(int id_pedido, int id_estado) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO historial_estado (id_pedido, id_estado) VALUES (?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id_pedido);
            stm.setInt(2, id_estado);
            

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR NUEVO HISTORIAL_ESTADO");
            e.getMessage();
        }

    }
     
     
     
}
