package InterfazGrafica;

import DAOs.PartidaDAO;
import Modelos.*;
import Servicios.*;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PartidaJF extends javax.swing.JFrame {

    private Usuario activo = new Usuario();
    private PartidaDAO partidaDao = new PartidaDAO();
    private Partida partida;
    private ArrayList<Pedido> listPedidos = new ArrayList();//listado en donde se almacenaran todos los pedidos activos
    private Timer timerPartida;
    private Timer timerPedidos;

    private final int MAX_PEDIDOS = 5;
    private final int TIEMPO_ENTRE_PEDIDO = 3000;//se genera un pedido cada 3 segundos
    private int tiempoLimite = 120;//120 segundos por partida
    private int puntajeTotal = 0;
    private int nivelActual = 1;
    private int id_partida;
    private String[] estados = {"RECIBIDA", "PREPARANDO", "EN_HORNO", "ENTREGADO", "CANCELADA", "NO_ENTREGADO"};
    private Pedido pedidoActivo = new Pedido();//el pedido con el que el usuario está trabajando

    private PartidaService servicioPartida = new PartidaService();

    public PartidaJF(Usuario activo) {
        initComponents();
        iniciarPartida(activo);
        
        this.setTitle("Pizza Express Tycoon");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        
        
    }

    private void iniciarPartida(Usuario activo) {

        ocultarPartida();

        this.activo = activo;
        this.id_partida = partidaDao.registrarPartida(activo);//crea una partida nueva y guarda su id
        this.partida = new Partida(id_partida, activo);
        panelPedidos.setLayout(new FlowLayout());
        timerPedidos = new Timer(TIEMPO_ENTRE_PEDIDO, e -> agregarPedido());//regula cada cuanto se genera un pedido
        timerPartida = new Timer(1000, e -> mostrarProgresoPartida());//lleva el tiempo de la partida
        timerPedidos.start();
        timerPartida.start();
    }

    public void ocultarPartida() {

        lblProducto.setVisible(false);
        lblTiempoPedido.setVisible(false);
        lblEstado.setVisible(false);
        lblOrden.setVisible(false);
        btnPreparar.setVisible(false);
        btnCancelar.setVisible(false);
        btnEntregar.setVisible(false);
        btnHornear.setVisible(false);

    }

    private void mostrarPedido(Pedido pedido) {

        pedidoActivo = pedido;

        lblProducto.setText("Producto: " + pedido.getProducto().getNombre_producto());

        //muestra la informacion del pedido en la partida
        lblProducto.setVisible(true);
        lblTiempoPedido.setVisible(true);
        lblEstado.setVisible(true);
        lblOrden.setVisible(true);
        btnPreparar.setVisible(true);
        btnCancelar.setVisible(true);
        btnEntregar.setVisible(true);
        btnHornear.setVisible(true);

        btnEntregar.setEnabled(false);
        btnHornear.setEnabled(false);

    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public JPanel getPanelPedidos() {
        return panelPedidos;
    }

    private void mostrarProgresoPartida() {

        tiempoLimite--;
        lblTiempo.setText("Tiempo restante: " + tiempoLimite);
        lblNivel.setText("Nivel actual: " + nivelActual);
        lblPuntos.setText("Puntos: " + puntajeTotal);
        lblTiempoPedido.setText("Tiempo de pedido: " + pedidoActivo.getTiempoRestante());
        lblEstado.setText("Estado: " + estados[pedidoActivo.getId_estado() - 1]);

        int estadoActivo = pedidoActivo.getId_estado();

        if (estadoActivo == 6) {//si no lo entregó a tiempo

            pedidoNoEntregado();

        }

        if (estadoActivo == 4 || estadoActivo == 5 || estadoActivo == 6) {
            ocultarPartida();
        }

        verificarNivel();

        if (tiempoLimite <= 0) {//si ya se acabó el tiempo
            terminarPartida();
        }

    }

    private void verificarNivel() {

        if (puntajeTotal < 500) {
            nivelActual = 1;
        } else if (puntajeTotal < 1000) {
            nivelActual = 2;
        } else {
            nivelActual = 3;
        }

    }

    private void agregarPedido() {

        if (panelPedidos.getComponentCount() >= MAX_PEDIDOS) {
            return;
        }

        Pedido pedido = servicioPartida.generarPedidos(activo.getId_sucursal(), nivelActual, id_partida);

        listPedidos.add(pedido);//agrega el nuevo pedido a la lista

        pedido.getBoton().addActionListener(e -> {
            mostrarPedido(pedido);
            panelPedidos.revalidate();
            panelPedidos.repaint();
        });

        panelPedidos.add(pedido.getBoton());
        panelPedidos.revalidate();
        panelPedidos.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPedidos = new javax.swing.JPanel();
        panelJuego = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        lblPuntos = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblTiempo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblProducto = new javax.swing.JLabel();
        lblTiempoPedido = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblOrden = new javax.swing.JLabel();
        btnPreparar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEntregar = new javax.swing.JButton();
        btnHornear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPedidos.setBackground(new java.awt.Color(153, 51, 0));

        javax.swing.GroupLayout panelPedidosLayout = new javax.swing.GroupLayout(panelPedidos);
        panelPedidos.setLayout(panelPedidosLayout);
        panelPedidosLayout.setHorizontalGroup(
            panelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPedidosLayout.setVerticalGroup(
            panelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelJuego.setBackground(new java.awt.Color(153, 153, 153));

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblPuntos.setForeground(new java.awt.Color(0, 0, 0));
        lblPuntos.setText("Puntos: ");

        lblNivel.setForeground(new java.awt.Color(0, 0, 0));
        lblNivel.setText("Nivel actual: ");

        lblTiempo.setForeground(new java.awt.Color(0, 0, 0));
        lblTiempo.setText("Tiempo restante:");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        lblProducto.setText("Producto: ");

        lblTiempoPedido.setText("Tiempo de pedido:");

        lblEstado.setText("Estado:");

        lblOrden.setText("Orden correcto de estados: RECIBIDA---PREPARANDO---EN_HORNO---ENTREGADO");

        btnPreparar.setText("PREPARAR");
        btnPreparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrepararActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEntregar.setText("ENTREGAR");
        btnEntregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregarActionPerformed(evt);
            }
        });

        btnHornear.setText("HORNEAR");
        btnHornear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHornearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblProducto)
                        .addGap(68, 68, 68)
                        .addComponent(lblTiempoPedido)
                        .addGap(88, 88, 88)
                        .addComponent(lblEstado))
                    .addComponent(lblOrden))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPreparar)
                    .addComponent(btnEntregar))
                .addGap(156, 156, 156)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar)
                    .addComponent(btnHornear))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(lblTiempoPedido)
                    .addComponent(lblEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPreparar)
                    .addComponent(btnCancelar))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHornear)
                    .addComponent(btnEntregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addGap(18, 18, 18)
                .addComponent(lblPuntos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNivel)
                .addGap(42, 42, 42))
            .addGroup(panelJuegoLayout.createSequentialGroup()
                .addComponent(lblTiempo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTiempo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(lblPuntos)
                    .addComponent(lblNivel))
                .addGap(14, 14, 14))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo_pizza2.jpg"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo_pizza2.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrepararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrepararActionPerformed

        partidaDao.cambiarEstadoDePedido(2, pedidoActivo.getId_pedido());
        //le setea al pedido activo el nuevo estado
        avanzarEstado();

        btnHornear.setEnabled(true);


    }//GEN-LAST:event_btnPrepararActionPerformed

    private void btnHornearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHornearActionPerformed

        partidaDao.cambiarEstadoDePedido(3, pedidoActivo.getId_pedido());
        //le setea al pedido activo el nuevo estado
        avanzarEstado();

        btnEntregar.setEnabled(true);

    }//GEN-LAST:event_btnHornearActionPerformed

    private void btnEntregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregarActionPerformed

        entregarPedido();


    }//GEN-LAST:event_btnEntregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        cancelarPedido();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        JOptionPane.showMessageDialog(null, "La partida terminara y se guardaran los puntos y nivel obtenidos",
                "ADVERTENSIA", JOptionPane.WARNING_MESSAGE);
        terminarPartida();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void entregarPedido() {

        partidaDao.cambiarEstadoDePedido(4, pedidoActivo.getId_pedido());
        //le setea al pedido activo el nuevo estado en la BD
        avanzarEstado();

        int puntos = 100;

        puntajeTotal = puntajeTotal + puntos;//le suma 100 puntos por entregar el pedido

        int tiempoBonificacion = (pedidoActivo.getTiempo_limite() / 2);

        if (pedidoActivo.getTiempoRestante() >= tiempoBonificacion) {
            puntajeTotal = puntajeTotal + 50;//si termino antes del 50% total del tiempo
            puntos = puntos + 50;
        }

        panelPedidos.remove(pedidoActivo.getBoton());
        listPedidos.remove(pedidoActivo);//saca el pedido entregado de la lista
        ocultarPartida();
        JOptionPane.showMessageDialog(null, pedidoActivo.getProducto().getNombre_producto() + " Entregado + " + puntos + " puntos",
                "ENTREGADO", JOptionPane.PLAIN_MESSAGE);
        
        pedidoActivo.terminarEnfriamiento();

    }

    private void cancelarPedido() {

        partidaDao.cambiarEstadoDePedido(5, pedidoActivo.getId_pedido());
        //le setea al pedido activo el nuevo estado en la BD
        avanzarEstado();

        puntajeTotal = puntajeTotal - 30;

        panelPedidos.remove(pedidoActivo.getBoton());
        listPedidos.remove(pedidoActivo);//saca el pedido cancelado de la lista
        ocultarPartida();
        JOptionPane.showMessageDialog(null, pedidoActivo.getProducto().getNombre_producto() + " Cancelado -30 puntos",
                "CANCELADO", JOptionPane.PLAIN_MESSAGE);

        pedidoActivo.terminarEnfriamiento();
    }

    private void pedidoNoEntregado() {

        panelPedidos.remove(pedidoActivo.getBoton());

        puntajeTotal = puntajeTotal - 50;
        
        registrarCambioEstado(pedidoActivo);

        JOptionPane.showMessageDialog(null, "No entregado: " + pedidoActivo.getProducto().getNombre_producto() + " -50 puntos",
                "QUE MAL", JOptionPane.ERROR_MESSAGE);

        pedidoActivo.terminarEnfriamiento();
        pedidoActivo = new Pedido();

    }

    private void avanzarEstado() {
        pedidoActivo.setId_estado(pedidoActivo.getId_estado() + 1);//avanza en el arreglo de estados para mostrarlo en pantalla
        registrarCambioEstado(pedidoActivo);

    }

    private void terminarPartida() {

        for (Pedido pedido : listPedidos) {//recorre todos los pedidos pendientes y los marca como no entregados

            pedido.setId_estado(6);
            partidaDao.cambiarEstadoDePedido(pedido.getId_estado(), pedido.getId_pedido());
            puntajeTotal = puntajeTotal - 50;
            registrarCambioEstado(pedido);
            pedido.terminarEnfriamiento();
        }

        partidaDao.terminarPartida(id_partida, puntajeTotal, nivelActual);//se guarda todo en la DB
        JOptionPane.showMessageDialog(null, "Puntaje total: " + puntajeTotal + ", Nivel alcanzado: " + nivelActual,
                "FIN DE LA PARTIDA", JOptionPane.PLAIN_MESSAGE);

        timerPartida.stop();
        timerPedidos.stop();

        this.setVisible(false);
        VistaJugador regresar = new VistaJugador(activo);
        regresar.setVisible(true);

    }

    private void registrarCambioEstado(Pedido pedido) {//gurada en la BD los cambios de estado de los pedidos

        partidaDao.regitrarHistorialPedido(pedido.getId_pedido(), pedido.getId_estado());

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEntregar;
    private javax.swing.JButton btnHornear;
    private javax.swing.JButton btnPreparar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblOrden;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblPuntos;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JLabel lblTiempoPedido;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelPedidos;
    // End of variables declaration//GEN-END:variables
}
