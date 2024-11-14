package entidades;

/**
 *
 * @author luisa M
 */
public class Dominio {

    public static void main(String[] args) {
        Cuenta karim = new Cuenta();
        Cuenta luisa = new Cuenta();
        Cuenta paul = new Cuenta();
        Cuenta gibran = new Cuenta();
        
        Lobby lobby = new Lobby();
        
        lobby.agregarCuenta(karim);
        lobby.agregarCuenta(luisa);
        lobby.agregarCuenta(paul);
        lobby.cambiarAvatar(paul, Avatar.SERPIENTE);
        lobby.agregarCuenta(gibran);
        lobby.obtenerAvataresDisponibles();
    }
}
