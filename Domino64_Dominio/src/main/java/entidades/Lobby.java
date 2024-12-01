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
    private Cuenta admin;
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
    
    public Cuenta agregarCuenta(Cuenta cuenta) {
        if(cuentas.isEmpty()){
            admin = cuenta;
            admin.setAdmin(true);
        }else{
            admin.setAdmin(false);
        }
        cuenta = asignarAvatar(cuenta);
        cuentas.add(cuenta);
        return cuenta;
    }

    public void removerCuenta(Cuenta cuenta) {
        Cuenta c = obtenerCuenta(cuenta);
        
        cuentas.remove(c);
        System.out.println("se borro "+ this);
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
            if (esLaMismaCuenta(c, cuenta)) {
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
    
    private boolean esLaMismaCuenta(Cuenta cuenta1, Cuenta cuenta2){
        return cuenta1.getIdCadena().equalsIgnoreCase(cuenta2.getIdCadena());
    }

    @Override
    public String toString() {
        return "Lobby{" + "cuentas=" + cuentas + ", codigoPartida=" + codigoPartida + '}';
    }
}
