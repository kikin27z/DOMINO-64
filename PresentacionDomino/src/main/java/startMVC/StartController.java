/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luisa
 */
public class StartController {
    StartView view;
    StartModel model;
    
    public StartController(StartView view, StartModel model){
        this.view = view;
        this.model = model;
        setListener();
    }
    
    /**
     * agrega un actionListener a la vista,
     * donde el actionPerformed ejecuta el metodo changeView
     * en el modelo. 
     * Le inidica al modelo que debe cerrar la vista existente
     * para cambiar a otra vista
     */
    private void setListener(){
        view.addListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeView(true);
            }
        });
    }
    
    /**
     * Construye una vista nueva.
     * Este metodo se debe ejecutar una vez que se cerro 
     * esta vista anteriormente.
     */
    public void showView(){
        this.view = new StartView(model);
    }
    
}
