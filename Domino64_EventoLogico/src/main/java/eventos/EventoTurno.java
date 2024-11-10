package eventos;

//import categorias.CategoriaEvento;
import entidades.Cuenta;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends EventoLogico<Cuenta>{
    private List<Cuenta> jugadores;
    private Cuenta jugador;
    private boolean flag;
    private TipoLogicaTurno tipo;
    
    public EventoTurno(){}
    
    public EventoTurno(TipoLogicaTurno tipo){
        super();
        this.tipo = tipo;
        if(tipo.equals(TipoLogicaTurno.DESIGNAR_OTROS_TURNOS)){
            jugadores = new ArrayList<>();
            flag = true;
        }
    }
    
    /**
     * Agrega la cuenta referente para este evento de turno.
     * Normalmente cuando se envie un evento de este tipo,
     * el contexto que se va a agregar es el jugador que esta en turno.
     * @param info Cuenta del contexto de este evento
     */
    @Override
    public void agregarInfo(Cuenta info) {
        if(flag)
            jugadores.add(info);
        else{
            jugador = info;
        }
    }

    /**
     * Obtiene la informacion de la cuenta del contexto
     * de este evento
     * @return La cuenta como parte del contexto
     */
    @Override
    public Cuenta getInfo() {
        return jugador;
    }
    /**
     * Metodo que obtiene la lista de jugadores del contexto 
     * de este evento. 
     * Este metodo fue pensado para los eventos de turno para
     * designar los turnos. Debido a que cuando se designen los turnos
     * se quiere obtener la lista de jugadores en orden segun como se 
     * designaron los turnos.
     * @return La lista de jugadores como contexto del evento
     */
    public List<Cuenta> getJugadores(){
        if(flag)
            return jugadores;
        return null;
    }

    @Override
    public TipoLogicaTurno getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLogicaTurno tipo){
        this.tipo = tipo;
    }
}
