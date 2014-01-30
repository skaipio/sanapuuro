/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author skaipio
 */
public class LetterPool{
    public final int poolSize = 8;
    private final Letters letters;
    private final Letter[] pool = new Letter[poolSize];
    private final Set<Integer> pickedLetterIndices = new HashSet(poolSize);
    private final List<LetterPoolListener> listeners = new ArrayList<>();
    private int currentSelection = 0;
            
    public LetterPool(Letters letters){
        this.letters = letters;
        for(int i = 0; i < poolSize; i++){
            this.pool[i] = letters.getRandomLetter();
        }
    }
    
    public void addListener(LetterPoolListener listener){
        this.listeners.add(listener);
        this.notifyLetterPoolChanged();
    }
    
    public Letter[] getLetters(){
        return this.pool.clone();
    }
    
    public void setCurrentSelection(int i){
        if (i < 0) throw new IllegalArgumentException("Given index is negative, when it should be positive");
        if (i >= poolSize) throw new IllegalArgumentException("Given index is greater than pool size");
        this.currentSelection = i;
        this.notifyLetterPoolSelectionChanged();
    }
    
    public Letter pickLetter(){
        if (this.pickedLetterIndices.contains(this.currentSelection)) return null;
        this.pickedLetterIndices.add(this.currentSelection);
        return this.pool[this.currentSelection];
    }
    
    public void clearLetterPicks(){
        this.pickedLetterIndices.clear();
    }
    
    public void removePickedLetters(){
        for(int i : this.pickedLetterIndices){
            this.pool[i] = this.letters.getRandomLetter();
        }
        this.notifyLetterPoolChanged();
    }
    
    private void notifyLetterPoolChanged(){
        for(LetterPoolListener listener : this.listeners){
            listener.letterPoolChanged(pool);
        }
    }
    private void notifyLetterPoolSelectionChanged(){
        for(LetterPoolListener listener : this.listeners){
            listener.letterPoolSelectionChanged(this.currentSelection);
        }
    }
}
