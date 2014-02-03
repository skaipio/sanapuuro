/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class GridCell extends JPanel{
    private String letter = "";
    private int letterSize = 24;
    private boolean isSelected = false;
    private Color fontColor = Color.WHITE;
    public final int x, y;
    
    public GridCell(int x, int y){
        this.x = x;
        this.y = y;
        this.setFont(new Font("Arial", Font.PLAIN, letterSize));
        this.setBackground(Color.BLACK);
        this.setSize(letterSize+6, letterSize+6);
        //this.setPreferredSize(new Dimension(letterSize+6, letterSize+6));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(fontColor);
        g.drawString(letter, this.getWidth()/2-8, this.getHeight()/2+10);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
    }
    
    public void setLetter(String letter){
        this.letter = letter;
        this.repaint();
    }
    
    public void removeLetter(){
        this.letter = "";
        this.repaint();
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
