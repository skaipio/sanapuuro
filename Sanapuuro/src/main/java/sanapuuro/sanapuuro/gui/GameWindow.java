/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class GameWindow extends javax.swing.JFrame {

    /**
     * Creates new form GameWindow
     */
    public GameWindow() {

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sanapuuro");
        setBackground(new java.awt.Color(47, 41, 35));
        setName("gameWindowFrame"); // NOI18N
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUISettings.initGUISettings();
                GameWindow window = new GameWindow();
                window.initWindows();
                window.setVisible(true);
                window.pack();
            }
        });
    }

    private void initWindows() {
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        Container mainGameView = new JPanel(new GridBagLayout());
        mainGameView.setBackground(GUISettings.getColorBackground1());
        mainGameView.setFocusable(true);

        this.add(mainGameView);

        GridBagConstraints constraints = new GridBagConstraints();

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        statusPanel.setBackground(GUISettings.getColorBackground1());
        statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        TimeLabel timeLabel = new TimeLabel();
        statusPanel.add(timeLabel);

        JLabel scoreLabel = new ScoreLabel();
        scoreLabel.setText("");
        statusPanel.add(scoreLabel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainGameView.add(statusPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainGameView.add(Box.createVerticalStrut(5), constraints);

        JPanel submissionPanel = new JPanel();
        submissionPanel.setLayout(new BoxLayout(submissionPanel, BoxLayout.LINE_AXIS));
        submissionPanel.setBackground(GUISettings.getColorBackground1());

        JLabel selectedLettersLabel = new SelectedLettersLabel();
        JPanel selectedLettersPanel = new JPanel();
        selectedLettersPanel.setBorder(BorderFactory.createLineBorder(GUISettings.getColorBorder()));
        selectedLettersPanel.setBackground(GUISettings.getColorSelectedCell());
        int width = 200, height = 40;
        selectedLettersPanel.setPreferredSize(new Dimension(width, height));
        selectedLettersPanel.add(selectedLettersLabel);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        mainGameView.add(selectedLettersPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        mainGameView.add(Box.createVerticalStrut(5), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        int fillerWidth = 50;
        mainGameView.add(Box.createHorizontalStrut(fillerWidth), constraints);

        LetterGridPanel cells = new LetterGridPanel(8, 8);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        mainGameView.add(cells, constraints);

        constraints.gridx = 2;
        mainGameView.add(Box.createHorizontalStrut(fillerWidth), constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.NONE;
        mainGameView.add(Box.createVerticalStrut(5), constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        LetterPoolPanel letterPoolPanel = new LetterPoolPanel();
        mainGameView.add(letterPoolPanel, constraints);

        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.setBackground(GUISettings.getColorBackground1());
//        JLabel stateText = new JLabel("<html><p>Use WASD keys to move the grid cursor "
//                + "and click on the letter pool letters or letters in the grid to "
//                + "form a word. Right click removes the previously selected/added letter.</p></html>");     
        JLabel stateText = new JLabel("Add letters to grid to form a word.");
        stateText.setFont(GUISettings.getMediumFont());
        infoPanel.add(stateText, BorderLayout.NORTH);
        //infoPanel.setPreferredSize(new Dimension(this.getPreferredSize().width, stateText.getPreferredSize().height * 3));
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        mainGameView.add(infoPanel, constraints);

        GamePresenter presenter = new GamePresenter(mainGameView);
        presenter.setSelectedLettersLabel(selectedLettersLabel);
        presenter.setLetterGridPanel(cells);
        presenter.setLetterPoolPanel(letterPoolPanel);
        presenter.setScoreLabel(scoreLabel);
        presenter.setTimeLabel(timeLabel);
        presenter.setStateLabel(stateText);

        presenter.newGame();

        this.pack();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
