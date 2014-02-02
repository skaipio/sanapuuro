/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import sanapuuro.sanapuuro.Game;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.gui.GridCell;
import sanapuuro.sanapuuro.gui.LetterGridPanel;
import sanapuuro.sanapuuro.gui.LetterPoolCell;
import sanapuuro.sanapuuro.gui.LetterPoolPanel;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterPoolListener;

/**
 *
 * @author skaipio
 */
public class GameController implements MouseListener, LetterPoolListener {

    private final Game game;
    private LetterGridPanel letterGridPanel;
    private LetterPoolPanel letterPoolPanel;
    private JLabel stateLabel;
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
    
    public void setLetterGridPanel(LetterGridPanel letterGridPanel){
        this.letterGridPanel = letterGridPanel;
        this.letterGridPanel.addListenerToCells(this);
    }
    
    public void setStateLabel(JLabel label){
        this.stateLabel = label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent().getClass());
        if (e.getComponent() instanceof LetterPoolCell) {
            LetterPoolCell cell = (LetterPoolCell) e.getComponent();
            this.letterPool.setCurrentSelection(cell.index);
            this.letterPoolPanel.changeCurrentSelectionTo(cell.index);
        }else if(SwingUtilities.isLeftMouseButton(e) && e.getComponent() instanceof GridCell){
            GridCell cell = (GridCell) e.getComponent();
            this.gridCursor.setLocation(cell.x, cell.y);
            if (this.gridCursor.addLetterUnderCursor()){
                this.letterPoolPanel.grayOutLetter(this.letterPool.getCurrentSelection());
                this.setSelectedLettersToGrid(this.gridCursor.getSelectedLetters());
            }
        }else if (SwingUtilities.isRightMouseButton(e)){
            if (!this.gridCursor.isSelectionModeOn()){
                this.gridCursor.selectionModeOn();
                this.stateLabel.setText("Press left mouse button to add letters to grid.");
            }
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
    
    private void setSelectedLettersToGrid(List<LetterContainer> letterContainers){
        System.out.println("Setting selected letters to grid");
        for(LetterContainer lc : letterContainers){
            this.letterGridPanel.setLetterToCell(lc.letter.toString(), lc.x, lc.y);
        }
    }
}
