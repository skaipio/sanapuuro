/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import sanapuuro.sanapuuro.grid.GridCursor;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.gui.GridCell;
import sanapuuro.sanapuuro.gui.LetterGridPanel;
import sanapuuro.sanapuuro.gui.LetterPoolCell;
import sanapuuro.sanapuuro.gui.LetterPoolPanel;
import sanapuuro.sanapuuro.gui.SubmitButton;
import sanapuuro.sanapuuro.letters.LetterPool;

/**
 * Updates game logic instances and GUI instances according to input.
 * Resembles the presenter in MVP pattern.
 * @author skaipio
 */
public class GamePresenter implements MouseListener, ActionListener {

    private final Game game = new Game();
    private JLabel selectedLettersLabel;
    private JButton submitButton;
    private LetterGridPanel letterGridPanel;
    private LetterPoolPanel letterPoolPanel;
    private JLabel scoreLabel;
    private JLabel stateLabel;

    private GridCursor gridCursor;
    private LetterPool letterPool;

    /**
     * Starts a new game and prepares the views.
     */
    public void newGame() {
        this.game.newGame();
        this.gridCursor = this.game.getGridCursor();
        this.letterPool = this.game.getLetterPool();
        this.letterPoolPanel.init(this.letterPool.poolSize);
        this.letterPoolPanel.addListenerToCells(this);
        this.updateLetterPoolPanel();
    }

    public void setSelectedLettersLabel(JLabel label) {
        this.selectedLettersLabel = label;
    }

    public void setSubmitButton(JButton button) {
        this.submitButton = button;
        this.submitButton.addActionListener(this);
    }

    public void setLetterPoolPanel(LetterPoolPanel letterPoolPanel) {
        this.letterPoolPanel = letterPoolPanel;
    }

    public void setLetterGridPanel(LetterGridPanel letterGridPanel) {
        this.letterGridPanel = letterGridPanel;
        this.letterGridPanel.addListenerToCells(this);
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void setStateLabel(JLabel label) {
        this.stateLabel = label;
    }

    /**
     * Handles mouse click events from views.
     * @param e Event from one of the controller's views.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getComponent() instanceof LetterPoolCell) {
            LetterPoolCell cell = (LetterPoolCell) e.getComponent();
            this.leftClickLetterPoolCell(cell);
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getComponent() instanceof GridCell) {           
            GridCell cell = (GridCell) e.getComponent();
            System.out.println(e.getComponent().getClass() + " clicked at " + cell.x + "," + cell.y);
            this.leftClickGridCell(cell);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            GridCell cell = (GridCell) e.getComponent();
            System.out.println(e.getComponent().getClass() + " clicked at " + cell.x + "," + cell.y);
            this.rightClickGridCell(cell);
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

    /**
     * Handles only the submit button event.
     * @param e Button event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof SubmitButton) {
            List<LetterContainer> selectedContainers = this.gridCursor.getSelectedContainers();
            if (this.gridCursor.submitLetters()) {
                this.scoreLabel.setText(this.game.getScore() + "");
                this.updateLetterPoolPanel();
                this.deselectCells(selectedContainers);
                this.selectedLettersLabel.setText("");
            }
            this.stateLabel.setText(this.game.getStatus());
        }
    }

    private void updateLetterPoolPanel() {
        for (LetterContainer container : this.letterPool.getLetters()) {
            this.letterPoolPanel.setLetterToCell(container.letter.toString(), container.letterPoolIndex());
        }
    }

    private void deselectCells(List<LetterContainer> selectedContainers) {
        System.out.print("Deselecting: ");
        for (LetterContainer container : selectedContainers) {
            System.out.print(container.letter + " ");
            this.letterGridPanel.setCellSelectionAt(false, container.getX(), container.getY());
        }
    }

    private void leftClickLetterPoolCell(LetterPoolCell cell) {
        this.letterPool.setCurrentSelection(cell.index);
        this.letterPoolPanel.changeCurrentSelectionTo(cell.index);
    }

    private void leftClickGridCell(GridCell cell) {
        this.gridCursor.setLocation(cell.x, cell.y);
        if (this.gridCursor.selectLetterUnderCursor()) {
            cell.select();
            this.updateSelectedLettersLabel();
        } else if (this.gridCursor.addLetterUnderCursor()) {
            cell.select();
            this.letterPoolPanel.grayOutLetter(this.letterPool.getCurrentSelectedIndex());
            String letter = this.gridCursor.getLetterUnderCursor().toString();
            this.letterGridPanel.setLetterToCell(letter, this.gridCursor.getX(), this.gridCursor.getY());
            this.updateSelectedLettersLabel();
        }
    }

    private void rightClickGridCell(GridCell cell) {
        this.gridCursor.setLocation(cell.x, cell.y);
        LetterContainer container = this.gridCursor.removeSelectionUnderCursor();
        if (container != null) {
            if(container.isFromLetterPool()){
                cell.removeLetter();
                this.letterPoolPanel.letterReturnedToPool(container.letterPoolIndex());
            }          
            cell.deselect();
            this.updateSelectedLettersLabel();
        }
    }

    private void updateSelectedLettersLabel() {
        List<LetterContainer> selectedLetters = this.gridCursor.getSelectedContainers();
        StringBuilder letters = new StringBuilder(selectedLetters.size());
        for (LetterContainer lc : selectedLetters) {
            letters.append(lc.letter.character);
        }
        this.selectedLettersLabel.setText(letters.toString());
    }

}
