/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entidadesDTO;

import java.io.Serializable;

/**
 *
 *
 * Enumeración que representa los avatares disponibles en el juego. Cada avatar
 * contiene información sobre su URL, nombre y tipo de animal. Esto permite
 * acceder a su representación visual y describir sus características.
 *
 * @author Luisa
 *
 * Fernanda Morales Espinoza - 00000233450 José Karim Franco Valencia -
 * 00000245138
 */
public enum AvatarDTO implements Serializable {
    AVE("/avatar/ave.png", "Piolin", "ave"),
    GATO("/avatar/gato.png", "Silvestre", "gato"),
    JAGUAR("/avatar/jaguar.png", "Lucchi", "jaguar"),
    KIWI("/avatar/kiwi.png", "Jenni Rivera", "kiwi"),
    MARIPOSA("/avatar/mariposa.png", "Victor Encinas", "mariposa"),
    PANDA("/avatar/panda.png", "Po", "panda"),
    SERPIENTE("/avatar/serpiente.png", "Orochimaru", "serpiente"),
    TORTUGA("/avatar/tortuga.png", "Donatello", "tortuga"),
    VENADO("/avatar/venado.png", "Chopper", "venado");

    // Atributos
    private final String url;  // Ruta de la imagen del avatar
    private final String nombre;  // Nombre descriptivo del avatar
    private final String animal;  // Tipo de animal representado por el avatar

    /*
     
Constructor de la enumeración AvatarDTO.@param url Ruta de la imagen del avatar.@param nombre Nombre descriptivo del avatar.@param animal Tipo de animal representado por el avatar

    .*/
    AvatarDTO(String url, String nombre, String animal) {
        this.url = url;
        this.nombre = nombre;
        this.animal = animal;

    }

    /*
     
Obtiene el nombre descriptivo del avatar
    .
@
    
    return el nombre del avatar

    .*/
    public String getNombre() {
        return nombre;
    }

    /*
     
Obtiene la URL de la imagen del avatar
    .

@
    
    return la ruta del avatar

    .*/
    public String getUrl() {
        return url;
    }

    /**
     * Obtiene el tipo de animal representado por el avatar.
     *
     * @return el tipo de animal.
     */
    public String getAnimal() {
        return animal;
    }
}
