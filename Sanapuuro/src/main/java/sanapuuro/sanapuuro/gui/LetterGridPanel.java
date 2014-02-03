/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class LetterGridPanel extends JPanel {
    private final int rows, columns;
    private final int cellSize = 42;
    private final GridCell[][] cells;

    public LetterGridPanel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new GridCell[rows][columns];
        this.setLayout(new GridLayout(rows, columns));
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(columns * cellSize, rows * cellSize+2));
        this.initCells();
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    private void initCells(){
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                GridCell cell = new GridCell(x, y);
                this.cells[y][x] = cell;
                this.add(cell);
            }
        }
    }

    public void addListenerToCells(MouseListener inputHandler) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                this.cells[y][x].addMouseListener(inputHandler);
            }
        }
    }
    
    public void setLetterToCell(String letter, int x, int y){
        this.cells[y][x].setLetter(letter);
    }
    
    public void removeLetterFromCell(int x, int y){
        this.cells[y][x].removeLetter();
    }
}
