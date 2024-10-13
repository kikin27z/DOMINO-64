package presentacion_observers;

import entidadesDTO.CuentaDTO;

/**
 *
 * @author karim
 */
public interface ObservadorLobby {
    public void actualizarMensajeAviso();
    public void actualizarEncabezado();
    public void agregarOtroJugador(CuentaDTO cuenta);
    public void quitarOtroJugador(CuentaDTO cuenta);
    public void ponerListoJugador(CuentaDTO cuenta);
    public void quitarListoJugador(CuentaDTO cuenta);
    public void actualizarAvatarJugador(CuentaDTO cuenta);
    public void actualizarNombreOtroJugador(CuentaDTO cuenta);
    
}
