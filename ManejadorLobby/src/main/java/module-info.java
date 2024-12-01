module ManejadorLobby {
    requires EventoLogico;
    requires Evento;
    requires Client;
    requires Observer;
    requires Domino64_EventBuilder;
    requires Domino64_Dominio;
    requires Domino64_DTO;
    requires java.logging;
    requires Domino64.AdaptadorDTO;
    
    uses java.util.logging.Level;
    uses java.util.logging.Logger;
    uses abstraccion.ICliente;
    uses implementacion.Client;
//    uses cliente_suscripciones.ObserverCliente;
}
