/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventosPartida;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import java.util.List;


/**
 *
 * @author karim
 */
public interface  ObservablePartidaMVC {
    public void agregarObserver(ObserverPartidaMVC observador);
    public void quitarObserver(ObserverPartidaMVC observador);
    public void actualizarDarFichas(List<FichaDTO> fichas);
    public void actualizarDarFicha(FichaDTO ficha);
    public void actualizarTurno(JugadaDTO jugada);
}
