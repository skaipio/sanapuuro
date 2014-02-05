/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.utils.MathUtils;

/**
 *
 * @author skaipio
 */
public class GridCursor {

    private int x, y;
    private final Grid grid;
    private final LetterPool letterPool;
    private final List<LetterContainer> selectedLetters = new ArrayList<>();

    public GridCursor(Grid grid, LetterPool letterPool) {
        this.grid = grid;
        this.letterPool = letterPool;
        this.x = this.grid.width / 2;
        this.y = this.grid.height / 2;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    /**
     * Sets the grid cursors to point at the given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void setLocation(int x, int y) {
        if (this.grid.isWithinGrid(x, y)) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Moves the cursor up by one unit.
     */
    public void moveUp() {
        this.y = MathUtils.clamp(0, this.grid.height - 1, y + 1);
    }

    /**
     * Moves the cursor down by one unit.
     */
    public void moveDown() {
        this.y = MathUtils.clamp(0, this.grid.height - 1, y - 1);
    }

    /**
     * Moves the cursor left by one unit.
     */
    public void moveLeft() {
        this.x = MathUtils.clamp(0, this.grid.width - 1, x - 1);
    }

    /**
     * Moves the cursor right by one unit.
     */
    public void moveRight() {
        this.x = MathUtils.clamp(0, this.grid.width - 1, x + 1);
    }

    /**
     * Clears all selections.
     *
     * @return The list of selected letters if there were any and empty list
     * otherwise.
     */
    public List<LetterContainer> clearSelectedContainers() {
        List<LetterContainer> containers = new ArrayList<>(this.selectedLetters);
        for (LetterContainer container : containers) {
            if (container.isFromLetterPool()) {
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
            }
        }
        this.selectedLetters.clear();
        return containers;
    }

    /**
     * Returns a list of selected letters.
     *
     * @return The list of selected letters if there are any and empty list
     * otherwise.
     */
    public List<LetterContainer> getSelectedContainers() {
        List<LetterContainer> letters = new ArrayList<>(this.selectedLetters);
        return letters;
    }

    public void setSelectedLettersToGridPermanently() {
        for (LetterContainer container : this.selectedLetters) {
            container.setToGridPermanently();
        }
        this.letterPool.removePickedLetters();
        this.selectedLetters.clear();
    }

    public boolean hasContainerUnderCursor() {
        return this.grid.hasContainerAt(x, y);
    }

    public LetterContainer getContainerUnderCursor() {
        return this.grid.getContainerAt(x, y);
    }

    /**
     * Adds a letter container from the letter pool on the current cursor
     * location if there is no other container already present and adds it to
     * selected containers.
     *
     * @return
     */
    public boolean addLetterUnderCursor() {
        if (!this.grid.hasContainerAt(x, y)) {
            LetterContainer container = this.letterPool.useLetter();
            if (container != null) {
                this.grid.setContainerAt(container, x, y);
                this.selectedLetters.add(container);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the letter under the cursor to selected letter containers if there
     * is any.
     *
     * @return True if there was a container to select, false otherwise.
     */
    public boolean selectLetterUnderCursor() {
        if (this.grid.hasContainerAt(x, y) && !this.selectedLetters.contains(this.getContainerUnderCursor())) {
            this.selectedLetters.add(this.grid.getContainerAt(x, y));
            return true;
        }
        return false;
    }

    /**
     * Removes letter container under cursor from selected containers if it was
     * there to begin with.
     */
    public void removeSelectionUnderCursor() {
        if (this.grid.hasContainerAt(x, y)) {
            LetterContainer container = this.grid.getContainerAt(x, y);
            if (this.selectedLetters.remove(container)) {
                if (container.isFromLetterPool()) {
                    this.grid.removeContainerAt(x, y);
                    this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
                }
            }
        }
    }
}
