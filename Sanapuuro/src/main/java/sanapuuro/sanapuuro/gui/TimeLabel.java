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
public class TimeLabel extends JLabel{
    public TimeLabel(){
        super();
        this.setFont(GUISettings.getMediumFont());
        this.setForeground(GUISettings.getColorDefaultFont());
        this.setText("TIME LEFT: ");
    }
}
