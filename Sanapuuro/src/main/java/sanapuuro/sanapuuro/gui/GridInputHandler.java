/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import sanapuuro.sanapuuro.grid.GridCursor;

/**
 *
 * @author skaipio
 */
public class GridInputHandler implements MouseInputListener, KeyListener {
    private GridCursor gridCursor;
    private SelectedLettersPanel selectedLettersPanel;

    public GridInputHandler(GridCursor cursor, SelectedLettersPanel selectedLettersPanel) {
        this.gridCursor = cursor;
        this.selectedLettersPanel = selectedLettersPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GridCell cell = (GridCell) e.getComponent();
        this.gridCursor.setLocation(cell.x, cell.y);
        boolean selected = this.gridCursor.selectLetterUnderCursor();
        if (selected) {
            cell.select();
            this.selectedLettersPanel.setSelectedLetters(this.gridCursor.getSelectedLetters());
        }
        // System.out.println("Pushed " + cell.x + ", " + cell.y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
