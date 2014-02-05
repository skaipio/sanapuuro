/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 *
 * @author skaipio
 */
public class PlayerTest {

    private Player player;
    private LetterPool letterPool;

    public PlayerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Grid grid = new Grid(8, 8);
        letterPool = new LetterPool(new LettersForTest());
        GridCursor cursor = new GridCursor(grid, letterPool);
        WordEvaluator evaluator = new WordEvaluator();
        this.player = new Player(cursor, evaluator);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void playerHasStatusAndGridCursor() {
        assertNotNull(this.player.getControlledCursor());
        assertNotNull(this.player.getStatus());
    }

    @Test
    public void playerGetsScoreForSubmittedWord() {
        GridCursor cursor = player.getControlledCursor();
        cursor.addLetterUnderCursor();
        cursor.moveRight();
        this.letterPool.setCurrentSelection(1);
        cursor.addLetterUnderCursor();
        cursor.moveRight();
        this.letterPool.setCurrentSelection(2);
        cursor.addLetterUnderCursor();
        boolean submission = player.submitSelectedLetters();
        assertTrue(submission);
        assertEquals(6, player.getScore());
    }

    private class LettersForTest implements Letters {

        List<Letter> letters = new ArrayList<>();

        private int i = 0;

        public LettersForTest() {
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
        }

        @Override
        public Letter getLetterMatchingCharacter(char c) {
            return new Letter(' ', 0, 0);
        }

        @Override
        public Letter getRandomLetter() {
            i++;
            return this.letters.get(i - 1);
        }

    }
}
