package manejadorTurnos;

import entidades.Ficha;
import entidadesDTO.CuentaDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.ReglasDTO;
import java.util.ArrayList;
import java.util.List;
import manejadorPozo.ManejadorPozo;

/**
 *
 * @author karim
 */
public class Principal {

    public static void main(String[] args) {
//        ControlTurnos manejador = new ControlTurnos();
//        manejador.iniciaConexion();
        simulacionAsignarTurnos();

//        manejador.imprimirJuego();
//    
//    
//    ReglasDTO reglasDTO = new ReglasDTO(5);
//    reglasDTO.setCuentas(cuentas);
//    
//    mTurnos.determinarOrden(reglasDTO);
    }

    private static void simulacionAsignarTurnos() {
        ManejadorTurnos mTurnos = new ManejadorTurnos();
        ManejadorPozo mPozo = new ManejadorPozo();
        List<CuentaDTO> cuentas = new ArrayList<>();
        CuentaDTO cuenta1 = new CuentaDTO();
        cuenta1.setIdCadena("Karim");
        CuentaDTO cuenta2 = new CuentaDTO();
        cuenta2.setIdCadena("Luffy");
        CuentaDTO cuenta3 = new CuentaDTO();
        cuenta3.setIdCadena("Paul");
        CuentaDTO cuenta4 = new CuentaDTO();
        cuenta4.setIdCadena("Luisa");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);

        ReglasDTO reglas = new ReglasDTO();
        reglas.setCantidadFichas(2);
        reglas.setCuentas(cuentas);

        MazosDTO mazos = mPozo.repartirFichas(reglas);
        mTurnos.determinarOrden(mazos);
        simularRotarTurno(mTurnos);
    }

    private static void simularRotarTurno(ManejadorTurnos mTurnos) {
        CuentaDTO cuenta3 = new CuentaDTO();
        cuenta3.setIdCadena("Paul");
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();

        System.out.println("\nSe quita cuenta\n");
        mTurnos.quitarJugador(cuenta3);
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
        mTurnos.rotarSiguienteTurno();
    }
}
