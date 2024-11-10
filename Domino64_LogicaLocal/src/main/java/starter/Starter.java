/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package starter;

import com.domino64.base.Publicador;
import com.luisa.entidades.Cuenta;
import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import implementacion.Client;
import java.util.Scanner;
import logicaLobby.ManejadorCuenta;
import logicaNotificacionEventos.PublicadorEventos;

/**
 *
 * @author luisa M
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initGame();
    }
    
    private static void initGame(){
        Client c = Client.getClient(5000);
        PublicadorEventos publicador = PublicadorEventos.getInstance();
        
        ManejadorCuenta manejadorC = new ManejadorCuenta();
        
        for (Enum<?> evento : manejadorC.getEventos()) {
            c.addObserver(evento, manejadorC);
        }
        
        manejadorC.init(c, publicador);
        c.iniciar();
        manejadorC.setClientId(c.getClientId());
        
//        ManejadorJugador manejadorJ = new ManejadorJugador();
//        
//        for (Enum<?> evento : manejadorJ.getEventos()) {
//            c.addObserver(evento, manejadorJ);
//        }
//        
//        manejadorJ.init(c);
        
        
        simulacion(manejadorC, publicador);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void initFrame(Frame frame) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
    }
    
    private static void simulacion(ManejadorCuenta mc, PublicadorEventos pub){
        Cuenta cuenta = mc.getCuenta();
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(cuenta.getId());
        
        Publicador publicadorP = PublicadorEventosPresentacion.getInstance();
        
        for (Enum<?> eventoMVC : mc.getEventosMVC()) {
            publicadorP.suscribir(eventoMVC, mc);
        }
        
        Frame frame = new Frame(publicadorP, cuentaDTO);
        
        for (Enum<?> evento : frame.getEventos()) {
            pub.suscribir(evento, frame);
        }
        
        initFrame(frame);
//        Scanner s = new Scanner(System.in);
//        
//        int opcion;
//        do {
//            System.out.println("(1)Crear partida");
//            System.out.println("(2)Unirse a partida");
//            opcion = s.nextInt();
//            switch (opcion) {
//                case 1 -> crear(mc);
//                case 2 -> unirse(mc, s);
//                default -> System.out.println("elige una opcion valida (1 o 2)");
//            }
//        } while (opcion != 1 && opcion != 2);
//        System.out.println("hola");
//        
    }
    
    private static void crear(ManejadorCuenta mc){
        PartidaDTO partida = new PartidaDTO();
        partida.setFichasPorJugador(5);
        //mc.crearPartida(partida);
    }
    
    private static void unirse(ManejadorCuenta mc, Scanner s){
        System.out.println("ingresa el codigo de la partida:");
        String codigo = s.next();
        PartidaDTO partida = new PartidaDTO();
        partida.setCodigoPartida(codigo);
       // mc.unirmePartida(partida);
    }
}
