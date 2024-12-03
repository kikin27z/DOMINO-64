package eventos;

import eventoBase.Evento;

/**
 * Clase que define todos los eventos logicos.
 * Como su nombre lo indica, estos eventos son los que 
 * van a manejar los componentes encargados de la logica del juego.
 * Al igual que Evento, maneja tipos genericos; esto es para que 
 * los eventos logicos concretos puedan definir que entidad va 
 * a ser el contexto del evento. 
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 * @param <T> Tipo de entidad que va a tener como contexto este evento
 */
public abstract class EventoLogico implements Evento{
    protected int idPublicador;
    protected int idDestinatario;
    protected int idContexto;
    
    public EventoLogico(){
        this.idPublicador = 0;
        this.idContexto = 0;
    }
    /**
     * este metodo es abstracto pensando en la posibilidad
     * de que un evento puede incluir en su informacion
     * una lista de la entidad que esta manejando. 
     * Por ejemplo, un evento puede tener como informacion 
     * una lista de jugadores, o una lista de fichas,
     * y no un solo objeto de dicha entidad.
     * @param id
     */
//    public abstract void agregarInfo(T info);

    public void setIdPublicador(int id){
        this.idPublicador = id;
    }
    public void setIdDestinatario(int id){
        this.idDestinatario = id;
    }
    public void setIdContexto(int id){
        this.idContexto = id;
    }
    
    @Override
    public int getIdContexto(){
        return idContexto;
    }
    
    @Override
    public int getIdPublicador(){
        return idPublicador;
    }


    public EventoLogico(int idPublicador, int idDestinatario, int idContexto) {
        this.idPublicador = idPublicador;
        this.idDestinatario = idDestinatario;
        this.idContexto = idContexto;
    }

   @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventoLogico{");
        sb.append(", idPublicador=").append(idPublicador);
        sb.append(", idContexto=").append(idContexto);
        sb.append('}');
        return sb.toString();
    }
    
}
