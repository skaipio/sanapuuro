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
    private String letter = "";
    private int letterSize = 24;
    private boolean isSelected = false;
    private Color fontColor = Color.WHITE;
    public final int x, y;
    
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.setFont(new Font("Arial", Font.PLAIN, letterSize));
        this.setBackground(Color.BLACK);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(fontColor);
        g.drawString(letter, this.getWidth()/2-8, this.getHeight()/2+10);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    public boolean isSelected(){
        return this.isSelected;
    }
    
    public void select(){
        this.isSelected = true;
        this.fontColor = Color.BLACK;
        this.setBackground(Color.WHITE);
        this.repaint();
    }
    
    public void deselect(){
        this.isSelected = false;
        this.fontColor = Color.WHITE;
        this.setBackground(Color.BLACK);
        this.repaint();
    }
}
