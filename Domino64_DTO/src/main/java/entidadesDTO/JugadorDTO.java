/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class JugadorDTO {
    private String username;
    private List<FichaDTO> fichas;
    private FichaDTO fichaSeleccionada;

    public JugadorDTO(String username){
        this.username = username;
        fichas= new ArrayList<>();
    }
    public JugadorDTO(){
        fichas = new ArrayList<>();
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<FichaDTO> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaDTO> fichas) {
        this.fichas = fichas;
    }

    public FichaDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDTO fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }
    
    public void removerFicha(FichaDTO ficha){
        this.fichas.remove(ficha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("username=").append(username);
        sb.append('}');
        return sb.toString();
    }
}
