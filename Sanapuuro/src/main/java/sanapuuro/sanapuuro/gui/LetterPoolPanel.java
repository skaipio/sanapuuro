/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class LetterPoolPanel extends JPanel {
    private LetterPoolCellButton[] letterCells;
    private LetterPoolCellButton currentHoverOn;
    
    public LetterPoolPanel(){
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
        this.setBackground(GUISettings.getColorBackground1());
    }
    
    public void init(int size){
        this.letterCells = new LetterPoolCellButton[size];
        for(int i = 0; i < letterCells.length; i++){
            LetterPoolCellButton cell = new LetterPoolCellButton(i);
            this.letterCells[i] = cell;
            this.add(cell);           
        }
        this.currentHoverOn = this.letterCells[0];
        this.setHoverTo(0);
        this.repaint();
    }
    
    public void addListenerToCells(MouseListener listener){
        for (LetterPoolCellButton letterCell : letterCells) {
            letterCell.addMouseListener(listener);
        }
    }   
    
    public void setLetterToCell(String letter, int i){
        this.letterCells[i].setLetter(letter);
        this.letterCells[i].setInUse(false);
    }
    
    public void setContainerAsUsed(int i, boolean enabled){
        this.letterCells[i].setInUse(enabled);
    }
    
    public void setHoverTo(int i){
        System.out.println("hover at: " + i);
        this.currentHoverOn.hover(false);
        this.currentHoverOn = this.letterCells[i];
        this.currentHoverOn.hover(true);
    }
}
