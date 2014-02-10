/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author skaipio
 */
public class GUISettings {
    private static int hex = 16;
    private static final String LogicalFont = "SansSerif";
    private static Font mediumFont;
    private static Color colorBackground1;
    private static Color colorBackground2;
    private static Color colorBackground3;
    private static Color colorBorder;
    private static Color colorButton1;
    private static Color colorDefaultFont;
    
    public static void initGUISettings(){
        mediumFont = new Font(LogicalFont, Font.PLAIN, 20);
        colorBackground1 = new Color(Integer.parseInt("f9e0b0", hex));
        colorBackground2 = new Color(Integer.parseInt("f9ca6d", hex));
        colorBackground3 = new Color(Integer.parseInt("e6a117", hex));
        colorBorder = new Color(Integer.parseInt("8f753f", hex));
        colorButton1 = new Color(Integer.parseInt("6d4a03", hex));
        colorDefaultFont = new Color(Integer.parseInt("000000", hex));
    }
    
    public static Font getMediumFont(){
        return mediumFont;
    }
    
    public static Color getColorBackground1(){
        return colorBackground1;
    }
    
    public static Color getColorBackground2(){
        return colorBackground2;
    }
    
    public static Color getColorBackground3(){
        return colorBackground3;
    }
    
    public static Color getColorBorder(){
        return colorBorder;
    }
    
    public static Color getColorButton1(){
        return colorButton1;
    }
    
    public static Color getColorDefaultFont(){
        return colorDefaultFont;
    }
}
