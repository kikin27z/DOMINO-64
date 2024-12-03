/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesDTO;
import java.io.Serializable;

/**
 * Clase que representa los datos necesarios para unirse a una partida. Incluye
 * el código de la partida a la que se desea unir y la cuenta asociada.
 * Implementa {@code Serializable} para permitir la transferencia de datos.
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class UnirseDTO implements Serializable {

    private String codigoPartida;
    private CuentaDTO cuenta;

    /**
     * Constructor que inicializa el objeto con el código de la partida.
     *
     * @param codigoPartida el código de la partida.
     */
    public UnirseDTO(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    /**
     * Obtiene el código de la partida.
     *
     * @return el código de la partida como una cadena de texto.
     */
    public String getCodigoPartida() {
        return codigoPartida;
    }

    /**
     * Establece el código de la partida.
     *
     * @param codigoPartida el código de la partida a establecer.
     */
    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    /**
     * Obtiene los datos de la cuenta asociada.
     *
     * @return un objeto {@code CuentaDTO} que representa la cuenta.
     */
    public CuentaDTO getCuenta() {
        return cuenta;
    }

    /**
     * Establece los datos de la cuenta asociada.
     *
     * @param cuenta un objeto {@code CuentaDTO} que contiene la información de
     * la cuenta.
     */
    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}
