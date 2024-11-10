/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module ManejadorLobby {
    requires EventoLogico;
    requires Dominio;
    requires Excepciones;
    requires Evento;
    requires Client;
    requires Observer;
    requires Domino64_EventBuilder;
    
    uses abstraccion.ICliente;
    uses implementacion.Client;
    uses observer.Observer;
}
