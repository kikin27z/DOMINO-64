package presentacion_utilities;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase principal que extiende de {@link Application} y sirve como punto de entrada
 * para iniciar la aplicación. Esta clase configura la ventana principal de la
 * aplicación, establece su icono, título y la vista inicial.
 * 
 * En el método {@link #start(Stage)} se configura la escena inicial y se establece
 * la primera vista.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class App extends Application{

    /**
     * Método que se llama al inicio de la aplicación. Este método configura el
     * escenario (Stage) y cambia la vista inicial a la pantalla de opciones de partida.
     * 
     * También establece el icono de la aplicación y el título de la ventana. Además,
     * desactiva el redimensionamiento de la ventana para que tenga un tamaño fijo.
     * 
     * @param stage el escenario principal donde se mostrará la vista.
     * @throws Exception si ocurre algún error durante la inicialización de la ventana.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Establece el fondo (escenario).
        Navegacion.getInstance().setFondo(stage);
        Navegacion.getInstance().cambiarOpcionesPartida();
        
        // Establece el icono de la aplicación.
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        stage.getIcons().add(icon);
        
        // Establece el título de la ventana.
        stage.setTitle("Domino 64");
        
        // Desactiva el redimensionamiento de la ventana.
        stage.setResizable(false);
    }
}
