package main;

import entidades.Ficha;
import entidadesDTO.CuentaDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.ReglasDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import manejadorPozo.ControlPozo;
import manejadorPozo.IControlPozo;

/**
 *
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class MainPozo {

    public static void main(String[] args) {
        IControlPozo pozo = new ControlPozo();
        pozo.iniciaConexion();
//        ManejadorPozo mp = new ManejadorPozo();
//
//        List<CuentaDTO> cuentas = new ArrayList<>();
//        CuentaDTO cuenta1 = new CuentaDTO();
//        cuenta1.setIdCadena("Karim");
//        CuentaDTO cuenta2 = new CuentaDTO();
//        cuenta2.setIdCadena("Luffy");
//        CuentaDTO cuenta3 = new CuentaDTO();
//        cuenta3.setIdCadena("Paul");
//        CuentaDTO cuenta4 = new CuentaDTO();
//        cuenta4.setIdCadena("Luisa");
//
//        cuentas.add(cuenta1);
//        cuentas.add(cuenta2);
//        cuentas.add(cuenta3);
//        cuentas.add(cuenta4);
//
//        ReglasDTO reglasDTO = new ReglasDTO(1);
//        reglasDTO.setCuentas(cuentas);
//        MazosDTO mazos = mp.repartirFichas(reglasDTO);
//        System.out.println(mazos);
//        mp.imprimirFichasRestantes();

    }
}
