package org.cis120.snakegame;

/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Snake {

    private Color color;

    private Queue<GridBox> snakeBody = new LinkedList<>();
    private GridBox head;

    public Snake(GridBox initialBox) {
        head = initialBox;
        head.setType(BoxType.SNAKE);
        snakeBody.add(head);
    }

    public GridBox getHead() {
        return this.head;
    }

    public Queue<GridBox> getSnakeBody() {
        return new LinkedList<>(this.snakeBody);
    }

    public void move(GridBox nextBox) {
        head = nextBox;
        snakeBody.add(nextBox);
        nextBox.setType(BoxType.SNAKE);
        GridBox tailBox = snakeBody.remove();
        tailBox.setType(BoxType.EMPTY);
    }

    public void grow(int g) {
        if (g >= 1) {
            for (int i = 0; i < g; i++) {
                snakeBody.add(head);
            }
        }
    }

    public boolean crash(GridBox nextBox) {
        if (nextBox == null) {
            return true;
        }
        for (GridBox box : snakeBody) {
            if (box == nextBox) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        for (GridBox box : snakeBody) {
            g.fillRect(box.getY(), box.getX(), GameCourt.CELLSIZE, GameCourt.CELLSIZE);
        }
    }
}