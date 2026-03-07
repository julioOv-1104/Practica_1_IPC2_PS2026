package Modelos;

import DAOs.PartidaDAO;
import javax.swing.JButton;
import javax.swing.Timer;

public class Pedido {

    private int id_pedido;
    private int tiempo_limite = 0;
    private int tiempoRestante = 0;
    private int id_partida;
    private Producto producto;
    private int id_estado = 1;
    private JButton boton;
    private Timer timerPedido;

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public JButton getBoton() {
        return boton;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }

    public int getTiempo_limite() {
        return tiempo_limite;
    }

    public void setTiempo_limite(int tiempo_limite) {
        this.tiempo_limite = tiempo_limite;
    }

    private void controlarTiempo() {

        PartidaDAO partidaDao = new PartidaDAO();

        if (tiempoRestante > 0) {
            tiempoRestante--;
        } else {

            id_estado = 6;//si se acabó el tiempo lo marca como no entregado
            partidaDao.cambiarEstadoDePedido(id_estado, id_pedido);
        }

    }

    public void iniciarEnfriamientoPedido() {

        timerPedido = new Timer(1000, e -> controlarTiempo());//lleva el tiempo de la partida
        timerPedido.start();

    }
    
    public void terminarEnfriamiento(){
    timerPedido.stop();
    }

}
