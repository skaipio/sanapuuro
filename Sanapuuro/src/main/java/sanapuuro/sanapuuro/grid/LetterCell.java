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
public class LetterCell extends LetterContainer {
    
    public LetterCell(int x, int y){
        super(x,y);
    }
    
    public void setAsUsed(){
        this.used = true;
    }

    public void setLetter(Letter letter){
        this.letter = letter;
    }
    
    public void clear(){
        this.letter = null;
        this.used = false;
    }
}
