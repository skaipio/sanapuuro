/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author skaipio
 */
public class SelectedLettersPanel extends JLabel{
    public SelectedLettersPanel(){
        super();
        this.setFont(new Font("Arial", Font.PLAIN, 24));
        this.setText("");
    }
    
    @Override
    public void setText(String text){
        if (text.isEmpty()){
            super.setText("Selected letters:");
        }else{
            super.setText("Selected letters: " + text.toUpperCase());
        }
    }
}
