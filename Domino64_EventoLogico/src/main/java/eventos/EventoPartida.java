package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.PosibleJugadaDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.ResultadosDTO;
import entidadesDTO.TurnosDTO;
import java.util.List;
import java.util.Map;
import tiposLogicos.TipoLogicaPartida;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class EventoPartida extends EventoLogico {
    private CuentaDTO cuenta;
    private TipoLogicaPartida tipo;
    private ReglasDTO reglas;
    private JugadorDTO jugador;
    private TurnosDTO turnos;
    private JugadaDTO jugada;
    private Map<FichaDTO, PosibleJugadaDTO> posiblesJugadas;
    private ResultadosDTO resultados;
    
    public EventoPartida(){}
    
    public EventoPartida(TipoLogicaPartida tipo){
        this.tipo = tipo;
    }

    public TurnosDTO getTurnos() {
        return turnos;
    }

    public Map<FichaDTO, PosibleJugadaDTO> getPosiblesJugadas() {
        return posiblesJugadas;
    }

    public void setPosiblesJugadas(Map<FichaDTO, PosibleJugadaDTO> posiblesJugadas) {
        this.posiblesJugadas = posiblesJugadas;
    }

    public void setTurnos(TurnosDTO turnos) {
        this.turnos = turnos;
    }

    public JugadaDTO getJugada() {
        return jugada;
    }

    public void setJugada(JugadaDTO jugada) {
        this.jugada = jugada;
    }

    
    public JugadorDTO getJugador() {
        return jugador;
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public void setTipo(TipoLogicaPartida tipo) {
        this.tipo = tipo;
    }

    public ReglasDTO getReglas() {
        return reglas;
    }

    public void setReglas(ReglasDTO reglas) {
        this.reglas = reglas;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public ResultadosDTO getResultados() {
        return resultados;
    }

    public void setResultados(ResultadosDTO resultados) {
        this.resultados = resultados;
    }
    
    @Override
    public TipoLogicaPartida getTipo() {
        return tipo;
    }
}
