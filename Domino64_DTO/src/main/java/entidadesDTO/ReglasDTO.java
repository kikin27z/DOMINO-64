package entidadesDTO;

import java.util.List;

/**
 *
 
@author Luisa Fernanda Morales Espinoza - 00000233450
@author José Karim Franco Valencia - 00000245138*/
public class ReglasDTO {
    private int cantidadFichas;
    private List<CuentaDTO> cuentas;

    public ReglasDTO(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }


    public ReglasDTO() {
    }

    public int getCantidadFichas() {
        return cantidadFichas;
    }

    public void setCantidadFichas(int cantidadFichas) {
        this.cantidadFichas = cantidadFichas;
    }

    public int getCantidadJugadores() {
        return cuentas.size();
    }

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

}