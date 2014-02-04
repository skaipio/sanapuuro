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
public class LetterContainer {
    public final Letter letter;
    private boolean fromLetterPool;
    private boolean isSelected;
    private int letterPoolIndex;
    private int x, y;
    
    public LetterContainer(Letter letter, boolean fromLetterPool, int letterPoolIndex){
        this.letter = letter;
        this.fromLetterPool = fromLetterPool;
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
    
    public boolean isSelected(){
        return this.isSelected;
    }
    
    public boolean isFromLetterPool(){
        return this.fromLetterPool;
    }
    
    public void setToGridPermanently(){
        this.fromLetterPool = false;
    }
    
    public int letterPoolIndex(){
        return this.letterPoolIndex;
    }
}
