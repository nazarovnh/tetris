package org.example.utils;

import java.util.Arrays;

/**
 * class of Utils.
 * Consist of all constants of game and mine
 */
public class Utils {
    public final String TITLE_OF_PROGRAM = "Tetris";
    public final int BLOCK_SIZE = 25; // size of block
    public final int FIELD_WIDTH = 10; // width game field in block
    public final int FIELD_HEIGHT = 18; // height game field in block
    private int[][] mine = new int[FIELD_HEIGHT + 1][FIELD_WIDTH];
    public final int START_LOCATION = 180; // start location left corner window
    public final int FIELD_DX = 7;
    public final int FIELD_DY = 37;
    public final int LEFT = 37; // key codes
    public final int UP = 38;
    public final int RIGHT = 39;
    public final int DOWN = 40;
    public final int SHOW_DELAY = 250; // delay for render
    private boolean gameOver = false;
    public final int[] SCORES = {100, 300, 700, 1500}; // score
    public final int[][] GAME_OVER_MSG = {
            {0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0}};

    /**
     * public constructions of Utils
     */
    public Utils() {
        Arrays.fill(mine[FIELD_HEIGHT], 1);
    }

    /**
     * Set new value in mine
     *
     * @param y        - position of value by axis Oy
     * @param x        - position of value by axis Ox
     * @param newValue - new value
     */
    public void setNewValueMine(int y, int x, int newValue) {
        this.mine[y][x] = newValue;
    }

    /**
     * Get mine of game
     *
     * @return array of pixel
     */
    public int[][] getMine() {
        return mine;
    }

    /**
     * Set game over
     *
     * @param gameOver - bool value game over
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Checking is game over
     *
     * @return is game over
     */
    public boolean isGameOver() {
        return gameOver;
    }
}
