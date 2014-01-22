/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

/**
 *
 * @author skaipio
 */
public class Letter{
    public final char character;
    public final int score;
    public final float frequency;
    public Letter(char character, int score, float frequency){
        this.character = character;
        this.score = score;
        this.frequency = frequency;
    }
    
    public String toString(){
        return this.character + " : " + this.score;
    }
}
