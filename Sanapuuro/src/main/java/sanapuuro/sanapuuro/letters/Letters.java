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
public interface Letters {
    public Letter getLetterMatchingCharacter(char c);
    public Letter getRandomLetter();
}
