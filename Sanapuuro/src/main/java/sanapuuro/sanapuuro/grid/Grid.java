/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.grid;

/**
 *
 * @author skaipio
 */
public class Grid {

    public final int width, height;
    private final LetterContainer[][] containers;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.containers = new LetterContainer[width][height];
    }

    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.containers[x][y] = null;
            }
        }
    }
    
    public boolean hasContainerAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        return this.containers[x][y] != null;
    }

    public LetterContainer getContainerAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        return this.containers[x][y];
    }
    
    public boolean setContainerAt(LetterContainer container, int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        if (!this.hasContainerAt(x, y)){
            this.containers[x][y] = container;
            container.setX(x);
            container.setY(y);
            return true;
        }
        return false;
    }
    
    public void removeContainerAt(int x, int y) {
        if (!isWithinGrid(x, y)) {
            throw new IllegalArgumentException("Given coordinates are not within grid.");
        }
        this.containers[x][y] = null;
    }

    public boolean isWithinGrid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

//     public enum Size {
//
//        SMALL(8, 8), MEDIUM(10, 10), LARGE(12, 12);
//
//        public final int Width, Height;
//
//        private Size(int width, int height) {
//            this.Width = width;
//            this.Height = height;
//        }
//    }
}
