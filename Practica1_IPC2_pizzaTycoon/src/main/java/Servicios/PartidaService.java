package Servicios;

import DAOs.*;
import InterfazGrafica.PartidaJF;
import Modelos.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PartidaService {

    private ProductoDAO productoDao = new ProductoDAO();
    private PartidaDAO partidaDao = new PartidaDAO();

    public Pedido generarPedidos(int sucursal, int nivel, int id_partida ) {

        Producto productoPedido = decidirProductoPedido(sucursal);//obtiene el producto para usar en el pedido

        int tiempo = partidaDao.obtenerTiemposPorNivel(nivel);//obitnen cuanto tiempo tendra el pedido

        int id_pedido = partidaDao.regitrarPedido(id_partida, tiempo);//registra el pedido y obtiene su id

        partidaDao.regitrarDetallePedido(id_pedido, productoPedido.getId_producto());//registra el detalle del pedido

        Pedido pedidoNuevo = new Pedido();
        pedidoNuevo.setId_pedido(id_pedido);
        pedidoNuevo.setTiempo_limite(tiempo);
        pedidoNuevo.setId_partida(id_partida);
        pedidoNuevo.setProducto(productoPedido);
        pedidoNuevo.setBoton(new JButton(pedidoNuevo.getProducto().getNombre_producto()));//crea un pedido como objeto
        pedidoNuevo.iniciarEnfriamientoPedido();//inicia con el temporizador del pedido

         return pedidoNuevo;//muestra el boton que representa el pedido

       

    }

    private Producto decidirProductoPedido(int sucursal) {

        ArrayList<Producto> productos = productoDao.obtenerProductosPorSucursal(sucursal);

        Random random = new Random();
        int min = 0;
        int max = productos.size() - 1;//maximo el tamaño menor uno para que no se pase

        System.out.println("max: " + max);

        int numero = random.nextInt((max - min) + 1) + min;

        System.out.println("numero: " + numero);

        return productos.get(numero);

    }

}
