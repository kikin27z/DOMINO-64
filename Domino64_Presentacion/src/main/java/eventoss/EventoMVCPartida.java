/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventoss;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadorDTO;

/**
 *
 * @author luisa M
 */
public class EventoMVCPartida extends EventoMVC {
    private TipoPartidaMVC tipo;
    private FichaDTO contexto;

    public EventoMVCPartida(TipoPartidaMVC tipo) {
        this.tipo = tipo;
    }

    public void agregarContexto(FichaDTO contexto) {
        this.contexto = contexto;
    }

    @Override
    public TipoPartidaMVC getTipo() {
        return tipo;
    }

    //@Override
    public FichaDTO getInfo() {
        return contexto;
    }

    public void setPublicador(JugadorDTO jugador) {
        this.setPublicador(jugador.getCuenta());
    }

}
