/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

import java.util.HashSet;
import java.util.List;
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
public class LetterReaderTest {
    private Set<Character> englishLetters;
    private LetterReader letterReader;
    
    public LetterReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.letterReader = new LetterReader();
        this.englishLetters = new HashSet<Character>();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < letters.length(); i++){
            this.englishLetters.add(letters.charAt(i));
        }
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
     public void allLettersHaveBeenReadIn() {
         List<Letter> letters = this.letterReader.getLetters();
         for (int i = 0; i < letters.size(); i++){
             Letter letter = letters.get(i);
             this.englishLetters.remove(letter.character);
         }
         assertTrue(this.englishLetters.size() + " letters were left out", this.englishLetters.isEmpty());
     }
}
