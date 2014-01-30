/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.grid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class GridCursorTest {
    private final Grid grid = new Grid(8, 8);
    private GridCursor gridCursor;
    
    public GridCursorTest() { }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.gridCursor = new GridCursor(this.grid);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
     public void cursorFetchesCorrectLetterFromGrid() {    
         int x = this.gridCursor.getX();
         int y = this.gridCursor.getY();
         Letter expected = new Letter('a', 0, 0);
         this.grid.setLetterTo(x, y, expected);
         Letter actual = this.gridCursor.getLetterUnderCursor();
         assertEquals(expected, actual);
     }

    @Test
     public void cursorStartsAtMiddleOfGrid() {
         int x = this.gridCursor.getX();
         int y = this.gridCursor.getY();
         int expectedX = this.grid.width/2;
         int expectedY = this.grid.height/2;
         assertEquals(expectedX, x);
         assertEquals(expectedY, y);
     }
    
     @Test
     public void cursorMovesCorrectly() {
         int x = this.gridCursor.getX();        
         this.gridCursor.moveRight();
         int actualX = this.gridCursor.getX();
         assertEquals(x+1, actualX);
         x = actualX;
         this.gridCursor.moveLeft();
         actualX = this.gridCursor.getX();
         assertEquals(x-1, actualX);
         
         int y = this.gridCursor.getY();        
         this.gridCursor.moveUp();
         int actualY = this.gridCursor.getY();
         assertEquals(y+1, actualY);
         y = actualY;
         this.gridCursor.moveDown();
         actualY = this.gridCursor.getY();
         assertEquals(y-1, actualY);
     }
     
     @Test
     public void cursorDoesNotMoveOutsideGrid() {
         for (int x = this.gridCursor.getX(); x <= this.grid.width; x++){
             this.gridCursor.moveRight();
         }
         assertEquals(this.gridCursor.getX(), this.grid.width-1);
         for (int y = this.gridCursor.getY(); y <= this.grid.height; y++){
             this.gridCursor.moveUp();
         }
     }
     
     @Test
     public void cursorSetsLetterToGridOnlyWhenInSelectionModeAndCellDoesNotHaveLetter() {
         Letter letter = new Letter('a', 0, 0);
         boolean letterWasSet = this.gridCursor.addLetterUnderCursor(letter);
         Letter letterInGrid = this.gridCursor.getLetterUnderCursor();
         assertNull(letterInGrid);
         assertFalse(letterWasSet);
         
         this.gridCursor.selectionModeOn();
         letterWasSet = this.gridCursor.addLetterUnderCursor(letter);
         letterInGrid = this.gridCursor.getLetterUnderCursor();
         assertEquals(letter, letterInGrid);
         assertTrue(letterWasSet);
         
         Letter secondTestLetter = new Letter('b', 0, 0);
         letterWasSet = this.gridCursor.addLetterUnderCursor(secondTestLetter);
         assertEquals(letter, letterInGrid);
         assertFalse(letterWasSet);
         
         this.gridCursor.selectionModeOff();
         letterWasSet = this.gridCursor.addLetterUnderCursor(secondTestLetter);
         assertEquals(letter, letterInGrid);
         assertFalse(letterWasSet);
     }
     
     @Test
     public void cursorSelectsLetterOnlyWhenInSelectionModeAndCellHasLetter() {
         Letter letter = new Letter('a', 0, 0);
         this.grid.setLetterTo(4, 4, letter);       
         boolean letterWasSelected = this.gridCursor.selectLetterUnderCursor();
         assertFalse(letterWasSelected);
         
         this.gridCursor.selectionModeOn();
         letterWasSelected = this.gridCursor.selectLetterUnderCursor();
         assertTrue(letterWasSelected);
         
         this.gridCursor.moveUp();
         
         letterWasSelected = this.gridCursor.selectLetterUnderCursor();
         assertFalse(letterWasSelected);
     }
     
     @Test
     public void cursorAddsSelectedAndAddedLettersToSelectionList() {
         Letter l1 = new Letter('a', 0, 0);
         this.grid.setLetterTo(4, 4, l1);
         Letter l2 = new Letter('b', 0, 0);
         this.grid.setLetterTo(4, 5, l2); 
         Letter l3 = new Letter('c', 0, 0);
         Set<Letter> expectedLetters = new HashSet<>();
         expectedLetters.add(l1);
         
         this.gridCursor.selectionModeOn();
         this.gridCursor.selectLetterUnderCursor();
         List<LetterContainer> containers = this.gridCursor.getSelectedLetters();
         LetterContainer selected = containers.get(0);
         expectedLetters.remove(selected.getLetter());
         assertEquals(0, expectedLetters.size());
         
         expectedLetters.add(l1);
         expectedLetters.add(l2);
         this.gridCursor.moveUp();
         this.gridCursor.selectLetterUnderCursor();
         containers = this.gridCursor.getSelectedLetters();
         for(LetterContainer container : containers){          
             expectedLetters.remove(container.getLetter());
         }
         assertEquals(0, expectedLetters.size());
         
         
         expectedLetters.add(l1);
         expectedLetters.add(l2);
         expectedLetters.add(l3);
         this.gridCursor.moveLeft();
         this.gridCursor.addLetterUnderCursor(l3);
         containers = this.gridCursor.getSelectedLetters();
         for(LetterContainer container : containers){          
             expectedLetters.remove(container.getLetter());
         }
         assertEquals(0, expectedLetters.size());
     }
     
     @Test
     public void cursorClearsSelectionListWhenTurningOffSelectionMode() {
         Letter l1 = new Letter('a', 0, 0);
         this.grid.setLetterTo(4, 4, l1);
         
         this.gridCursor.selectionModeOn();
         this.gridCursor.selectLetterUnderCursor();        
         this.gridCursor.selectionModeOff();
         List<LetterContainer> containers = this.gridCursor.getSelectedLetters();
         assertTrue(containers.isEmpty());
     }
}
