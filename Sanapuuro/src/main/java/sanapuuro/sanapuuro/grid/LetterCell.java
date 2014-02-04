/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

/**
 *
 * @author skaipio
 */
public class LetterCell {

    public final int x, y;
    private LetterContainer container;

    public LetterCell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean hasContainer(){
        return this.container != null;
    }
    
    public LetterContainer getContainer(){
        return this.container;
    }

    public void setContainer(LetterContainer container) {
        this.container = container;
        this.container.setX(x);
        this.container.setY(y);
    }

    public void clear() {
        this.container = null;
    }
}
