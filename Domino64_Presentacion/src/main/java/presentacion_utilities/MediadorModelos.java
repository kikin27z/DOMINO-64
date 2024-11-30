package presentacion_utilities;

import entidadesDTO.CuentaDTO;
import entidadesDTO.LobbyDTO;
import eventoss.EventoMVCDisplay;
import eventosInicio.ObservableInicio;
import eventosLobby.ObservableLobbyMVC;
import eventosLobby.ObserverLobby;
import eventosLobby.ObserverLobbyMVC;
import javafx.application.Platform;
import eventosOpcionesPartida.ObservableOpcionesMVC;
import eventosOpcionesPartida.ObserverOpcionesMVC;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import eventosPartida.ObservablePartidaMVC;
import eventosPartida.ObserverPartida;
import eventosPartida.ObserverPartidaMVC;
import inicio.InicioModel;
import lobby.LobbyModel;
import opciones_partida.OpcionesPartidaModel;
import partida.PartidaModel;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorModelos {
    private ObservableOpcionesMVC modeloOpciones;
    private ObservablePartidaMVC modeloPartida;
    private ObservableLobbyMVC modeloLobby;
    private ObservableInicio modeloInicio;
//    
    private static OpcionesPartidaModel modeloOpciones2;
    private static PartidaModel modeloPartida2;
    private LobbyModel modeloLobby2;
    private static InicioModel modeloInicio2;
    private static MediadorModelos mediador;

    private MediadorModelos() {
        modeloInicio2 = new InicioModel();
        modeloOpciones2 = new OpcionesPartidaModel();
        modeloLobby2 = LobbyModel.getInstance();
        modeloPartida2 = new PartidaModel();
    }
    
    public static MediadorModelos getInstance(){
        if (mediador == null) {
            mediador = new MediadorModelos(); // Crea la instancia si no existe
        }
        return mediador;
    }

    protected void actualizarModeloLobby(EventoMVCDisplay evento){
        Platform.runLater(() -> {
            switch (evento.getTipo()) {
                case IR_LOBBY ->
                    modeloLobby2.inicializarLobby(evento.getLobby());
                case AGREGAR_JUGADOR ->
                    modeloLobby2.actualizarNuevoJugador(evento.getCuenta());
                case REMOVER_JUGADOR ->
                    modeloLobby2.actualizarQuitarJugador(evento.getCuenta());
                case AGREGAR_JUGADOR_LISTO -> 
                    modeloLobby2.actualizarJugadorListo(evento.getCuenta());
                case REMOVER_JUGADOR_LISTO -> 
                    modeloLobby2.actualizarJugadorNoListo(evento.getCuenta());
                case ACTUALIZAR_AVATARES -> 
                    modeloLobby2.actualizarAvatarJugador(evento.getCuenta());
            }
        });
        
    }
    
    protected static InicioModel getModeloInicio(){
        return modeloInicio2;
    }
    
    protected static OpcionesPartidaModel getModeloOpciones(){
        return modeloOpciones2;
    }
    
    protected static PartidaModel getModeloPartida(){
        return modeloPartida2;
    }
    
    public LobbyModel getModeloLobby(){
        return modeloLobby2;
    }
    
    public void setModeloOpciones(ObservableOpcionesMVC modeloOpciones) {
        this.modeloOpciones = modeloOpciones;
    }
    public void setModeloPartida(ObservablePartidaMVC modeloPartida) {
        this.modeloPartida = modeloPartida;
    }
    public void setModeloLobby(ObservableLobbyMVC modeloLobby) {
        this.modeloLobby = modeloLobby;
    }
    
    public void agregarObserverLobbyMVC(ObserverLobbyMVC observer){
        modeloLobby2.agregarObserver(observer);
    }
    
    public void agregarObserverPartidaMVC(ObserverPartidaMVC observer){
        modeloPartida2.agregarObserver(observer);
    }
    
    public void agregarObserverOpcionesPartidaMVC(ObserverOpcionesMVC observer){
        modeloOpciones2.agregarObserver(observer);
    }
    
    public void agregarObserverLobby(ObserverLobby observer){
        modeloLobby2.agregarObserver(observer);
    }
    
    public void agregarObserverPartida(ObserverPartida observer){
        modeloPartida2.agregarObserver(observer);
    }
    
    public void agregarObserverOpciones(ObserverOpcionesPartida observer){
        modeloOpciones2.agregarObserver(observer);
    }
}
