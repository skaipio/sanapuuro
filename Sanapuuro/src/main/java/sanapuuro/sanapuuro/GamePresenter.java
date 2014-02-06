/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
 * Updates game logic instances and GUI instances according to input. Resembles
 * the presenter in MVP pattern.
 *
 * @author skaipio
 */
public class GamePresenter implements MouseListener, KeyListener, ActionListener {

    private final Game game = new Game();
    private Player player;
    private final Container contentPane;
    private JLabel selectedLettersLabel;
    private JButton submitButton;
    private LetterGridPanel letterGridPanel;
    private LetterPoolPanel letterPoolPanel;
    private JLabel scoreLabel;
    private JLabel stateLabel;

    private GridCursor gridCursor;
    private LetterPool letterPool;

    public GamePresenter(Container contentPane) {
        this.contentPane = contentPane;
    }

    /**
     * Starts a new game and prepares the views.
     */
    public void newGame() {
        this.game.newGame();
        this.player = this.game.getPlayer();
        this.gridCursor = this.player.getControlledCursor();
        this.letterPool = this.gridCursor.getLetterPool();
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
     *
     * @param e Event from one of the controller's views.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getComponent() instanceof LetterPoolCell) {
            LetterPoolCell cell = (LetterPoolCell) e.getComponent();
            this.leftClickLetterPoolCell(cell);
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getComponent() instanceof GridCell) {
            GridCell cell = (GridCell) e.getComponent();
            //System.out.println(e.getComponent().getClass() + " clicked at " + cell.x + "," + cell.y);
            this.leftClickGridCell(cell);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            GridCell cell = (GridCell) e.getComponent();
            //System.out.println(e.getComponent().getClass() + " clicked at " + cell.x + "," + cell.y);
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
     *
     * @param e Button event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof SubmitButton) {
            List<LetterContainer> selectedContainers = this.gridCursor.getSelectedContainers();
            if (this.player.submitSelectedLetters()) {
                this.scoreLabel.setText(this.player.getScore() + "");
                this.updateLetterPoolPanel();
                this.deselectCells(selectedContainers);
                this.selectedLettersLabel.setText("");
            }
            this.stateLabel.setText(this.player.getStatus());
        }
        System.out.println(this.contentPane.isFocusOwner());
        this.contentPane.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key typed");
        if (e.getKeyChar() == 'a') {
            int selection = this.letterPool.getCurrentSelectedIndex();
            int newSelection = selection - 1;
            newSelection = newSelection < 0 ? 8 + newSelection : newSelection;
            System.out.println(newSelection);
            this.letterPool.setCurrentSelection(newSelection);
            this.letterPoolPanel.setCurrentSelectionTo(newSelection);
        } else if (e.getKeyChar() == 'd') {
            int selection = this.letterPool.getCurrentSelectedIndex();
            int newSelection = (selection + 1) % 8;
            System.out.println("d: " + newSelection);
            this.letterPool.setCurrentSelection(newSelection);
            this.letterPoolPanel.setCurrentSelectionTo(newSelection);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateLetterPoolPanel() {
        for (LetterContainer container : this.letterPool.getLetters()) {
            this.letterPoolPanel.setLetterToCell(container.letter.toString(), container.letterPoolIndex());
        }
    }

    private void deselectCells(List<LetterContainer> selectedContainers) {
        for (LetterContainer container : selectedContainers) {
            //System.out.print(container.letter + " ");
            this.letterGridPanel.setCellSelectionAt(false, container.getX(), container.getY());
        }
    }

    private void leftClickLetterPoolCell(LetterPoolCell cell) {
        this.letterPool.setCurrentSelection(cell.index);
        this.letterPoolPanel.setCurrentSelectionTo(cell.index);
    }

    private void leftClickGridCell(GridCell cell) {
        this.gridCursor.setLocation(cell.x, cell.y);
        if (this.gridCursor.selectLetterUnderCursor()) {
            cell.select();
            this.updateSelectedLettersLabel();
        } else if (this.gridCursor.addLetterUnderCursor()) {
            cell.select();
            this.letterPoolPanel.grayOutLetter(this.letterPool.getCurrentSelectedIndex());
            String letter = this.gridCursor.getContainerUnderCursor().letter.toString();
            this.letterGridPanel.setLetterToCell(letter, this.gridCursor.getX(), this.gridCursor.getY());
            this.updateSelectedLettersLabel();
        }

    }

    private void rightClickGridCell(GridCell cell) {
        this.gridCursor.setLocation(cell.x, cell.y);
        if (this.gridCursor.hasContainerUnderCursor()) {
            LetterContainer containerUnderCursor = this.gridCursor.getContainerUnderCursor();
            if (this.gridCursor.removeSelectionUnderCursor()) {

                if (containerUnderCursor.isFromLetterPool()) {
                    cell.removeLetter();
                    this.letterPoolPanel.letterReturnedToPool(containerUnderCursor.letterPoolIndex());
                }
                cell.deselect();
                this.updateSelectedLettersLabel();
            }
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
