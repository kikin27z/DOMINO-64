/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventos;

import categorias.CategoriaEvento;
import com.luisa.entidades.Cuenta;

/**
 *
 * @author luisa M
 * @param <T>
 */
public abstract class Event<T> {
    private CategoriaEvento categoria;
    private Cuenta publicador;
    private Enum<?> tipo;

    public Event(Cuenta publicador, Enum<?> tipo){
        this.publicador = publicador;
        this.tipo = tipo;
    }
    
    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEvento categoria) {
        this.categoria = categoria;
    }

    public void setPublicador(Cuenta publicador) {
        this.publicador = publicador;
    }

    public Enum<?> getTipo() {
        return tipo;
    }

    public void setTipo(Enum<?> tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public Cuenta getPublicador() {
        return publicador;
    }

    public abstract void agregarInfo(T info);

    public abstract T getInfo();
}
