package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaRealizadaDTO;
import tiposLogicos.TipoJugadorFicha;

/**
 *
 * @author karim
 */
public class EventoJugadorFicha extends EventoLogico {
    private TipoJugadorFicha tipo;
    private JugadaRealizadaDTO jugada;
    private FichaDTO ficha;
    private CuentaDTO cuenta;

    public EventoJugadorFicha() {
    }

    public EventoJugadorFicha(TipoJugadorFicha tipo) {
        this.tipo = tipo;
    }

    public void setTipo(TipoJugadorFicha tipo) {
        this.tipo = tipo;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
    
    
    @Override
    public TipoJugadorFicha getTipo() {
        return tipo;
    }

    public JugadaRealizadaDTO getJugada() {
        return jugada;
    }

    public void setJugada(JugadaRealizadaDTO jugada) {
        this.jugada = jugada;
    }

    public FichaDTO getFicha() {
        return ficha;
    }

    public void setFicha(FichaDTO ficha) {
        this.ficha = ficha;
    }
}
