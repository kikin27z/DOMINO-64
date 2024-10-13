package acciones;

import com.mycompany.starter.Display;
/**
 *
 * @author luisa M
 */
public class CambiarPantalla extends AccionBase{
    private int destino;
    public static final int IR_INICIO = 0;
    public static final int IR_LOBBY = 1;
    public static final int IR_PARTIDA = 2;
    
    public CambiarPantalla(Display displayNuevo, int destino){
        if(display==null)
            display = displayNuevo;
        this.destino = destino;
    }
    
    public int getDestino(){
        return this.destino;
    }
    
    @Override
    public void ejecutarAccion() {
        switch (destino) {
            case IR_INICIO -> {
                display.irInicio();
            }
            case IR_LOBBY -> {
                display.irLobby();
            }
            case IR_PARTIDA ->{
                display.irPartida();
                System.out.println("se fue a la pantalla partida");
            }
        }
    }

    @Override
    public String toString() {
        return "CambiarPantalla{" + "destino=" + destino + '}';
    }
    
    
}
