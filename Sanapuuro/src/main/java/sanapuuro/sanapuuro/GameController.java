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
import sanapuuro.sanapuuro.letters.LetterContainer;
import sanapuuro.sanapuuro.gui.GridCell;
import sanapuuro.sanapuuro.gui.LetterGridPanel;
import sanapuuro.sanapuuro.gui.LetterPoolCell;
import sanapuuro.sanapuuro.gui.LetterPoolPanel;
import sanapuuro.sanapuuro.gui.SubmitButton;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.LetterPoolListener;

/**
 *
 * @author skaipio
 */
public class GameController implements MouseListener, ActionListener, LetterPoolListener {

    private final Game game = new Game();
    private JLabel selectedLettersLabel;
    private JButton submitButton;
    private LetterGridPanel letterGridPanel;
    private LetterPoolPanel letterPoolPanel;
    private JLabel stateLabel;
    private GridCursor gridCursor;
    private LetterPool letterPool;

    public void newGame() {
        this.game.newGame();
        this.gridCursor = this.game.getGridCursor();
        this.letterPool = this.game.getLetterPool();
        this.letterPoolPanel.init(this.letterPool.poolSize);
        this.letterPool.addListener(this);
        this.letterPoolPanel.addListenerToCells(this);
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

    public void setStateLabel(JLabel label) {
        this.stateLabel = label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent().getClass());
        if (e.getComponent() instanceof LetterPoolCell) {
            LetterPoolCell cell = (LetterPoolCell) e.getComponent();
            this.letterPool.setCurrentSelection(cell.index);
            this.letterPoolPanel.changeCurrentSelectionTo(cell.index);
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getComponent() instanceof GridCell) {
            GridCell cell = (GridCell) e.getComponent();
            this.leftClickGridCell(cell);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            LetterContainer container = this.gridCursor.removeSelectionUnderCursor();
            if (container != null) {
                this.letterGridPanel.removeLetterFromCell(this.gridCursor.getX(), this.gridCursor.getY());
                this.letterPoolPanel.letterReturnedToPool(container.letterPoolIndex());
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof SubmitButton) {
            this.gridCursor.submitLetters();
        }
    }

    @Override
    public void letterPoolChanged(LetterContainer[] letters) {
        for (int i = 0; i < letters.length; i++) {
            this.letterPoolPanel.setLetterToCell(letters[i].letter.toString(), i);
            this.letterPoolPanel.repaint();
        }
    }

    private void setSelectedLettersToGrid(List<LetterContainer> letterContainers) {
        System.out.println("Setting selected letters to grid");
        for (LetterContainer lc : letterContainers) {
            this.letterGridPanel.setLetterToCell(lc.letter.toString(), lc.getX(), lc.getY());
        }
    }

    private void leftClickGridCell(GridCell cell) {
        this.gridCursor.setLocation(cell.x, cell.y);
        if (this.gridCursor.selectLetterUnderCursor()) {
            cell.select();
            this.updateSelectedLettersLabel();
        } else if (this.gridCursor.addLetterUnderCursor()) {
            this.letterPoolPanel.grayOutLetter(this.letterPool.getCurrentSelection());
            String letter = this.gridCursor.getLetterUnderCursor().toString();
            this.letterGridPanel.setLetterToCell(letter, this.gridCursor.getX(), this.gridCursor.getY());
            //this.setSelectedLettersToGrid(this.gridCursor.getSelectedLetters());
            this.updateSelectedLettersLabel();
        }
    }

    private void updateSelectedLettersLabel() {
        List<LetterContainer> selectedLetters = this.gridCursor.getSelectedLetters();
        StringBuilder letters = new StringBuilder(selectedLetters.size());
        for (LetterContainer lc : selectedLetters) {
            letters.append(lc.letter.character);
        }
        this.selectedLettersLabel.setText(letters.toString());
    }
}
