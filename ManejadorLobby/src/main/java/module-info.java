module ManejadorLobby {
    requires EventoLogico;
    requires Domino64_EventBuilder;
    requires Domino64_Cliente;
    requires Domino64.Dominio;
    requires Observer;
    requires Evento;
    
    
    uses abstraccion.ICliente;
    uses implementacion.Client;
    uses observer.Observer;
    
}
