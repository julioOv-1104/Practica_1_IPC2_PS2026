package DAOs;

import Modelos.*;
import java.sql.*;
import InterfazGrafica.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import paquete.practica1_ipc2_pizzatycoon.ConexionDB;

public class UsuarioDAO {

    private ConexionDB conexion = new ConexionDB();

    public void login(String nombre, int id, JFrame frame) {

        try (Connection conn = conexion.conectar()) {
            
            System.out.println("id: "+id);
            System.out.println("Nombre: "+nombre);

            String sql = "SELECT * FROM usuario WHERE id_usuario = ? AND nombre = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, nombre);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {//si si encuentra algo
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setId(rs.getInt("id_usuario"));
                nuevoUsuario.setId_sucursal(rs.getInt("id_sucursal"));
                nuevoUsuario.setNombre(rs.getString("nombre"));
                int rol = rs.getInt("id_rol");

                switch (rol) {
                    case 1:
                        nuevoUsuario.setRol(Rol.JUGADOR);
                        break;
                    case 2:
                        nuevoUsuario.setRol(Rol.ADMIN_TIENDA);
                        break;
                    case 3:
                        nuevoUsuario.setRol(Rol.SUPER_ADMIN);
                        break;

                }
                
                mostrarVista(nuevoUsuario, frame);

            }else{
            JOptionPane.showMessageDialog(null,"No se encontro al usuario ", "ADVERTENSIA", 
                    JOptionPane.INFORMATION_MESSAGE);
                System.out.println("NO SE ENCONTRO AL USUARIO");
            }

        } catch (Exception e) {
            System.out.println("ERROR AL LOGUEAR USUARIO");
            e.printStackTrace();
        }
    }
    
    private void mostrarVista(Usuario nuevo, JFrame frame){//muestra la vista dependiendo del rol
        
        switch (nuevo.getRol()) {
                    case JUGADOR:
                        VistaJugador juga = new VistaJugador(nuevo);
                        juga.setVisible(true);
                        break;
                    case ADMIN_TIENDA:
                        VistaAdmin admin = new VistaAdmin(nuevo);
                        admin.setVisible(true);
                        break;
                    case SUPER_ADMIN:
                        VistaSuperAdmin sup = new VistaSuperAdmin(nuevo);
                        sup.setVisible(true);
                        break;

                }
        frame.setVisible(false);
    
        System.out.println("USUARIO LOGUEADO: "+nuevo.getNombre() + ", rol: "+nuevo.getRol()+ ", sucursal: "+nuevo.getId_sucursal());
        
    }
    
    

}
