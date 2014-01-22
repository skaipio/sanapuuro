/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.utils;

/**
 *
 * @author skaipio
 */
public class MathUtils {
    /**
     * Clamp n between min and max.
     */
    public static int clamp(int min, int max, int n){
        return Math.min(max, Math.max(min, n));
    }
}
