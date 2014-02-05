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
    private Direction selectionDirection;

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
                this.grid.removeContainerAt(container.getX(), container.getY());
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
            }
        }
        this.selectionDirection = null;
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

    /**
     * Sets the currently selected letters to the grid permanently and also
     * removes them from the player's letter pool.
     */
    public void setSelectedLettersToGridPermanently() {
        for (LetterContainer container : this.selectedLetters) {
            container.setToGridPermanently();
        }
        this.letterPool.replacePickedLetters();
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
     * @return True if adding letter from the letter pool was successful, false
     * otherwise.
     */
    public boolean addLetterUnderCursor() {
        if (!this.hasContainerUnderCursor() && this.canSelectContainerUnderCursor()) {
            LetterContainer container = this.letterPool.useLetter();
            if (container != null) {
                this.setSelectionDirection();
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
        if (this.hasContainerUnderCursor() && this.canSelectContainerUnderCursor()) {
            LetterContainer selection = this.getContainerUnderCursor();
            this.setSelectionDirection();
            this.selectedLetters.add(selection);
            return true;
        }
        return false;
    }

    /**
     * Removes letter container under cursor from selected containers if it was
     * there to begin with.
     *
     * @return True, if there was a letter container from a letter pool to
     * remove, false otherwise.
     */
    public boolean removeSelectionUnderCursor() {
        if (this.grid.hasContainerAt(x, y)) {
            LetterContainer container = this.grid.getContainerAt(x, y);
            if ((container == this.selectedLetters.get(0)
                    || container == this.selectedLetters.get(this.selectedLetters.size() - 1))
                    && this.selectedLetters.remove(container)) {
                if (container.isFromLetterPool()) {
                    this.grid.removeContainerAt(x, y);
                    this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
                }
                if (this.selectedLetters.size() == 1) {
                    this.selectionDirection = null;
                }
                return true;
            }
        }
        return false;
    }

    private void setSelectionDirection() {
        if (!this.selectedLetters.isEmpty()) {
            LetterContainer previous = this.selectedLetters.get(this.selectedLetters.size() - 1);
            this.selectionDirection = Direction.getDirection(previous.getX(), previous.getY(), x, y);
        }
    }

    private boolean canSelectContainerUnderCursor() {

        if (this.selectedLetters.isEmpty()) {
            return true;
        }
        if (this.selectedLetters.size() == 1) {
            return this.selectionIsNextToPreviouslySelectedContainer();
        }

        if (this.selectionDirection != null) {
            return this.selectionIsAligned();
        }
        return false;
    }

    private boolean selectionIsNextToPreviouslySelectedContainer() {
        LetterContainer previouslySelected = this.selectedLetters.get(this.selectedLetters.size() - 1);
        int deltaX = Math.abs(x - previouslySelected.getX());
        int deltaY = Math.abs(y - previouslySelected.getY());
        return (!(deltaX >= 1 && deltaY >= 1) && (deltaX == 1 || deltaY == 1));
    }

    private boolean selectionIsAligned() {
        LetterContainer previous = this.selectedLetters.get(this.selectedLetters.size() - 1);
        switch (this.selectionDirection) {
            case Left:
                return previous.getX() + Direction.Left.deltaX == x && previous.getY() == y;
            case Right:
                return previous.getX() + Direction.Right.deltaX == x && previous.getY() == y;
            case Up:
                return previous.getY() + Direction.Up.deltaY == y && previous.getX() == x;
            case Down:
                return previous.getY() + Direction.Down.deltaY == y && previous.getX() == x;
        }
        return false;
    }

    /**
     * A utility class used in determining whether selected letter containers
     * are aligned.
     */
    private static enum Direction {

        Left(-1, 0), Right(1, 0), Up(0, -1), Down(0, 1);

        public final int deltaX, deltaY;

        public static Direction getDirection(int fromX, int fromY, int toX, int toY) {
            int dX = toX - fromX;
            if (dX > 0) {
                return Right;
            }
            if (dX < 0) {
                return Left;
            }
            int dY = toY - fromY;
            if (dY > 0) {
                return Down;
            } else {
                return Up;
            }
        }

        private Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
}
