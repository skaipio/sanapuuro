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
 * Letter pool where all the letters a player can place to a letter grid are kept.
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
    
    public boolean isIndexUsed(int i){
        return this.usedLetterIndices.contains(i);
    }
    
    /**
     * @return Currently selected LetterContainer.
     */
    public LetterContainer getCurrentSelection(){
        return this.pool[this.currentSelection];
    }
    
    /**
     * Sets the index to point at a LetterContainer in the pool.
     * @param i 
     */
    public void setCurrentSelection(int i){
        if (i < 0) throw new IllegalArgumentException("Given index is negative, when it should be positive");
        if (i >= poolSize) throw new IllegalArgumentException("Given index is greater than pool size");
        this.currentSelection = i;
    }
    
    /**
     * Marks an index as used and returns the LetterContainer it was pointing at.
     * @return LetterContainer at the current selection.
     */
    public LetterContainer useLetter(){
        if (this.usedLetterIndices.contains(this.currentSelection)) return null;
        this.usedLetterIndices.add(this.currentSelection);
        return this.pool[this.currentSelection];
    }
    
    /**
     * Clears all selections freeing them for use again.
     */
    public void clearLetterPicks(){
        this.usedLetterIndices.clear();
    }
    
    /**
     * Replaces all LetterContainers that used indices are pointing at
     * with new ones.
     */
    public void replacePickedLetters(){
        for(int i : this.usedLetterIndices){
            this.pool[i] = new LetterContainer(letters.getRandomLetter(), true, i);
        }
        this.usedLetterIndices.clear();
    }
    
    /**
     * Frees a LetterContainer for use at the location
     * the given index is pointing at.
     * @param i Index pointing at a LetterContainer to free.
     */
    public void unpickLetterAtIndex(int i){
        this.usedLetterIndices.remove(i);
    }
}
