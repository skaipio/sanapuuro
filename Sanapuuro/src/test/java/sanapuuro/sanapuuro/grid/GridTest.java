/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class GridTest {

    private Grid grid;

    public GridTest() {
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void gridIsFilledWithCellsAfterCreation() {
        boolean hasNulls = false;
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
                if (this.grid.getCellAt(x, y) == null){
                    hasNulls = true;
                    break;
                }
            }
            if (hasNulls) break;
        }
        assertFalse("grid shouldn't have null cells", hasNulls);
    }
    
    @Test
    public void gridIsHasNoLettersAfterClearing() {
        this.grid.addLetterTo(0, 0, new Letter('a', 0, 0));
        this.grid.addLetterTo(7, 7, new Letter('b', 0, 0));
        boolean hasLetters = false;
        this.grid.clear();
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
                if (this.grid.getCellAt(x, y).getLetter() != null){
                    hasLetters = true;
                    break;
                }
            }
            if (hasLetters) break;
        }
        assertFalse("grid shouldn't have any letters", hasLetters);
    }

    @Test
    public void throwsExceptionWhenGettingCellOutsideGrid() {
        boolean thrown = false;
        try {
            this.grid.getCellAt(-1, 0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("x=-1 was not catched as out of bounds", thrown);
        thrown = false;
        try {
            this.grid.getCellAt(0, -1);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("y=-1 was not catched as out of bounds", thrown);
        thrown = false;
        try {
            this.grid.getCellAt(this.grid.width, 0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("x=gridWidth was not catched as out of bounds", thrown);
        thrown = false;
        try {
            this.grid.getCellAt(0, this.grid.height);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("y=gridHeight was not catched as out of bounds", thrown);
    }
    
    @Test
    public void throwsExceptionWhenAddingCellOutsideGrid() {
        boolean thrown = false;
        try {
            this.grid.addLetterTo(8, 8, new Letter('a', 0, 0));
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("trying to add letter outside grid should throw an exception", thrown); 
    }

    @Test
    public void setsAndGetsLettersAndCorrectly() {
        boolean wasAbleToAdd = this.grid.addLetterTo(0, 0, new Letter('a', 0, 0));
        assertTrue("result should be true when successfully adding a letter", wasAbleToAdd);
        Letter letter = this.grid.getCellAt(0, 0).getLetter();
        assertTrue("letter was not set into grid", letter != null);
        assertEquals('a', letter.character);
        wasAbleToAdd = this.grid.addLetterTo(this.grid.width - 1, this.grid.height - 1, new Letter('b', 0, 0));
        assertTrue("result should be true when successfully adding a letter", wasAbleToAdd);
        letter = this.grid.getCellAt(this.grid.width - 1, this.grid.height - 1).getLetter();
        assertTrue("letter was not set into grid", letter != null);
        assertEquals('b', letter.character);
    }

    @Test
    public void doesNotChangeLettersAlreadyPlaced() {
        this.grid.addLetterTo(1, 3, new Letter('a', 0, 0));
        boolean wasAbleToAdd = this.grid.addLetterTo(1, 3, new Letter('b', 0, 0));
        assertFalse("result should be false when trying to place a letter on another one", wasAbleToAdd);
        assertEquals("should not be able to replace a letter", 'a', this.grid.getCellAt(1, 3).getLetter().character);
    }
}
