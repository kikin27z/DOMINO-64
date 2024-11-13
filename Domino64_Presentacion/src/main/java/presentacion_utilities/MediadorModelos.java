package presentacion_utilities;

import entidadesDTO.PartidaDTO;
import eventosLobby.ObservableLobbyMVC;
import javafx.application.Platform;
import eventosOpcionesPartida.ObservableOpcionesMVC;
import eventosPartida.ObservablePartidaMVC;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MediadorModelos {
    private ObservableOpcionesMVC modeloOpciones;
    private ObservablePartidaMVC modeloPartida;
    private ObservableLobbyMVC modeloLobby;
    
    private static MediadorModelos mediador;

    private MediadorModelos() {
    }
    
    public static MediadorModelos getInstance(){
        if (mediador == null) {
            mediador = new MediadorModelos(); // Crea la instancia si no existe
        }
        return mediador;
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
    
    
    
    public void respuesta(String respuesta){
        Platform.runLater(()-> {
            System.out.println("Imprimiendo en mediador ");
            System.out.println(respuesta);
            System.out.println(Thread.currentThread());
        });
    }
}
