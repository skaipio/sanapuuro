/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author skaipio
 */
public class MainGameView extends JPanel {

    public final TimeLabel time;
    public final ScoreLabel score;
    public final SelectedLettersLabel selectedLetters;
    public final LetterGridPanel letterGrid;
    public final LetterPoolPanel letterPool;
    public final JLabel state;

    public MainGameView() {
        this.setLayout(new GridBagLayout());
        this.setBackground(GUISettings.getColorBackground());
        this.setFocusable(true);

        GridBagConstraints constraints = new GridBagConstraints();

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        statusPanel.setBackground(GUISettings.getColorBackground());
        statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.time = new TimeLabel();
        statusPanel.add(this.time);

        this.score = new ScoreLabel();
        statusPanel.add(this.score);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(statusPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(Box.createVerticalStrut(5), constraints);

        JPanel submissionPanel = new JPanel();
        submissionPanel.setLayout(new BoxLayout(submissionPanel, BoxLayout.LINE_AXIS));
        submissionPanel.setBackground(GUISettings.getColorBackground());

        this.selectedLetters = new SelectedLettersLabel();
        JPanel selectedLettersPanel = new JPanel();
        selectedLettersPanel.setBorder(BorderFactory.createLineBorder(GUISettings.getColorBorder()));
        selectedLettersPanel.setBackground(GUISettings.getColorLetterPoolCell());
        int width = 200, height = 40;
        selectedLettersPanel.setPreferredSize(new Dimension(width, height));
        selectedLettersPanel.add(this.selectedLetters);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        this.add(selectedLettersPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        this.add(Box.createVerticalStrut(5), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        int fillerWidth = 50;
        this.add(Box.createHorizontalStrut(fillerWidth), constraints);

        this.letterGrid = new LetterGridPanel(8, 8);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(this.letterGrid, constraints);

        constraints.gridx = 2;
        this.add(Box.createHorizontalStrut(fillerWidth), constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.NONE;
        this.add(Box.createVerticalStrut(5), constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.letterPool = new LetterPoolPanel();
        this.add(this.letterPool, constraints);

        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.setBackground(GUISettings.getColorBackground());
        this.state = new JLabel("Add letters to grid to form a word.");
        this.state.setFont(GUISettings.getMediumFont());
        infoPanel.add(this.state, BorderLayout.NORTH);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        this.add(infoPanel, constraints);
    }

}
