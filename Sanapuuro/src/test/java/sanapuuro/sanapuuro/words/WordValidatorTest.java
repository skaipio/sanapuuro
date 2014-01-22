/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.words;

import java.util.ArrayList;
import java.util.List;
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
public class WordValidatorTest {
    private WordValidator wordValidator = new WordValidator();
    
    public WordValidatorTest(){
        List<String> validWords = new ArrayList<>();
        validWords.add("i");
        validWords.add("am");
        validWords.add("cake");
        validWords.add("delicious");
        this.wordValidator.setValidWords(validWords);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void recognizesWordsOnList() {
         assertTrue(this.wordValidator.isValidWord("am"));
         assertTrue(this.wordValidator.isValidWord("cake"));
         assertTrue(this.wordValidator.isValidWord("delicious"));
     }
     @Test
     public void wordNotOnWordListIsNotValid() {
         assertFalse(this.wordValidator.isValidWord("hessu"));
     }
     
     @Test
     public void wordsTooShortNotValid() {
         assertFalse(this.wordValidator.isValidWord("i"));
     }
}
