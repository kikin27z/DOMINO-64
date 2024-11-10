/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module EventoLogico{
    requires Dominio;
    requires Excepciones;
    requires Evento;
    
    exports eventos;
    exports tiposLogicos;
    requires Domino64.Dominio;
}
