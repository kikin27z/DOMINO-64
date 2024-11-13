/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package starter;

import com.domino64.base.Publicador;
import entidades.Cuenta;
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
        c.iniciar(true);
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
        //crea una cuenta dto a partir de la entidad Cuenta del manejador
        Cuenta cuenta = mc.getCuenta();
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(cuenta.getId());
        
        //crear la instancia del publicador de eventos de presentacion
        Publicador publicadorP = PublicadorEventosPresentacion.getInstance();
        
        /*se agrega como suscriptor al manejadorCuenta 
        el manejador se suscribe a los eventos declarados en su lista de eventos
        de manera que, cuando el publicadorEventosPresentacion publique un
        evento de un tipo que este en la lista del manejadorCuenta, el
        evento le llegara al manejador
        */
        for (Enum<?> eventoMVC : mc.getEventosMVC()) {
            publicadorP.suscribir(eventoMVC, mc);
        }
        
        /*
        se inicia el frame, asignandole el publicador de presentacion,
        de esta manera, se van a poder publicar eventos desde el frame.
        Tambien se le asigna el dto de la cuenta del jugador
        */
        Frame frame = new Frame(publicadorP, cuentaDTO);
        
        /*
        el frame se suscribe a los eventos delcarados en su lista de eventos,
        
        */
        for (Enum<?> evento : frame.getEventos()) {
            pub.suscribir(evento, frame);
        }
        
        initFrame(frame);
    }
    
}
