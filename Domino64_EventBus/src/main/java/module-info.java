/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module BusExample {
    requires java.logging;
    requires Evento;
    requires PublicadorSuscriptor;
    
    exports eventBus;
    exports exceptions;
    
    uses domino64.eventos.base.Evento;
}
