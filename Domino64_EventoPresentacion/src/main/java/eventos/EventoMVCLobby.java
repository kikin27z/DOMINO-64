/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import entidadesDTO.CuentaDTO;
import entidadesDTO.PartidaDTO;
import tiposEventos.TipoLobbyMVC;

/**
 *
 * @author luisa M
 */
public class EventoMVCLobby extends EventoMVC<CuentaDTO> {
    private CuentaDTO cuentaActualizada;
    private TipoLobbyMVC tipo;
    private PartidaDTO partida;
    
    public EventoMVCLobby(TipoLobbyMVC tipo){
        this.tipo = tipo;
    }
    
    public void setPartida(PartidaDTO partida){
        this.partida = partida;
    }
    
    public PartidaDTO getPartida(){
        return partida;
    }
    
    @Override
    public void agregarContexto(CuentaDTO contexto) {
        cuentaActualizada = contexto;
    }

    @Override
    public TipoLobbyMVC getTipo() {
        return tipo;
    }

    @Override
    public CuentaDTO getInfo() {
        return cuentaActualizada;
    }
    
}
