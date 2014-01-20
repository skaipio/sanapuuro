/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gamelogic;

/**
 *
 * @author skaipio
 */
public class Cell {
    private String letter = "";
    
    public String getLetter(){
        return this.letter;
    }
    
    public void setLetter(String letter){
        this.letter = letter;
    }
    
    public boolean hasLetter(){
        return !letter.equals("");
    }
}
