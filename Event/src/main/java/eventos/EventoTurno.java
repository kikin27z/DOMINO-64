/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import com.luisa.entidades.Cuenta;
import com.luisa.entidades.Jugador;
import com.luisa.excepcionesLogica.LogicException;
import java.util.ArrayList;
import java.util.List;
import tiposLogicos.TipoLogicaTurno;

/**
 *
 * @author luisa M
 */
public class EventoTurno extends Event<Cuenta>{
    private List<Cuenta> jugadores;
    private Cuenta jugador;
    private boolean flag;
    
    public EventoTurno(Cuenta publicador, Enum<?> tipo){
        super(publicador, tipo);
        if(this.getTipo().equals(TipoLogicaTurno.DESIGNAR_OTROS_TURNOS)){
            jugadores = new ArrayList<>();
            flag = true;
        }
    }
    
    @Override
    public void agregarInfo(Cuenta info) {
        if(flag)
            jugadores.add(info);
        else{
            jugador = info;
        }
    }

    @Override
    public Cuenta getInfo() {
        return jugador;
    }
    
    public List<Cuenta> getJugadores() throws LogicException{
        if(flag)
            return jugadores;
        throw new LogicException("ERROR: Accion invalida para este tipo de evento de turno");
    }
    
}
