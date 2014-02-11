/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro;

/**
 *
 * @author skaipio
 */
public interface GameTimer {
    void addListener(GameTimerListener listener);
    void startCountdownFrom(int seconds);
    void decreaseTime(int seconds);
    void addTime(int seconds);
}
