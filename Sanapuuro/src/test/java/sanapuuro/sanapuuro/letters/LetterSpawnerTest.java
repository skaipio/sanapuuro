/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skaipio
 */
public class LetterSpawnerTest {
    private Set<Character> englishLetters;
    private final List<Letter> readInLetters;
    private LetterSpawner letterSpawner;
    
    public LetterSpawnerTest() {
        LetterReader reader = new LetterReader();
        this.readInLetters = reader.getLetters();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Random rnd = new Random(1);
        this.letterSpawner = new LetterSpawner(rnd);
        this.letterSpawner.setLetters(readInLetters);
        this.englishLetters = new HashSet<Character>();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < characters.length(); i++){
            this.englishLetters.add(characters.charAt(i));
        }
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void allEnglishLettersAreProduced() {
         for (int i = 0; i < 1000; i++){
             Letter letter = this.letterSpawner.getRandomLetter();
             this.englishLetters.remove(letter.character);
             if (this.englishLetters.isEmpty()) break;
         }
         StringBuilder lettersNotProduced = new StringBuilder();
         if (!this.englishLetters.isEmpty()){
             for(char c : this.englishLetters){
                 lettersNotProduced.append(c + " ");
             }
         }
         assertTrue(this.englishLetters.size() + " letters were not produced (" + lettersNotProduced + ")", this.englishLetters.isEmpty());
     }
}
