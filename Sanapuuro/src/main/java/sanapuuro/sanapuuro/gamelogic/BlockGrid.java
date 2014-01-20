/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gamelogic;

/**
 *
 * @author skaipio
 */
public class BlockGrid {

    private Cell[][] cells;

    public BlockGrid(Size size) {
        this.cells = new Cell[size.Width][size.Height];
        for (int x = 0; x < size.Width; x++) {
            for (int y = 0; y < size.Height; y++) {
                this.cells[x][y] = new Cell();
            }
        }
    }

    public enum Size {

        SMALL(8, 8), MEDIUM(10, 10), LARGE(12, 12);

        public final int Width, Height;

        private Size(int width, int height) {
            this.Width = width;
            this.Height = height;
        }
    }
}
