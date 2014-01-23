/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gamelogic;

import java.util.List;
import java.util.Random;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;

/**
 *
 * @author skaipio
 */
public class Game {
    private int score;
    private final Grid grid;
    private final GridCursor cursor;
    private final LetterPool letterPool = new LetterPool(new Random());
    
    public Game(){
        this.grid = new Grid(12, 12);
        this.cursor = new GridCursor(grid);
    }
    
    public void newGame(List<Letter> usingLetters) {
        this.score = 0;
        this.grid.clear();  
        this.letterPool.setLetters(usingLetters);
    }
    
    public GridCursor getGridCursor(){
        return this.cursor;
    }
    
    public int getGridWidth(){
        return this.grid.width;
    }
    public int getGridHeight(){
        return this.grid.height;
    }
    
    public Letter getLetterAt(int x, int y){
        return this.grid.getCellAt(x, y).getLetter();
    }
    
   
}
