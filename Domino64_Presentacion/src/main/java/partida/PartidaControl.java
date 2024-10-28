package partida;

//import entidades.Ficha;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import observer_MVC.EventoMVC;
import observer_MVC.ObservadorMVC;


/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class PartidaControl implements ObservadorMVC{   
    private final Map<String, Consumer<EventoMVC<?>>> eventos;
    private PartidaView view;
    private PartidaModel modelo;

    public PartidaControl(PartidaView view, PartidaModel modelo) {
        this.view = view;
        this.modelo = modelo;
        view.agregarObservador(this);
        
        CuentaDTO cuenta = new CuentaDTO(0, "/avatar/tortuga.png", "Karim");
        
        
        view.insertarMesaAba(cuenta);
        eventos = new HashMap<>();
        cargarEventos();
       
//        modelo.agregarFichasUsuarioActual(fichas);
    }
    
    //-------------------Eventos-------------------
    private void cargarEventos(){
        eventos.put("agarrarFichaPozo", this::eventoPozo);   
    }
    
    public void fichaDominoValidar(MouseEvent e){
        
        Canvas dibujoFicha = (Canvas) e.getSource();
        FichaDTO ficha = modelo.obtenerFichaExacta(dibujoFicha);
        modelo.agregarFichaAlTablero(ficha, modelo.getTablero(), true);
    }
    
    public void mostrarFichasMazo(MouseEvent e){
//        modelo.agregarFichaUsuarioActual(new FichaDTO(3,3));
    }
    
    public void eventoPozo(EventoMVC<?> evento){
        System.out.println((String)evento.getData());
        
    }

    @Override
    public void actualizar(EventoMVC<?> evento) {
        Consumer<EventoMVC<?>> manejador = eventos.get(evento.getTipo());
        if (manejador != null) {
            manejador.accept(evento);
        } else {
            System.out.println("Evento desconocido: " + evento.getTipo());
        }
    }
}
