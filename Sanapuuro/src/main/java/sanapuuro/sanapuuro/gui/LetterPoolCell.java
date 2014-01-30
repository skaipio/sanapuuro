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
    private int fontSize = 16;
    
    public void setLetter(String letter){
        this.letter = letter;
        this.setBackground(Color.BLACK);
        this.setFont(new Font("Arial", Font.PLAIN, this.fontSize));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString(letter, 0, fontSize);
    }
}
