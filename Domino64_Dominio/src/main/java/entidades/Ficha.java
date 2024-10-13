package entidades;

/**
 * La clase Ficha representa una ficha de dominó con dos valores (izquierda y derecha)
 * y una orientación (horizontal o vertical). Proporciona métodos para girar la ficha,
 * verificar si es una "mula" (cuando ambos lados son iguales) y representar la ficha 
 * como una cadena de texto.
 * 
 * @author Paul Alejandro Vázquez Cervantes - 00000241400
 * @author Luisa Fernanda Morales Espinoza - 00000233450
 * @author José Karim Franco Valencia - 00000245138
 */
public class Ficha {
    private Orientacion orientacion; 
    private int izquierda;
    private int derecha;

    /**
     * Constructor de la clase Ficha. Inicializa una ficha con valores en sus 
     * lados izquierdo y derecho.
     * 
     * @param izquierda Valor del lado izquierdo de la ficha.
     * @param derecha Valor del lado derecho de la ficha.
     */
    public Ficha(int izquierda, int derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        if(esMula())
            this.orientacion = Orientacion.VERTICAL;
        this.orientacion = Orientacion.HORIZONTAL;
    }

    
    /**
     * Constructor de la clase Ficha. Inicializa una ficha con valores en sus 
     * lados izquierdo y derecho.
     * 
     * @param izquierda Valor del lado izquierdo de la ficha.
     * @param derecha Valor del lado derecho de la ficha.
     */
    public Ficha(int izquierda, int derecha, String imgUrl) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    /**
     * Obtiene el valor del lado izquierdo de la ficha.
     * 
     * @return Valor del lado izquierdo de la ficha.
     */
    public int getIzquierda() {
        return izquierda;
    }

    /**
     * Establece un nuevo valor para el lado izquierdo de la ficha.
     * 
     * @param izquierda Nuevo valor para el lado izquierdo.
     */
    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Obtiene el valor del lado derecho de la ficha.
     * 
     * @return Valor del lado derecho de la ficha.
     */
    public int getDerecha() {
        return derecha;
    }

    /**
     * Establece un nuevo valor para el lado derecho de la ficha.
     * 
     * @param derecha Nuevo valor para el lado derecho.
     */
    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    /**
     * Obtiene la orientación actual de la ficha (horizontal o vertical).
     * 
     * @return Orientación de la ficha.
     */
    public Orientacion getOrientacion() {
        return orientacion;
    }

    /**
     * Establece la orientación de la ficha (horizontal o vertical).
     * 
     * @param orientacion Nueva orientación para la ficha.
     */
    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    /**
     * Verifica si la ficha es una "mula", es decir, si el valor de ambos lados 
     * de la ficha es igual.
     * 
     * @return true si la ficha es una mula, false en caso contrario.
     */
    public boolean esMula() {
        return izquierda == derecha;
    }

    /**
     * Gira la ficha, intercambiando los valores del lado izquierdo y derecho.
     */
    public void girarFicha() {
        int aux = izquierda;
        izquierda = derecha;
        derecha = aux;
    }

    /**
     * Devuelve una representación en forma de cadena de la ficha, que incluye
     * los valores de los lados izquierdo y derecho, así como su orientación 
     * (horizontal o vertical).
     * 
     * @return Representación en cadena de la ficha.
     */
    @Override
    public String toString() {
        return  " " + izquierda + "-" + derecha + ((this.orientacion == Orientacion.HORIZONTAL) ? " horizontal" : " vertical");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.izquierda;
        hash = 67 * hash + this.derecha;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        if (this.izquierda != other.izquierda) {
            return false;
        }
        return this.derecha == other.derecha;
    }
    
    
}
