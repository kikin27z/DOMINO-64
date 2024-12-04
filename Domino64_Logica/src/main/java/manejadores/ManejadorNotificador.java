package manejadores;

import comunicadores_logica.IReceptorEventosLogica;
import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import eventos.EventoJugadorFicha;
import tiposLogicos.TipoLogicaLobby;
import tiposLogicos.TipoLogicaPartida;
import tiposLogicos.TipoLogicaPozo;
import utilities.DirectorJugador;
import utilities.DirectorJugadorFicha;

/**
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorNotificador {

    private final ManejadorCuenta manejadorCuenta;
    private final DirectorJugador directorEventos;
    private final DirectorJugadorFicha directorEventosFicha;
    private final IReceptorEventosLogica receptor;

    public ManejadorNotificador() {
        manejadorCuenta = Control.obtenerManejadorCuenta();
        receptor = Control.obtenerReceptor();
        directorEventos = new DirectorJugador(receptor.devolverIdCliente());
        directorEventosFicha = new DirectorJugadorFicha(receptor.devolverIdCliente());
    }

    public void crearPartida() {
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        EventoJugador crear = directorEventos.crearEventoCrearPartida(cuentaDTO);
        receptor.enviarEvento(crear);
        receptor.agregarSuscripcion(TipoLogicaLobby.LOBBY_CREADO, receptor::lobbyCreado);
    }

    public void unirsePartida(UnirseDTO unirse) {
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        unirse.setCuenta(cuentaDTO);
        EventoJugador crear = directorEventos.crearEventoUnirsePartida(unirse);
        receptor.agregarSuscripcion(TipoLogicaLobby.LOBBY_ENCONTRADO, receptor::lobbyEncontrado);
        receptor.enviarEvento(crear);
    }

    public void abandonarLobby() {
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador abandonar = directorEventos.crearEventoAbandonarLobby(cuenta);
        receptor.enviarEvento(abandonar);
        receptor.removerSuscripcion(TipoLogicaPartida.INICIO_PARTIDA);
    }

    public void cuentaLista(){
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador listo = directorEventos.crearEventoCuentaLista(cuenta);
        receptor.enviarEvento(listo);
    }

    public void cuentaNoLista() {
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador noListo = directorEventos.crearEventoCuentaNoLista(cuenta);
        receptor.enviarEvento(noListo);
    }

    public void cambiarReglas(ReglasDTO reglas) {
        EventoJugador cambiarReglas = directorEventos.crearEventoActualizarConfigPartida(reglas);
        receptor.enviarEvento(cambiarReglas);
    }

    public void cambiarAvatar(CuentaDTO cuentaActualizada) {
        EventoJugador cambiarAvatar = directorEventos.crearEventoCambiarAvatar(cuentaActualizada);
        receptor.enviarEvento(cambiarAvatar);
    }

    //----------------------------------Eventos Partida----------------------------------
    public void enviarJugadaRealizada(JugadaRealizadaDTO jugada) {
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugadorFicha evento = directorEventosFicha.crearEventoJugadaRealizada(jugada, cuenta);
        receptor.enviarEvento(evento);
    }

    public void jalarFichaPozo() {
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugadorFicha evento = directorEventosFicha.crearEventoJalarPozo(cuenta);
        receptor.agregarSuscripcion(TipoLogicaPozo.FICHA_OBTENIDA, receptor::fichaObtenida);
        receptor.agregarSuscripcion(TipoLogicaPozo.POZO_VACIO, receptor::pasarTurno);
        receptor.enviarEvento(evento);
    }

    public void abandonarPartida() {
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador abandonar = directorEventos.crearEventoAbandonarPartida(cuenta);
        receptor.enviarEvento(abandonar);
    }

    public void peticionRendirse() {

    }
}
