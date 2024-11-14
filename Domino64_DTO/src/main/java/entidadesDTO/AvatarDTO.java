package entidadesDTO;

import java.io.Serializable;

/**
 * Enumeración que representa los avatares disponibles en el juego.
 * Cada avatar tiene un identificador único (url) que puede utilizarse para
 * acceder a la representación visual del avatar.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author José Karim Franco Valencia - 00000245138
 */
public enum AvatarDTO implements Serializable {
    AVE(0),       
    GATO(1),       
    JAGUAR(2),     
    KIWI(3),      
    MARIPOSA(4),   
    PANDA(5),     
    SERPIENTE(6),  
    TORTUGA(7),
    VENADO(8);     

    private final int url;  

    /**
     * Constructor de la enumeración AvatarDTO.
     * 
     * @param url el identificador único del avatar.
     */
    AvatarDTO(int url) {
        this.url = url; 
    }

    /**
     * Obtiene el identificador único del avatar.
     * 
     * @return el identificador del avatar.
     */
    public int getUrl() {
        return url;  
    }
}
