package org.example.game;

import org.example.figure.Figure;
import org.example.render.Render;
import org.example.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

    private Utils utils = new Utils();
    private Render render;
    private Figure figure;
    private JFrame frame;


    private int gameScore = 0;

    public Game() {
        this.figure = new Figure(utils);
        render = new Render(this.figure, utils);
        frame = new JFrame(utils.TITLE_OF_PROGRAM);
        frame.setTitle(utils.TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(utils.START_LOCATION, utils.START_LOCATION, utils.FIELD_WIDTH * utils.BLOCK_SIZE + utils.FIELD_DX, utils.FIELD_HEIGHT * utils.BLOCK_SIZE + utils.FIELD_DY);
        frame.setResizable(false);
        render.setBackground(Color.black);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!utils.isGameOver()) {
                    if (e.getKeyCode() == utils.DOWN) figure.drop();
                    if (e.getKeyCode() == utils.UP) figure.rotate();
                    if (e.getKeyCode() == utils.LEFT || e.getKeyCode() == utils.RIGHT) figure.move(e.getKeyCode());
                }
                render.repaint();
            }
        });
        frame.add(BorderLayout.CENTER, render);
        frame.setVisible(true);
        Arrays.fill(utils.getMine()[utils.FIELD_HEIGHT], 1);
    }

    public void start() {
        while (!utils.isGameOver()) {
            try {
                Thread.sleep(utils.SHOW_DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            render.repaint();
            if (figure.isTouchDown()) {
                figure.leaveOnTheGround();
                checkFilling();
                this.figure = new Figure(utils);
                render.setFigure(this.figure);
                utils.setGameOver(figure.isCrossDown());
            } else {
                figure.fallDown();
            }
        }

    }

    public void checkFilling() {
        int row = utils.FIELD_HEIGHT - 1;
        int countFillRows = 0;
        while (row > 0) {
            int filled = 1;
            for (int col = 0; col < utils.FIELD_WIDTH; col++)
                filled *= Integer.signum(utils.getMine()[row][col]);
            if (filled > 0) {
                countFillRows++;
                for (int i = row; i > 0; i--)
                    System.arraycopy(utils.getMine()[i - 1], 0,
                            utils.getMine()[i], 0, utils.FIELD_WIDTH);
            } else
                row--;
        }
        if (countFillRows > 0) {
            gameScore += utils.SCORES[countFillRows - 1];
            frame.setTitle(utils.TITLE_OF_PROGRAM + " : " + gameScore);
        }
    }
}
