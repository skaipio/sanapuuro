/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import sanapuuro.sanapuuro.Game;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterPoolListener;

/**
 *
 * @author skaipio
 */
public class GameController implements MouseListener, LetterPoolListener {

    private final Game game;
    private LetterPoolPanel letterPoolPanel;
    private GridCursor gridCursor;
    private LetterPool letterPool;

    public GameController(Game game) {
        this.game = game;
    }
    
    public void newGame(){
        this.game.newGame();  
        this.gridCursor = this.game.getGridCursor();
        this.letterPool = this.game.getLetterPool();        
        this.letterPoolPanel.init(this.letterPool.poolSize);
        this.letterPool.addListener(this);
        this.letterPoolPanel.addListenerToCells(this);
    }

    public void setLetterPoolPanel(LetterPoolPanel letterPoolPanel) {
        this.letterPoolPanel = letterPoolPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof LetterPoolCell) {
            LetterPoolCell cell = (LetterPoolCell) e.getComponent();
            this.letterPool.setCurrentSelection(cell.index);
            this.letterPoolPanel.changeCurrentSelectionTo(cell.index);
        }else if(e.getComponent() instanceof GridCell){
            GridCell cell = (GridCell) e.getComponent();
            this.gridCursor.setLocation(cell.x, cell.y);
            this.gridCursor.addLetterUnderCursor();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void letterPoolChanged(Letter[] letters) {
        for (int i = 0; i < letters.length; i++) {
            this.letterPoolPanel.setLetterToCell(letters[i].toString(), i);
            this.letterPoolPanel.repaint();
        }
    }
}
