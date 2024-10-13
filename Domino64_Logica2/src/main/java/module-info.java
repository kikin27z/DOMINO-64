/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module Domino64_Logica_ {
    requires utilities;
    requires PatronesAPI;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires java.logging;
    requires Domino64_Dominio;
    
    requires Domino64_DTO;
    uses java.util.logging.Logger;
    uses java.util.logging.Level;
    
    uses presentacion_utilities.FachadaNavegador;
    uses presentacion_utilities.Navegacion;
}
