
package paquete.practica1_ipc2_pizzatycoon;

import java.sql.*;

public class ConexionDB {
    
    private Connection conn;
    private String URL = "jdbc:mysql://localhost:3306/game_eshop";
    private String USUARIO = "root";
    private String CONTRASENNA = "julioadmin";
    
    public Connection conectar(){
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASENNA);
            System.out.println("Conexion exitosa con pizza express tycoon");
            return conn;
            
        } catch (SQLException e) {
            System.out.println("Error al conectar "+e.getMessage());
            return null;
        }catch(ClassNotFoundException e){
            System.out.println("ERROR EN LA CONEXION "+e.getMessage());
            return null;
        }
    }
    
}
