module ManejadorLobby {
    requires EventoLogico;
    requires Excepciones;
    requires Evento;
    requires Client;
    requires Observer;
    requires Domino64_EventBuilder;
    
    uses abstraccion.ICliente;
    uses implementacion.Client;
    uses observer.Observer;
}
