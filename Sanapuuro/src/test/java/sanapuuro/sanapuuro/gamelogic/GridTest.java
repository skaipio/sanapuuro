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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void throwsExceptionWhenGettingLetterOutsideGrid() {
         boolean thrown = false;
         try{
             this.grid.getLetterAt(-1, 0);
         }catch(IllegalArgumentException e){
             thrown = true;
         }
         assertTrue("x=-1 was not catched as out of bounds", thrown);
         thrown = false;
         try{
             this.grid.getLetterAt(0, -1);
         }catch(IllegalArgumentException e){
             thrown = true;
         }
         assertTrue("y=-1 was not catched as out of bounds", thrown);
         thrown = false;
         try{
             this.grid.getLetterAt(this.grid.width, 0);
         }catch(IllegalArgumentException e){
             thrown = true;
         }
         assertTrue("x=gridWidth was not catched as out of bounds", thrown);
         thrown = false;
         try{
             this.grid.getLetterAt(0, this.grid.height);
         }catch(IllegalArgumentException e){
             thrown = true;
         }
         assertTrue("y=gridHeight was not catched as out of bounds", thrown);
     }
     @Test
     public void setsAndGetsLettersAndCorrectly() {
         boolean wasAbleToAdd = this.grid.addLetterTo(0, 0, new Letter('a', 0, 0));
         assertTrue("result should be true when successfully adding a letter", wasAbleToAdd);
         Letter letter = this.grid.getLetterAt(0, 0);
         assertTrue("letter was not set into grid", letter != null);
         assertEquals('a', letter.character);
         wasAbleToAdd = this.grid.addLetterTo(this.grid.width-1, this.grid.height-1, new Letter('b', 0, 0));
         assertTrue("result should be true when successfully adding a letter", wasAbleToAdd);
         letter = this.grid.getLetterAt(this.grid.width-1, this.grid.height-1);
         assertTrue("letter was not set into grid", letter != null);
         assertEquals('b', letter.character);
     }
     @Test
     public void doesNotChangeLettersAlreadyPlaced() {
         this.grid.addLetterTo(1, 3, new Letter('a', 0, 0));
         boolean wasAbleToAdd = this.grid.addLetterTo(1, 3, new Letter('b', 0, 0));
         assertFalse("result should be false when trying to place a letter on another one", wasAbleToAdd);
         assertEquals("should not be able to replace a letter", 'a', this.grid.getLetterAt(1, 3).character);
     }
}
