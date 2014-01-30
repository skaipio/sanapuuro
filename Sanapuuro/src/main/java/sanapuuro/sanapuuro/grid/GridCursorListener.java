/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.grid;

import java.util.List;

/**
 *
 * @author skaipio
 */
public interface GridCursorListener {
    void lettersSubmitted(List<LetterContainer> letters);
}
