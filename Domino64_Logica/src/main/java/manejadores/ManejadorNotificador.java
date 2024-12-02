package manejadores;

import comunicadores_logica.IReceptorEventosLogica;
import domino64.eventos.base.suscripcion.EventoSuscripcion;
import entidadesDTO.CuentaDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.UnirseDTO;
import eventos.EventoJugador;
import tiposLogicos.TipoLogicaLobby;
import utilities.DirectorJugador;
import utilities.DirectorSuscripcion;

/**
 * @author karim
 */
public class ManejadorNotificador {
    private final ManejadorCuenta manejadorCuenta;
    private final DirectorJugador directorEventos;
    private final IReceptorEventosLogica receptor;

    public ManejadorNotificador() {
        manejadorCuenta = Control.obtenerManejadorCuenta();
        receptor = Control.obtenerReceptor();
        directorEventos = new DirectorJugador(receptor.devolverIdCliente());
    }

    public void crearPartida() {
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        EventoJugador crear = directorEventos.crearEventoCrearPartida(cuentaDTO);
        receptor.enviarEvento(crear);
        receptor.agregarSuscripcion(TipoLogicaLobby.PARTIDA_CREADA, receptor::partidaCreada);
    }
    
    
    public void unirsePartida(UnirseDTO unirse){
        CuentaDTO cuentaDTO = manejadorCuenta.getCuenta();
        unirse.setCuenta(cuentaDTO);
        EventoJugador crear = directorEventos.crearEventoUnirsePartida(unirse);
        receptor.enviarEvento(crear);
        receptor.agregarSuscripcion(TipoLogicaLobby.PARTIDA_ENCONTRADA, receptor::recibirPartida);
    }

    public void abandonarPartida() {
       CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador abandonar = directorEventos.crearEventoAbandonarPartida(cuenta);
        receptor.enviarEvento(abandonar);
    }

    public void cuentaLista(){
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador listo = directorEventos.crearEventoCuentaLista(cuenta);
        receptor.enviarEvento(listo);
    }
    public void cuentaNoLista(){
        CuentaDTO cuenta = manejadorCuenta.getCuenta();
        EventoJugador noListo = directorEventos.crearEventoCuentaNoLista(cuenta);
        receptor.enviarEvento(noListo);
    }
    
    public void cambiarReglas(ReglasDTO reglas){
        EventoJugador cambiarReglas = directorEventos.crearEventoActualizarConfigPartida(reglas);
        receptor.enviarEvento(cambiarReglas);
    }
}
