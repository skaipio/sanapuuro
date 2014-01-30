/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import sanapuuro.sanapuuro.letters.LetterPool;

/**
 *
 * @author skaipio
 */
public class LetterPoolInputHandler implements MouseListener {
    private final LetterPool letterPool;
    
    
    public LetterPoolInputHandler(LetterPool letterPool){
        this.letterPool = letterPool;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LetterPoolCell cell = (LetterPoolCell)e.getComponent();
        this.letterPool.setCurrentSelection(cell.index);
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
    
}
