package utilities;

import builder.DirectorEventos;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.LobbyDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import tiposLogicos.TiposJugador;

/**
 * Clase directora que se encarga de crear los eventos del jugador. Esta clase
 * la va a usar el manejador de turnos para crear los eventos que el jugador
 * puede hacer. Cada metodo es una rutina de creacion de un evento especifico,
 * solo piden los parametros necesarios para asignarle el contexto al evento.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class DirectorJugador extends DirectorEventos<BuilderEventoJugador> {

    private final int idPublicador;

    public DirectorJugador(int id) {
        super(new BuilderEventoJugador());
        this.idPublicador = id;
    }

    public EventoJugador crearEventoColocarFicha(FichaDTO ficha, CuentaDTO publicador) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.COLOCAR_FICHA);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoUnirsePartida(UnirseDTO unirse) {
        builder.setIdPublicador(idPublicador);
        builder.agregarCuenta(unirse.getCuenta());
        builder.agregarUnirse(unirse);
        builder.setTipo(TiposJugador.UNIRSE_PARTIDA);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoCrearPartida(CuentaDTO creador) {
        builder.agregarCuenta(creador);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TiposJugador.CREAR_PARTIDA);
        EventoJugador e = builder.construirEvento();
        return e;
    }

    public EventoJugador crearEventoJalarFicha(CuentaDTO publicador) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.JALAR_FICHA);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoAbandonarLobby(CuentaDTO publicador) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.ABANDONAR_LOBBY);
        return builder.construirEvento();
    }
    public EventoJugador crearEventoAbandonarPartida(CuentaDTO publicador) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(publicador);
        builder.setTipo(TiposJugador.ABANDONAR_PARTIDA);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoCambiarAvatar(CuentaDTO cuentaActualizada) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuentaActualizada);
        builder.setTipo(TiposJugador.CAMBIAR_AVATAR);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoPeticionRendirse(CuentaDTO cuenta) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setTipo(TiposJugador.PETICION_RENDIRSE);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoPasarTurno(CuentaDTO cuenta) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setTipo(TiposJugador.PASAR_TURNO);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoCuentaLista(CuentaDTO cuenta) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setTipo(TiposJugador.CUENTA_LISTA);
        return builder.construirEvento();
    }

    public EventoJugador crearEventoCuentaNoLista(CuentaDTO cuenta) {
        builder.setIdPublicador(idPublicador);
        builder.setPublicador(cuenta);
        builder.setTipo(TiposJugador.CUENTA_NO_LISTA);

        return builder.construirEvento();
    }

    public EventoJugador crearEventoActualizarConfigPartida(ReglasDTO reglas) {
        builder.agregarReglas(reglas);
        builder.setIdPublicador(idPublicador);
        builder.setTipo(TiposJugador.CAMBIAR_CONFIG_PARTIDA);
        return builder.construirEvento();
    }
}
