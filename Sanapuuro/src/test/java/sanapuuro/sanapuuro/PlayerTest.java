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
import sanapuuro.sanapuuro.grid.LetterContainer;
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
    private Grid grid;
    private final WordEvaluator evaluator = new WordEvaluator();

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
        this.grid = new Grid(8, 8);
        Letters letters = new LettersForTest();
        this.player = new Player(grid, evaluator, letters);
        this.letterPool = this.player.getLetterPool();
    }

    @After
    public void tearDown() {
    }

    
    
    

    @Test
    public void letterIsAddedToCellIfCellEmpty() {
        boolean letterWasSet = this.player.addLetterTo(0, 0);
        assertTrue(letterWasSet);
        assertTrue(this.grid.hasContainerAt(0, 0));
        assertFalse(this.player.getAddedContainers().isEmpty());
    }

    @Test
    public void letterIsNotAddedToCellIfCellNotEmpty() {
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        LetterContainer container = this.grid.getContainerAt(0, 0);
        boolean letterWasSet = this.player.addLetterTo(0, 0);
        assertFalse(letterWasSet);
        assertSame(this.grid.getContainerAt(0, 0), container);
        assertTrue(this.player.getAddedContainers().isEmpty());
    }

    @Test
    public void letterIsSelectedIfCellIsNotSelected() {
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        boolean letterWasSelected = this.player.selectLetterAt(0, 0);
        assertTrue(letterWasSelected);
        assertTrue(this.player.getAddedContainers().isEmpty());
        assertFalse(this.player.getSelectedContainers().isEmpty());
    }
    
    @Test
    public void letterIsNotSelectedIfCellIsSelected() {
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        boolean letterWasSelected = this.player.selectLetterAt(0, 0);
        assertTrue(letterWasSelected);
        assertTrue(this.player.getAddedContainers().isEmpty());
        assertFalse(this.player.getSelectedContainers().isEmpty());
        letterWasSelected = this.player.selectLetterAt(0, 0);
        assertFalse(letterWasSelected);
        assertFalse(this.player.getSelectedContainers().size() > 1);
    }
    
    @Test
    public void lettersAreNotSelectedOrAddedIfControlsAreNotEnabled(){
        this.player.setControlsEnabled(false);
        
        boolean letterWasSet = this.player.addLetterTo(0, 0);
        assertFalse(letterWasSet);
        assertFalse(this.grid.hasContainerAt(0, 0));
        assertTrue(this.player.getAddedContainers().isEmpty());
        
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        boolean letterWasSelected = this.player.selectLetterAt(0, 0);
        assertFalse(letterWasSelected);
        assertTrue(this.player.getSelectedContainers().isEmpty());
    }
    
    @Test
    public void playerGetsScoreForSubmittedWordWhenAllAddedByPlayer() {
        this.player.addLetterTo(0, 0);
        this.letterPool.setCurrentSelection(1);
        this.player.addLetterTo(0, 1);
        this.letterPool.setCurrentSelection(2);
        this.player.addLetterTo(0, 2);
        
        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);
        this.player.selectLetterAt(0, 2);
        
        boolean submission = player.submitSelectedLetters();
        assertTrue(submission);
        assertEquals(6, player.getScore());
    }
    
    @Test
    public void playerGetsScoreForSubmittedWordWhenAllPreAddedAndThenSelected() {
        this.grid.setContainerAt(new LetterContainer(new Letter('t', 1, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('o', 2, 0)), 0, 1);
        this.grid.setContainerAt(new LetterContainer(new Letter('e', 3, 0)), 0, 2);
        
        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);
        this.player.selectLetterAt(0, 2);
        
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
