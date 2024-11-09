/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module Client {
    requires Observer;
    requires Evento;
    requires java.logging;
    
    uses java.util.logging.Level;
    uses java.util.logging.Logger;
    
    exports abstraccion;
    exports implementacion;
}
