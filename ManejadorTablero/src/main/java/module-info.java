/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module ManejadorTablero {
    requires Domino64_Dominio;
    requires Evento;
    requires Domino64_DTO;
    requires Domino64_AdaptadorDTO;
    requires Observer;
    requires EventoLogico;
    requires Client;
    requires Domino64_EventBuilder;
    requires java.logging;
    exports manejadorTablero;
}
