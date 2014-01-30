/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.words;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author skaipio
 */
public class WordReader implements WordValidator {
    private final String englishWordListPath = "assets/english_words";
     private List<String> words;
    
    public WordReader(){
        this.words = this.getWords();
    }

    private List<String> getWords() {
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

    @Override
    public boolean isValidWord(String word) {
        return word.length() >= 2 && words.contains(word);
    }
}
