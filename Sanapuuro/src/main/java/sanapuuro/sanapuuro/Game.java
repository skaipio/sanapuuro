/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.util.Random;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterReader;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 * The main game logic class that is used to start a new game and
 * retrieve the grid cursor and letter pool for input.
 * @author skaipio
 */
public class Game {
    private final Grid grid;
    private Letters letters;
    private Player player;

    public Game() {
        this.grid = new Grid(12, 12);
    }

    /**
     * Starts a new game and loads in valid letters and words.
     */
    public void newGame() {
        this.grid.clear();
        this.letters = new LetterReader(new Random());
        
        LetterPool pool = new LetterPool(letters);
        GridCursor cursor = new GridCursor(this.grid, pool);
        WordEvaluator wordEval = new WordEvaluator();
        
        this.player = new Player(cursor, wordEval);
    }

    public Player getPlayer(){
        return this.player;
    }

    public int getGridWidth() {
        return this.grid.width;
    }

    public int getGridHeight() {
        return this.grid.height;
    }
}
