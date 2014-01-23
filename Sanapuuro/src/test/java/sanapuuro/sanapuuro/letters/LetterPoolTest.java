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
public class LetterPoolTest {
    private final Set<Character> testLetters = new HashSet<>();
    private final List<Letter> poolLetters = new ArrayList<>();
    private LetterPool letterPool;
    
    public LetterPoolTest(){
        poolLetters.add(new Letter('a',1,0.15f));
        poolLetters.add(new Letter('b',2,0.35f));
        poolLetters.add(new Letter('c',3,0.50f));
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
        this.testLetters.add('a');
        this.testLetters.add('b');
        this.testLetters.add('c');
        this.letterPool = new LetterPool(rnd);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
     public void correctlyMatchesLetters() {
         this.letterPool.setLetters(poolLetters);
         Letter letter = this.letterPool.getLetterMatchingCharacter('a');
         assertEquals('a', letter.character);
         assertEquals(0.15f, letter.frequency, 0.0000001f);
         assertEquals(1, letter.score);
         letter = this.letterPool.getLetterMatchingCharacter('d');
         assertNull(letter);
     }
     
      @Test
     public void throwsNullExceptionIfNoLettersAssignedYet() {
         String errorMessage = "";
         try{
             this.letterPool.getLetterMatchingCharacter('a');
         }catch(NullPointerException e){
             errorMessage = e.getMessage();
            
         }
          assertEquals("No letters assigned yet.", errorMessage);     
         try{
             this.letterPool.getRandomLetter();
         }catch(NullPointerException e){
              errorMessage = e.getMessage();
         }
         assertEquals("No letters assigned yet.", errorMessage);  
     }
    
     @Test
     public void allLettersAreProduced() {
         this.letterPool.setLetters(poolLetters);
         for (int i = 0; i < 10; i++){
             Letter letter = this.letterPool.getRandomLetter();
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
