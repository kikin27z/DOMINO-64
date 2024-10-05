package run;

import utilities.INavegacion;
import utilities.Navegacion;

/**
 * Esta clase carga el juego de Domino 64
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public class CargarJuego {

    public static void main(String[] args) {
        INavegacion navegacion = Navegacion.getInstance();
        navegacion.iniciarApp();

    }
}
