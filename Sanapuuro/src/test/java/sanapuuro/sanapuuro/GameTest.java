/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro;

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
public class GameTest {
    private Game game;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.game = new Game();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void playerIsNotNullAfterNewGameAndGridWidthAndHeightAreRight() {
         this.game.newGame();
         assertNotNull(this.game.getPlayer());
         assertEquals(12, this.game.getGridWidth());
         assertEquals(12, this.game.getGridHeight());
     }
}
