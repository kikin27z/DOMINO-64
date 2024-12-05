package entidadesDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa los resultados de una partida o proceso en el sistema.
 * Contiene una lista de cuentas involucradas y los puntos obtenidos por cada una.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ResultadosDTO implements Serializable {

    // Lista de cuentas participantes en el resultado.
    private List<CuentaDTO> cuentas;

    // Lista de puntos conseguidos, donde cada índice corresponde a la cuenta en la misma posición.
    private List<Integer> puntosConseguidos;

    /**
     * Constructor por defecto.
     * Inicializa una instancia vacía de ResultadosDTO.
     */
    public ResultadosDTO() {
    }

    /**
     * Obtiene la lista de cuentas participantes.
     * 
     * @return una lista de objetos {@code CuentaDTO}.
     */
    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    /**
     * Establece la lista de cuentas participantes.
     * 
     * @param cuentas una lista de objetos {@code CuentaDTO}.
     */
    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * Obtiene la lista de puntos conseguidos por las cuentas.
     * 
     * @return una lista de enteros que representan los puntos conseguidos.
     */
    public List<Integer> getPuntosConseguidos() {
        return puntosConseguidos;
    }

    /**
     * Establece la lista de puntos conseguidos por las cuentas.
     * 
     * @param puntosConseguidos una lista de enteros que representan los puntos.
     */
    public void setPuntosConseguidos(List<Integer> puntosConseguidos) {
        this.puntosConseguidos = puntosConseguidos;
    }
}
