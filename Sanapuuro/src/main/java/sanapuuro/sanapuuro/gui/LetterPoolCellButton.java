/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JToggleButton;

/**
 *
 * @author skaipio
 */
public class LetterPoolCellButton extends JToggleButton {

    private String letter = "";
    private final int cellSize = 40;
    private boolean selected = false;
    private boolean inUse = false;
    public final int index;

    public LetterPoolCellButton(int index) {
        this.index = index;
        this.setBorder(null);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBackground(GUISettings.getColorButton2());
        this.setFont(GUISettings.getMediumFont());
        this.setPreferredSize(new Dimension(cellSize, cellSize));
    }

    public void setLetter(String letter) {
        this.letter = letter;
        this.setText(letter);
        this.repaint();
    }

    public void setInUse(boolean enabled) {
        this.inUse = enabled;
        if (enabled){          
            this.setBackground(GUISettings.getColorLetterPoolButtonUsed());
            this.setSelected(true);
        }else{
            this.setBackground(GUISettings.getColorButton2());
            this.setSelected(false);
        }
        this.repaint();
    }

    void deselect() {
        this.selected = false;
        if (this.inUse){
            this.setBackground(GUISettings.getColorLetterPoolButtonUsed());
        }
        else{
            this.setBackground(GUISettings.getColorButton2());
        }
        this.repaint();
    }

    void select() {
        this.selected = true;
        this.setBackground(GUISettings.getColorButtonHighlight2());
        this.repaint();
    }
}
