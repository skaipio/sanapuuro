/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class CellGrid extends JPanel {

    private final int rows, columns;
    private final int cellSize = 42;

    public CellGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.setLayout(new GridLayout(rows, columns));
        this.setBounds(0, 0, columns * cellSize, rows * cellSize);
        this.setBackground(Color.BLACK);
        this.initCells();
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void initCells() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                this.add(new Cell(x, y));
            }
        }
    }
}