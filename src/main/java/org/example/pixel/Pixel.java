package org.example.pixel;

import java.awt.*;

/**
 * class Block.
 * Realization of pixel in the game.
 * All shape consist of blocks.
 */
public class Pixel {
    private int x;
    private int y;
    private final int BLOCK_SIZE = 25; // size of block
    private final int ARC_RADIUS = 6; // border radius block


    /**
     * public construction of Pixel.
     *
     * @param x  - position of pixel by axis Ox
     * @param y- position of pixel by axis Oy
     */
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Render pixel in the game
     *
     * @param g     - graphics
     * @param color - color of pixel
     *              graphics is realization for all graphics contexts
     *              that allow an application to draw onto components that are realized
     *              on various devices, as well as onto off-screen images.
     */
    public void paint(Graphics g, int color) {
        g.setColor(new Color(color));
        g.drawRoundRect(x * BLOCK_SIZE + 1, y * BLOCK_SIZE + 1,
                BLOCK_SIZE - 2, BLOCK_SIZE - 2, ARC_RADIUS, ARC_RADIUS);
    }

    /**
     * Get position of pixel by axis Ox
     *
     * @return position of pixel by axis Ox
     */
    public int getX() {
        return x;
    }

    /**
     * Get position of pixel by axis Oy
     *
     * @return position of pixel by axis Oy
     */
    public int getY() {
        return y;
    }

    /**
     * Set position of pixel by axis Ox
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set position of pixel by axis Oy
     */
    public void setY(int y) {
        this.y = y;
    }
}
