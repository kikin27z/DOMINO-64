/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventoss;

import entidadesDTO.CuentaDTO;
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
    
    public void agregarContexto(Object contexto) {
        this.contexto = contexto;
    }

    @Override
    public TipoLobbyMVC getTipo() {
        return tipo;
    }

    public Object getInfo() {
        return contexto;
    }
    
}
