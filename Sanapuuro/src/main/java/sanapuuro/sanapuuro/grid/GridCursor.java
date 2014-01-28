/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import sanapuuro.sanapuuro.grid.Grid;
import java.util.HashMap;
import java.util.Map;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.utils.MathUtils;

/**
 *
 * @author skaipio
 */
public class GridCursor {

    private int x, y;
    private final Grid grid;
    private final Map<Coordinate, Letter> selectedLetters = new HashMap<>();
    private Coordinate currentSelection;

    private boolean selectionMode = false;

    GridCursor(Grid grid) {
        this.grid = grid;
        this.x = this.grid.width / 2;
        this.y = this.grid.height / 2;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void moveUp() {
        this.y = MathUtils.clamp(0, this.grid.height - 1, y + 1);
    }

    public void moveDown() {
        this.y = MathUtils.clamp(0, this.grid.height - 1, y - 1);
    }

    public void moveLeft() {
        this.x = MathUtils.clamp(0, this.grid.width - 1, x - 1);
    }

    public void moveRight() {
        this.x = MathUtils.clamp(0, this.grid.width - 1, x + 1);
    }

    public void turnOnSelectionMode() {
        this.selectionMode = true;
    }

    public void turnOffSelectionMode() {
        this.selectionMode = false;
        this.selectedLetters.clear();
    }

    public Letter getLetterUnderCursor() {
        return this.grid.getCellAt(x, y).getLetter();
    }

    public boolean addLetterUnderCursor(Letter letter) {
        if (this.selectionMode) {
            return this.grid.addLetterTo(x, y, letter);
        }
        return false;
    }

    //    public void startSelecting() {
//        this.inSelectionMode = true;
//        this.currentSelection = this.gridCursor.getCellAtCursor();
//    }
//
//    public void stopSelecting() {
//        this.selectedLetters.clear();
//        this.inSelectionMode = false;
//    }
//
//    public boolean putCharAtCursor(char letter) {
//        if (this.gridCursor.getCellAtCursor().hasLetter()) {
//            return false;
//        }
//        if (!this.inSelectionMode) {
//            this.startSelecting();
//        }
//        this.game.setLetterTo(currentSelection.x, currentSelection.y, charToAdd);
//        this.selectedLetters.put(this.currentSelection.coordinate, letter);
//        return true;
//    }
}
