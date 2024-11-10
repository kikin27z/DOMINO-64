/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logicaPartida;

import abstraccion.ICliente;
import com.luisa.entidades.Jugador;
import domino64.eventos.base.Evento;
import eventos.EventoPartida;
import logicaNotificacionEventos.PublicadorEventos;

/**
 *
 * @author luisa M
 */
public class ManejadorJugador extends ObservadorPartidaLocal{
    private Jugador jugador;
    private ICliente cliente;
    private PublicadorEventos publicador;
    
    public void init(ICliente cliente, PublicadorEventos publicador){
        this.cliente = cliente;
        this.publicador = publicador;
    }
    
    @Override
    public void manejarError(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void iniciarPartida(Evento evento) {
        EventoPartida ev = (EventoPartida)evento;
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removerJugador(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void notificarPeticion(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void anunciarGanador(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void terminarPartida(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cambiarTurno(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
