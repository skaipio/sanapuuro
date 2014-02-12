/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
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
        this.letterCells = new LetterPoolCellButton[8];
        for(int i = 0; i < letterCells.length; i++){
            LetterPoolCellButton cell = new LetterPoolCellButton(i);
            this.letterCells[i] = cell;
            this.add(cell);           
        }
    }
    
    public void init(int size){
//        int width = this.letterCells[0].getPreferredSize().width*8;
//        int height = this.letterCells[0].getPreferredSize().height;
//        this.setPreferredSize(new Dimension(width,height));
        this.currentHoverOn = this.letterCells[0];
        this.setHoverTo(0);
        this.repaint();
    }
    
    public void addListenerToCells(ActionListener listener){
        for (LetterPoolCellButton letterCell : letterCells) {
            letterCell.addActionListener(listener);
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
