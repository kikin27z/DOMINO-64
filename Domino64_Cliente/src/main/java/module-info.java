module Client {
    requires Observer;
    requires Evento;
    requires java.logging;
    
    uses java.util.logging.Level;
    uses java.util.logging.Logger;
    
    exports abstraccion;
    exports implementacion;
}
