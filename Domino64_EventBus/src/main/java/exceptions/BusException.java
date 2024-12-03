package exceptions;

/**
 * Excepción personalizada para manejar errores relacionados con el bus de eventos.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class BusException extends Exception {

    // Constructor por defecto sin mensaje ni causa
    public BusException() {
    }

    // Constructor que acepta un mensaje de error
    public BusException(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje de error y una causa subyacente
    public BusException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor que acepta una causa subyacente
    public BusException(Throwable cause) {
        super(cause);
    }

    // Constructor con parámetros adicionales para habilitar la supresión y la escritura de la pila de seguimiento
    public BusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
