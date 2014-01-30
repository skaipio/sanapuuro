/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.utils.MathUtils;

/**
 *
 * @author skaipio
 */
public class GridCursor {

    private int x, y;
    private final Grid grid;
    private final List<LetterCell> selectedCells = new ArrayList<>();
    private final List<GridCursorListener> listeners = new ArrayList<>();

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
    
    public void setLocation(int x, int y){
        if (!this.grid.isWithinGrid(x, y))
            throw new IllegalArgumentException("Given position is not within grid.");
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

    public void selectionModeOn() {
        this.selectionMode = true;
    }

    public List<LetterContainer> selectionModeOff() {
        this.selectionMode = false;
        List<LetterContainer> letters = new ArrayList<>();
        for(LetterCell cell : this.selectedCells){
            letters.add(cell);
            cell.setSelected(false);
        }
        this.selectedCells.clear();
        return letters;
    }
    
    public List<LetterContainer> getSelectedLetters(){
        List<LetterContainer> letters = new ArrayList<>();
        for(LetterCell cell : this.selectedCells){
            letters.add(cell);
        }
        return letters;
    }
    
    public void submitLetters(){
        List<LetterContainer> letters = this.selectionModeOff();
        for(GridCursorListener listener : this.listeners){
            listener.lettersSubmitted(letters);
        }
    }
    
//    public boolean hasLetterUnderCursor() {
//        return this.grid.getCellAt(x, y).hasLetter();
//    }

    public Letter getLetterUnderCursor() {
        return this.grid.getCellAt(x, y).getLetter();
    }

    public boolean addLetterUnderCursor(Letter letter) {
        LetterCell cell = this.grid.getCellAt(x, y);
        if (this.selectionMode && !cell.hasLetter()) {
            cell.setLetter(letter);
            cell.setSelected(true);
            this.selectedCells.add(cell);
            return true;
        }
        return false;
    }
    
    public boolean selectLetterUnderCursor(){  
        LetterCell cell = this.grid.getCellAt(x, y);
        if (this.selectionMode && cell.hasLetter()){
            this.selectedCells.add(cell);
            cell.isSelected = true;
            return true;
        }
        return false;
    }
    
    public void addListener(GridCursorListener listener){
        this.listeners.add(listener);
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
