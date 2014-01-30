/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.utils.LetterCoordinateComparator;

/**
 *
 * @author skaipio
 */
public class WordEvaluator {   
    private final WordValidator wordValidator = new WordReader();
    
    public boolean isValidWord(List<LetterContainer> letterContainers){
        if (letterContainers == null || letterContainers.isEmpty()){
            throw new IllegalArgumentException("No letters were given for validation.");
        }
        List<LetterContainer> containerCopy = new ArrayList(letterContainers);
        if (allContainersOnSameColumnWithoutGaps(containerCopy) || allContainersOnSameRowWithoutGaps(containerCopy)){
            StringBuilder word = new StringBuilder(letterContainers.size());
            for(LetterContainer container : letterContainers){
                if (container.hasLetter()){
                    word.append(container.getLetter().character);
                }
                else{
                    return false;
                }
            }
            return wordValidator.isValidWord(word.toString());
        }
        return false;
    }
            
    public int evaluteLetters(List<LetterContainer> letterContainers){
        int score = 0;
        for(LetterContainer container : letterContainers){
            score+=container.getLetter().score;
        }
        return score;
    }
    
    private boolean allContainersOnSameRowWithoutGaps(List<LetterContainer> letterContainers){
        Collections.sort(letterContainers, new LetterCoordinateComparator(false));
        LetterContainer previous = letterContainers.get(0);
        for(int i = 1; i < letterContainers.size(); i++){
            LetterContainer current = letterContainers.get(i);
            if (current.y != previous.y || (current.x - previous.x) > 1) return false;
        }
        return true;
    }
    
     private boolean allContainersOnSameColumnWithoutGaps(List<LetterContainer> letterContainers){
        Collections.sort(letterContainers, new LetterCoordinateComparator(true));
        LetterContainer previous = letterContainers.get(0);
        for(int i = 1; i < letterContainers.size(); i++){
            LetterContainer current = letterContainers.get(i);
            if (current.x != previous.x || (current.y - previous.y) > 1) return false;
        }
        return true;
    }
}
