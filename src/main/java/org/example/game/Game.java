package org.example.game;

import org.example.figure.Figure;
import org.example.render.Render;
import org.example.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Game {

    private Utils utils = new Utils();
    private Render render;
    private Figure figure;
    JFrame frame;


    public int gameScore = 0;
    public boolean gameOver = false;

    public Game() {
        this.figure = new Figure(utils);
        render = new Render(this.figure, utils);
    }

    public void start() {
        frame = new JFrame(utils.TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(utils.FIELD_WIDTH * utils.BLOCK_SIZE + utils.FIELD_DX,
                utils.FIELD_HEIGHT * utils.BLOCK_SIZE + utils.FIELD_DY);
        frame.setLocation(utils.START_LOCATION, utils.START_LOCATION);
        frame.setResizable(false);
        render.setBackground(Color.black);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!gameOver) {
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

        while (!gameOver) {
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
                gameOver = figure.isCrossDown();
            } else {
                figure.fallDown();
            }
        }

    }

    void checkFilling() {
    }
}
