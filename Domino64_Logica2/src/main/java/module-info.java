module Domino64_Logica_ {
    requires utilities;
    requires PatronesAPI;

    requires java.logging;
    requires Domino64_Dominio;
    
    requires Domino64_DTO;
    uses java.util.logging.Logger;
    uses java.util.logging.Level;
    uses presentacion_utilities.FachadaNavegador;
    uses presentacion_utilities.Navegacion;
}
