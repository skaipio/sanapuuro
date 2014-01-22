/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gamelogic;

import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class Grid {
    public final int width, height;
    private final Letter[][] letters;
    
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.letters = new Letter[width][height];
    }
    
    public Letter getLetterAt(int x, int y){
        if (!isWithinGrid(x,y)){
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        return this.letters[x][y];
    }
    
    public boolean addLetterTo(int x, int y, Letter letter){
        if (!isWithinGrid(x,y)){
            throw new IllegalArgumentException("Given arguments are not within grid.");
        }
        if (this.letters[x][y] == null){
            this.letters[x][y] = letter;
            return true;
        }
        return false;
    }
    
    private boolean isWithinGrid(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
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
