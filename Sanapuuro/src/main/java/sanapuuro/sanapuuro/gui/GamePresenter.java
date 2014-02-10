/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

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
import sanapuuro.sanapuuro.Game;
import sanapuuro.sanapuuro.Player;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
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
    private Grid grid;
    private final Container contentPane;
    private JLabel selectedLettersLabel;
    private JButton submitButton;
    private LetterGridPanel letterGridPanel;
    private LetterPoolPanel letterPoolPanel;
    private JLabel scoreLabel;
    private JLabel stateLabel;

    private boolean selectionMode = false;
    private GridCellPanel rootSelection;
    private GridCellPanel tailSelection;

    private LetterPool letterPool;

    public GamePresenter(Container contentPane) {
        this.contentPane = contentPane;
    }

    /**
     * Starts a new game and prepares the views.
     */
    public void newGame() {
        this.game.newGame();
        this.grid = this.game.getGrid();
        this.player = this.game.getPlayer();
        this.letterPool = this.player.getLetterPool();
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
        this.letterGridPanel.addMouseListener(this);
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
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            this.leftClickGridCell(cell);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (!this.selectionMode && e.getComponent() instanceof GridCellPanel) {
                GridCellPanel cell = (GridCellPanel) e.getComponent();
                this.returnAddedLetter(cell);
            } else if (this.selectionMode) {
                this.rightClick();
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
        if (e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            if (!this.selectionMode) {
                cell.hoverOn();
            } else if (this.canSelectCell(cell)) {
                System.out.println("can select");
                this.selectCell(cell);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof GridCellPanel) {
            if (!this.selectionMode) {
                GridCellPanel cell = (GridCellPanel) e.getSource();
                cell.hoverOff();
            }
        }
    }

    /**
     * Handles only the submit button event.
     *
     * @param e Button event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof SubmitButton) {
            List<LetterContainer> selectedContainers = this.player.getSelectedContainers();
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

    private void leftClickGridCell(GridCellPanel cell) {
        if (!this.selectionMode) {
            if (this.player.selectLetterAt(cell.x, cell.y)) {
                this.rootSelection = cell;
                this.selectionMode = true;
                this.selectCell(cell);
            } else if (this.player.addLetterTo(cell.x, cell.y)) {
                cell.highlight();
                this.letterPoolPanel.grayOutLetter(this.letterPool.getCurrentSelectedIndex());
                LetterContainer container = this.grid.getContainerAt(cell.x, cell.y);
                String letter = container.letter.toString();
                this.letterGridPanel.setLetterToCell(letter, cell.x, cell.y);
                this.updateSelectedLettersLabel();
            }
        } else {
            this.selectionMode =false;
            List<LetterContainer> selectedContainers = this.player.getSelectedContainers();
            if (this.player.submitSelectedLetters()) {
                this.scoreLabel.setText(this.player.getScore() + "");
                this.updateLetterPoolPanel();
                this.deselectCells(selectedContainers);
                this.selectedLettersLabel.setText("");
            }
        }
    }

    private boolean canSelectCell(GridCellPanel cell) {
        if (this.selectionMode && !cell.isSelected() && this.player.selectLetterAt(cell.x, cell.y)) {
            if (cellOnSameRowWithRootAndTail(cell)) {
                int diff = Math.abs(cell.x - tailSelection.x);
                return diff == 1;
            } else if (cellOnSameColumnWithRootAndTail(cell)) {
                int diff = Math.abs(cell.y - tailSelection.y);
                return diff == 1;
            }
        }
        return false;
    }

    private boolean cellOnSameRowWithRootAndTail(GridCellPanel cell) {
        return cell.y == this.tailSelection.y && cell.y == this.rootSelection.y;
    }

    private boolean cellOnSameColumnWithRootAndTail(GridCellPanel cell) {
        return cell.x == this.tailSelection.x && cell.x == this.rootSelection.x;
    }

    private void selectCell(GridCellPanel cell) {
        cell.select();
        this.updateSelectedLettersLabel();
        this.tailSelection = cell;
    }

    private void rightClick() {
        this.selectionMode = false;
        List<LetterContainer> containers = this.player.getSelectedContainers();
        for (LetterContainer container : containers) {
            GridCellPanel cell = this.letterGridPanel.getCellAt(container.getX(), container.getY());
            cell.deselect();
        }
        this.player.clearSelections();
        this.updateSelectedLettersLabel();
    }

    private void returnAddedLetter(GridCellPanel cell) {
        LetterContainer containerUnderCursor = this.grid.getContainerAt(cell.x, cell.y);
        if (this.player.returnContainerToLetterPoolAt(cell.x, cell.y)) {
            cell.removeLetter();
            this.letterPoolPanel.letterReturnedToPool(containerUnderCursor.letterPoolIndex());
            cell.removeHighlight();
        }
    }

    private void updateSelectedLettersLabel() {
        List<LetterContainer> selectedLetters = this.player.getSelectedContainers();
        StringBuilder letters = new StringBuilder(selectedLetters.size());
        for (LetterContainer lc : selectedLetters) {
            letters.append(lc.letter.character);
        }
        this.selectedLettersLabel.setText(letters.toString());
    }

}
