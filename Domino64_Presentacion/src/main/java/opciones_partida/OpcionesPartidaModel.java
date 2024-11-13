package opciones_partida;

import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import entidadesDTO.UnirseDTO;
import eventosOpcionesPartida.ObserverOpcionesPartida;
import java.util.ArrayList;
import java.util.List;
import presentacion_utilities.NotificadorEvento;
import eventosOpcionesPartida.ObservableOpcionesPartida;
import eventosOpcionesPartida.ObserverOpcionesMVC;
import eventosPantallas.ObservablePantallas;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import eventosOpcionesPartida.ObservableOpcionesMVC;

/**
 * Modelo para la gestión de opciones de partida, que implementa el patrón
 * Observable para notificar a los observadores sobre eventos de lógica y vista.
 * Maneja la validación del código de partida y notifica la creación y búsqueda de partidas.
 * 
 * @autor Luisa Fernanda Morales Espinoza - 00000233450
 * @autor José Karim Franco Valencia - 00000245138
 */
public class OpcionesPartidaModel implements ObservableOpcionesPartida, ObservableOpcionesMVC {

    private final ObservablePantallas notificador;
    private final List<ObserverOpcionesPartida> observersLogica;
    private final List<ObserverOpcionesMVC> observersMVC;
    private String codigoPartida;

    /**
     * Constructor que inicializa el modelo con listas de observadores
     * y una instancia de NotificadorEvento para gestionar notificaciones globales.
     */
    public OpcionesPartidaModel() {
        notificador = NotificadorEvento.getInstance();
        observersLogica = new ArrayList<>();
        observersMVC = new ArrayList<>();
    }

    /**
     * Valida el formato del código de partida, verificando que cumpla con el patrón ###-###.
     * Retorna un mensaje de aviso en caso de error o `null` si el código es válido.
     * 
     * @return String mensaje de aviso si el formato del código es incorrecto, o `null` si es válido.
     */
    private String validarCodigo() {
        String aviso = null;
        String regex = "^[\\d]{3}-[\\d]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigoPartida);

        if (codigoPartida.isBlank()) {
            aviso = """
                    Formato del c\u00f3digo vacio
                    Debe ser ###-### sin espacios""";
        } else if (!matcher.matches()) {
            aviso = """
                    Formato del c\u00f3digo incorrecto
                    Debe ser ###-### sin espacios""";
        }
        return aviso;
    }

    /**
     * Notifica a todos los observadores registrados para mostrar la pantalla de inicio.
     */
    public void avisarMostrarInicio() {
        notificador.avisarMostrarInicio();
    }

    /**
     * Obtiene el código de la partida actual.
     * 
     * @return String el código de la partida en el formato ###-###.
     */
    public String getCodigoPartida() {
        return codigoPartida;
    }

    /**
     * Establece el código de la partida.
     * 
     * @param codigoPartida el código de partida en formato ###-###.
     */
    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    //---------------------Eventos Modelo a vista--------------------------------
    /**
     * Agrega un observador MVC para recibir notificaciones de mensajes de aviso.
     * 
     * @param observador el observador a agregar.
     */
    @Override
    public void agregarObserver(ObserverOpcionesMVC observador) {
        this.observersMVC.add(observador);
    }

    /**
     * Elimina un observador MVC registrado.
     * 
     * @param observador el observador a eliminar.
     */
    @Override
    public void quitarObserver(ObserverOpcionesMVC observador) {
        this.observersMVC.remove(observador);
    }

    /**
     * Notifica a todos los observadores MVC un mensaje de aviso.
     * 
     * @param mensaje el mensaje de aviso a mostrar en la vista.
     */
    @Override
    public void actualizarMensajeAviso(String mensaje) {
        for (var observer : observersMVC) {
            observer.actualizarMensajeAviso(mensaje);
        }
    }
    

    //---------------------Eventos Modelo a lógica--------------------------------
    /**
     * Agrega un observador de lógica de opciones de partida.
     * 
     * @param observador el observador a agregar.
     */
    @Override
    public void agregarObserver(ObserverOpcionesPartida observador) {
        this.observersLogica.add(observador);
    }

    /**
     * Elimina un observador de lógica de opciones de partida registrado.
     * 
     * @param observador el observador a eliminar.
     */
    @Override
    public void quitarObserver(ObserverOpcionesPartida observador) {
        this.observersLogica.remove(observador);
    }

    /**
     * Notifica a todos los observadores de lógica que deben iniciar la creación de una partida.
     */
    @Override
    public void avisarCrearPartida() {
        for (var observer : observersLogica) {
            observer.crearPartida();
        }
    }

    /**
     * Valida el código de partida y, si es válido, notifica a los observadores de lógica
     * para que inicien la búsqueda de partida.
     * En caso de error en el formato, envía un mensaje de aviso a la vista.
     */
    @Override
    public void avisarBuscarPartida() {
        String aviso = validarCodigo();
        if (aviso != null) {
            actualizarMensajeAviso(aviso);
            return;
        }
        UnirseDTO unirse = new UnirseDTO(codigoPartida);
        for (var observer : observersLogica) {
            observer.buscarPartida(unirse);
        }
    }
}
