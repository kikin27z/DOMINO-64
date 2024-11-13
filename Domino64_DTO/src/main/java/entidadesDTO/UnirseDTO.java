package entidadesDTO;

/**
 *
 * @author karim
 */
public class UnirseDTO {
    private String codigoPartida;
    private int idJugador;

    public UnirseDTO(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    
    
}
