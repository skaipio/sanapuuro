/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import sanapuuro.sanapuuro.grid.LetterContainer;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author skaipio
 */
public class LetterPool{
    public final int poolSize = 8;
    private final Letters letters;
    private final LetterContainer[] pool = new LetterContainer[poolSize];
    private final Set<Integer> usedLetterIndices = new HashSet(poolSize);
    private int currentSelection = 0;
            
    public LetterPool(Letters letters){
        this.letters = letters;
        for(int i = 0; i < poolSize; i++){
            this.pool[i] = new LetterContainer(letters.getRandomLetter(), true, i);
        }
    }
    
    public LetterContainer[] getLetters(){
        return this.pool.clone();
    }
    
    public int getCurrentSelectedIndex(){
        return this.currentSelection;
    }
    
    public LetterContainer getCurrentSelection(){
        return this.pool[this.currentSelection];
    }
    
    public void setCurrentSelection(int i){
        if (i < 0) throw new IllegalArgumentException("Given index is negative, when it should be positive");
        if (i >= poolSize) throw new IllegalArgumentException("Given index is greater than pool size");
        this.currentSelection = i;
    }
    
    public LetterContainer useLetter(){
        if (this.usedLetterIndices.contains(this.currentSelection)) return null;
        this.usedLetterIndices.add(this.currentSelection);
        return this.pool[this.currentSelection];
    }
    
    public void clearLetterPicks(){
        this.usedLetterIndices.clear();
    }
    
    public void removePickedLetters(){
        for(int i : this.usedLetterIndices){
            this.pool[i] = new LetterContainer(letters.getRandomLetter(), true, i);
        }
        this.usedLetterIndices.clear();
    }
    
    public void unpickLetterAtIndex(int i){
        this.usedLetterIndices.remove(i);
    }
}
