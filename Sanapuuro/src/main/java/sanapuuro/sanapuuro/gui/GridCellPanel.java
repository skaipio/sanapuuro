/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author skaipio
 */
public class GridCellPanel extends JPanel{
    private String letter = "";
    private final int cellSize;
    private int letterSize = 24;
    private Color fontColor = Color.WHITE;
    public final int x, y;
    private boolean isHighlighted = false;
    private boolean isSelected = false;
    private boolean showAsSelectable = false;
    
    public GridCellPanel(int x, int y, int cellSize){
        this.cellSize = cellSize;
        this.x = x;
        this.y = y;
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setBackground(GUISettings.getColorButton1());
        this.setPreferredSize(new Dimension(cellSize, cellSize));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(fontColor);
        g.setFont(GUISettings.getMediumFont());
        g.drawString(letter, this.getWidth()/2-8, this.getHeight()/2+10);
    }
    
    public boolean isSelected(){
        return this.isSelected;
    }
    
    public void setLetter(String letter){
        this.letter = letter;
        this.repaint();
    }
    
    public void removeLetter(){
        this.letter = "";
        this.repaint();
    }
    
    public void hoverOn(){
        this.setBackground(GUISettings.getColorSelectedCell());
    }
    
    public void hoverOff(){
        if (this.showAsSelectable){
            this.setBackground(GUISettings.getColorSelectableCell());
        }
        else if (isSelected){
            this.setBackground(GUISettings.getColorBackground3());
        }
        else if (isHighlighted){
            this.setBackground(GUISettings.getColorBorder());
        }else{
            this.setBackground(GUISettings.getColorButton1());
        }
    }
    
    public void highlight(){
        this.isHighlighted = true;
        this.setBackground(GUISettings.getColorBorder());
    }
    
    public void removeHighlight(){
        this.isHighlighted = false;
        this.setBackground(GUISettings.getColorButton1());
    }
    
    public void select(){
        this.isSelected = true;
        this.setBackground(GUISettings.getColorSelectedCell());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.repaint();
    }
    
    public void deselect(){
        this.isSelected = false;
        this.setBackground(GUISettings.getColorButton1());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.repaint();
    }
    
    public void showAsSelectable(boolean show){
        this.showAsSelectable = show;
        if (show){
            this.setBackground(GUISettings.getColorSelectableCell());
        }else if (this.isSelected){
            this.setBackground(GUISettings.getColorSelectedCell());
        }else{
            this.setBackground(GUISettings.getColorButton1());
        }
        this.repaint();
    }
}
