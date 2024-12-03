package entidades;

import java.util.List;

/**
 *
 * @author luisa M
 */
public class Jugador {
    private List<Ficha> fichas;
    private Cuenta cuenta;

    public Jugador(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Jugador() {
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void agregarFicha(Ficha ficha) {
        this.fichas.add(ficha);
    }

    public Ficha removerFicha(Ficha ficha) {
        Ficha fichaRemovida = null;
        for (Ficha f : fichas) {
            if(f.esLaMisma(ficha)){
                fichaRemovida = f;
                fichas.remove(f);
                break;
            }
        }
        return fichaRemovida;
    }

    public Ficha obtenerMayorMula() {
        Ficha mayorMula = null;
        int maxValor = -1;

        for (Ficha ficha : fichas) {
            if (ficha.esMula() && ficha.getIzquierda() > maxValor) {
                mayorMula = ficha;
                maxValor = ficha.getIzquierda();
            }
        }

        return mayorMula;
    }
    
    public String obtenerIdJugador(){
        return this.cuenta.getIdCadena();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("fichas=").append(fichas);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
