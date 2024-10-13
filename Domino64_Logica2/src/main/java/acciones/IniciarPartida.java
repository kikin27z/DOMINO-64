/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import static acciones.AccionBase.gameHandler;
import static acciones.AccionBase.tileHandler;
import static acciones.AccionBase.turnHandler;
import entidades.Ficha;
import entidades.JugadaPosible;
import entidades.Jugador;
import entidades.ModoPartida;
import entidades.Partida;
import excepciones.LogicException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.GameHandler;

/**
 *
 * @author luisa M
 */
public class IniciarPartida extends AccionBase implements Runnable{
    
    private void buscarPrimeraMula(List<Jugador> jugadores) throws LogicException {
        Object[] dupla;
        Jugador primerJugador;
        Ficha ficha;
        do{
            dupla = tileHandler.obtenerPrimeraMula(jugadores);
            ficha = (Ficha) dupla[0];
        }while(ficha==null);
        
        primerJugador = (Jugador) dupla[1];
        tileHandler.colocarPrimeraMula(ficha);
        turnHandler.designarPrimerTurno(primerJugador);
    }

    private void colocarPrimeraMula() throws LogicException {
        Ficha primeraMula = turnHandler.getMulaMasAlta();
        Jugador primerJugador = turnHandler.getJugadorEnTurno();
        tileHandler.colocarPrimeraMula(primeraMula);
        primerJugador.removerFicha(primeraMula);
    }
    
    /**
     * Metodo que obtiene un arreglo con la ficha seleccionada como primer elemento,
     * y el segundo elemento es el valor de la jugada posible con la ficha seleccionada.
     * La jugada se obtiene buscando el mapeo dentro del mapa 'fichasValidas' del parametro,
     * usando la ficha seleccionada por el jugador, como llave para buscar el valor mediante
     * el metodo 'get(key)' de Map 
     * @param fichasValidas mapeo de jugadas validas que puede hacer el jugador.
     * @return un arreglo con la ficha seleccionada y la jugada que se puede hacer con dicha ficha
     * @throws InterruptedException en caso de que se interrumpa el hilo mientras esta dormido
     */
    private Object[] obtenerFichaSeleccionada(Map<Ficha,JugadaPosible> fichasValidas) throws InterruptedException{
        while (gameHandler.getJugador().getFichaSeleccionada() == null) {
            System.out.println("SELECCIONA UNA FICHA");
            System.out.println("TUS FICHAS VALIDAS SON:\n" + fichasValidas);
            Thread.sleep(5000);//verifica que pasa si se selecciona una ficha mientras el hilo esta dormido
        }
        Ficha fichaSeleccionada = gameHandler.getJugador().getFichaSeleccionada();
        JugadaPosible jugada = fichasValidas.get(fichaSeleccionada);
        Object[] jugadaSeleccionada = {fichaSeleccionada,jugada};
        return jugadaSeleccionada;
    }
    
    /**
     * metodo para crear una jugada en base a la ficha seleccionada.
     * Este metodo valida que se haya seleccionado una ficha valida.
     * Al llamar al metodo 'obtenerFichaSeleccionada', se obtiene un arreglo
     * con la ficha seleccionada como primer elemento, y el segundo elemento
     * es el valor de la jugada posible con la ficha seleccionada. 
     * Si el valor de la jugada posible es igual a null, significa que la ficha
     * no se encuentra dentro del mapeo de jugadas validas proporcionadas 
     * por el parametro, por lo tanto no se puede jugar esa ficha.
     * @param fichasValidas mapeo de jugadas validas que puede hacer el jugador. 
     * Cada ficha esta mapeada al tipo de jugada que puede hacer con ella, es decir,
     * si la puede colocar de un extremo, o si la puede colocar en ambos extremos.
     * @return Un mapeo con la ficha seleccionada por el jugador y el tipo de jugada
     * que puede realizar con dicha ficha
     * @throws InterruptedException en caso de que se interrumpa el hilo mientras esta dormido
     */
    private Map.Entry<Ficha,JugadaPosible> crearJugada(Map<Ficha,JugadaPosible> fichasValidas) throws InterruptedException{
        Object[] jugadaSeleccionada;
        Ficha fichaSeleccionada;
        JugadaPosible jugada;
        do{
            jugadaSeleccionada = obtenerFichaSeleccionada(fichasValidas);
            fichaSeleccionada = (Ficha) jugadaSeleccionada[0];
            jugada = (JugadaPosible) jugadaSeleccionada[1];
        }while(jugada == null);
        
        Map.Entry<Ficha, JugadaPosible> entry = Map.entry(fichaSeleccionada, jugada);
        return entry;
    }
    
    private Map.Entry<Ficha,JugadaPosible> seleccionarFichaAutomaticamente(
            Map<Ficha,JugadaPosible> fichasValidas){
        
        Map.Entry<Ficha, JugadaPosible> entry = null;
        Set<Map.Entry<Ficha, JugadaPosible>> entrySet = fichasValidas.entrySet();
        
        for (Map.Entry<Ficha, JugadaPosible> entry2 : entrySet) {
            entry = entry2;
            break;
        }
        return entry;
    }
    
    
    private void colocarFicha(JugadaPosible jugada, Ficha ficha, Jugador jugadorEnTurno) throws LogicException, InterruptedException {
        if (jugada == JugadaPosible.AMBAS) {
            tileHandler.colocarFicha(ficha, "izquierda");//falta arreglar que esto lo indique el jugador en si, no hardcodeado
        } else if (jugada != JugadaPosible.NINGUNA) {
            tileHandler.colocarFicha(ficha, jugada);
        }
        jugadorEnTurno.removerFicha(ficha);
        jugadorEnTurno.setFichaSeleccionada(null);
        display.enviarPartidaActualizada();
        if(jugadorEnTurno.equals(GameHandler.getJugador()))
            display.enviarJugadorActualizado();
    }

    private Map<Ficha, JugadaPosible> jugadasPosibles(Jugador jugadorEnTurno) throws InterruptedException, LogicException {
        Map<Ficha, JugadaPosible> fichasValidas
                = tileHandler.verificaFichasValidas(jugadorEnTurno.getFichas());
        return fichasValidas;
    }
    
    private void mecanicaOnline() throws InterruptedException, LogicException{
        while (true) {            
            turnHandler.setEnTurno(turnHandler.getJugadorEnTurno().equals(gameHandler.getJugador()));
            
            if (turnHandler.estaEnTurno()) {
                Map<Ficha, JugadaPosible> fichasValidas = jugadasPosibles(gameHandler.getJugador());
                if(fichasValidas.isEmpty()){
                    System.out.println("el jugador en turno no tiene fichas para poner");
                    turnHandler.cambiarTurno();
                }else{
                    crearJugada(fichasValidas);
                }
            }
        }
    }
    
    private void hacerJugadaOffline(Map<Ficha, JugadaPosible> fichasValidas) throws InterruptedException, LogicException{
        Map.Entry<Ficha, JugadaPosible> entry;
        if (turnHandler.estaEnTurno()) {
            entry = crearJugada(fichasValidas);
        } else {
            entry = seleccionarFichaAutomaticamente(fichasValidas);
        }
        JugadaPosible jugada = entry.getValue();
        Ficha fichaJugada = entry.getKey();

        if (jugada != null) {
            colocarFicha(jugada, fichaJugada, turnHandler.getJugadorEnTurno());
        } else {
            System.out.println("no puedes colocar esta ficha");
        }
    }
    
    private void mecanicaOffline() throws InterruptedException, LogicException{
        while (true) {
            Thread.sleep(3000);
            turnHandler.setEnTurno(turnHandler.getJugadorEnTurno().equals(gameHandler.getJugador()));
            
            Map<Ficha, JugadaPosible> fichasValidas = jugadasPosibles(turnHandler.getJugadorEnTurno());
            if (fichasValidas.isEmpty()) {
                System.out.println("el jugador en turno no tiene fichas para poner");
                System.out.println("le toca jalar una ficha");
                Ficha ficha = tileHandler.darFicha();
                System.out.println("ficha jalada : "+ficha);
                turnHandler.getJugadorEnTurno().agregarFicha(ficha);
                if(turnHandler.estaEnTurno())
                    display.enviarJugadorActualizado();
            } else {
                hacerJugadaOffline(fichasValidas);
            }
            display.enviarPartidaActualizada();
            turnHandler.cambiarTurno();
        }
    }
    
    private void inicializarManejadores(){
        tileHandler.setPozo(gameHandler.getPartida().getPozo());
        tileHandler.setTablero(gameHandler.getPartida().getTablero());
        turnHandler.setJugadores(gameHandler.getPartida().getJugadores());
    }
    
    private void iniciarJuego() throws LogicException{
        tileHandler.repartirFichas(gameHandler.getPartida().getJugadores(), gameHandler.getPartida().getFichasPorJugador());
        if (!turnHandler.designarPrimerTurno()) {
            buscarPrimeraMula(gameHandler.getPartida().getJugadores());
        } else {
            colocarPrimeraMula();
        }
        turnHandler.designarOtrosTurnos();
    }
    
    @Override
    public void ejecutarAccion() {
        try {
            inicializarManejadores();
            iniciarJuego();
            if (GameHandler.getPartida().getModo() == ModoPartida.VS_CPU) {
                display.enviarPartidaActualizada();
                display.enviarJugadorActualizado();
                //display.irPartida();
                Thread.sleep(5000);
                mecanicaOffline();
            } else {
                mecanicaOnline();
            }
        } catch (InterruptedException | LogicException ex) {
            Logger.getLogger(IniciarPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        
    }
    
}
