/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion_utilities;


import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;
import inicio.InicioModel;
import java.util.ArrayList;
import java.util.List;
import lobby.LobbyModel;
import observer.Observable;
import partida.PartidaModel;

/**
 *
 * @author luisa M
 */
public class NotificadorPresentacion extends Observable<NotificadorPresentacion>{
    private static NotificadorPresentacion notificador;
    private LobbyModel lobbyModel;
    private InicioModel inicioModel;
    private PartidaModel partidaModel;
    
    public final static int INICIALIZAR_PARITDA = 0;
    public final int CAMBIAR_PANTALLA = 1;
    public final static int FICHA_SELECCIONADA = 2;
    
    private NotificadorPresentacion(){
        
    }
    
    public static synchronized NotificadorPresentacion getInstance(){
        if(notificador == null)
            notificador = new NotificadorPresentacion();
        return notificador;
    }
    
    public JugadorDTO getJugador(){
        JugadorDTO jugador = lobbyModel.getJugador();
        return jugador;
    }
    
    public FichaDTO getFichaSeleccionada(){
        FichaDTO ficha = partidaModel.obtenerFichaSeleccionada();
        return ficha;
    }
    
    public void notificar(Object contexto){
        int tipoAccion = -1;
        if(contexto.getClass() == LobbyModel.class){
            this.lobbyModel = (LobbyModel)contexto;
            tipoAccion = INICIALIZAR_PARITDA;
        }else if(contexto.getClass() == InicioModel.class){
            this.inicioModel = (InicioModel)contexto;
        }else if(contexto.getClass() == PartidaModel.class){
            this.partidaModel = (PartidaModel)contexto;
            tipoAccion = FICHA_SELECCIONADA;
        }
        this.notifyObservers(this, tipoAccion);
    }
}
