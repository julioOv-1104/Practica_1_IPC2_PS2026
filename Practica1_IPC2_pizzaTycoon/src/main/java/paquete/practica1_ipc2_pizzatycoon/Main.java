
package paquete.practica1_ipc2_pizzatycoon;
import InterfazGrafica.*;

public class Main {

    public static void main(String[] args) {
        ConexionDB conexion = new ConexionDB();
        conexion.conectar();
        LoginJF login = new LoginJF();
        login.setVisible(true);
    }
}
