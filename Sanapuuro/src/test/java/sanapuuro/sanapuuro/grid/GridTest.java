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
                if (this.grid.getCellAt(x, y) == null) {
                    hasNulls = true;
                    break;
                }
            }
            if (hasNulls) {
                break;
            }
        }
        assertFalse("grid shouldn't have null cells", hasNulls);
    }

    @Test
    public void gridHasNoLettersAfterClearing() {
        LetterCell cell = this.grid.getCellAt(0, 0);
        cell.setContainer(new LetterContainer(new Letter('a', 0, 0)));
        cell = this.grid.getCellAt(7, 7);
        cell.setContainer(new LetterContainer(new Letter('b', 0, 0)));
        boolean hasLetters = false;
        this.grid.clear();
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
                if (this.grid.getCellAt(x, y).getContainer() != null) {
                    hasLetters = true;
                    break;
                }
            }
            if (hasLetters) {
                break;
            }
        }
        assertFalse("grid shouldn't have any letters", hasLetters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridNegativeX() {
        this.grid.getCellAt(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridNegativeY() {
        this.grid.getCellAt(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridXTooBig() {
        this.grid.getCellAt(this.grid.width, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenGettingCellOutsideGridYTooBig() {
        this.grid.getCellAt(0, this.grid.height);
    }
}
