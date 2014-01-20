/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gamelogic;

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
    private WordValidator wordValidator;
    
    public WordValidatorTest() {
        this.wordValidator = new WordValidator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.wordValidator = new WordValidator();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void recognizesWordsOnList() {
         this.wordValidator.loadWords(WordValidator.Language.ENGLISH);
         this.wordValidator.setWordLengthLimit(16);
         assertTrue(this.wordValidator.isValidWord("aa"));
         assertTrue(this.wordValidator.isValidWord("zyzzyvas"));
         assertTrue(this.wordValidator.isValidWord("haggadists"));
     }
     @Test
     public void wordsNotOnWordListAreNotValid() {
         this.wordValidator.loadWords(WordValidator.Language.ENGLISH);
         this.wordValidator.setWordLengthLimit(16);
         assertFalse(this.wordValidator.isValidWord("hessu"));
         assertFalse(this.wordValidator.isValidWord("meesny"));
     }
     @Test
     public void wordsTooLongNotValid() {
         this.wordValidator.loadWords(WordValidator.Language.ENGLISH);
         this.wordValidator.setWordLengthLimit(8);
         assertTrue(this.wordValidator.isValidWord("zyzzyvas"));
         assertFalse(this.wordValidator.isValidWord("hagadists"));
     }
     
     @Test
     public void wordsTooShortNotValid() {
         this.wordValidator.loadWords(WordValidator.Language.ENGLISH);
         this.wordValidator.setWordLengthLimit(8);
         assertFalse(this.wordValidator.isValidWord("z"));
         assertFalse(this.wordValidator.isValidWord(""));
     }
}
