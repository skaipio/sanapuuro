/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author skaipio
 */
public class GridCellPanel extends JLabel{
    private String letter = "";
    private final int cellSize;
    public final int x, y;
    private boolean isHighlighted = false;
    private boolean isSelected = false;
    private boolean showAsSelectable = false;
    private boolean cursorEnabled = false;
    
    public GridCellPanel(int x, int y, int cellSize){
        this.cellSize = cellSize;
        this.x = x;
        this.y = y;
        this.setOpaque(true);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setBackground(GUISettings.getColorButton1());
        this.setPreferredSize(new Dimension(cellSize, cellSize));
        this.setFont(GUISettings.getMediumFont());
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public boolean isSelected(){
        return this.isSelected;
    }
    
    public boolean isSelectable(){
        return this.showAsSelectable;
    }
    
    public void setLetter(String letter){
        this.setText(letter);
    }
    
    public void removeLetter(){
        this.setText("");
    }
    
    public void hoverOn(){
        this.setBackground(GUISettings.getGridButtonHighlight());
    }
    
    public void hoverOff(){
        if (this.showAsSelectable){
            this.setBackground(GUISettings.getColorSelectableCell());
        }
        else if (isSelected){
            this.setBackground(GUISettings.getColorSelectedCell());
        }
        else if (isHighlighted){
            this.setBackground(GUISettings.getColorBorder());
        }else if(cursorEnabled){
            this.setBackground(GUISettings.getColorButton2());
        }else{
            this.setBackground(GUISettings.getColorButton1());
        }
    }
    
    public void enableCursor(boolean enabled){
        this.cursorEnabled = enabled;
        if (enabled){
            this.setBackground(GUISettings.getColorButton2());
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
