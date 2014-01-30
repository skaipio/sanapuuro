/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class LetterCell {

    public final int x, y;
    private Letter letter;
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
    
    public boolean hasLetter(){
        return this.letter != null;
    }
    
    public Letter getLetter(){
        return this.letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public void clear() {
        this.letter = null;
        this.used = false;
    }
}
