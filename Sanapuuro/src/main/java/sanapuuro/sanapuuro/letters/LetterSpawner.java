/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.letters;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author skaipio
 */
public class LetterSpawner {

    private final Random random;
    private final LetterFrequencyComparator letterFreqComparator = new LetterFrequencyComparator();
    
    private List<Letter> letters;
    
    public LetterSpawner(Random randomizer){
        this.random = randomizer;
    }

    public Letter getRandomLetter() {
        if (this.letters == null){
            throw new NullPointerException("No letters assigned yet.");
        }
        float rnd = (float) random.nextDouble();
        float accumulated = 0;
        int i = 0;
        Letter letter;
        do {          
            letter = this.letters.get(i);
            accumulated += letter.frequency;
            i++;
        }while (accumulated < rnd && i < this.letters.size());
        return letter;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
        Collections.sort(this.letters, this.letterFreqComparator);
    }

    
}
