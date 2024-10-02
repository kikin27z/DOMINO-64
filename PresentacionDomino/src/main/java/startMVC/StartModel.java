/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startMVC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import utilities.Mediator;
import utilities.Observable;

/**
 *
 * @author luisa
 */
public class StartModel extends Observable{
    private final int WIDTH=600;//px
    private final int HEIGHT=500;//px
    private final Dimension DIMENSION= new Dimension(WIDTH, HEIGHT);
    private final Color BACKGROUND_COLOR = new Color(24,111,101);//hex 186F65
    private final Color LABEL_TEXT_COLOR = Color.WHITE;
    private final Color BUTTON_BACKGROUND_COLOR = new Color(178,83,62);//hex B2533E
    private final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private final String LABEL_TEXT = "Domino64";
    private final String BUTTON_TEXT = "Iniciar Juego";
    private final Font LABEL_FONT = new Font("Franklin Gothic Heavy", Font.BOLD, 60);
    private final Font BUTTON_TEXT_FONT = new Font("Franklin Gothic Heavy", Font.PLAIN, 16);
    private final Rectangle BUTTON_BOUNDS = new Rectangle(200, 300, 170, 60);
    private final Rectangle LABEL_BOUNDS = new Rectangle(130, 100, 550, 100);
    private boolean visible;
    private Mediator mediator;
    
    public void setVisible(boolean flag){
        visible = flag;
        this.notifyObservers(visible);
    }
    
    public Dimension getDimension(){
        return DIMENSION;
    }
    
    public Color getBackgroundColor(){
        return BACKGROUND_COLOR;
    }
    
    public Color getLabelTextColor(){
        return LABEL_TEXT_COLOR;
    }
    
    public Color getButtonTextColor(){
        return BUTTON_TEXT_COLOR;
    }
    
    public Color getButtonBackgroundColor(){
        return BUTTON_BACKGROUND_COLOR;
    }
    
    public String getLabelText(){
        return LABEL_TEXT;  
    }
    
    public String getbuttonText(){
        return BUTTON_TEXT;
    }
    
    public Font getLabelFont(){
        return LABEL_FONT;
    }
    
    public Font getButtonTextFont(){
        return BUTTON_TEXT_FONT;
    }
    
    public Rectangle getButtonBounds(){
        return BUTTON_BOUNDS;
    }
    public Rectangle getLabelBounds(){
        return LABEL_BOUNDS;
    }
}
