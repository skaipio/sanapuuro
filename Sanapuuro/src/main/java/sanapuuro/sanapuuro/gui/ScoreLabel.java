/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author skaipio
 */
public class ScoreLabel extends JLabel {
    private String prefix = "SCORE: ";
     public ScoreLabel(){
        super();
        this.setFont(GUISettings.getMediumFont());
        this.setForeground(GUISettings.getColorDefaultFont());
        this.setText("");
    }
    
    @Override
    public void setText(String text){
        if (text.isEmpty()){
            super.setText(prefix);
        }else{
            super.setText(prefix + text);
        }
    }
}
