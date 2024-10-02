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
    
    private void setListener(){
        view.addListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setVisible(false);
            }
        });
    }
    
    public void showView(){
        this.model.setVisible(true);
    }
    
    public void destroyView(){
        this.view.dispose();
    }
}
