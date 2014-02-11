/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.filereaders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sanapuuro.sanapuuro.words.WordList;

/**
 * Holds and reads valid English words from a file.
 * @author skaipio
 */
public class WordReader implements WordList {
    private final String englishWordListPath = "assets/english_words";
    private List<String> words;
    
    public WordReader(){
        this.words = this.getWords();
    }

    /**
     * @return All valid English words given by a file.
     */
    public List<String> getWords() {
        try {
            Scanner reader = new Scanner(new File(englishWordListPath));
            List<String> scannedWords = new ArrayList<>();
            while (reader.hasNext()) {
                String word = reader.nextLine();
                scannedWords.add(word);
            }
            return scannedWords;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return new ArrayList<>();
    }

    /**
     * @param word Word to check.
     * @return True, if the word was found in the English word list, false otherwise.
     */
    @Override
    public boolean hasWord(String word) {
        return word.length() >= 2 && words.contains(word);
    }
}