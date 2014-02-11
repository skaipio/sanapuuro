package sanapuuro.sanapuuro;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 * Keeps track of score and gives access to a controllable letter grid cursor.
 *
 * @author skaipio@cs
 */
public class Player {

    private final Grid grid;
    private final LetterPool letterPool;
    private final WordEvaluator wordEval;
    private final List<LetterContainer> selectedContainers = new ArrayList<>();
    private final List<LetterContainer> addedContainers = new ArrayList<>();

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

    public List<LetterContainer> getSelectedContainers() {
        return new ArrayList<>(this.selectedContainers);
    }

    public List<LetterContainer> getAddedContainers() {
        return new ArrayList<>(this.addedContainers);
    }
    
    public LetterContainer getLastSelection(){
        if (!this.selectedContainers.isEmpty()){
            return this.selectedContainers.get(this.selectedContainers.size()-1);
        }
        return null;
    }
    
    public LetterContainer getFirstSelection(){
        if (!this.selectedContainers.isEmpty()){
            return this.selectedContainers.get(0);
        }
        return null;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void clearSelections() {
        this.returnAllAddedLettersBackToLetterPool();
        this.addedContainers.clear();
        this.selectedContainers.clear();
    }

    private void returnAllAddedLettersBackToLetterPool() {
        this.grid.removeContainersFromGrid(this.addedContainers);
        this.letterPool.clearLetterPicks();
    }

    /**
     * Adds a letter container from the letter pool on the current cursor
     * location if there is no other container already present and adds it to
     * selected containers.
     *
     * @return True if adding letter from the letter pool was successful, false
     * otherwise.
     */
    public boolean addLetterTo(int x, int y) {
        LetterContainer container = this.letterPool.useLetter();
        if (container != null) {

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

    public boolean returnContainerToLetterPoolAt(int x, int y) {
        if (this.grid.hasContainerAt(x, y)) {
            LetterContainer container = this.grid.getContainerAt(x, y);
            if (this.addedContainers.contains(container)) {
                this.grid.removeContainerAt(x, y);
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
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
    public boolean selectLetterAt(int x, int y) {
        if (this.grid.hasContainerAt(x, y)) {
            LetterContainer selection = this.grid.getContainerAt(x, y);
            if (!this.selectedContainers.contains(selection)) {
                this.selectedContainers.add(selection);
                return true;
            }
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
//    public boolean removeSelectionAndAdditionAt(int x, int y) {
//        if (this.grid.hasContainerAt(x, y)) {
//            LetterContainer container = this.grid.getContainerAt(x, y);
//            return this.selectedContainers.remove(container);
//        }
//        return false;
//    }
    
    public boolean removeLastSelection() {
        if (!this.selectedContainers.isEmpty()){
            int index = this.selectedContainers.size()-1;
            LetterContainer container = this.selectedContainers.get(index);           
            if (this.addedContainers.contains(container)){
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
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

    /**
     * Sets the currently selected letters to the grid permanently and also
     * removes them from the player's letter pool.
     */
    private void setSelectedLettersToGridPermanently() {
        this.grid.setLettersToGridPermanently(this.selectedContainers);
        //this.addedContainers.removeAll(this.selectedContainers);
        this.letterPool.replacePickedLetters();
    }

    private void clearSelectionsAndAdditions() {
        this.addedContainers.clear();
        this.selectedContainers.clear();
    }
}
