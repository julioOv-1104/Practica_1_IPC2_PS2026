package DAOs;

import Modelos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import paquete.practica1_ipc2_pizzatycoon.ConexionDB;

public class ReportesDAO {

    private ConexionDB conexion = new ConexionDB();

    public ArrayList<Partida> obtenerPartidasSucursal(int parametro, int id) {

        ArrayList<Partida> partidas = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql1 = "SELECT u.nombre, p.puntaje_total, p.nivel_alcanzado "
                    + "FROM partida p JOIN usuario u "
                    + "ON p.id_usuario = u.id_usuario WHERE p.id_sucursal = ?";

            String sql2 = "SELECT u.nombre, p.puntaje_total, p.nivel_alcanzado "
                    + "FROM partida p JOIN usuario u ON p.id_usuario = u.id_usuario WHERE p.id_sucursal = ? "
                    + "ORDER BY p.puntaje_total DESC LIMIT 10";

            PreparedStatement stm = conn.prepareStatement(sql1);

            switch (parametro) {//recibe el parametro para saber si es ranking o no
                case 1:
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, id);
                    break;
                case 2:
                    stm = conn.prepareStatement(sql2);
                    stm.setInt(1, id);
                    break;

            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                Partida nueva = new Partida(rs.getString("nombre"), rs.getInt("puntaje_total"), rs.getInt("nivel_alcanzado"));
                partidas.add(nueva);
                //guarda las partidas en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PARTIDAS DE SUCURSAL");
            e.printStackTrace();
        }

        return partidas;

    }
    
    
    public ArrayList<Partida> obtenerPartidasGlobales(int parametro) {

        ArrayList<Partida> partidas = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql1 = "SELECT u.nombre, p.puntaje_total, p.nivel_alcanzado "
                    + "FROM partida p JOIN usuario u "
                    + "ON p.id_usuario = u.id_usuario";

            String sql2 = "SELECT u.nombre, p.puntaje_total, p.nivel_alcanzado "
                    + "FROM partida p JOIN usuario u ON p.id_usuario = u.id_usuario "
                    + "ORDER BY p.puntaje_total DESC LIMIT 10";

            PreparedStatement stm = conn.prepareStatement(sql1);

            switch (parametro) {//recibe el parametro para saber si es ranking o no
                case 1:
                    stm = conn.prepareStatement(sql1);
                   
                    break;
                case 2:
                    stm = conn.prepareStatement(sql2);
                  
                    break;

            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                Partida nueva = new Partida(rs.getString("nombre"), rs.getInt("puntaje_total"), rs.getInt("nivel_alcanzado"));
                partidas.add(nueva);
                //guarda las partidas en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PARTIDAS GLOBALES");
            e.printStackTrace();
        }

        return partidas;

    }

    
    public ArrayList<Partida> obtenerPartidasJugador(int id) {

        ArrayList<Partida> partidas = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT s.nombre_sucursal, p.puntaje_total, p.nivel_alcanzado "
                    + "FROM partida p JOIN sucursal s "
                    + "ON p.id_sucursal = s.id_sucursal WHERE p.id_usuario = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {//si si encuentra algo

                Partida nueva = new Partida(rs.getString("nombre_sucursal"), rs.getInt("puntaje_total"), rs.getInt("nivel_alcanzado"));
                partidas.add(nueva);
                //guarda las partidas en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PARTIDAS DE JUGADOR");
            e.printStackTrace();
        }

        return partidas;

    }
    
}
