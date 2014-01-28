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
    public final Coordinate coordinate;
    private Letter letter;
    
    public LetterCell(Coordinate coordinate){
        this.coordinate = coordinate;
    }
    
    public Letter getLetter(){
        return this.letter;
    }

    public void setLetter(Letter letter){
        this.letter = letter;
    }
    
     public boolean hasLetter(){
         return this.letter != null;
     }
    
    public void clear(){
        this.letter = null;
    }
}
