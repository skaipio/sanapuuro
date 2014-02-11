/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.grid;

import sanapuuro.sanapuuro.letters.Letter;

/**
 * Holds a letter and keeps track of whether it is from a letter pool or
 * if it has been permanently set to the grid.
 * @author skaipio
 */
public class LetterContainer {
    public final Letter letter;
    private boolean isPermanent = false;
    private int letterPoolIndex;
    private int x, y;
    
    public LetterContainer(Letter letter, boolean fromLetterPool, int letterPoolIndex){
        this.letter = letter;
        this.letterPoolIndex = letterPoolIndex;
    }
    
    public LetterContainer(Letter letter){
        this(letter,false,0);
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    void setX(int x){
        this.x = x;
    }
    
    void setY(int y){
        this.y = y;
    }
    
    /**
     * True if container is in grid permanently.
     * @return 
     */
    public boolean isPermanent(){
        return this.isPermanent;
    }
    
    /**
     * Sets the container to the grid permanently so that it can no longer be removed.
     */
    public void setToGridPermanently(){
        this.isPermanent = true;
    }
    
    /**
     * This is only valid if the letter has not been set permanently.
     * @return Letter's letter pool index.
     */
    public int letterPoolIndex(){
        return this.letterPoolIndex;
    }
}
