
package InterfazGrafica;

import DAOs.*;
import Modelos.Sucursal;
import Modelos.Usuario;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class CambiarSucursalJF extends javax.swing.JFrame {

  private Usuario activo = new Usuario();
  private SucursalDAO sucursalDao = new SucursalDAO();
  private UsuarioDAO usuarioDao = new UsuarioDAO();
  
  private ArrayList<Sucursal> sucursales = new ArrayList<>();
    
    public CambiarSucursalJF(Usuario activo) {
        initComponents();
        this.activo = activo;
        mostrarSucursal();
        enlistarSucursales();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sucursalesList = new javax.swing.JList<>();
        btnRegresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnCambiar = new javax.swing.JButton();
        lblSucursal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Cambiar de sucursal");

        jScrollPane1.setViewportView(sucursalesList);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel3.setText("ID:");

        btnCambiar.setText("Cambiar");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        lblSucursal.setText("Sucursal actual:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSucursal)
                    .addComponent(btnRegresar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCambiar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCambiar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(lblSucursal)
                .addGap(28, 28, 28)
                .addComponent(btnRegresar)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        
        if (txtID.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Ingrese el ID de la sucursal", "ADVERTENSIA",
                            JOptionPane.WARNING_MESSAGE);
        }else{
        
            try {
                
                String idS = txtID.getText().trim();
                int id = Integer.parseInt(idS);
                
                if (usuarioDao.cambiarSucursal(id, activo.getNombre())) {
                    
                    activo.setId_sucursal(id);
                    JOptionPane.showMessageDialog(null, "Jugador "+activo.getNombre()+" asignado sucursal "+id, "Todo bien",
                            JOptionPane.PLAIN_MESSAGE);
                }else{
                
                    JOptionPane.showMessageDialog(null, "No existe el ID de la sucursal", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese el ID de la sucursal (numero)", "ADVERTENSIA",
                            JOptionPane.WARNING_MESSAGE);
            }
            
        }
        
        mostrarSucursal();
        
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        
        this.setVisible(false);
        VistaJugador jugador = new VistaJugador(activo);
        jugador.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void mostrarSucursal(){
    
        lblSucursal.setText("Sucursal actual: "+activo.getId_sucursal());
        
    }
    
 private void enlistarSucursales() {

       
        sucursales = sucursalDao.obtenerSucursales();//obtiene las sucursales de la DB

        DefaultListModel<String> modelo = new DefaultListModel<>();


        for (Sucursal sucursal : sucursales) {
            modelo.addElement(sucursal.getNombre_sucursal() + "------------ID(" + sucursal.getId_sucursal()+")");
        }

        sucursalesList.setModel(modelo);//se enlistan los nombres de las sucursales

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JList<String> sucursalesList;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
