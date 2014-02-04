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
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class WordEvaluatorTest {

    private Grid grid;
    private final WordEvaluator wordEvaluator = new WordEvaluator();
    private final List<LetterContainer> validHorizontalWord = new ArrayList<>();
    private final List<LetterContainer> validVerticalWord = new ArrayList<>();
    private final List<LetterContainer> wordWithGaps = new ArrayList<>();
    private final List<LetterContainer> wordNotOnSameRow = new ArrayList<>();
    private final List<LetterContainer> wordTooShort = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.grid = new Grid(8, 8);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void correctValidationOfHorizontalWords() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.getCellAt(0, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1));
        this.grid.getCellAt(1, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1));
        this.grid.getCellAt(2, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1));
        this.grid.getCellAt(3, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        assertTrue(this.wordEvaluator.isValidWord(validHorizontalWord));
    }

    @Test
    public void correctValidationOfVerticalWords() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.getCellAt(0, 0).setContainer(container);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.getCellAt(0, 1).setContainer(container);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.getCellAt(0, 2).setContainer(container);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.getCellAt(0, 3).setContainer(container);
        this.validVerticalWord.add(container);
        assertTrue(this.wordEvaluator.isValidWord(validVerticalWord));
    }

    @Test
    public void doesNotValidateWordWithGaps() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.getCellAt(0, 0).setContainer(container);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.getCellAt(0, 1).setContainer(container);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.getCellAt(0, 2).setContainer(container);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.getCellAt(0, 4).setContainer(container);
        this.wordWithGaps.add(container);
        assertFalse(this.wordEvaluator.isValidWord(wordWithGaps));
    }

    @Test
    public void doesNotValidateWordNotOnSameRowOrColumn() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.getCellAt(0, 0).setContainer(container);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.getCellAt(1, 1).setContainer(container);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.getCellAt(0, 2).setContainer(container);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.getCellAt(0, 3).setContainer(container);
        this.wordNotOnSameRow.add(container);
        assertFalse(this.wordEvaluator.isValidWord(wordNotOnSameRow));
    }

    @Test
    public void wordsTooShortNotValid() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.getCellAt(0, 0).setContainer(container);
        this.wordTooShort.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.getCellAt(0, 1).setContainer(container);
        this.wordTooShort.add(container);
        assertFalse(this.wordEvaluator.isValidWord(wordTooShort));
    }

    @Test
    public void returnCorrectScoreForLetters() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.getCellAt(0, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1));
        this.grid.getCellAt(1, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1));
        this.grid.getCellAt(2, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1));
        this.grid.getCellAt(3, 0).setContainer(container);
        this.validHorizontalWord.add(container);
        int score = this.wordEvaluator.evaluteLetters(validHorizontalWord);
        assertEquals(1 + 2 + 3 + 4, score);
    }
}
