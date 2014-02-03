/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro;

import java.util.List;
import java.util.Random;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.grid.GridCursorListener;
import sanapuuro.sanapuuro.grid.LetterCell;
import sanapuuro.sanapuuro.letters.LetterContainer;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterReader;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 *
 * @author skaipio
 */
class Game implements GridCursorListener {

        private int score;
        private final Grid grid;
        private GridCursor cursor;
        private LetterPool letterPool;
        private Letters letters;
        private WordEvaluator wordEval;

        public Game() {
            this.grid = new Grid(12, 12);
        }

        public void newGame() {
            this.score = 0;
            this.grid.clear();
            this.letters = new LetterReader(new Random());
            this.wordEval = new WordEvaluator();
            this.letterPool = new LetterPool(letters);
            this.cursor = new GridCursor(this.grid, this.letterPool);
        }

        public GridCursor getGridCursor() {
            return this.cursor;
        }

        public LetterPool getLetterPool() {
            return this.letterPool;
        }

        public int getGridWidth() {
            return this.grid.width;
        }

        public int getGridHeight() {
            return this.grid.height;
        }

        public LetterContainer getLetterContainerAt(int x, int y) {
            return this.grid.getCellAt(x, y).getContainer();
        }

        @Override
        public void lettersSubmitted(List<LetterContainer> letterContainers) {
            if (this.wordEval.isValidWord(letterContainers)) {
                this.score += this.wordEval.evaluteLetters(letterContainers);
                for (LetterContainer container : letterContainers) {
                    LetterCell cell = this.grid.getCellAt(container.getX(), container.getY());
                    cell.setContainer(container);
                }
            }
        }
    }