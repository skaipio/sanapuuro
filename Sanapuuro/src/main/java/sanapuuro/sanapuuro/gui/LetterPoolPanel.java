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
    public LetterPoolPanel(int letterPoolSize){
        this.setLayout(new GridLayout(1, letterPoolSize));
        this.letterCells = new LetterPoolCell[letterPoolSize];
        for(int i = 0; i < letterCells.length; i++){
            this.letterCells[i] = new LetterPoolCell();
            this.add(this.letterCells[i]);
        }
        //this.setSize(cellSize*letterPoolSize, cellSize);
        this.setPreferredSize(new Dimension(cellSize*letterPoolSize, cellSize));
        
    }

    @Override
    public void letterPoolChanged(Letter[] letters) {
        for(int i = 0; i < letterCells.length; i++){
            this.letterCells[i].setLetter(letters[i].toString());
        }
        this.repaint();
    }   
}
