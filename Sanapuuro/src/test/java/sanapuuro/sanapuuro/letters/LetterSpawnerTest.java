/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import java.util.ArrayList;
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
    private Set<Character> testLetters = new HashSet<Character>();
    private LetterSpawner letterSpawner;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Random rnd = new Random(1);
        this.testLetters.add('a');
        this.testLetters.add('b');
        this.testLetters.add('c');
        List<Letter> letters = new ArrayList<>();
        letters.add(new Letter('a',0,0.15f));
        letters.add(new Letter('b',0,0.35f));
        letters.add(new Letter('c',0,0.50f));
        this.letterSpawner = new LetterSpawner(rnd);
        this.letterSpawner.setLetters(letters);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void allLettersAreProduced() {
         for (int i = 0; i < 10; i++){
             Letter letter = this.letterSpawner.getRandomLetter();
             this.testLetters.remove(letter.character);
             if (this.testLetters.isEmpty()) break;
         }
         StringBuilder lettersNotProduced = new StringBuilder();
         if (!this.testLetters.isEmpty()){
             for(char c : this.testLetters){
                 lettersNotProduced.append(c + " ");
             }
         }
         assertTrue(this.testLetters.size() + " letters were not produced (" + lettersNotProduced + ")", this.testLetters.isEmpty());
     }
}
