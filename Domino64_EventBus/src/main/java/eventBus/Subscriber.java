/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventBus;

import com.domino64.base.Suscriptor;

/**
 * Interfaz para representar a los suscriptores del bus.
 * Hereda de la interfaz Suscriptor, donde se define el metodo
 * para recibir eventos.
 * Tambien es una interfaz comparable, esto quiere decir que los 
 * suscriptores se van a poder comparar para fines de ordenamiento
 * en las colecciones (listas, mapeos) de suscriptores.
 * El criterio de comparacion se define mediante el id del suscriptor.
 * Esta interfaz inlcuye solo un metodo para obtener el id 
 * del suscriptor en cuestion
 * 
 * @author luisa M
 * @author karim F
 */
public interface Subscriber extends Suscriptor , Comparable<Subscriber> {
    /**
     * Obtiene el id del suscriptor
     * @return Un string representando el id del suscriptor
     */
    public int getSubscriberId();
    
}
