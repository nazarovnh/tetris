package org.example.render;

import org.example.figure.Figure;
import org.example.utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * class Render.
 * The main task is render all components of the game.
 * Render children-class of JPanel
 */
public class Render extends JPanel {
    private Figure figure;
    private Utils utils;

    /**
     * public constructor of Render
     *
     * @param figure - figure
     * @param utils  - class of all constants
     */
    public Render(Figure figure, Utils utils) {
        this.figure = figure;
        this.utils = utils;
    }

    /**
     * Render all components of the game.
     * Checking the value of mine and draw pixel.
     * Also, method draw title game over.
     *
     * @param graphics - graphics
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (int x = 0; x < utils.FIELD_WIDTH; x++)
            for (int y = 0; y < utils.FIELD_HEIGHT; y++) {
                if (utils.getMine()[y][x] > 0) {
                    graphics.setColor(new Color(utils.getMine()[y][x]));
                    graphics.fill3DRect(x * utils.BLOCK_SIZE + 1, y * utils.BLOCK_SIZE + 1, utils.BLOCK_SIZE - 1, utils.BLOCK_SIZE - 1, true);
                }
            }
        if (utils.isGameOver()) {
            graphics.setColor(Color.white);
            for (int y = 0; y < utils.GAME_OVER_MSG.length; y++)
                for (int x = 0; x < utils.GAME_OVER_MSG[y].length; x++)
                    if (utils.GAME_OVER_MSG[y][x] == 1) graphics.fill3DRect(x * 11 + 18, y * 11 + 160, 10, 10, true);
        } else
            figure.paint(graphics);
    }

    /**
     * Set figure that need render
     *
     * @param figure - figure
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
