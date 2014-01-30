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
public class LetterContainer {
    public final int x, y;

    public final Letter letter;
    
    public LetterContainer(Letter letter, int x, int y){
        this.letter = letter;
        this.x = x;
        this.y = y;
    }
}
