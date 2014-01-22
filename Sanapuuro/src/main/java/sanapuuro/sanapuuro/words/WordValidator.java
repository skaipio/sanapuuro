/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gamelogic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author skaipio
 */
public class WordValidator {
    private final String englishWordListPath = "assets/word.list";
    private List<String> words;
    private int lengthLimit = 8;
    
    public void loadWords(Language language){
        String wordListPath = englishWordListPath;
        switch (language){
            case ENGLISH:
                wordListPath = englishWordListPath;
                break;
        }
        try{
            Scanner reader = new Scanner(new File(wordListPath));
            List<String> scannedWords = new ArrayList<String>();
            while(reader.hasNext()){
                String word = reader.nextLine();
                scannedWords.add(word);
            }
            this.words = scannedWords;
        }catch(Exception e){
            System.out.println(e.getMessage());
            this.words = new ArrayList<String>();
        }
    }
    
    public boolean isValidWord(String word){
        return word.length() >= 2 && word.length() <= this.lengthLimit && words.contains(word);
    }
    
    public void setWordLengthLimit(int limit){
        this.lengthLimit = limit;
    }
    
    public enum Language{
        ENGLISH
    }
}
