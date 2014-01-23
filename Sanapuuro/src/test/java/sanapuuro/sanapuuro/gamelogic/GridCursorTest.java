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
public class GridCursorTest {
    private Grid grid = new Grid(8, 8);
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
}
