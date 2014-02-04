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
    private final WordList wordValidator = new WordReader();
    
    public boolean isValidWord(List<LetterContainer> letterContainers){
        if (letterContainers == null || letterContainers.isEmpty()){
            throw new IllegalArgumentException("No letters were given for validation.");
        }
        if (letterContainers.size() < 3) return false;
        
        List<LetterContainer> containerCopy = new ArrayList(letterContainers);
        if (allContainersOnSameColumnWithoutGaps(containerCopy) || allContainersOnSameRowWithoutGaps(containerCopy)){
            StringBuilder word = new StringBuilder(letterContainers.size());
            for(LetterContainer container : letterContainers){
                    word.append(container.letter.character);
            }
            return wordValidator.hasWord(word.toString());
        }
        return false;
    }
            
    public int evaluteLetters(List<LetterContainer> letterContainers){
        int score = 0;
        for(LetterContainer container : letterContainers){
            score+=container.letter.score;
        }
        return score;
    }
    
    private boolean allContainersOnSameRowWithoutGaps(List<LetterContainer> letterContainers){
        Collections.sort(letterContainers, new LetterCoordinateComparator(false));      
        for(int i = 1; i < letterContainers.size(); i++){
            LetterContainer previous = letterContainers.get(i-1);
            LetterContainer current = letterContainers.get(i);
            if (current.getY() != previous.getY()  || (current.getX() - previous.getX()) > 1) return false;
        }
        return true;
    }
    
     private boolean allContainersOnSameColumnWithoutGaps(List<LetterContainer> letterContainers){
        Collections.sort(letterContainers, new LetterCoordinateComparator(true));
        for(int i = 1; i < letterContainers.size(); i++){
            LetterContainer previous = letterContainers.get(i-1);
            LetterContainer current = letterContainers.get(i);
            if (current.getX() != previous.getX() || (current.getY()  - previous.getY() ) > 1) return false;
        }
        return true;
    }
}
