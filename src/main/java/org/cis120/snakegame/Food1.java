package org.cis120.snakegame;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.util.Random;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class Food1 extends GridBox implements Eatable {

    private Color color = Color.YELLOW;
    private int collectPoint = 1;

    public Food1(int row, int column) {
        super(row, column, BoxType.FOODREG);
    }

    public int getCollectPoint() {
        return this.collectPoint;
    }

    public int generateRow() {
        Random rand = new Random();
        int r = rand.nextInt(GameCourt.NUMBERCELLS);
        return r;
    }

    public int generateCol() {
        Random rand = new Random();
        int r = rand.nextInt(GameCourt.NUMBERCELLS);
        return r;
    }

    public boolean checkCollision(GridBox nextBox) {
        return (nextBox.type() == BoxType.FOODREG);
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getY(), this.getX(), GameCourt.CELLSIZE, GameCourt.CELLSIZE);
    }
}