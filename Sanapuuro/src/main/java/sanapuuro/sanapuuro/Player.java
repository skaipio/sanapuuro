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

    private String status = "";
    private Grid grid;
    private final LetterPool letterPool;
    private final List<LetterContainer> selectedContainers = new ArrayList<>();
    private final List<LetterContainer> addedContainers = new ArrayList<>();
    private Direction selectionDirection;

    private int score = 0;
    private final WordEvaluator wordEval;

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
    
    public List<LetterContainer> getSelectedContainers(){
        return this.selectedContainers;
    }
    
    public List<LetterContainer> getAddedContainers(){
        return this.addedContainers;
    }

    public void resetScore() {
        this.score = 0;
    }

    /**
     * Clears all selections.
     *
     * @return The list of selected letters if there were any and empty list
     * otherwise.
     */
    public List<LetterContainer> clearSelections() {
        List<LetterContainer> containers = new ArrayList<>(this.selectedContainers);
        this.selectedContainers.clear();
        return containers;
    }

    public List<LetterContainer> returnAllAddedLettersBackToLetterPool() {
        List<LetterContainer> containers = new ArrayList<>(this.addedContainers);
        this.grid.removeLetterPoolContainersFromGrid(this.addedContainers);

        for (LetterContainer container : this.addedContainers) {
            this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
        }
        this.addedContainers.clear();
        return containers;
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
            }
            return containerSet;
        }
        return false;
    }

    public boolean returnContainerToLetterPoolAt(int x, int y) {
        if (this.grid.hasContainerAt(x, y)) {
            LetterContainer container = this.grid.getContainerAt(x, y);
            if (container.isFromLetterPool()) {
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
            this.selectedContainers.add(selection);
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
    public boolean removeSelectionAt(int x, int y) {
        if (this.grid.hasContainerAt(x, y)) {
            LetterContainer container = this.grid.getContainerAt(x, y);
            return this.selectedContainers.remove(container);
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
        if (this.selectedContainers.isEmpty()){
            this.status = "No letters have been selected!";
            return false;
        }
        List<LetterContainer> letterContainers = this.selectedContainers;
        WordEvaluator.EvaluationResult result = this.wordEval.evalute(letterContainers);
        this.status = result.reason;
        if (result.succeeded) {
            this.score += result.getScore();

            this.setSelectedLettersToGridPermanently();
            return true;
        }
        return false;
    }
    
    /**
     * Sets the currently selected letters to the grid permanently and also
     * removes them from the player's letter pool.
     */
    private void setSelectedLettersToGridPermanently() {
        this.grid.setLettersToGridPermanently(this.selectedContainers);
        this.addedContainers.removeAll(this.selectedContainers);
        this.letterPool.replacePickedLetters();
        this.selectedContainers.clear();
    }

    private void setSelectionDirection() {
//        if (!this.selectedLetters.isEmpty()) {
//            LetterContainer previous = this.selectedLetters.get(this.selectedLetters.size() - 1);
//            this.selectionDirection = Direction.getDirection(previous.getX(), previous.getY(), this.cursor.getX(), this.cursor.getY());
//        }
    }

    private boolean canSelectContainerUnderCursor() {

        if (this.selectedContainers.isEmpty()) {
            return true;
        }
        if (this.selectedContainers.size() == 1) {
            return this.selectionIsNextToPreviouslySelectedContainer();
        }

        if (this.selectionDirection != null) {
            return this.selectionIsAligned();
        }
        return false;
    }

    private boolean selectionIsNextToPreviouslySelectedContainer() {
        return true;
//        LetterContainer previouslySelected = this.selectedLetters.get(this.selectedLetters.size() - 1);
//        int deltaX = Math.abs(x - previouslySelected.getX());
//        int deltaY = Math.abs(y - previouslySelected.getY());
//        return (!(deltaX >= 1 && deltaY >= 1) && (deltaX == 1 || deltaY == 1));
    }

    private boolean selectionIsAligned() {
        return true;
//        LetterContainer previous = this.selectedLetters.get(this.selectedLetters.size() - 1);
//        switch (this.selectionDirection) {
//            case Left:
//                return previous.getX() + Direction.Left.deltaX == x && previous.getY() == y;
//            case Right:
//                return previous.getX() + Direction.Right.deltaX == x && previous.getY() == y;
//            case Up:
//                return previous.getY() + Direction.Up.deltaY == y && previous.getX() == x;
//            case Down:
//                return previous.getY() + Direction.Down.deltaY == y && previous.getX() == x;
//        }
//        return false;
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
