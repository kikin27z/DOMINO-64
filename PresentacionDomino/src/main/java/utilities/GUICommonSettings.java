/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

/**
 *
 * @author luisa    
 */
public class GUICommonSettings {
    private final Dimension DIMENSION = new Dimension(600, 500);
    private Color BACKGROUND_COLOR = new Color(24,111,101);//hex 186F65
    private final Color LABEL_TEXT_COLOR = new Color(204,204,255);
    private final Color EXIT_BTN_BACKGROUND_COLOR = new Color(204,204,255);
    private final Color BUTTON_BACKGROUND_COLOR = new Color(178, 83, 62);//hex B2533E
    private final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private final String LABEL_TEXT = "Escoge una opcion";
    private final String NEW_GAME_BTN_TEXT = "Nueva partida";
    private final String JOIN_GAME_BTN_TEXT = "Unirme a partida";
    private final String EXIT_BTN_TEXT = "Salir";
    private final Font LABEL_FONT = new Font("Franklin Gothic Heavy", Font.BOLD, 60);
    private final Font BUTTON_TEXT_FONT = new Font("Franklin Gothic Heavy", Font.PLAIN, 16);
    private final Rectangle NEW_GAME_BTN_BOUNDS = new Rectangle(100, 300, 170, 60);
    private final Rectangle JOIN_GAME_BTN_BOUNDS = new Rectangle(200, 300, 170, 60);
    private final Rectangle EXIT_BTN_BOUNDS = new Rectangle(50, 20, 170, 60);
    private final Rectangle LABEL_BOUNDS = new Rectangle(130, 100, 550, 100);

    
    public GUICommonSettings(){
        
    }
    
    public Dimension getDIMENSION() {
        return DIMENSION;
    }

    public Color getBACKGROUND_COLOR() {
        return BACKGROUND_COLOR;
    }

    public Color getLABEL_TEXT_COLOR() {
        return LABEL_TEXT_COLOR;
    }

    public Color getBUTTON_BACKGROUND_COLOR() {
        return BUTTON_BACKGROUND_COLOR;
    }

    public Color getEXIT_BTN_BACKGROUND_COLOR() {
        return EXIT_BTN_BACKGROUND_COLOR;
    }

    public Color getBUTTON_TEXT_COLOR() {
        return BUTTON_TEXT_COLOR;
    }

    public String getLABEL_TEXT() {
        return LABEL_TEXT;
    }

    public String getNEW_GAME_BTN_TEXT() {
        return NEW_GAME_BTN_TEXT;
    }

    public String getJOIN_GAME_BTN_TEXT() {
        return JOIN_GAME_BTN_TEXT;
    }

    public String getEXIT_BTN_TEXT() {
        return EXIT_BTN_TEXT;
    }

    public Font getLABEL_FONT() {
        return LABEL_FONT;
    }

    public Font getBUTTON_TEXT_FONT() {
        return BUTTON_TEXT_FONT;
    }

    public Rectangle getNEW_GAME_BTN_BOUNDS() {
        return NEW_GAME_BTN_BOUNDS;
    }

    public Rectangle getJOIN_GAME_BTN_BOUNDS() {
        return JOIN_GAME_BTN_BOUNDS;
    }

    public Rectangle getEXIT_BTN_BOUNDS() {
        return EXIT_BTN_BOUNDS;
    }

    public Rectangle getLABEL_BOUNDS() {
        return LABEL_BOUNDS;
    }
}
