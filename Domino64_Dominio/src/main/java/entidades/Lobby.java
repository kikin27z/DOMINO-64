package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * La clase Lobby maneja la gestión de jugadores dentro de un lobby de juego.
 * Permite agregar jugadores, asignar avatares, gestionar el estado de "listo"
 * de los jugadores y generar un código único para la partida.
 * 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Lobby {
    private Map<Cuenta, Boolean> jugadoresListos;
    private List<Cuenta> cuentas;
    private Cuenta admin;
    private String codigoPartida;

    /**
     * Constructor que inicializa el lobby con un código de partida específico.
     * 
     * @param codigoPartida Código único para la partida.
     */
    public Lobby(String codigoPartida){
        this.codigoPartida = codigoPartida;
    }
    
    /**
     * Constructor por defecto que genera un código de partida aleatorio
     * y crea listas vacías para las cuentas y los jugadores listos.
     */
    public Lobby() {
        crearCodigo();
        cuentas = new ArrayList<>();
        jugadoresListos = new HashMap<>();
    }

    /**
     * Marca a un jugador como "listo" para la partida.
     * 
     * @param cuenta La cuenta del jugador que se marca como listo.
     */
    public void agregarJugadorListo(Cuenta cuenta){
        jugadoresListos.put(cuenta, true);
    }
    
    /**
     * Marca a un jugador como "no listo" para la partida.
     * 
     * @param cuenta La cuenta del jugador que se marca como no listo.
     */
    public void removerJugadorListo(Cuenta cuenta){
        jugadoresListos.put(cuenta, false);
    }
    
    /**
     * Obtiene una lista de los jugadores que están listos para la partida.
     * 
     * @return Lista de jugadores listos.
     */
    public List<Cuenta> getJugadoresListos(){
        List<Cuenta> jugadoresL = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if(jugadoresListos.get(cuenta))
                jugadoresL.add(cuenta);
        }
        return jugadoresL;
    }
    
    /**
     * Verifica si todos los jugadores están listos para la partida.
     * 
     * @return true si todos los jugadores están listos, false si alguno no lo está.
     */
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
    
    /**
     * Agrega una lista de cuentas de jugadores al lobby.
     * 
     * @param cuentas Lista de cuentas de jugadores.
     */
    public void agregarCuentas(List<Cuenta> cuentas){
        this.cuentas = cuentas;
    }
    
    /**
     * Agrega un solo jugador al lobby y asigna un avatar. Si es el primer jugador, 
     * se asigna como administrador.
     * 
     * @param cuenta La cuenta del jugador a agregar.
     * @return La cuenta del jugador agregado.
     */
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

    /**
     * Elimina una cuenta del lobby.
     * 
     * @param cuenta La cuenta a eliminar.
     */
    public void removerCuenta(Cuenta cuenta) {
        Cuenta c = obtenerCuenta(cuenta);
        
        cuentas.remove(c);
        System.out.println("se borro "+ this);
    }

    /**
     * Obtiene el código de la partida.
     * 
     * @return El código de la partida.
     */
    public String getCodigoPartida(){
        return codigoPartida;
    }
    
    /**
     * Asigna un avatar al jugador. Si hay avatares disponibles, se asigna uno.
     * 
     * @param cuenta La cuenta del jugador al que se asignará un avatar.
     * @return La cuenta del jugador con el avatar asignado.
     */
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

    /**
     * Verifica si un avatar ya está siendo usado.
     * 
     * @param avatares Lista de avatares ya usados.
     * @param avatar El avatar a verificar.
     * @return true si el avatar está en uso, false si no lo está.
     */
    private boolean avatarUsado(List<Avatar> avatares, Avatar avatar) {
        return avatares.contains(avatar);
    }

     /**
     * Cambia el avatar de un jugador.
     * 
     * @param cuenta La cuenta del jugador cuyo avatar será cambiado.
     * @param avatar El nuevo avatar a asignar.
     */
    public void cambiarAvatar(Cuenta cuenta, Avatar avatar) {
        Cuenta aux = obtenerCuenta(cuenta);
        aux.setAvatar(avatar);
    }

    /**
     * Obtiene los avatares ya usados por los jugadores en el lobby.
     * 
     * @return Lista de avatares usados.
     */
    private List<Avatar> obtenerAvataresUsados() {
        List<Avatar> avatares = new ArrayList<>();
        for (var cuenta : cuentas) {
            avatares.add(cuenta.getAvatar());
        }
        
        return avatares;
    }

    /**
     * Crea un código único aleatorio para la partida, de formato "XXXX-XX".
     */
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

     /**
     * Verifica si dos cuentas son la misma, comparando su ID de cadena.
     * 
     * @param cuenta1 La primera cuenta.
     * @param cuenta2 La segunda cuenta.
     * @return true si ambas cuentas son iguales, false si no lo son.
     */
    private Cuenta obtenerCuenta(Cuenta cuenta) {
        for (var c : cuentas) {
            if (esLaMismaCuenta(c, cuenta)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Obtiene todas las cuentas en el lobby.
     * 
     * @return Lista de cuentas de los jugadores.
     */
    public List<Cuenta> obtenerCuentas(){
        return cuentas;
    }
    
    /**
     * Obtiene los avatares disponibles que aún no han sido asignados a ningún jugador.
     * 
     * @return Lista de avatares disponibles.
     */
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
    
    /**
     * Método que compara si 2 cuentas son la misma
     * @param cuenta1 Cuenta a comparar 1
     * @param cuenta2 Cuenta a comparar 2
     * @return Verdadero si son las mismas, de lo contrario falso
     */
    private boolean esLaMismaCuenta(Cuenta cuenta1, Cuenta cuenta2){
        return cuenta1.getIdCadena().equalsIgnoreCase(cuenta2.getIdCadena());
    }

    @Override
    public String toString() {
        return "Lobby{" + "cuentas=" + cuentas + ", codigoPartida=" + codigoPartida + '}';
    }
}
