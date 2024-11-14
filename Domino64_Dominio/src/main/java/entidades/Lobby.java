package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author karim
 */
public class Lobby {

    private List<Cuenta> cuentas;
    private String codigoPartida;

    public Lobby() {
        crearCodigo();
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        asignarAvatar(cuenta);
        cuentas.add(cuenta);
    }

    private void asignarAvatar(Cuenta cuenta) {
        List<Avatar> usados = obtenerAvataresUsados();
        for (var avatar : Avatar.listaAvatares()) {
            if (!avatarUsado(usados, avatar)) {
                cuenta.setAvatar(avatar);
                break;
            }
        }
    }

    private boolean avatarUsado(List<Avatar> avatares, Avatar avatar) {
        for (var a : avatares) {
            if (a.getId() == avatar.getId()) {
                return true;
            }
        }
        return false;
    }

    public void cambiarAvatar(Cuenta cuenta, Avatar avatar) {
        Cuenta aux = obtenerCuenta(cuenta);
        aux.setAvatar(avatar);
    }

    private List<Avatar> obtenerAvataresUsados() {
        List<Avatar> avatares = new ArrayList<>();
        for (var cuenta : cuentas) {
            avatares.add(cuenta.getAvatar());
        }
        
        return avatares;
    }

    private void crearCodigo() {
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i == 3) {
                builder.append('-');
            }
            builder.append(rnd.nextInt(10));
        }
        codigoPartida = builder.toString();
    }

    private Cuenta obtenerCuenta(Cuenta cuenta) {
        for (var c : cuentas) {
            if (c.getIdCadena().equalsIgnoreCase(cuenta.getIdCadena())) {
                return c;
            }
        }
        return null;
    }

    public List<Avatar> obtenerAvataresDisponibles() {
        // Obtener los avatares que ya están en uso
        List<Avatar> avataresTotales = Avatar.listaAvatares();
        List<Avatar> avataresUsados = obtenerAvataresUsados();
        List<Avatar> disponibles = new ArrayList<>();

        // Por cada avatar total, verificar si no está siendo usado
        for (Avatar avatar : avataresTotales) {
            boolean estaUsado = avatarUsado(avataresUsados, avatar);
            // Si no está usado, agregarlo a la lista de disponibles
            if (!estaUsado) {
                disponibles.add(avatar);
            }
        }

        return disponibles;
    }
}
