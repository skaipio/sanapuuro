/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import sanapuuro.sanapuuro.letters.LetterContainer;
import java.util.ArrayList;
import java.util.List;
import sanapuuro.sanapuuro.letters.Letter;
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
    private final List<GridCursorListener> listeners = new ArrayList<>();

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

    public void setLocation(int x, int y) {
        if (!this.grid.isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given position is not within grid.");
        }
        this.x = x;
        this.y = y;
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

    public List<LetterContainer> clearSelectedLetters() {
        List<LetterContainer> letters = new ArrayList<>(this.selectedLetters);
        this.selectedLetters.clear();
        return letters;
    }

    public List<LetterContainer> getSelectedLetters() {
        List<LetterContainer> letters = new ArrayList<>(this.selectedLetters);
        return letters;
    }

    public void submitLetters() {
        List<LetterContainer> letters = this.clearSelectedLetters();
        for (GridCursorListener listener : this.listeners) {
            listener.lettersSubmitted(letters);
        }
    }

    public Letter getLetterUnderCursor() {
        return this.grid.getCellAt(x, y).getContainer().letter;
    }

    public boolean addLetterUnderCursor() {
        LetterCell cell = this.grid.getCellAt(x, y);
        if (!cell.hasContainer()) {
            LetterContainer container = this.letterPool.useLetter();
            if (container != null) {
                container.setX(x);
                container.setY(y);
                this.grid.getCellAt(x, y).setContainer(container);
                this.selectedLetters.add(container);
                return true;
            }
        }
        return false;
    }

    public boolean selectLetterUnderCursor() {
        LetterCell cell = this.grid.getCellAt(x, y);
        if (cell.hasContainer() && !cell.getContainer().isFromLetterPool()) {
            this.selectedLetters.add(cell.getContainer());
            return true;
        }
        return false;
    }

    public LetterContainer removeSelectionUnderCursor() {
        LetterCell cell = this.grid.getCellAt(x, y);
        if (cell.hasContainer()) {
            LetterContainer container = cell.getContainer();
            if (this.selectedLetters.remove(cell.getContainer())){
                this.grid.getCellAt(x, y).clear();
                if (container.isFromLetterPool()){
                    this.letterPool.returnPickedLetter(container.letterPoolIndex());
                }
                return container;
            }
        }
        return null;
    }

    public void addListener(GridCursorListener listener) {
        this.listeners.add(listener);
    }
}
