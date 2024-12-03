package eventosFin;

import entidadesDTO.CuentaDTO;
import entidadesDTO.ResultadosDTO;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public interface ObservableFinJuegoMVC {
    public void agregarObserver(ObserverFinJuegoMVC observador);
    public void quitarObserver(ObserverFinJuegoMVC observador);
    public void agregarPanelJugador(CuentaDTO cuenta, int puntos);
    public void inicializarFinJuego(ResultadosDTO resultados);
}
