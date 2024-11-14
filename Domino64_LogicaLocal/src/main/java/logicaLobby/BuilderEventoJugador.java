/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaLobby;

import builder.EventBuilder;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.PartidaDTO;
import eventos.EventoJugador;
import tiposLogicos.TiposJugador;

/**
 * Clase constructora de eventos de jugador.
 * Este builder lo va a usar el director del manejador de cuenta 
 * para facilitar y encapsular la logica de construccion de eventos del jugador
 * @author luisa M
 */
public class BuilderEventoJugador implements EventBuilder {
    private EventoJugador evento;
    private TiposJugador tipo;
    
    public BuilderEventoJugador(){
        evento = new EventoJugador();
    }
    
    public void setPartida(PartidaDTO partida){
        evento.setPartida(partida);
    }
    
    public void setPublicador(CuentaDTO jugador){
        evento.setJugador(jugador);
    }

    @Override
    public void setIdPublicador(int idPublicador) {
        evento.setIdPublicador(idPublicador);
    }

    @Override
    public void setContexto(Object contexto) {
        evento.agregarInfo(contexto);
    }

    @Override
    public EventoJugador construirEvento() {
        EventoJugador resultado = evento;
        reiniciar();
        return resultado;
    }

    @Override
    public void reiniciar() {
        evento = new EventoJugador();
    }

    @Override
    public void setTipo(Enum tipo) {
        this.tipo = (TiposJugador)tipo;
    }
    
}
