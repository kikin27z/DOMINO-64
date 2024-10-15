package entidadesDTO;

/**
 *
 * @author luisa M
 */
public class TableroDTO {
    private FichaDTO extremoIzq;
    private FichaDTO fichaInicial;
    private FichaDTO extremoDer;

    public TableroDTO() {
    }

    public FichaDTO getExtremoIzq() {
        return extremoIzq;
    }

    public void setExtremoIzq(FichaDTO extremoIzq) {
        if(tableroVacio()){
            this.fichaInicial = extremoIzq;
        }else{
            this.extremoIzq = extremoIzq;
        }
    }

    public FichaDTO getExtremoDer() {
        return extremoDer;
    }

    public void setExtremoDer(FichaDTO extremoDer) {
        if(tableroVacio()){
            this.fichaInicial = extremoDer;
        }else{
            this.extremoDer = extremoDer;
        }
    }

    public FichaDTO getFichaInicial() {
        return fichaInicial;
    }

    public void setFichaInicial(FichaDTO fichaInicial) {
        this.fichaInicial = fichaInicial;
    }
    
    
    
    
    public boolean tableroVacio(){
        return fichaInicial == null;
    }
    
}
