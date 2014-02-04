/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

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
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterReader;
import sanapuuro.sanapuuro.letters.Letters;

/**
 *
 * @author skaipio
 */
public class GridCursorTest {

    private Grid grid = new Grid(8, 8);
    private GridCursor gridCursor;
    private LetterPool letterPool;
    private final Letters letters;

    public GridCursorTest() {
        this.letters = new LetterReader(new Random(1));
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
        this.letterPool = new LetterPool(letters);
        this.gridCursor = new GridCursor(grid, letterPool);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void cursorFetchesCorrectLetterFromGrid() {
        int x = this.gridCursor.getX();
        int y = this.gridCursor.getY();
        Letter expected = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(expected), x, y);
        Letter actual = this.gridCursor.getContainerUnderCursor().letter;
        assertEquals(expected, actual);
    }

    @Test
    public void cursorStartsAtMiddleOfGrid() {
        int x = this.gridCursor.getX();
        int y = this.gridCursor.getY();
        int expectedX = this.grid.width / 2;
        int expectedY = this.grid.height / 2;
        assertEquals(expectedX, x);
        assertEquals(expectedY, y);
    }

    @Test
    public void cursorIsSetToCorrectLocation() {
        int expectedX = 0;
        int expectedY = 0;

        this.gridCursor.setLocation(expectedX, expectedY);

        int actualX = this.gridCursor.getX();
        int actualY = this.gridCursor.getY();

        assertEquals(expectedX, actualX);
        assertEquals(expectedY, actualY);
    }

    @Test
    public void attemptingToSetCursorOutsideBoundsKeepsCursorAtCurrentPosition() {
        int expectedX = this.gridCursor.getX();
        int expectedY = this.gridCursor.getY();
        this.gridCursor.setLocation(this.grid.width, 0);
        assertEquals(expectedX, this.gridCursor.getX());
        assertEquals(expectedY, this.gridCursor.getY());

        this.gridCursor.setLocation(0, this.grid.height);
        assertEquals(expectedX, this.gridCursor.getX());
        assertEquals(expectedY, this.gridCursor.getY());
    }

    @Test
    public void cursorMovesCorrectly() {
        int x = this.gridCursor.getX();
        this.gridCursor.moveRight();
        int actualX = this.gridCursor.getX();
        assertEquals(x + 1, actualX);
        x = actualX;
        this.gridCursor.moveLeft();
        actualX = this.gridCursor.getX();
        assertEquals(x - 1, actualX);

        int y = this.gridCursor.getY();
        this.gridCursor.moveUp();
        int actualY = this.gridCursor.getY();
        assertEquals(y + 1, actualY);
        y = actualY;
        this.gridCursor.moveDown();
        actualY = this.gridCursor.getY();
        assertEquals(y - 1, actualY);
    }

    @Test
    public void cursorDoesNotMoveOutsideGrid() {
        for (int x = this.gridCursor.getX(); x <= this.grid.width; x++) {
            this.gridCursor.moveRight();
        }
        assertEquals(this.gridCursor.getX(), this.grid.width - 1);
        for (int y = this.gridCursor.getY(); y <= this.grid.height; y++) {
            this.gridCursor.moveUp();
        }
    }

    @Test
    public void cursorAddsPickedLetterToCellIfCellEmpty() {
        Letter letter = new Letter('a', 0, 0);
        boolean letterWasSet = this.gridCursor.addLetterUnderCursor();
        assertTrue(letterWasSet);
        assertTrue(this.grid.hasContainerAt(this.gridCursor.getX(), this.gridCursor.getY()));
    }

    @Test
    public void cursorDoesNotAddPickedLetterToCellIfCellNotEmpty() {
        Letter letter = new Letter('a', 0, 0);
        LetterCell cell = this.grid.getCellAt(this.gridCursor.getX(), this.gridCursor.getY());
        cell.setContainer(new LetterContainer(letter));
        boolean letterWasSet = this.gridCursor.addLetterUnderCursor();
        assertNotSame(this.letterPool.getCurrentSelection(), cell.getContainer());
    }

    @Test
    public void cursorSelectsLetterWhenCellHasLetter() {
        Letter letter = new Letter('a', 0, 0);
        LetterCell cell = this.grid.getCellAt(this.gridCursor.getX(), this.gridCursor.getY());
        cell.setContainer(new LetterContainer(letter));
        boolean letterWasSelected = this.gridCursor.selectLetterUnderCursor();
        assertTrue(letterWasSelected);
    }

    @Test
    public void cursorAddsSelectedAndAddedLettersToSelectionList() {
        Letter l1 = new Letter('a', 0, 0);
        LetterCell cell = this.grid.getCellAt(4, 4);
        cell.setContainer(new LetterContainer(l1));
        Letter l2 = new Letter('b', 0, 0);
        cell = this.grid.getCellAt(4, 5);
        cell.setContainer(new LetterContainer(l2));
        Letter l3 = this.letterPool.getCurrentSelection().letter;
        Set<Letter> expectedLetters = new HashSet<>();
        expectedLetters.add(l1);

        this.gridCursor.selectLetterUnderCursor();
        List<LetterContainer> containers = this.gridCursor.getSelectedContainers();
        LetterContainer selected = containers.get(0);
        expectedLetters.remove(selected.letter);
        assertEquals(0, expectedLetters.size());

        expectedLetters.add(l1);
        expectedLetters.add(l2);
        this.gridCursor.moveUp();
        this.gridCursor.selectLetterUnderCursor();
        containers = this.gridCursor.getSelectedContainers();
        for (LetterContainer container : containers) {
            expectedLetters.remove(container.letter);
        }
        assertEquals(0, expectedLetters.size());

        expectedLetters.add(l1);
        expectedLetters.add(l2);
        expectedLetters.add(l3);
        this.gridCursor.moveLeft();
        this.gridCursor.addLetterUnderCursor();
        containers = this.gridCursor.getSelectedContainers();
        for (LetterContainer container : containers) {
            expectedLetters.remove(container.letter);
        }
        assertEquals(0, expectedLetters.size());
    }

    @Test
    public void cursorClearsSelectionListOnClearAndReturnsTheClearedLetters() {
        Letter l1 = new Letter('a', 0, 0);
        LetterCell cell = this.grid.getCellAt(4, 4);
        cell.setContainer(new LetterContainer(l1));

        this.gridCursor.selectLetterUnderCursor();
        List<LetterContainer> clearedLetters = this.gridCursor.clearSelectedContainers();
        List<LetterContainer> containers = this.gridCursor.getSelectedContainers();
        assertTrue(containers.isEmpty());
        assertEquals(1, clearedLetters.size());
    }

    @Test
    public void selectedLettersAreSubmittedCorrectly() {
        Letter l1 = new Letter('a', 0, 0);
        LetterCell cell = this.grid.getCellAt(4, 4);
        cell.setContainer(new LetterContainer(l1));

        GridCursorTestListener listener = new GridCursorTestListener();
        this.gridCursor.addListener(listener);

        this.gridCursor.selectLetterUnderCursor();
        this.gridCursor.submitLetters();
        assertFalse(listener.letters.isEmpty());
        assertTrue(this.gridCursor.getSelectedContainers().isEmpty());
    }

    private class GridCursorTestListener implements GridCursorListener {

        public List<LetterContainer> letters;

        @Override
        public boolean lettersSubmitted(List<LetterContainer> letters) {
            this.letters = letters;
            return true;
        }
    }
}
