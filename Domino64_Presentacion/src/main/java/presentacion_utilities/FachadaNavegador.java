/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion_utilities;

import com.mycompany.patrones.command.Accion;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class FachadaNavegador {
    private final Navegacion navegador;
    private static FachadaNavegador fachada;
    private boolean appIniciada;
    
    private FachadaNavegador(){
        navegador = Navegacion.getInstance();
    }
    
    public static synchronized FachadaNavegador getInstance(){
        if(fachada==null){
            fachada = new FachadaNavegador();
        }
        return fachada;
    }
    
    public void iniciarApp(List<List<Accion>> acciones){
        if(!appIniciada){
            setAcciones(acciones);
            navegador.iniciarApp();
        }
    }
    
    private void setAcciones(List<List<Accion>> acciones){
        navegador.setAcciones(acciones);
    }
    
    public void irInicio(){
        navegador.cambiarInicio();
    }
    
    public void irLobby(){
        navegador.cambiarLobby();
    }
    
    public void irPartida(){
        navegador.cambiarPartida();
    }
    
    public void actualizarPartida(PartidaDTO partida){
        navegador.actualizarPartida(partida);
    }
    
    public void actualizarJugador(JugadorDTO jugador){
        navegador.actualizarJugador(jugador);
    }
    
}
