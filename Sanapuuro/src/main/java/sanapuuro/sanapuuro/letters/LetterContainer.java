/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

/**
 *
 * @author skaipio
 */
public class LetterContainer {
    public final Letter letter;
    private boolean fromLetterPool;
    private int letterPoolIndex;
    private int x, y;
    
    public LetterContainer(Letter letter, boolean fromLetterPool, int letterPoolIndex){
        this.letter = letter;
        this.fromLetterPool = fromLetterPool;
        this.letterPoolIndex = letterPoolIndex;
    }
    
    public LetterContainer(Letter letter, int x, int y){
        this(letter,false,0);
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public boolean isFromLetterPool(){
        return this.fromLetterPool;
    }
    
    public int letterPoolIndex(){
        return this.letterPoolIndex;
    }
}
