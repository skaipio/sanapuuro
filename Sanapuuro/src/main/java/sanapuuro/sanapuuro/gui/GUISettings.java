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
    private static Color colorSelectedCell;
    private static Color colorBackground3;
    private static Color colorBorder;
    private static Color colorButton1;
    private static Color colorButton2;
    private static Color colorLetterPoolButtonUsed;
    private static Color colorButtonHighlight2;
    private static Color colorSelectableCell;
    private static Color colorDefaultFont;

    public static void initGUISettings() {
        mediumFont = new Font(LogicalFont, Font.PLAIN, 20);
        colorBackground1 = new Color(Integer.parseInt("f9e0b0", hex));
        //colorSelectedCell = new Color(Integer.parseInt("f9ca6d", hex));
        colorSelectedCell = new Color(Integer.parseInt("e6a117", hex));
        colorBackground3 = new Color(Integer.parseInt("e6a117", hex));
        colorBorder = new Color(Integer.parseInt("8f753f", hex));
        colorButton1 = new Color(Integer.parseInt("6d4a03", hex));
        colorButton2 = new Color(Integer.parseInt("1e439a", hex));
        colorLetterPoolButtonUsed = new Color(Integer.parseInt("303e60", hex));
        colorButtonHighlight2 = new Color(Integer.parseInt("7295e6", hex));
        //colorSelectableCell = new Color(Integer.parseInt("aabce6", hex));
        colorSelectableCell = new Color(Integer.parseInt("8f753f", hex));
        colorDefaultFont = new Color(Integer.parseInt("000000", hex));
    }

    public static Font getMediumFont() {
        return mediumFont;
    }

    public static Color getColorBackground1() {
        return colorBackground1;
    }

    public static Color getColorSelectedCell() {
        return colorSelectedCell;
    }

    public static Color getColorBackground3() {
        return colorBackground3;
    }

    public static Color getColorBorder() {
        return colorBorder;
    }

    public static Color getColorButton1() {
        return colorButton1;
    }

    public static Color getColorButton2() {
        return colorButton2;
    }

    public static Color getColorLetterPoolButtonUsed() {
        return colorLetterPoolButtonUsed;
    }

    public static Color getColorButtonHighlight2() {
        return colorButtonHighlight2;
    }

    public static Color getColorSelectableCell() {
        return colorSelectableCell;
    }

    public static Color getColorDefaultFont() {
        return colorDefaultFont;
    }
}
