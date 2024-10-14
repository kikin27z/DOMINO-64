package entidadesDTO;

/**
 *
 * @author luisa M
 */
public class TableroDTO {
    private FichaDTO extremoIzq;
    private FichaDTO extremoDer;

    public TableroDTO() {
    }

    public FichaDTO getExtremoIzq() {
        return extremoIzq;
    }

    public void setExtremoIzq(FichaDTO extremoIzq) {
        if(tableroVacio()){
            this.extremoIzq = extremoIzq;
            this.extremoDer = extremoIzq;
        }else{
            this.extremoIzq = extremoIzq;
        }
    }

    public FichaDTO getExtremoDer() {
        return extremoDer;
    }

    public void setExtremoDer(FichaDTO extremoDer) {
        if(tableroVacio()){
            this.extremoIzq = extremoDer;
            this.extremoDer = extremoDer;
        }else{
            this.extremoDer = extremoDer;
        }
    }
    
    
    
    public boolean tableroVacio(){
        return extremoIzq == null && extremoDer == null;
    }
    
}
