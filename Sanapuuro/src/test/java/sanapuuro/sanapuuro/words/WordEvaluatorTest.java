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
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class WordEvaluatorTest {

    private final WordEvaluator wordEvaluator = new WordEvaluator();
    private final List<LetterContainer> validHorizontalWord = new ArrayList<>();
    private final List<LetterContainer> validVerticalWord = new ArrayList<>();
    private final List<LetterContainer> wordWithGaps = new ArrayList<>();
    private final List<LetterContainer> wordNotOnSameRow = new ArrayList<>();
    private final List<LetterContainer> wordTooShort = new ArrayList<>();

    public WordEvaluatorTest() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1), 0, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1), 1, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1), 2, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1), 3, 0);
        this.validHorizontalWord.add(container);

        container = new LetterContainer(new Letter('r', 1, 1), 0, 0);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('u', 1, 1), 0, 1);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('b', 2, 1), 0, 2);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('y', 1, 1), 0, 3);
        this.validVerticalWord.add(container);

        container = new LetterContainer(new Letter('r', 1, 1), 0, 0);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('u', 1, 1), 0, 1);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('b', 2, 1), 0, 2);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('y', 1, 1), 0, 4);
        this.wordWithGaps.add(container);

        container = new LetterContainer(new Letter('r', 1, 1), 0, 0);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('u', 1, 1), 1, 1);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('b', 2, 1), 0, 2);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('y', 1, 1), 0, 3);
        this.wordNotOnSameRow.add(container);

        container = new LetterContainer(new Letter('r', 1, 1), 0, 0);
        this.wordTooShort.add(container);
        container = new LetterContainer(new Letter('u', 1, 1), 0, 1);
        this.wordTooShort.add(container);
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
    public void correctValidationOfHorizontalWords() {
        assertTrue(this.wordEvaluator.isValidWord(validHorizontalWord));
    }

    @Test
    public void correctValidationOfVerticalWords() {
        assertTrue(this.wordEvaluator.isValidWord(validVerticalWord));
    }

    @Test
    public void doesNotValidateWordWithGaps() {
        assertFalse(this.wordEvaluator.isValidWord(wordWithGaps));
    }

    @Test
    public void doesNotValidateWordNotOnSameRowOrColumn() {
        assertFalse(this.wordEvaluator.isValidWord(wordNotOnSameRow));
    }

    @Test
    public void wordsTooShortNotValid() {
        assertFalse(this.wordEvaluator.isValidWord(wordTooShort));
    }

    @Test
    public void returnCorrectScoreForWord() {
        int score = this.wordEvaluator.evaluteLetters(validHorizontalWord);
        assertEquals(1 + 2 + 3 + 4, score);
        score = this.wordEvaluator.evaluteLetters(validVerticalWord);
        assertEquals(1 + 1 + 2 + 1, score);
    }
}
