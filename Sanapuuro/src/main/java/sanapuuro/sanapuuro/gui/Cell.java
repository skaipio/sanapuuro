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
public class Cell extends JPanel{
    private String letter = "L";
    private int letterSize = 24;
    
    public Cell(int x, int y){
        this.setFont(new Font("Arial", Font.PLAIN, letterSize));
        //this.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString(letter, this.getWidth()/2-8, this.getHeight()/2+10);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}
