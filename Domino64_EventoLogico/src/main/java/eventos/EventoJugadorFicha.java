package eventos;

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

    public EventoJugadorFicha() {
    }

    public EventoJugadorFicha(TipoJugadorFicha tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public TipoJugadorFicha getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
