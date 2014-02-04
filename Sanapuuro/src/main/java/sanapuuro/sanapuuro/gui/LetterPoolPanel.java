/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class LetterPoolPanel extends JPanel {
    private final int cellSize = 36;
    private LetterPoolCell[] letterCells;
    private LetterPoolCell currentSelection;
    
    public LetterPoolPanel(){
        this.setLayout(null);     
    }
    
    public void init(int size){
        this.letterCells = new LetterPoolCell[size];
        //int poolXOffset = 42*12/2-size*cellSize/2;
        int poolXOffset = 0;
        for(int i = 0; i < letterCells.length; i++){
            LetterPoolCell cell = new LetterPoolCell(i);
            this.letterCells[i] = cell;
            this.add(cell);
            cell.setBounds(i*cellSize+poolXOffset, 0, cellSize, cellSize);
        }
        this.setPreferredSize(new Dimension(36*size,36));
        this.letterCells[0].select();
        this.currentSelection = this.letterCells[0];
        this.repaint();
    }
    
    public void addListenerToCells(MouseListener listener){
        for (LetterPoolCell letterCell : letterCells) {
            letterCell.addMouseListener(listener);
        }
    }
    
    public void setLetterToCell(String letter, int i){
        this.letterCells[i].setLetter(letter);
        this.letterCells[i].setInUse(false);
    }
    
    public void grayOutLetter(int i){
        this.letterCells[i].setInUse(true);
    }
    
    public void letterReturnedToPool(int i){
        this.letterCells[i].setInUse(false);
    }
    
    public void changeCurrentSelectionTo(int i){
        this.currentSelection.deselect();
        this.currentSelection = this.letterCells[i];
        this.currentSelection.select();
    }
}
