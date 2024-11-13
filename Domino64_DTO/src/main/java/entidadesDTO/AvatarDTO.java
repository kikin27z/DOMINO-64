package entidadesDTO;

/**
 * Enumeración que representa los avatares disponibles en el juego.
 * Cada avatar tiene un identificador único (url) que puede utilizarse para
 * acceder a la representación visual del avatar.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public enum AvatarDTO {
    AVE("/avatar/ave.png"),       
    GATO("/avatar/gato.png"),       
    JAGUAR("/avatar/jaguar.png"),     
    KIWI("/avatar/kiwi.png"),      
    MARIPOSA("/avatar/mariposa.png"),   
    PANDA("/avatar/panda.png"),     
    SERPIENTE("/avatar/serpiente.png"),  
    TORTUGA("/avatar/tortuga.png"),
    VENADO("/avatar/venado.png");     

    private final String url;  

    /**
     * Constructor de la enumeración AvatarDTO.
     * 
     * @param url el identificador único del avatar.
     */
    AvatarDTO(String url) {
        this.url = url; 
    }

    /**
     * Obtiene el identificador único del avatar.
     * 
     * @return el identificador del avatar.
     */
    public String getUrl() {
        return url;  
    }
}
