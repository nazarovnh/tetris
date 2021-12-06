package org.example.render;

import org.example.figure.Figure;
import org.example.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel {
    private Figure figure;
    private Utils utils;

    public Render(Figure figure, Utils utils) {
        this.figure = figure;
        this.utils = utils;
    }

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
        figure.paint(graphics);
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
