module utilities {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    // Exporta y abre el paquete 'utilities' para que otros módulos puedan acceder y cargar FXML
    exports utilities;
    opens utilities to javafx.fxml;

    // Exporta y abre el paquete 'inicio' para que FXMLLoader pueda acceder a los archivos y controladores FXML
    exports inicio;
    opens inicio to javafx.fxml;

    // Exporta y abre el paquete 'lobby' para que FXMLLoader pueda acceder a los archivos y controladores FXML
    exports lobby;
    opens lobby to javafx.fxml;
    
    exports partida;
    opens partida to javafx.fxml;
//    
}
