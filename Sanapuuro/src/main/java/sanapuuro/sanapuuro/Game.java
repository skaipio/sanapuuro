/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro;

import sanapuuro.sanapuuro.grid.GridCursorListener;
import java.util.ArrayList;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.grid.Grid;
import java.util.List;
import java.util.Random;
import sanapuuro.sanapuuro.grid.LetterCell;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterReader;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 *
 * @author skaipio
 */
public class Game implements GridCursorListener{
    private int score;
    private final Grid grid;
    private final GridCursor cursor;
    private LetterPool letterPool;
    private WordEvaluator wordEval;
    
    public Game(){
        this.grid = new Grid(12, 12);
        this.cursor = this.grid.createGridCursor();
    }
    
    public void newGame() {
        this.score = 0;
        this.grid.clear();  
        this.letterPool = new LetterReader(new Random());
        this.wordEval = new WordEvaluator();
        
        List<LetterContainer> validHorizontalWord = new ArrayList<>();
        LetterCell cell = new LetterCell(0, 0);
        cell.setLetter(new Letter('c', 1, 1));
        validHorizontalWord.add(cell);
        cell = new LetterCell(1, 0);
        cell.setLetter(new Letter('a', 2, 1));
        validHorizontalWord.add(cell);
        cell = new LetterCell(2, 0);
        cell.setLetter(new Letter('k', 3, 1));
        validHorizontalWord.add(cell);
        cell = new LetterCell(3, 0);
        cell.setLetter(new Letter('e', 4, 1));
        validHorizontalWord.add(cell);
        
        wordEval.isValidWord(validHorizontalWord);
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

    @Override
    public void lettersSubmitted(List<LetterContainer> letters) {
        if (this.wordEval.isValidWord(letters)){
            this.score+=this.wordEval.evaluteLetters(letters);
        }
    }
}
