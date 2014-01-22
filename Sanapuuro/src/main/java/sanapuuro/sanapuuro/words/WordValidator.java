/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.words;

import java.util.List;

/**
 *
 * @author skaipio
 */
public class WordValidator {
    private List<String> words;
    
    public void setValidWords(List<String> words){
        this.words = words;
    }
    
    public boolean isValidWord(String word){
        return word.length() >= 2 && words.contains(word);
    }
}
