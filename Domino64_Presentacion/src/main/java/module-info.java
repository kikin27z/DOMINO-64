module utilities {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires PatronesAPI;
    requires Domino64_DTO;
    
    uses com.mycompany.patrones.observer.Observable;
    uses com.mycompany.patrones.observer.Observer;
    requires Domino64_Dominio;
    requires Domino64.DTOs;
    
    // Exporta y abre el paquete 'utilities' para que otros módulos puedan acceder y cargar FXML
    exports presentacion_utilities;
    opens presentacion_utilities to javafx.fxml;

    // Exporta y abre el paquete 'inicio' para que FXMLLoader pueda acceder a los archivos y controladores FXML
    exports inicio;

    // Exporta y abre el paquete 'lobby' para que FXMLLoader pueda acceder a los archivos y controladores FXML
    exports lobby;
    
    exports partida;
    opens partida to javafx.fxml;
    
    exports opciones_partida;
    opens opciones_partida to javafx.fxml;
//    
    
}
