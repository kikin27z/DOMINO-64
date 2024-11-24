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
public class EventoMVCLobby extends EventoMVC {
    private Object contexto;
    private CuentaDTO cuentaActualizada;
    private TipoLobbyMVC tipo;
    
    public EventoMVCLobby(TipoLobbyMVC tipo){
        this.tipo = tipo;
    }
    
    public void setCuenta(CuentaDTO cuenta){
        this.cuentaActualizada = cuenta;
    }
    
    public CuentaDTO getCuenta(){
        return cuentaActualizada;
    }
    
    @Override
    public void agregarContexto(Object contexto) {
        this.contexto = contexto;
    }

    @Override
    public TipoLobbyMVC getTipo() {
        return tipo;
    }

    @Override
    public Object getInfo() {
        return contexto;
    }

    @Override
    public int getIdContexto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
