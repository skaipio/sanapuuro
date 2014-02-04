/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import sanapuuro.sanapuuro.GameController;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        setName("gameWindowFrame"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

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
            public void run() {
                GameWindow window = new GameWindow();
                window.setVisible(true);
                window.newGame();
            }
        });
    }

    private void newGame() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        JLabel selectedLettersPanel = new SelectedLettersLabel();
        this.add(selectedLettersPanel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        JButton submitButton = new SubmitButton();
        this.add(submitButton, constraints);

        LetterGridPanel cells = new LetterGridPanel(12, 12);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(cells, constraints);
        
        JPanel poolAndScorePanel = new JPanel();
        poolAndScorePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));

        LetterPoolPanel letterPoolPanel = new LetterPoolPanel();
        poolAndScorePanel.add(letterPoolPanel);

        JLabel scoreLabel = new ScoreLabel();
        scoreLabel.setText(letterPoolPanel.getWidth() + "");
        poolAndScorePanel.add(scoreLabel);
        constraints.gridy = 2;
        this.add(poolAndScorePanel, constraints);

        JLabel stateLabel = new JLabel("Press left mouse button to add letters to or select letters from grid.");
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(stateLabel, constraints);

        GameController controller = new GameController();
        controller.setSelectedLettersLabel(selectedLettersPanel);
        controller.setSubmitButton(submitButton);
        controller.setLetterGridPanel(cells);
        controller.setLetterPoolPanel(letterPoolPanel);
        controller.setScoreLabel(scoreLabel);
        controller.setStateLabel(stateLabel);

        controller.newGame();

        this.pack();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
