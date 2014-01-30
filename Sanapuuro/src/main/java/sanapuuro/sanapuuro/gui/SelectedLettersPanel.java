/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.util.List;
import javax.swing.JLabel;
import sanapuuro.sanapuuro.grid.LetterContainer;

/**
 *
 * @author skaipio
 */
public class SelectedLettersPanel extends JLabel{   
    public void setSelectedLetters(List<LetterContainer> letters){
        StringBuilder text = new StringBuilder();
        for (LetterContainer letterContainer : letters) {
            text.append(letterContainer.getLetter().toString());
        }
        this.setText(text.toString());
        this.repaint();
    }
}
