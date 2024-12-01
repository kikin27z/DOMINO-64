package entidadesDTO;

/**
 *
 * @author karim
 */
public class ReglasDTO {
    private int cantidadFichas;

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
}
