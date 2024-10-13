/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import com.mycompany.patrones.command.Accion;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class InicioModel {
    private Accion jugarSolo;
    private Accion irLobby;
    private List<Accion> acciones;
    
    public void setAcciones(List<Accion> acciones){
        this.acciones = acciones;
    }
    
    public List<Accion> getAcciones(){
        return acciones;
    }
    
    public void setAccionJugarSolo(Accion accion){
        this.jugarSolo = accion;
    }
    
    public Accion getAccionJugarSolo() {
        return jugarSolo;
    }
    
    public void setAccionIrLobby(Accion accion){
        this.irLobby = accion;
    }
    
    public Accion getAccionIrLobby() {
        return irLobby;
    }
}
