
package DAOs;

import Modelos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                //guarda los sucursales en una lista

            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER SUCURSALES");
            e.printStackTrace();
        }

        return niveles;

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
    
}
