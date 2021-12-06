package org.example.figure;

import org.example.block.Block;
import org.example.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Figure {
    private ArrayList<Block> figure = new ArrayList<Block>();
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

    public void move(int keyCode) {
    }

    /**
     * Drop a figure press arrow down ↓
     */
    public void drop() {
        System.out.println("down");
        while (!isTouchDown()) fallDown();
    }

    public void rotate() {
        System.out.println("up");
    }

    /**
     * Check collision figure with ground
     *
     * @return is figure touch ground
     */
    public boolean isTouchDown() {
        for (Block block : figure) {
            if (utils.getMine()[block.getY() + 1][block.getX()] > 0) {
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
                    figure.add(new Block(i + this.x, j + this.y));
                }
            }
        }
    }

    /**
     * Checking game over
     *
     * @return
     */
    public boolean isCrossDown() {
        return false;
    }

    /**
     * Decrement position of figure by Oy axis
     */
    public void fallDown() {
        for (Block block :
                figure) {
            block.setY(block.getY() + 1);
            y++;
        }
    }

    /**
     * Method called when figure fall lower than ground
     * Array of mine set color of figure in position blocks in figure
     */
    public void leaveOnTheGround() {
        for (Block block : figure) {
            utils.setNewValueMine(block.getY(), block.getX(), color);
        }
    }

    /**
     * Paint every block in figure
     *
     * @param g - Graphics
     */
    public void paint(Graphics g) {
        for (Block block :
                figure) {
            block.paint(g, color);
        }
    }
}
