package sanapuuro.sanapuuro;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission.
 *
 * @author skaipio@cs
 */
public class Player {

    private final Grid grid;
    private final LetterPool letterPool;
    private final WordEvaluator wordEval;
    private final List<LetterContainer> selectedContainers = new ArrayList<>();
    private final List<LetterContainer> addedContainers = new ArrayList<>();
    private boolean controlsEnabled = true;

    private String status = "";
    private int score = 0;

    public Player(Grid grid, WordEvaluator wordEvaluator, Letters letters) {
        this.grid = grid;
        this.letterPool = new LetterPool(letters);
        this.wordEval = wordEvaluator;
    }

    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    public String getStatus() {
        return this.status;
    }

    public int getScore() {
        return this.score;
    }
    
    public boolean isEnabled(){
        return this.controlsEnabled;
    }

    public List<LetterContainer> getSelectedContainers() {
        return new ArrayList<>(this.selectedContainers);
    }

    public List<LetterContainer> getAddedContainers() {
        return new ArrayList<>(this.addedContainers);
    }

    /**
     * Gets player's latest selection.
     * @return Latest selected letter container, null if none has been selected.
     */
    public LetterContainer getLastSelection() {
        if (!this.selectedContainers.isEmpty()) {
            return this.selectedContainers.get(this.selectedContainers.size() - 1);
        }
        return null;
    }

    /**
     * Gets player's first selection.
     * @return First selected letter container, null if none has been selected.
     */
    public LetterContainer getFirstSelection() {
        if (!this.selectedContainers.isEmpty()) {
            return this.selectedContainers.get(0);
        }
        return null;
    }

    /**
     * Disables all control methods if set to false. Enables them again if set to true.
     * @param enabled True for control enabling, false for disabling.
     */
    void setControlsEnabled(boolean enabled) {
        this.controlsEnabled = enabled;
    }

//    public boolean clearSelections() {
//        if (this.controlsEnabled) {
//            this.returnAllAddedLettersBackToLetterPool();
//            this.addedContainers.clear();
//            this.selectedContainers.clear();
//        }
//        return false;
//    }

    /**
     * Adds a letter container from the letter pool on the given location
     * location if there is no other container already present and adds it to
     * added containers.
     *
     * @param x Coordinate to set to.
     * @param y Coordinate to set to.
     * @return True if adding letter from the letter pool was successful, false
     * otherwise.
     */
    public boolean addLetterTo(int x, int y) {
        LetterContainer container = this.letterPool.useLetter();
        if (this.controlsEnabled && container != null) {

            boolean containerSet = this.grid.setContainerAt(container, x, y);
            if (!containerSet) {
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
            } else {
                this.addedContainers.add(container);
            }
            return containerSet;
        }
        return false;
    }

//    public boolean returnContainerToLetterPoolAt(int x, int y) {
//        if (this.controlsEnabled && this.grid.hasContainerAt(x, y)) {
//            LetterContainer container = this.grid.getContainerAt(x, y);
//            if (this.addedContainers.contains(container)) {
//                this.grid.removeContainer(container);
//                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Selects the letter from the given location and adds to selected
     * letter containers if there is any at the location.
     *
     * @param x Coordinate to set to.
     * @param y Coordinate to set to.
     * @return True if there was a container to select, false otherwise.
     */
    public boolean selectLetterAt(int x, int y) {
        if (this.controlsEnabled && this.grid.hasContainerAt(x, y)) {
            LetterContainer selection = this.grid.getContainerAt(x, y);
            if (!this.selectedContainers.contains(selection)) {
                this.selectedContainers.add(selection);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the latest selection from player's selected
     * letter containers if there is any.
     * @return True if controls are enabled and there were selected containers, false otherwise.
     */
    public boolean removeLastSelection() {
        if (this.controlsEnabled && !this.selectedContainers.isEmpty()) {
            int index = this.selectedContainers.size() - 1;
            LetterContainer container = this.selectedContainers.get(index);
            if (this.addedContainers.contains(container)) {
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
                this.grid.removeContainerAt(container.getX(), container.getY());
                this.addedContainers.remove(container);
            }
            this.selectedContainers.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Evaluates currently selected letters and raises the score if evaluation
     * was successful.
     *
     * @return True if letters formed a valid word, false otherwise.
     */
    public boolean submitSelectedLetters() {
        if(!this.controlsEnabled){
            return false;
        }
        if (this.selectedContainers.isEmpty()) {
            this.status = "No letters have been selected!";
            return false;
        }
        List<LetterContainer> letterContainers = this.selectedContainers;
        WordEvaluator.EvaluationResult result = this.wordEval.evalute(letterContainers);
        this.status = result.reason;

        if (result.succeeded) {
            this.score += result.getScore();

            this.setSelectedLettersToGridPermanently();
            this.letterPool.replacePickedLetters();

            this.clearSelectionsAndAdditions();
            return true;
        }

        this.returnAllAddedLettersBackToLetterPool();
        this.clearSelectionsAndAdditions();
        return false;
    }

    private void setSelectedLettersToGridPermanently() {
        this.grid.setLettersToGridPermanently(this.selectedContainers);
        this.letterPool.replacePickedLetters();
    }

    private void clearSelectionsAndAdditions() {
        this.addedContainers.clear();
        this.selectedContainers.clear();
    }

    private void returnAllAddedLettersBackToLetterPool() {
        this.grid.removeContainersFromGrid(this.addedContainers);
        this.letterPool.clearLetterPicks();
    }
}
