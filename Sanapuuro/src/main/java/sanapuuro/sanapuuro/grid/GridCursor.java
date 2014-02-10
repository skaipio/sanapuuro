/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import java.util.List;
import sanapuuro.sanapuuro.utils.MathUtils;

/**
 *
 * @author skaipio
 */
public class GridCursor {

    private int x, y;
    private final Grid grid;

    public GridCursor(Grid grid) {
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
     * Returns a list of selected letters.
     *
     * @return The list of selected letters if there are any and empty list
     * otherwise.
     */
//    public List<LetterContainer> getSelectedContainers() {
//        List<LetterContainer> letters = new ArrayList<>(this.selectedLetters);
//        return letters;
//    }

    public boolean hasContainerUnderCursor() {
        return this.grid.hasContainerAt(x, y);
    }

    public LetterContainer getContainerUnderCursor() {
        return this.grid.getContainerAt(x, y);
    }

    public void removeLetterPoolContainersFromGrid(List<LetterContainer> containers) {
        for (LetterContainer container : containers) {
            if (container.isFromLetterPool()) {
                this.grid.removeContainerAt(container.getX(), container.getY());
            }
        }
    }

    /**
     * Adds a letter container from the letter pool on the current cursor
     * location if there is no other container already present and adds it to
     * selected containers.
     *
     * @return True if adding letter from the letter pool was successful, false
     * otherwise.
     */
    public boolean addLetterUnderCursor(LetterContainer container) {
        return this.grid.setContainerAt(container, x, y);
    }
}
