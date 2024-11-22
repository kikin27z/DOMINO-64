package entidadesDTO;

import java.io.Serializable;

/**
 * Enumeración que representa los avatares disponibles en el juego.
 * Cada avatar tiene un identificador único (url) que puede utilizarse para
 * acceder a la representación visual del avatar.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public enum AvatarDTO {
    AVE("/avatar/ave.png","Piolin", "ave"),       
    GATO("/avatar/gato.png","Silvestre","gato"),       
    JAGUAR("/avatar/jaguar.png","Lucchi","jaguar"),     
    KIWI("/avatar/kiwi.png", "Jenni Rivera","kiwi"),      
    MARIPOSA("/avatar/mariposa.png", "Victor Encinas","mariposa"),   
    PANDA("/avatar/panda.png", "Po","panda"),     
    SERPIENTE("/avatar/serpiente.png", "Orochimaru","serpiente"),  
    TORTUGA("/avatar/tortuga.png", "Donatello","tortuga"),
    VENADO("/avatar/venado.png", "Chopper","venado");     

    private final String url;  
    private final String nombre;
    private final String animal;

    /**
     * Constructor de la enumeración AvatarDTO.
     * 
     * @param url el identificador único del avatar.
     */
    AvatarDTO(String url, String nombre, String animal) {
        this.url = url;
        this.nombre = nombre;
        this.animal = animal;
    }

    public String getNombre(){
        return nombre;
    }
    
    /**
     * Obtiene el identificador único del avatar.
     * 
     * @return el identificador del avatar.
     */
    public String getUrl() {
        return url;  
    }
    
    public String getAnimal() {
        return animal;  
    }
}
