/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gamelogic;

import sanapuuro.sanapuuro.utils.MathUtils;

/**
 *
 * @author skaipio
 */
public class GridCursor {
    private int x, y;
    private Grid grid;
    
    public GridCursor(Grid grid){
        this.grid = grid;
        this.x = grid.width/2;
        this.y = grid.height/2;
    }
    
    public void moveUp(){
        this.y = MathUtils.clamp(0, this.grid.height-1, y+1);
    }
    public void moveDown(){
        this.y = MathUtils.clamp(0, this.grid.height-1, y-1);
    }
    public void moveLeft(){
        this.x = MathUtils.clamp(0, this.grid.width-1, x-1);
    }
    public void moveRight(){
        this.x = MathUtils.clamp(0, this.grid.width-1, x+1);
    }
}
