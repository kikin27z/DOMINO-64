/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package starter;

import com.domino64.base.Publicador;
import com.domino64.base.Suscriptor;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import eventoss.EventoMVC;
import eventoss.EventoMVCJugador;
import eventoss.EventoMVCLobby;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import tiposEventos.TipoJugadorMVC;
import tiposEventos.TipoLobbyMVC;

/**
 *
 * @author luisa M
 */
public class Frame extends javax.swing.JFrame implements Suscriptor<EventoMVC>{
    private Publicador publicador;
    private CuentaDTO cuenta;
    private JugadorDTO jugador;
    private PartidaDTO partida;
    private Map<Enum<?>, Consumer<EventoMVC>> consumers;
    private final List<Enum<?>> eventos = new ArrayList<>(
            List.of(
                    TipoLobbyMVC.PARTIDA_OBTENIDA,
                    TipoLobbyMVC.ACTUALIZAR_AVATARES,
                    TipoLobbyMVC.ACTUALIZAR_USERNAME,
                    TipoLobbyMVC.INICIO_PARTIDA,
                    TipoLobbyMVC.ACTUALIZAR_JUGADORES_LISTOS,
                    TipoLobbyMVC.JUGADOR_NUEVO,
                    TipoLobbyMVC.JUGADOR_SALIO
            ));
    
    public Frame(){}
    
    /**
     * Creates new form Frame
     */
    public Frame(Publicador publicador, CuentaDTO cuenta) {
        this.publicador = publicador;
        this.cuenta = cuenta;
        this.consumers = new HashMap<>();
        initComponents();
        init();
        setConsumers();
        this.setVisible(true);
    }

    public List<Enum<?>> getEventos(){
        return eventos;
    }
    
    private void recibirPartida(EventoMVC evento){
        EventoMVCLobby eventoL = (EventoMVCLobby)evento;
        partida = eventoL.getPartida();
        
        jugador = new JugadorDTO(cuenta);
        
        System.out.println("partida: "+partida);
        cambiarVista(true);
        agregarJugadores(partida.getJugadores());
    }
    
    private void recibirJugador(EventoMVC evento){
        EventoMVCLobby eventoL = (EventoMVCLobby)evento;
        CuentaDTO jugadorNuevo = eventoL.getPublicador();
        areaJugadores.setText(agregarJugador(jugadorNuevo));
    }
    
    private void removerJugador(EventoMVC evento){
        EventoMVCLobby eventoL = (EventoMVCLobby)evento;
        CuentaDTO exjugador = eventoL.getPublicador();
        CuentaDTO c;
        
        for (JugadorDTO j : partida.getJugadores()) {
            c = j.getCuenta();
            if(c.getId() == exjugador.getId()){
                partida.getJugadores().remove(j);
                break;
            }
        }
        areaJugadores.setText(removerJugador(exjugador));
    }
    
    private void actualizarUsername(EventoMVC evento){
        EventoMVCLobby eventoL = (EventoMVCLobby)evento;
        CuentaDTO jugadorActualizado = eventoL.getInfo();
        actualizarUsername(jugadorActualizado);
    }
    
    private void setConsumers(){
        consumers.putIfAbsent(TipoLobbyMVC.PARTIDA_OBTENIDA, this::recibirPartida);
        consumers.putIfAbsent(TipoLobbyMVC.JUGADOR_NUEVO, this::recibirJugador);
        consumers.putIfAbsent(TipoLobbyMVC.JUGADOR_SALIO, this::removerJugador);
        consumers.putIfAbsent(TipoLobbyMVC.ACTUALIZAR_USERNAME, this::actualizarUsername);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaJugadores = new javax.swing.JTextArea();
        lblJugadores = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        btnListo = new javax.swing.JButton();
        btnCrearPartida = new javax.swing.JButton();
        btnUnirse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        areaJugadores.setColumns(20);
        areaJugadores.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        areaJugadores.setRows(5);
        jScrollPane1.setViewportView(areaJugadores);

        lblJugadores.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        lblJugadores.setText("Jugadores en partida:");

        btnSalir.setText("abandonar partida");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtUsername.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsername.setText("Tu username");
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });

        btnListo.setText("estoy listo");

        btnCrearPartida.setText("crear partida");
        btnCrearPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPartidaActionPerformed(evt);
            }
        });

        btnUnirse.setText("unirse a partida");
        btnUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJugadores)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(btnSalir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(btnCrearPartida)))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUnirse)
                            .addComponent(btnListo))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblJugadores)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearPartida)
                            .addComponent(btnUnirse))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir)
                            .addComponent(btnListo))
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        // TODO add your handling code here:
//        System.out.println("key typed: "+evt.paramString());
//        String keyChar = String.valueOf(evt.getKeyChar());
//        System.out.println("keychar: "+keyChar);
//        if(keyChar.equals('\n')){
//            System.out.println("se presiono la tecla: "+evt.getKeyCode());
//            String usernameNuevo = txtUsername.getText();
//            if(usernameNuevo.isBlank())
//                JOptionPane.showMessageDialog(this, "No puede dejar el username en blanco");
//            else{
//                if(validarUsername(usernameNuevo)){
//                    CuentaDTO cuentaActualizada = new CuentaDTO();
//                    cuentaActualizada.setUsername(usernameNuevo);
//                    EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.CAMBIAR_USERNAME);
//                    evento.setPublicador(cuenta);
//                    System.out.println("evento enviado: ");
//                    publicador.publicarEvento(evento.getTipo(), evento);
//                }
//            }
//        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void btnCrearPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPartidaActionPerformed
        // TODO add your handling code here:
        String username = JOptionPane.showInputDialog(this, "ingresa tu username");
        cuenta.setUsername(username);
        EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.CREAR_PARTIDA);
        
        jugador = new JugadorDTO(cuenta);
        
        partida = new PartidaDTO();
        partida.getJugadores().add(jugador);
        partida.setFichasPorJugador(5);//hardcodeado
        evento.setPartida(partida);
        evento.setPublicador(cuenta);
        
        publicador.publicarEvento(evento.getTipo(), evento);
        
        cambiarVista(true);
        areaJugadores.setText(agregarJugador(cuenta));
    }//GEN-LAST:event_btnCrearPartidaActionPerformed

    private void btnUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirseActionPerformed
        // TODO add your handling code here:
        String username = JOptionPane.showInputDialog(this, "ingresa tu username:");
        cuenta.setUsername(username);
        
        String codigo = JOptionPane.showInputDialog(this, "ingresa el codigo partida:");
        
        EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.UNIRSE_PARTIDA);
        
        PartidaDTO pDTO = new PartidaDTO();
        pDTO.setCodigoPartida(codigo);
        
        evento.setPartida(pDTO);
        evento.setPublicador(cuenta);
        
        publicador.publicarEvento(evento.getTipo(), evento);
        
    }//GEN-LAST:event_btnUnirseActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.ABANDONAR_PARTIDA);
        evento.setPublicador(cuenta);
        publicador.publicarEvento(evento.getTipo(), evento);
        
        cambiarVista(false);
        txtUsername.setText("");
        areaJugadores.setText("");
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(evt.getNewState() == java.awt.event.WindowEvent.WINDOW_CLOSING){
            EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.ABANDONAR_PARTIDA);
            evento.setPublicador(cuenta);
            publicador.publicarEvento(evento.getTipo(), evento);
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void init(){
        this.txtUsername.setVisible(false);
        this.areaJugadores.setVisible(false);
        this.lblJugadores.setVisible(false);
        this.btnListo.setVisible(false);
        this.btnSalir.setVisible(false);
        this.btnCrearPartida.setVisible(true);
        this.btnUnirse.setVisible(true);
        
        this.txtUsername.addActionListener((ActionEvent e) -> {
            String usernameNuevo = txtUsername.getText();
            if (usernameNuevo.isBlank())
                JOptionPane.showMessageDialog(null, "No puede dejar el username en blanco");
            else {
                if (validarUsername(usernameNuevo)) {
                    CuentaDTO cuentaActualizada = new CuentaDTO();
                    cuentaActualizada.setUsername(usernameNuevo);
                    cuentaActualizada.setId(cuenta.getId());
                    
                    EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.CAMBIAR_USERNAME);
                    evento.setPublicador(cuentaActualizada);
                    System.out.println("evento enviado: "+evento.getPublicador());
                    publicador.publicarEvento(evento.getTipo(), evento);
                }
            }
        });
    }
    
    private boolean validarUsername(String user){
        List<JugadorDTO> jugadores = partida.getJugadores();
        if (jugadores.size() > 1) {
            CuentaDTO cuentaDTO;
            for (JugadorDTO j : jugadores) {
                cuentaDTO = j.getCuenta();
                if (cuentaDTO.getUsername().equals(user)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void cambiarVista(boolean enPartida) {
        this.areaJugadores.setVisible(enPartida);
        this.lblJugadores.setVisible(enPartida);
        this.txtUsername.setVisible(enPartida);
        this.btnCrearPartida.setVisible(!enPartida);
        this.btnUnirse.setVisible(!enPartida);
        this.btnSalir.setVisible(enPartida);
        this.btnListo.setVisible(enPartida);
        if(enPartida)
            txtUsername.setText(jugador.getCuenta().getUsername());
    }
    
    private String removerJugador(CuentaDTO jugador){
        StringBuilder builder = new StringBuilder(areaJugadores.getText());
        int index = this.areaJugadores.getText().indexOf(jugador.getUsername());
        builder.delete(index, index+jugador.getUsername().length());
        
        return builder.toString();
    }
    
    private String agregarJugador(CuentaDTO jugador){
        
        StringBuilder builder = new StringBuilder(areaJugadores.getText());
        builder.append(jugador.getUsername()).append('\n');
        
        return builder.toString();
    }
    
    private String actualizarTxtArea(String nombreAnterior, String nombreNuevo){
        int index = areaJugadores.getText().indexOf(nombreAnterior);
        
        StringBuilder builder = new StringBuilder(areaJugadores.getText());
        builder.replace(index, index+nombreAnterior.length(), nombreNuevo);
        
        return builder.toString();
    }
    
    private void actualizarUsername(CuentaDTO jugador){
        CuentaDTO aux;
        String nombreAnterior = null;
        for (JugadorDTO j : partida.getJugadores()) {
            aux = j.getCuenta();
            if(aux.getId() == jugador.getId()){
                nombreAnterior = aux.getUsername();
                j.getCuenta().setUsername(jugador.getUsername());
                break;
            }
        }
        
        areaJugadores.setText(actualizarTxtArea(nombreAnterior, jugador.getUsername()));
    }
    
    private void agregarJugadores(List<JugadorDTO> jugadores){
        StringBuilder builder = new StringBuilder();
        CuentaDTO aux;
        for (JugadorDTO j : jugadores) {
            aux = j.getCuenta();
            builder.append(aux.getUsername()).append('\n');
        }
        
        areaJugadores.setText(builder.toString());
    }
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Frame();
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaJugadores;
    private javax.swing.JButton btnCrearPartida;
    private javax.swing.JButton btnListo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUnirse;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJugadores;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void recibirEvento(EventoMVC evento) {
        Consumer<EventoMVC> cons = consumers.get(evento.getTipo());
        if (cons != null) {
            cons.accept(evento);
        }
    }
}
