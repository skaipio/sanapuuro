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
public abstract class LetterContainer {
    public final int x, y;
    protected boolean used;
    protected Letter letter;
    
    public LetterContainer(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public boolean hasBeenUsed(){
        return used;
    }
    
    public Letter getLetter(){
        return this.letter;
    }
    
    public boolean hasLetter(){
        return this.letter != null;
    }
    
//    public Powerup getPowerup(){
//        
//    }
}
