package org.example.figure;

import org.example.pixel.Pixel;
import org.example.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * class Figure
 * Realization of figure in the game.
 * All figures consist of block, if block part of figure
 * has a value more than zero in array SHAPES
 * else zero.
 */
public class Figure {
    private ArrayList<Pixel> figure = new ArrayList<Pixel>();
    private int[][] shape = new int[4][4];
    private int type, size, color;
    private int x = 3;
    private int y = 0;
    private Utils utils;

    private Random random = new Random();
    final int[][][] SHAPES = {
            {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {4, 0x00f0f0}}, // I
            {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {4, 0xf0f000}}, // O
            {{1, 0, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x0000f0}}, // J
            {{0, 0, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf0a000}}, // L
            {{0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x00f000}}, // S
            {{1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xa000f0}}, // T
            {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf00000}}  // Z
    };

    /**
     * public constructor of Figure
     *
     * @param utils - class contains all const and mine of game
     */
    public Figure(Utils utils) {
        type = random.nextInt(SHAPES.length);
        size = SHAPES[type][4][0];
        color = SHAPES[type][4][1];
        if (size == 4) y = -1;
        for (int i = 0; i < size; i++)
            System.arraycopy(SHAPES[type][i], 0, shape[i], 0, SHAPES[type][i].length);
        createFromShape();
        this.utils = utils;
    }

    /**
     * Check collision figure with wall
     *
     * @param direction - direction of move figure
     * @return is figure touch wall
     */
    /*TODO(add condition for index out)*/
    public boolean isTouchWall(int direction) {
        for (Pixel pixel : figure) {
            if (direction == utils.LEFT && (pixel.getX() == 0 || utils.getMine()[pixel.getY()][pixel.getX() - 1] > 0))
                return true;
            if (direction == utils.RIGHT && (pixel.getX() == utils.FIELD_WIDTH - 1 || utils.getMine()[pixel.getY()][pixel.getX() + 1] > 0))
                return true;
        }
        return false;
    }

    /**
     * Move figure left or right
     *
     * @param keyCode - direction of move
     */
    public void move(int keyCode) {
        if (!isTouchWall(keyCode)) {
            int dx = keyCode - 38;
            for (Pixel pixel :
                    figure) {
                pixel.setX(pixel.getX() + dx);
            }
        }
    }

    /**
     * Drop a figure press arrow down ↓
     */
    public void drop() {

        /*while (!isTouchDown()) fallDown();*/
    }

    /**
     * Rotate the figure
     */
    public void rotate() {
        /*TODO: need realization of rotate of figure*/
    }

    /**
     * Check collision figure with ground
     *
     * @return is figure touch ground
     */
    public boolean isTouchDown() {
        for (Pixel pixel : figure) {
            if (utils.getMine()[pixel.getY() + 1][pixel.getX()] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create figure using array of blocks
     */
    public void createFromShape() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (shape[i][j] == 1) {
                    figure.add(new Pixel(i + this.x, j + this.y));
                }
            }
        }
    }

    /**
     * Checking game over.
     * Game over if any figure can not fall from start position.
     *
     * @return is game over
     */
    public boolean isCrossDown() {
        for (Pixel pixel : figure)
            if ((pixel.getY() <= utils.FIELD_HEIGHT && pixel.getY() >= 0 &&
                    pixel.getX() <= utils.FIELD_WIDTH && pixel.getX() >= 0) &&
                    (utils.getMine()[pixel.getY()][pixel.getX()] > 0)) return true;
        return false;
    }

    /**
     * Realization of fall down of figure.
     * Decrement position of figure by Oy axis.
     */
    public void fallDown() {
        for (Pixel pixel :
                figure) {
            pixel.setY(pixel.getY() + 1);
            y++;
        }
    }

    /**
     * Method called when figure fall lower than ground
     * Array of mine set color of figure in position blocks in figure
     */
    public void leaveOnTheGround() {
        for (Pixel pixel : figure) {
            utils.setNewValueMine(pixel.getY(), pixel.getX(), color);
        }
    }

    /**
     * Paint every block in figure
     *
     * @param g - Graphics
     */
    public void paint(Graphics g) {
        for (Pixel pixel :
                figure) {
            pixel.paint(g, color);
        }
    }
}
