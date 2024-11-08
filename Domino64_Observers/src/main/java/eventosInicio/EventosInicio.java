package eventosInicio;

/**
 *
 * @author karim
 */
public interface EventosInicio {
    public void agregarObserver(ObserverInicio observador);
    public void quitarObserver(ObserverInicio observador);
    public void avisarModoJugar();
    public void avisarMostrarCreditos();
}
