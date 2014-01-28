/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class Grid {

    public int width, height;
    private final LetterCell[][] cells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new LetterCell[width][height];
        this.init();
    }

    public GridCursor createGridCursor(){
        return new GridCursor(this);
    }

    public void clear() {
        for(LetterCell[] row : this.cells){
            for(LetterCell cell : row){
                cell.clear();
            }
        }
    }

    public LetterCell getCellAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        return this.cells[x][y];
    }

    public boolean addLetterTo(int x, int y, Letter letter) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given arguments are not within grid.");
        }
        if (!this.cells[x][y].hasLetter()) {
            this.cells[x][y].setLetter(letter);
            return true;
        }
        return false;
    }

    private boolean isWithinGrid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    private void init() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                this.cells[x][y] = new LetterCell(new Coordinate(x, y));
            }
        }
    }

//     public enum Size {
//
//        SMALL(8, 8), MEDIUM(10, 10), LARGE(12, 12);
//
//        public final int Width, Height;
//
//        private Size(int width, int height) {
//            this.Width = width;
//            this.Height = height;
//        }
//    }
}
