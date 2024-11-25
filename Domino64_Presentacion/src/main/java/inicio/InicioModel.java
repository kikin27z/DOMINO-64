package inicio;

import eventoss.EventoMVCJugador;
import eventosPantallas.ObservablePantallas;
import eventoss.TipoJugadorMVC;
import presentacion_utilities.ControladorComunicacion;
import presentacion_utilities.NotificadorEvento;

/**
 * Modelo de la pantalla de inicio que gestiona la lógica de negocio relacionada
 * con las acciones principales del usuario, como iniciar el modo de juego o 
 * mostrar los créditos.
 * Utiliza un notificador de eventos para comunicar cambios a otras partes de la
 * aplicación.
 * 
 * @autor Luisa Fernanda Morales Espinoza - 00000233450
 * @autor José Karim Franco Valencia - 00000245138
 */
public class InicioModel {
    private final ObservablePantallas notificador;

    /**
     * Constructor que inicializa el modelo de la pantalla de inicio.
     * Obtiene la instancia del notificador de eventos para comunicarse con
     * otros componentes de la aplicación.
     */
    public InicioModel() {
        notificador = NotificadorEvento.getInstance();
    }

    /**
     * Notifica a otros componentes que se debe activar el modo de juego.
     * Llama al método correspondiente del notificador para señalar que el usuario
     * ha elegido iniciar el modo de juego.
     */
    public void avisarModoJugar() {
//        EventoMVCJugador evento = new EventoMVCJugador(TipoJugadorMVC.IR_OPCIONES_PARTIDA);
//        ControladorComunicacion.enviarEventoALogica(evento);
        notificador.avisarMostrarOpcionesPartida();
    }

    /**
     * Notifica a otros componentes que se deben mostrar los créditos.
     * Llama al método correspondiente del notificador para señalar que el usuario
     * ha solicitado ver los créditos de la aplicación.
     */
    public void avisarMostrarCreditos() {
        notificador.avisarMostrarCreditos();
    }
}