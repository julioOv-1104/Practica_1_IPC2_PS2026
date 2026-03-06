package InterfazGrafica;

import DAOs.PartidaDAO;
import Modelos.*;
import Servicios.*;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PartidaJF extends javax.swing.JFrame {

    private Usuario activo = new Usuario();
    private PartidaDAO partidaDao = new PartidaDAO();
    private Partida partida;

    private final int MAX_PEDIDOS = 3;
    private final int TIEMPO_ENTRE_PEDIDO = 5000;
    private int tiempoLimite = 70;//50segundos por partida
    private int puntajeTotal = 0;
    private int nivelActual = 1;
    private int id_partida;
    private String[] estados = {"RECIBIDA","PREPARANDO","EN_HORNO","ENTREGADO","CANCELADA","NO_ENTREGADO"};
    private Pedido pedidoActivo = new Pedido();//el pedido con el que el usuario está trabajando

    private PartidaService servicioPartida = new PartidaService();

    public PartidaJF(Usuario activo) {
        initComponents();
        iniciarPartida(activo);
        //botonDePrueba();
    }

    private void iniciarPartida(Usuario activo) {

        ocultarPartida();

        this.activo = activo;
        this.id_partida = partidaDao.registrarPartida(activo);//crea una partida nueva y guarda su id
        this.partida = new Partida(id_partida, activo);
        panelPedidos.setLayout(new FlowLayout());
        Timer timerPedidos = new Timer(TIEMPO_ENTRE_PEDIDO, e -> agregarPedido());//regula cada cuanto se genera un pedido
        Timer timerPartida = new Timer(1000, e -> mostrarProgresoPartida());//lleva el tiempo de la partida
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
        lblTiempoPedido.setText("Tiempo de pedido: "+pedidoActivo.getTiempo_limite());
        lblEstado.setText("Estado: "+estados[pedidoActivo.getId_estado()-1]);
        
        int estadoActivo = pedidoActivo.getId_estado();
        
        if (estadoActivo == 6) {//si no lo entregó a tiempo
            
            panelPedidos.remove(pedidoActivo.getBoton());
            
            JOptionPane.showMessageDialog(null,"No entregado: "+pedidoActivo.getProducto().getNombre_producto(), 
                         "QUE MAL", JOptionPane.ERROR_MESSAGE);
            
            pedidoActivo = new Pedido();
            
        }
        
        if (estadoActivo==4 || estadoActivo==5 || estadoActivo==6) {
            ocultarPartida();
        }

    }

    private void agregarPedido() {

        if (panelPedidos.getComponentCount() >= MAX_PEDIDOS) {
            return;
        }

        Pedido pedido = servicioPartida.generarPedidos(activo.getId_sucursal(), nivelActual, id_partida);

        pedido.getBoton().addActionListener(e -> {
            mostrarPedido(pedido);
            panelPedidos.revalidate();
            panelPedidos.repaint();
        });

        /*pedido.addActionListener(e -> {
            panelPedidos.remove(pedido);
            panelPedidos.revalidate();
            panelPedidos.repaint();
        });*/
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
         
        partidaDao.cambiarEstadoDePedido(4, pedidoActivo.getId_pedido());
        //le setea al pedido activo el nuevo estado en la BD
        avanzarEstado();
        
        panelPedidos.remove(pedidoActivo.getBoton());
        ocultarPartida();
        JOptionPane.showMessageDialog(null,pedidoActivo.getProducto().getNombre_producto()+" Entregado", 
                         "ENTREGADO", JOptionPane.PLAIN_MESSAGE);
        
        
    }//GEN-LAST:event_btnEntregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        partidaDao.cambiarEstadoDePedido(5, pedidoActivo.getId_pedido());
        //le setea al pedido activo el nuevo estado en la BD
        avanzarEstado();
        
        panelPedidos.remove(pedidoActivo.getBoton());
        ocultarPartida();
        JOptionPane.showMessageDialog(null,pedidoActivo.getProducto().getNombre_producto()+" Cancelado", 
                         "CANCELADO", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void avanzarEstado(){
    pedidoActivo.setId_estado(pedidoActivo.getId_estado() + 1);//avanza en el arreglo de estados para mostrarlo en pantalla
    
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
