/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class LetterPoolCell extends JPanel {

    private String letter = "";
    private int fontSize = 24;
    private boolean selected = false;
    private int selecBorderWidth = 3;
    public final int index;
    private boolean isGreyedOut = false;

    public LetterPoolCell(int index) {
        this.index = index;
        this.setBackground(Color.BLACK);
        this.setFont(new Font("Arial", Font.PLAIN, this.fontSize));
    }

    public void setLetter(String letter) {
        this.letter = letter;
        this.repaint();
    }

    public void setInUse(boolean enabled) {
        this.isGreyedOut = enabled;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.selected) {
            g.setColor(Color.BLACK);
            g.fillRect(selecBorderWidth, selecBorderWidth, this.getWidth() - selecBorderWidth * 2, this.getHeight() - selecBorderWidth * 2);
        }
        if (this.isGreyedOut) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(letter, this.getWidth() / 2 - 8, fontSize + 3);

    }

    void deselect() {
        this.selected = false;
        this.setBackground(Color.BLACK);
        this.repaint();
    }

    void select() {
        this.selected = true;
        this.setBackground(Color.WHITE);
        this.repaint();
    }
}
