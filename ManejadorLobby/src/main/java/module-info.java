module ManejadorLobby {
    requires EventoLogico;
    requires Evento;
    requires Client;
    requires Observer;
    requires Domino64_EventBuilder;
    requires Domino64_Dominio;
    requires Domino64_DTO;
    requires java.logging;
    
    uses java.util.logging.Level;
    uses java.util.logging.Logger;
    uses abstraccion.ICliente;
    uses implementacion.Client;
    uses observer.Observer;
}
