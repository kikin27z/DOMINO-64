/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import static acciones.AccionBase.gameHandler;
import entidades.Ficha;
import entidades.JugadaPosible;
import java.util.Map;

/**
 *
 * @author luisa M
 */
public class SeleccionarFicha extends AccionBase{

    /**
     * Metodo que obtiene un arreglo con la ficha seleccionada como primer
     * elemento, y el segundo elemento es el valor de la jugada posible con la
     * ficha seleccionada. La jugada se obtiene buscando el mapeo dentro del
     * mapa 'fichasValidas' del parametro, usando la ficha seleccionada por el
     * jugador, como llave para buscar el valor mediante el metodo 'get(key)' de
     * Map
     *
     * @param fichasValidas mapeo de jugadas validas que puede hacer el jugador.
     * @return un arreglo con la ficha seleccionada y la jugada que se puede
     * hacer con dicha ficha
     * @throws InterruptedException en caso de que se interrumpa el hilo
     * mientras esta dormido
     */
    private Object[] obtenerFichaSeleccionada(Map<Ficha, JugadaPosible> fichasValidas) throws InterruptedException {
        while (gameHandler.getJugador().getFichaSeleccionada() == null) {
            System.out.println("SELECCIONA UNA FICHA");
            System.out.println("TUS FICHAS VALIDAS SON:\n" + fichasValidas);
            Thread.sleep(5000);//verifica que pasa si se selecciona una ficha mientras el hilo esta dormido
        }
        Ficha fichaSeleccionada = gameHandler.getJugador().getFichaSeleccionada();
        JugadaPosible jugada = fichasValidas.get(fichaSeleccionada);
        Object[] jugadaSeleccionada = {fichaSeleccionada, jugada};
        return jugadaSeleccionada;
    }
    
    @Override
    public void ejecutarAccion() {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
