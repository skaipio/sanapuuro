/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import java.util.HashSet;
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
public class LetterFactoryTest {
    private Set<Character> englishLetters;
    private LetterSpawner letterSpawner;
    
    public LetterFactoryTest() {}
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.letterSpawner = new LetterSpawner();
        this.englishLetters = new HashSet<Character>();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < letters.length(); i++){
            this.englishLetters.add(letters.charAt(i));
        }
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void allEnglishLettersAreProduced() {
         for (int i = 0; i < 10000; i++){
             ScoredLetter scoredLetter = this.letterSpawner.getRandomLetter();
             this.englishLetters.remove(scoredLetter.letter);
             if (this.englishLetters.isEmpty()) break;
         }
         assertTrue(this.englishLetters.size() + " letters were not produced", this.englishLetters.isEmpty());
     }
}
