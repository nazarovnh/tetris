package org.example.block;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    final int BLOCK_SIZE = 25; // size of block
    final int ARC_RADIUS = 6; // border radius block


    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g, int color){
        g.setColor(new Color(color));
        g.drawRoundRect(x*BLOCK_SIZE+1, y*BLOCK_SIZE+1,
                BLOCK_SIZE-2, BLOCK_SIZE-2, ARC_RADIUS, ARC_RADIUS);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
