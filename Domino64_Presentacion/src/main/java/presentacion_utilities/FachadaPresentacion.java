package presentacion_utilities;

import eventoss.EventoMVCDisplay;


/**
 *
 * @author luisa M
 */
public class FachadaPresentacion {
    private final INavegacion navegador;
    private final MediadorModelos mediador;
    private static FachadaPresentacion fachada;
    private boolean appIniciada;
    
    
    private FachadaPresentacion(){
        navegador = Navegacion.getInstance();
        mediador = MediadorModelos.getInstance();
    }
    
    public static synchronized FachadaPresentacion getInstance(){
        if(fachada==null){
            fachada = new FachadaPresentacion();
        }
        return fachada;
    }
    
    public void iniciarApp(){
        if(!appIniciada){
            navegador.iniciarApp();
        }
    }
    
    public void actualizarLobby(EventoMVCDisplay evento){
        mediador.actualizarModeloLobby(evento);
    }
    
    public void actualizarPartida(EventoMVCDisplay evento){
        mediador.actualizarModeloLobby(evento);
    }
    
    public void cambiarPantalla(EventoMVCDisplay evento){
        switch(evento.getTipo()){
            case IR_INICIO-> {
                navegador.cambiarInicio();
            }
            case IR_OPCIONES_PARTIDA -> {
                navegador.cambiarOpcionesPartida();
            }
            case IR_LOBBY -> {
                actualizarLobby(evento);
                navegador.cambiarLobby();
            }
            case IR_PARTIDA -> {
                actualizarLobby(evento);
                navegador.cambiarPartida();
            }
        }
    }
}