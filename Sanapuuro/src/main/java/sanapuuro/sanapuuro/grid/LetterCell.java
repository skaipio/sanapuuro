/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import sanapuuro.sanapuuro.letters.LetterContainer;

/**
 *
 * @author skaipio
 */
public class LetterCell {

    public final int x, y;
    private LetterContainer container;
    protected boolean used;

    public LetterCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean hasBeenUsed() {
        return used;
    }

    public boolean isSelected() {
        return used;
    }

    public void setAsUsed() {
        this.used = true;
    }
    
    public boolean hasContainer(){
        return this.container != null;
    }
    
    public LetterContainer getContainer(){
        return this.container;
    }

    public void setContainer(LetterContainer container) {
        this.container = container;
    }

    public void clear() {
        this.container = null;
        this.used = false;
    }
}
