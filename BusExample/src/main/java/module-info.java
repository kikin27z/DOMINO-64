/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module BusExample {
    requires ObserverAPI;
    requires java.logging;
    requires java.desktop;
    
    uses observer.Observable;
    uses observer.Observer;
}
