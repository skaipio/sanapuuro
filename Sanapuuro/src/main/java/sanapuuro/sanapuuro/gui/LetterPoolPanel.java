/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPoolListener;

/**
 *
 * @author skaipio
 */
public class LetterPoolPanel extends JPanel implements LetterPoolListener{
    private final int cellSize = 36;
    private final LetterPoolCell[] letterCells;
    private int currentSelection = 0;
    public LetterPoolPanel(int letterPoolSize){
        this.setLayout(null);
        this.letterCells = new LetterPoolCell[letterPoolSize];
        int poolXOffset = 42*12/2-letterPoolSize*cellSize/2;
        for(int i = 0; i < letterCells.length; i++){
            LetterPoolCell cell = new LetterPoolCell();
            this.letterCells[i] = cell;
            this.add(cell);
            cell.setBounds(i*cellSize+poolXOffset, 0, cellSize, cellSize);
        }
        this.letterCells[0].select();
        
        this.setPreferredSize(new Dimension((cellSize-16)*letterPoolSize, cellSize));   
        //this.setSize(new Dimension((cellSize-26)*letterPoolSize, cellSize));
    }

    @Override
    public void letterPoolChanged(Letter[] letters) {
        for(int i = 0; i < letterCells.length; i++){
            this.letterCells[i].setLetter(letters[i].toString());
        }
        this.repaint();
    }   

    @Override
    public void letterPoolSelectionChanged(int i) {
        this.letterCells[this.currentSelection].deselect();
        this.currentSelection = i;
        this.letterCells[this.currentSelection].select();
    }
}
