package manejadorPozo;

import entidades.Ficha;
import entidadesDTO.CuentaDTO;
import entidadesDTO.MazosDTO;
import entidadesDTO.ReglasDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class PrincipalPozo {

    public static void main(String[] args) {
//        ControlPozo manejador = new ControlPozo();
//        manejador.iniciaConexion();
        ManejadorPozo mp = new ManejadorPozo();

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

        ReglasDTO reglasDTO = new ReglasDTO(1);
        reglasDTO.setCuentas(cuentas);
        MazosDTO mazos = mp.repartirFichas(reglasDTO);
        System.out.println(mazos);
        mp.imprimirFichasRestantes();

    }
}
