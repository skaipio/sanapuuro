package sanapuuro.sanapuuro;

import java.util.List;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 *
 * @author skaipio@cs
 */
public class Player {

    private String status;
    private GridCursor cursor;
    private int score = 0;
    private WordEvaluator wordEval;

    public Player(GridCursor cursor, WordEvaluator wordEvaluator) {
        this.cursor = cursor;
        this.wordEval = wordEvaluator;
    }
    
    public GridCursor getControlledCursor(){
        return this.cursor;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public int getScore(){
        return this.score;
    }

    /**
     * Evaluates the given letters and raises the score if evaluation was
     * successful.
     *
     * @return True if letters formed a valid word, false otherwise.
     */
    public boolean submitSelectedLetters() {
        List<LetterContainer> letterContainers = this.cursor.getSelectedContainers();
        System.out.print("Submitting letters: ");
        for (LetterContainer letter : letterContainers) {
            System.out.print(letter.letter);
        }
        System.out.println("");
        WordEvaluator.EvaluationResult result = this.wordEval.evalute(letterContainers);
        this.status = result.reason;
        if (result.succeeded) {
            this.score += result.getScore();
            
            this.cursor.setSelectedLettersToGridPermanently();
            return true;
        }
        return false;
    }
}
