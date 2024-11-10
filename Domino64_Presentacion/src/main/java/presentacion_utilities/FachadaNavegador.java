/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion_utilities;

import entidadesDTO.JugadorDTO;
import entidadesDTO.PartidaDTO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class FachadaNavegador {
    private final Navegacion navegador;
    private static FachadaNavegador fachada;
    private boolean appIniciada;
    private static final int ESCOGER_MODO =3;
    private static final int LOBBY =2;
    private static final int PARTIDA =1;
    private static final int INICIO =0;
    
    
    private FachadaNavegador(){
        navegador = Navegacion.getInstance();
    }
    
    public static synchronized FachadaNavegador getInstance(){
        if(fachada==null){
            fachada = new FachadaNavegador();
        }
        return fachada;
    }
    
    public void iniciarApp(){
        if(!appIniciada){
            navegador.iniciarApp();
        }
    }
    
    
    
    public void cambiarPantalla(int destino){
        switch(destino){
            case INICIO -> {
                navegador.cambiarInicio();
            }
            case ESCOGER_MODO -> {
            }
            case LOBBY -> {
                navegador.cambiarLobby();
            }
            case PARTIDA -> {
                navegador.cambiarPartida();
            }
        }
    }
}
