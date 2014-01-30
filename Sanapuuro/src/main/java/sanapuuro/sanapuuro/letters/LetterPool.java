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
    private final int poolSize = 8;
    private final Letters letters;
    private final Letter[] pool = new Letter[poolSize];
    private final Set<Integer> pickedLetterIndices = new HashSet(poolSize);
    private final List<LetterPoolListener> listeners = new ArrayList<>();
            
    public LetterPool(Letters letters){
        this.letters = letters;
        for(int i = 0; i < poolSize; i++){
            this.pool[i] = letters.getRandomLetter();
        }
    }
    
    public void addListener(LetterPoolListener listener){
        this.listeners.add(listener);
    }
    
    public Letter[] getLetters(){
        return this.pool.clone();
    }
    
    public Letter pickLetter(int atIndex){
        if (this.pickedLetterIndices.contains(atIndex)) return null;
        this.pickedLetterIndices.add(atIndex);
        return this.pool[atIndex];
    }
    
    public void clearLetterPicks(){
        this.pickedLetterIndices.clear();
    }
    
    public void removePickedLetters(){
        for(int i : this.pickedLetterIndices){
            this.pool[i] = this.letters.getRandomLetter();
        }
    }
    
    private void notifyLetterPoolChanged(){
        for(LetterPoolListener listener : this.listeners){
            listener.letterPoolChanged(pool);
        }
    }
}
