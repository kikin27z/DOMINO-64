package excepciones;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author luisa M
 */
public class DominioException extends Exception{
    
    public DominioException() {
    }

    public DominioException(String message) {
        super(message);
    }

    public DominioException(String message, Throwable cause) {
        super(message, cause);
    }

    public DominioException(Throwable cause) {
        super(cause);
    }

    public DominioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
