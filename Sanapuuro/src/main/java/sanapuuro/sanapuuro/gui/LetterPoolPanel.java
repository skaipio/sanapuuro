/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterPoolListener;

/**
 *
 * @author skaipio
 */
public class LetterPoolPanel extends JPanel implements LetterPoolListener{
    private final int cellSize = 36;
    private final LetterPoolCell[] letterCells;
    private LetterPoolCell currentSelection;
    
    public LetterPoolPanel(LetterPool letterPool){
        this.setLayout(null);
        this.letterCells = new LetterPoolCell[letterPool.poolSize];
        int poolXOffset = 42*12/2-letterPool.poolSize*cellSize/2;
        LetterPoolInputHandler inputHandler = new LetterPoolInputHandler(letterPool);
        
        for(int i = 0; i < letterCells.length; i++){
            LetterPoolCell cell = new LetterPoolCell(i);
            cell.addMouseListener(inputHandler);
            this.letterCells[i] = cell;
            this.add(cell);
            cell.setBounds(i*cellSize+poolXOffset, 0, cellSize, cellSize);
        }
        this.letterCells[0].select();
        this.currentSelection = this.letterCells[0];
        
        this.setPreferredSize(new Dimension((cellSize-16)*letterPool.poolSize, cellSize));   
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
        this.currentSelection.deselect();
        this.currentSelection = this.letterCells[i];
        this.currentSelection.select();
    }
}
