/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.utils;

import java.util.Comparator;
import sanapuuro.sanapuuro.grid.LetterContainer;

/**
 *
 * @author skaipio
 */
public class LetterCoordinateComparator implements Comparator<LetterContainer> {
    private final boolean byRow;
    public LetterCoordinateComparator(boolean byRow){
        this.byRow = byRow;
    }

    @Override
    public int compare(LetterContainer o1, LetterContainer o2) {
        if (byRow){
            return Integer.compare(o1.y, o2.y);
        }
        return Integer.compare(o1.x, o2.x);
    }
}
