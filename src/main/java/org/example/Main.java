package org.example;

import org.example.figure.Figure;
import org.example.game.Game;
import org.example.render.Render;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}