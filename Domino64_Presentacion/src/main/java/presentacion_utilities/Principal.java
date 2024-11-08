package presentacion_utilities;

/**
 *
 * @author karim
 */
public class Principal {

    public static void main(String[] args) {
        System.out.println("Iniciado juego en presentación");

        INavegacion navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();

    }
}
