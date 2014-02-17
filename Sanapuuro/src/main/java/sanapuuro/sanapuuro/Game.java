/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.util.Random;
import sanapuuro.sanapuuro.grid.Grid;

import sanapuuro.sanapuuro.filereaders.LetterReader;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 * The main game logic class that is used to start a new game and
 * retrieve the grid cursor and letter pool for input.
 * @author skaipio
 */
public class Game implements GameTimerListener{
    private final Grid grid;
    private GameTimer timer;
    private Letters letters;
    private Player player;
    private final int gridSize = 8;

    public Game() {
        this.grid = new Grid(gridSize, gridSize);
    }

    /**
     * Starts a new game,loads in valid letters and words and starts the countdown timer.
     * @param timer A timer that notifies the game when it has counted down to zero.
     */
    public void newGame(GameTimer timer) {
        this.grid.clear();
        this.timer = timer;
        this.timer.addListener(this);
        this.timer.startCountdownFrom(10);
        this.letters = new LetterReader(new Random());
        
        WordEvaluator wordEval = new WordEvaluator();
        
        this.player = new Player(grid, wordEval, letters);
    }

    public Player getPlayer(){
        return this.player;
    }
    
    public Grid getGrid(){
        return this.grid;
    }

    /**
     * Disabled player's controls.
     */
    @Override
    public void notifyTimeOut() {
        this.player.setControlsEnabled(false);
    }
}
