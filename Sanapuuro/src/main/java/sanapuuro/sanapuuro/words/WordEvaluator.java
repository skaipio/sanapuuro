/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.utils.LetterCoordinateComparator;

/**
 *
 * @author skaipio
 */
public class WordEvaluator {

    private final int wordLengthMinimum = 3;
    private final WordList wordValidator = new WordReader();

    public EvaluationResult evalute(List<LetterContainer> letterContainers) {
        if (letterContainers == null || letterContainers.isEmpty()) {
            throw new IllegalArgumentException("No letters were given for validation.");
        }
        if (letterContainers.size() < wordLengthMinimum) {
            return new EvaluationResult(false, "Word must be at least " + this.wordLengthMinimum + " characters long");
        }

        List<LetterContainer> containerCopy = new ArrayList(letterContainers);
        if (allContainersOnSameColumnWithoutGaps(containerCopy) || allContainersOnSameRowWithoutGaps(containerCopy)) {
            StringBuilder word = new StringBuilder(letterContainers.size());
            boolean allUsed = true;
            for (LetterContainer container : letterContainers) {
                if (!container.hasBeenUsed()) {
                    allUsed = false;
                }
                word.append(container.letter.character);
            }
            if (!allUsed) {
                if (wordValidator.hasWord(word.toString())) {
                    int score = this.evaluteLetters(letterContainers);
                    return new EvaluationResult(true, "Score for word " + word.toString().toUpperCase() + ": " + score, score);
                }else{
                    return new EvaluationResult(false, word.toString().toUpperCase() + " is not a valid English word.");
                }
            } else {
                return new EvaluationResult(false, "Word must have at least one letter not used before.");
            }
        }
        return new EvaluationResult(false, "Word must be on the same column or row and should not have gaps.");
    }

    private int evaluteLetters(List<LetterContainer> letterContainers) {
        int score = 0;
        for (LetterContainer container : letterContainers) {
            score += container.letter.score;
        }
        return score;
    }

    private boolean allContainersOnSameRowWithoutGaps(List<LetterContainer> letterContainers) {
        Collections.sort(letterContainers, new LetterCoordinateComparator(false));
        for (int i = 1; i < letterContainers.size(); i++) {
            LetterContainer previous = letterContainers.get(i - 1);
            LetterContainer current = letterContainers.get(i);
            if (current.getY() != previous.getY() || (current.getX() - previous.getX()) > 1) {
                return false;
            }
        }
        return true;
    }

    private boolean allContainersOnSameColumnWithoutGaps(List<LetterContainer> letterContainers) {
        Collections.sort(letterContainers, new LetterCoordinateComparator(true));
        for (int i = 1; i < letterContainers.size(); i++) {
            LetterContainer previous = letterContainers.get(i - 1);
            LetterContainer current = letterContainers.get(i);
            if (current.getX() != previous.getX() || (current.getY() - previous.getY()) > 1) {
                return false;
            }
        }
        return true;
    }

    public static class EvaluationResult {

        public final boolean succeeded;
        public final String reason;
        private int score;

        EvaluationResult(boolean succeeded, String reason, int score) {
            this(succeeded, reason);
            this.score = score;
        }

        EvaluationResult(boolean succeeded, String reason) {
            this.succeeded = succeeded;
            this.reason = reason;
        }

        public int getScore() {
            return this.score;
        }
    }
}
