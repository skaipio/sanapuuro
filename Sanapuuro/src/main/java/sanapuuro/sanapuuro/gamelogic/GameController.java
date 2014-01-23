/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gamelogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterReader;

/**
 *
 * @author skaipio
 */
public class GameController {
    private final Game game;
    private final Map<Coordinate, Letter> selectedLetters = new HashMap<>();
    private boolean inSelectionMode = false;
    private Coordinate currentSelection;
    private char[][] testView;
    
    private final Scanner scanner = new Scanner(System.in);

    public GameController() {
        this.game = new Game();
    }
    
    public void newGame(){
        LetterReader reader = new LetterReader();
        this.game.newGame(reader.getLetters());
        this.testView = new char[12][12];
        while (true) {
            this.drawGrid();
            System.out.println("(w for up, s for down, a for left, d for right)");
            this.processCommand(scanner.nextLine());
        }
    }
    
    private void processCommand(String command){
        switch(command){
            case "w":
                this.game.getGridCursor().moveUp();
                break;
            case "s":
                this.game.getGridCursor().moveDown();
                break;
            case "a":
                this.game.getGridCursor().moveLeft();
                break;
            case "d":
                this.game.getGridCursor().moveRight();
                break;
                
        }
    }
    
     private void drawGrid() {
        for (int y = 0; y < this.game.getGridHeight(); y++) {
            for (int x = 0; x < this.game.getGridWidth(); x++) {
                Letter letter = this.game.getLetterAt(x, y);
                if (letter != null)
                    this.testView[y][x] = letter.character;
                else{
                    this.testView[y][x] = '.';
                }
            }
        }
        this.testView[this.game.getGridCursor().getY()][this.game.getGridCursor().getX()] = 'x';
        for (int y = this.game.getGridHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < this.game.getGridWidth(); x++) {
                System.out.print(this.testView[y][x]);
            }
            System.out.println("");
        }
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
