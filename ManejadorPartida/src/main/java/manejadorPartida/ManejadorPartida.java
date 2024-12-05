package manejadorPartida;

import adapter.AdaptadorDTO;
import adapter.AdaptadorEntidad;
import entidades.Cuenta;
import entidades.Ficha;
import entidades.Jugada;
import entidades.Jugador;
import entidades.Partida;
import entidadesDTO.CuentaDTO;
import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaIniciadaDTO;
import entidadesDTO.ResultadosDTO;
import entidadesDTO.TurnosDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.PosibleJugadaDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class ManejadorPartida {
    private final Partida partida;
    private List<Jugador> jugadores;
    private Map<Jugador,Integer> peticionesPorJugador;
    private Jugada jugadaActual;
    private final AdaptadorEntidad adaptador;
    private final AdaptadorDTO adaptadorDTO;
    
    public ManejadorPartida(){
        adaptador = new AdaptadorEntidad();
        adaptadorDTO = new AdaptadorDTO();
        partida = new Partida();
        peticionesPorJugador = new HashMap<>();
    }

    public void agregarJugador(JugadorDTO jugador){
        Jugador j = adaptadorDTO.adaptarJugadorDTO(jugador);
    }
    
    public void setJugadores(List<JugadorDTO> jugadores){
        partida.setJugadores(adaptadorDTO.adaptarJugadoresDTO(jugadores));
        this.jugadores = partida.getJugadores();
        for (Jugador jugador : this.jugadores) {
            peticionesPorJugador.put(jugador,0);
        }
    }
    
    public List<JugadorDTO> crearPartida(List<CuentaDTO> cuentasDTO){
        List<Cuenta> cuentas = adaptadorDTO.adaptarCuentasDTO(cuentasDTO);
        
        jugadores = new ArrayList<>();
        for (Cuenta c : cuentas) {
            Jugador jugador = new Jugador(c);
            jugadores.add(jugador);
        }

       partida.setJugadores(jugadores);
       return adaptador.adaptarJugadores(jugadores);
    }
    
    public ResultadosDTO terminarPartida(){
        ResultadosDTO resultados = new ResultadosDTO();
        Map<Cuenta, Integer> puntajes = obtenerResultados();
        resultados.setPuntajes(convertirDTO(puntajes));
        return resultados;
    }
    
    private Map<CuentaDTO, Integer> convertirDTO(Map<Cuenta, Integer> mapeo){
        Map<CuentaDTO, Integer> dtos = new LinkedHashMap<>();
        for (Map.Entry<Cuenta, Integer> entry : mapeo.entrySet()) {
            CuentaDTO cuenta = adaptador.adaptarEntidadCuenta(entry.getKey());
            dtos.put(cuenta, entry.getValue());
        }
        return dtos;
    }
    
    private Map<Cuenta, Integer> ordenarPorPuntaje(Map<Cuenta, Integer> mapeo){
        LinkedHashMap<Cuenta, Integer> ordenado = mapeo.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        return ordenado;
    }
    
    private Map<Cuenta, Integer> obtenerResultados(){
        Map<Cuenta, Integer> mapeo = new HashMap<>();
        
        for (Jugador jugador : jugadores) {
            Cuenta cuenta = jugador.getCuenta();
            int puntaje = 0;
            
            if(!jugador.getFichas().isEmpty()){
                for (Ficha ficha : jugador.getFichas()) {
                    puntaje+=ficha.getValor();
                }
            }
            mapeo.put(cuenta, puntaje);
        }
        
        return ordenarPorPuntaje(mapeo);
    }
    
    private Jugador buscarJugador(String idCuenta){
        for (Jugador jugador : jugadores) {
            if(jugador.obtenerIdJugador().equals(idCuenta)){
                return jugador;
            }
        }
        return null;
    }
    
    private void removerJugador(Jugador jugador){
        jugadores.remove(jugador);
        peticionesPorJugador.remove(jugador);
        
    }
    
    public boolean todosRendidos(){
        for (Map.Entry<Jugador, Integer> entry : peticionesPorJugador.entrySet()) {
            if(entry.getValue()==0)
                return false;
        }
        return true;
    }
    
    public boolean esSegundaPeticionJugador(CuentaDTO cuenta){
        Jugador jugador = buscarJugador(cuenta.getIdCadena());
        return peticionesPorJugador.get(jugador) == 2;
    }
    
    public void agregarPeticionRendirse(CuentaDTO cuenta){
        Jugador jugador = buscarJugador(cuenta.getIdCadena());
        peticionesPorJugador.merge(jugador, 1, (oldV, newV) -> oldV+newV);
        partida.seRindioJugador(jugador);
    }
    
    public JugadorDTO jugadorAbandono(CuentaDTO jugador) {
        Jugador j = buscarJugador(jugador.getIdCadena());
        JugadorDTO dto = adaptador.adaptarEntidadJugador(j);
        
//        List<Ficha> fichas = partida.abandonoJugador(j);
        removerJugador(j);
        return dto;
    }
    
    public void quitarFicha(CuentaDTO c, FichaDTO f){
//        Cuenta cuenta = adaptadorDTO.adaptarCuentaDTO(c);
        Ficha ficha = adaptadorDTO.adaptarFichaDTO(f);
        
        Jugador jugador = buscarJugador(c.getIdCadena());
        jugador.removerFicha(ficha);
        jugadores.set(jugadores.indexOf(jugador), jugador);
    }
    
    public void agregarFicha(CuentaDTO c, FichaDTO f){
//        Cuenta cuenta = adaptadorDTO.adaptarCuentaDTO(c);
        Ficha ficha = adaptadorDTO.adaptarFichaDTO(f);
        
//        Jugador jugador = partida.obtenerJugador(cuenta);
        Jugador jugador = buscarJugador(c.getIdCadena());
        jugador.agregarFicha(ficha);
        jugadores.set(jugadores.indexOf(jugador), jugador);
    }
    
    public void repartirFichas(MazosDTO mazos){
        List<Cuenta> cuentas = adaptadorDTO.adaptarCuentasDTO(mazos.getCuentas());
        List<List<Ficha>> manos = new ArrayList<>();
        
        for(var mazo : mazos.getMazos()){
            List<Ficha> fichas = adaptadorDTO.adaptarFichaDTO(mazo);
            manos.add(fichas);
        }
        
        for (int i = 0; i < cuentas.size(); i++) {
             Jugador jugador = partida.obtenerJugador(cuentas.get(i));
            jugador.setFichas(manos.get(i));
        }
    }
    
    public JugadaDTO obtenerJugadaActual(){
        return adaptador.adaptarJugada(jugadaActual);
    }
    
    public void agregarJugadaActual(JugadaDTO jugadaDTO){
        this.jugadaActual = adaptadorDTO.adaptarJugadaDTO(jugadaDTO);
    }
    
    
    public Map<FichaDTO, PosibleJugadaDTO> jugadasPosibles(CuentaDTO cuentaDTO, JugadaDTO jugadaPosible){
        Cuenta aux = adaptadorDTO.adaptarCuentaDTO(cuentaDTO);
        Jugador jugador = partida.obtenerJugador(aux);

        Map<FichaDTO, PosibleJugadaDTO> jugadas = new HashMap<>();
        for (Ficha ficha : jugador.getFichas()) {
            PosibleJugadaDTO jugada = jugadaPosible.determinarJugada(adaptador.adaptarEntidadFicha(ficha));
            if (!jugada.equals(PosibleJugadaDTO.NINGUNA)) {
                jugadas.put(adaptador.adaptarEntidadFicha(ficha), jugada);
            }
        }
        return jugadas;
    }
    
    public boolean tieneJugada(CuentaDTO cuentaDTO){
        Cuenta aux = adaptadorDTO.adaptarCuentaDTO(cuentaDTO);
        Jugador jugador = partida.obtenerJugador(aux);
        
        return jugadaActual.puedeJugar(jugador.getFichas());
    }
}
