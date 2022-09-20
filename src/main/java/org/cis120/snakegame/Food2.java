package org.cis120.snakegame;

import java.awt.*;
import java.util.Random;

public class Food2 extends GridBox implements Eatable {

    private Color color = Color.GREEN;
    private int collectPoint = 5;

    public Food2(int r, int c) {
        super(r, c, BoxType.FOODSPECIAL);
    }

    public int getCollectPoint() {
        return this.collectPoint;
    }

    @Override
    public int generateRow() {
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 0) {
            return 0;
        } else {
            return GameCourt.NUMBERCELLS - 1;
        }
    }

    @Override
    public int generateCol() {
        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 0) {
            return 0;
        } else {
            return GameCourt.NUMBERCELLS - 1;
        }
    }

    @Override
    public boolean checkCollision(GridBox nextBox) {
        return (nextBox.type() == BoxType.FOODSPECIAL);
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getY(), this.getX(), GameCourt.CELLSIZE, GameCourt.CELLSIZE);
    }
}
