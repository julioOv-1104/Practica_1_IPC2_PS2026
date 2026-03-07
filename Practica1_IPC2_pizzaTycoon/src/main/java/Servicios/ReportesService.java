
package Servicios;

import Modelos.Partida;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReportesService {
    
    
    public void exportarCSV(ArrayList<Partida> partidas, String rutaArchivo) {

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {

        // Encabezados del documento
        writer.write("NombreJugador,PuntajeTotal, NivelAlcanzado");
        writer.newLine();

        // Datos obtenidos de la DB
        for (Partida p : partidas) {

            String linea = p.getUsuario().getNombre()+ "," +
                           p.getPuntaje_total()+ "," +
                           p.getNivel_alcanzado();
 

            writer.write(linea);
            writer.newLine();
        }

        JOptionPane.showMessageDialog(null, "Reportes exportados con exito a "+rutaArchivo, "Exportado",
                    JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Reporte CSV generado correctamente");

    } catch (IOException e) {
        System.out.println("ERROR AL IMPORTAR REPORTE POR SUCURSAL");
        e.getMessage();
    }
}
    
}
