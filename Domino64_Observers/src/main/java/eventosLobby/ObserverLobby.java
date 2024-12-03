package eventosLobby;

import entidadesDTO.CuentaDTO;
import entidadesDTO.ReglasDTO;

/**
 *
 * @author karim
 */
public interface ObserverLobby {
    public void avisarCambioAvatar(CuentaDTO cuentaActualizada);
    public void avisarCuentaLista();
    public void avisarCuentaNoLista();
//    public void avisarIniciarPartida();
    public void avisarAbandonar();
     public void avisarActualizarReglas(ReglasDTO reglas);
}
