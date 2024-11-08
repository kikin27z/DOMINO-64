package manejadores;


import presentacion_utilities.NotificadorEvento;

/**
 *
 * @author luisa M
 */
public class ManejadorCuenta  {

    private NotificadorEvento notificador;
    //private ICliente cliente;

    public ManejadorCuenta() {
        notificador = NotificadorEvento.getInstance();
    }

}
