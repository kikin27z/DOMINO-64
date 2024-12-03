package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.ReglasDTO;
import entidadesDTO.ResultadosDTO;
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
    private ResultadosDTO resultados;
    
    public EventoPartida(){}
    
    public EventoPartida(TipoLogicaPartida tipo){
        this.tipo = tipo;
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

    @Override
    public Object getInfo() {
        return new Object();
    }
}
