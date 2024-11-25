package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author karim
 */
public class Lobby {
    private Map<Cuenta, Boolean> jugadoresListos;
    private List<Cuenta> cuentas;
    private String codigoPartida;

    public Lobby(String codigoPartida){
        this.codigoPartida = codigoPartida;
    }
    
    public Lobby() {
        crearCodigo();
        cuentas = new ArrayList<>();
        jugadoresListos = new HashMap<>();
    }

    public void agregarJugadorListo(Cuenta cuenta){
        jugadoresListos.put(cuenta, true);
    }
    
    public void removerJugadorListo(Cuenta cuenta){
        jugadoresListos.put(cuenta, false);
    }
    
    public List<Cuenta> getJugadoresListos(){
        List<Cuenta> jugadoresL = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if(jugadoresListos.get(cuenta))
                jugadoresL.add(cuenta);
        }
        return jugadoresL;
    }
    
    public boolean todosListos(){
        int contador = 0;
        for (Cuenta cuenta : cuentas) {
            if(jugadoresListos.get(cuenta))
                contador++;
            else
                break;
        }
        return contador == cuentas.size();
    }
    
    public void agregarCuentas(List<Cuenta> cuentas){
        this.cuentas = cuentas;
    }
    
    public void agregarCuenta(Cuenta cuenta) {
        //cuenta = asignarAvatar(cuenta);
        cuentas.add(cuenta);
        jugadoresListos.putIfAbsent(cuenta, false);
    }

    public void removerCuenta(Cuenta cuenta) {
        //cuenta = asignarAvatar(cuenta);
        cuentas.remove(cuenta);
        jugadoresListos.remove(cuenta);
    }

    public String getCodigoPartida(){
        return codigoPartida;
    }
    
    public Cuenta asignarAvatar(Cuenta cuenta) {
        List<Avatar> usados = obtenerAvataresUsados();
        for (var avatar : Avatar.listaAvatares()) {
            if (!avatarUsado(usados, avatar)) {
                cuenta.setAvatar(avatar);
                break;
            }
        }
        return cuenta;
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

    public List<Cuenta> obtenerCuentas(){
        return cuentas;
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

    @Override
    public String toString() {
        return "Lobby{" + "cuentas=" + cuentas + ", codigoPartida=" + codigoPartida + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigoPartida);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lobby other = (Lobby) obj;
        return Objects.equals(this.codigoPartida, other.codigoPartida);
    }
    
    
    
}
