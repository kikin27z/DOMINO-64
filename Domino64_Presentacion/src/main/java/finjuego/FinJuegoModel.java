package finjuego;

import entidadesDTO.CuentaDTO;
import entidadesDTO.ResultadosDTO;
import eventosFin.ObservableFinJuego;
import eventosFin.ObservableFinJuegoMVC;
import eventosFin.ObserverFinJuego;
import eventosFin.ObserverFinJuegoMVC;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class FinJuegoModel implements ObservableFinJuego, ObservableFinJuegoMVC {
    private final List<ObserverFinJuego> observersLogica;
    private final List<ObserverFinJuegoMVC> observersMVC;
    private boolean ganadorPuesto;

    public FinJuegoModel() {
        observersLogica = new ArrayList<>();
        observersMVC = new ArrayList<>();
    }

    public boolean isGanadorPuesto() {
        return ganadorPuesto;
    }

    public void setGanadorPuesto(boolean ganadorPuesto) {
        this.ganadorPuesto = ganadorPuesto;
    }
    
    @Override
    public void agregarObserver(ObserverFinJuego observador) {
        this.observersLogica.add(observador);
    }

    @Override
    public void quitarObserver(ObserverFinJuego observador) {
        this.observersLogica.remove(observador);
    }

    @Override
    public void avisarIrInicio() {
        for (var observer : observersLogica) {
            observer.avisarIrInicio();
        }
    }

    
    //Modelo a vista

    @Override
    public void agregarObserver(ObserverFinJuegoMVC observador) {
         this.observersMVC.add(observador);
    }

    @Override
    public void quitarObserver(ObserverFinJuegoMVC observador) {
        this.observersMVC.remove(observador);
    }

    @Override
    public void agregarPanelJugador(CuentaDTO cuenta, int puntos) {
        for (var observer : observersMVC) {
            observer.agregarPanelJugador(cuenta,puntos);
        }
    }

    @Override
    public void inicializarFinJuego(ResultadosDTO resultados) {
        List<CuentaDTO> cuentas = resultados.getCuentas();
        List<Integer> puntos = resultados.getPuntosConseguidos();
        for (int i = 0; i < cuentas.size(); i++) {
            agregarPanelJugador(cuentas.get(i), puntos.get(i));
        }
        
        for (var observer : observersMVC) {
            observer.inicializarFinJuego(resultados);
        }
    }

}
