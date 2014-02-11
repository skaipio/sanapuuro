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

        if (e.getComponent() instanceof LetterPoolCellButton) {
            LetterPoolCellButton cell = (LetterPoolCellButton) e.getComponent();
            this.leftClickLetterPoolCell(cell);
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            this.leftClickGridCell(cell);
        } else if (SwingUtilities.isRightMouseButton(e)) {
//            if (!this.selectionMode && e.getComponent() instanceof GridCellPanel) {
//                GridCellPanel cell = (GridCellPanel) e.getComponent();
//                this.returnAddedLetter(cell);
//            } else 
            if (this.selectionMode) {
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
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof GridCellPanel) {
            GridCellPanel cell = (GridCellPanel) e.getSource();
            if (!this.selectionMode) {
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
//        if (e.getSource() instanceof SubmitButton) {
//            List<LetterContainer> selectedContainers = this.player.getSelectedContainers();
//            if (this.player.submitSelectedLetters()) {
//                this.scoreLabel.setText(this.player.getScore() + "");
//                this.updateLetterPoolPanel();
//                this.deselectCells(selectedContainers);
//                this.selectedLettersLabel.setText("");
//            }
//            this.stateLabel.setText(this.player.getStatus());
//        }
        System.out.println(this.contentPane.isFocusOwner());
        this.contentPane.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            int selection = this.letterPool.getCurrentSelectedIndex();
            int newSelection = selection - 1;
            newSelection = newSelection < 0 ? 8 + newSelection : newSelection;
            this.letterPool.setCurrentSelection(newSelection);
            this.letterPoolPanel.setCurrentSelectionTo(newSelection);
        } else if (e.getKeyChar() == 'd') {
            int selection = this.letterPool.getCurrentSelectedIndex();
            int newSelection = (selection + 1) % 8;
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
            this.letterGridPanel.setCellSelectionAt(false, container.getX(), container.getY());
        }
    }

    private void leftClickLetterPoolCell(LetterPoolCellButton cell) {
        this.letterPool.setCurrentSelection(cell.index);
        this.letterPoolPanel.setCurrentSelectionTo(cell.index);
    }

    private void leftClickGridCell(GridCellPanel cell) {
        if (!this.player.getSelectedContainers().isEmpty() && this.cellIsTail(cell)) {
            this.attemptToSubmitSelections();
        } else if (this.player.getSelectedContainers().isEmpty()
                || cellIsAlignedWithAndNeighbourOfLastSelection(cell)) {
            this.attemptToSelectOrAddCell(cell);
        }
    }

    private void attemptToSelectOrAddCell(GridCellPanel cell) {
        if (this.player.addLetterTo(cell.x, cell.y)) {
            LetterContainer container = this.letterPool.getCurrentSelection();
            this.letterPoolPanel.grayOutLetter(container.letterPoolIndex());
        }
        LetterContainer previousSelection = this.player.getLastSelection();
        if (this.player.selectLetterAt(cell.x, cell.y)) {
            this.selectionMode = true;
            LetterContainer container = this.grid.getContainerAt(cell.x, cell.y);
            String letter = container.letter.toString();
            this.letterGridPanel.getCellAt(cell.x, cell.y).setLetter(letter);
            this.updateSelectedLettersLabel();
            if (previousSelection != null) {
                this.removeSelectableLightUpsAroundContainer(previousSelection);
            }
            this.lightUpSelectableCells();
            this.selectCell(cell);
        }
    }

    private void attemptToSubmitSelections() {
        this.selectionMode = false;
        LetterContainer tail = this.player.getLastSelection();
        List<LetterContainer> selections = this.player.getSelectedContainers();
        List<LetterContainer> added = this.player.getAddedContainers();
        if (this.player.submitSelectedLetters()) {
            this.scoreLabel.setText(this.player.getScore() + "");
        } else {
            this.removeLettersFromCells(added);
        }
        this.clearSelections(selections);
        this.removeSelectableLightUpsAroundContainer(tail);
    }

    private boolean cellIsTail(GridCellPanel cell) {
        LetterContainer tail = this.player.getLastSelection();
        return cell.x == tail.getX() && cell.y == tail.getY();
    }

    private boolean cellIsAlignedWithAndNeighbourOfLastSelection(GridCellPanel cell) {
        LetterContainer root = this.player.getFirstSelection();
        LetterContainer tail = this.player.getLastSelection();
        if (cellOnSameRowWithRootAndTail(cell, root, tail)) {
            return this.cellHasNoHorizontalGapToTail(cell, tail);
        } else if (cellOnSameColumnWithRootAndTail(cell, root, tail)) {
            return this.cellHasNoVerticalGapToTail(cell, tail);
        }
        return false;
    }

    private boolean cellOnSameRowWithRootAndTail(GridCellPanel cell, LetterContainer root, LetterContainer tail) {
        return cell.y == tail.getY() && cell.y == root.getY();
    }

    private boolean cellOnSameColumnWithRootAndTail(GridCellPanel cell, LetterContainer root, LetterContainer tail) {
        return cell.x == tail.getX() && cell.x == root.getX();
    }

    private boolean cellHasNoHorizontalGapToTail(GridCellPanel cell, LetterContainer tail) {
        return Math.abs(cell.x - tail.getX()) == 1;
    }

    private boolean cellHasNoVerticalGapToTail(GridCellPanel cell, LetterContainer tail) {
        return Math.abs(cell.y - tail.getY()) == 1;
    }

    private void selectCell(GridCellPanel cell) {
        cell.select();
        this.updateSelectedLettersLabel();
    }

    private void deselectCell(GridCellPanel cell) {
        cell.deselect();
        this.updateSelectedLettersLabel();
    }

    private void lightUpSelectableCells() {
        LetterContainer tail = this.player.getLastSelection();
        if (this.grid.isWithinGrid(tail.getX(), tail.getY() + 1)) {
            GridCellPanel cell = this.letterGridPanel.getCellAt(tail.getX(), tail.getY() + 1);
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
        if (this.grid.isWithinGrid(tail.getX(), tail.getY() - 1)) {
            GridCellPanel cell = this.letterGridPanel.getCellAt(tail.getX(), tail.getY() - 1);
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
        if (this.grid.isWithinGrid(tail.getX() + 1, tail.getY())) {
            GridCellPanel cell = this.letterGridPanel.getCellAt(tail.getX() + 1, tail.getY());
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
        if (this.grid.isWithinGrid(tail.getX() - 1, tail.getY())) {
            GridCellPanel cell = this.letterGridPanel.getCellAt(tail.getX() - 1, tail.getY());
            if (this.cellIsAlignedWithAndNeighbourOfLastSelection(cell) && !cell.isSelected()) {
                cell.showAsSelectable(true);
            }
        }
    }

    private void removeSelectableLightUpsAroundContainer(LetterContainer container) {
        if (this.grid.isWithinGrid(container.getX(), container.getY() + 1)) {
            this.letterGridPanel.getCellAt(container.getX(), container.getY() + 1).showAsSelectable(false);
        }
        if (this.grid.isWithinGrid(container.getX(), container.getY() - 1)) {
            this.letterGridPanel.getCellAt(container.getX(), container.getY() - 1).showAsSelectable(false);
        }
        if (this.grid.isWithinGrid(container.getX() + 1, container.getY())) {
            this.letterGridPanel.getCellAt(container.getX() + 1, container.getY()).showAsSelectable(false);
        }
        if (this.grid.isWithinGrid(container.getX() - 1, container.getY())) {
            this.letterGridPanel.getCellAt(container.getX() - 1, container.getY()).showAsSelectable(false);
        }
    }

    private void rightClick() {
        List<LetterContainer> containers = this.player.getSelectedContainers();
        if (!containers.isEmpty()) {
            LetterContainer tail = this.player.getLastSelection();
            this.player.removeLastSelection();
            GridCellPanel cell = this.letterGridPanel.getCellAt(tail.getX(), tail.getY());
            cell.deselect();
            this.removeSelectableLightUpsAroundContainer(tail);
            if (!tail.isPermanent()) {
                cell.removeLetter();
                this.letterPoolPanel.letterReturnedToPool(tail.letterPoolIndex());
            }
            this.updateSelectedLettersLabel();
            if(containers.isEmpty()){
                this.selectionMode = false;
            }
        }

//        for (LetterContainer container : containers) {
//            GridCellPanel cell = this.letterGridPanel.getCellAt(container.getX(), container.getY());
//            cell.deselect();
//        }
//        this.player.clearSelections();
    }

//    private void returnAddedLetter(GridCellPanel cell) {
//        LetterContainer containerUnderCursor = this.grid.getContainerAt(cell.x, cell.y);
//        if (this.player.returnContainerToLetterPoolAt(cell.x, cell.y)) {
//            cell.removeLetter();
//            this.letterPoolPanel.letterReturnedToPool(containerUnderCursor.letterPoolIndex());
//            cell.removeHighlight();
//        }
//    }
    private void updateSelectedLettersLabel() {
        List<LetterContainer> selectedLetters = this.player.getSelectedContainers();
        StringBuilder letters = new StringBuilder(selectedLetters.size());
        for (LetterContainer lc : selectedLetters) {
            letters.append(lc.letter.character);
        }
        this.selectedLettersLabel.setText(letters.toString());
    }

    private void clearSelections(List<LetterContainer> selections) {
        this.deselectCells(selections);
        this.updateLetterPoolPanel();
        this.selectedLettersLabel.setText("");
    }

    private void removeLettersFromCells(List<LetterContainer> addedContainers) {
        for (LetterContainer container : addedContainers) {
            this.letterGridPanel.getCellAt(container.getX(), container.getY()).removeLetter();
        }
    }

}
