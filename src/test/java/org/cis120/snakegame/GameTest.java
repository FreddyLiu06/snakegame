package org.cis120.snakegame;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testSnakeMove() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        assertEquals(grid[0][0], snake.getHead());
        snake.move(grid[0][1]);
        assertNotEquals(grid[0][0], snake.getHead());
        assertEquals(grid[0][1], snake.getHead());
    }

    @Test
    public void testSnakeMoveLongSnake() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        snake.move(grid[0][1]);
        snake.grow(1);
        snake.move(grid[0][2]);
        snake.grow(1);
        snake.move(grid[0][3]);
        snake.move(grid[0][4]);
        snake.move(grid[1][4]);
        Queue<GridBox> body = new LinkedList<>();
        body.add(grid[0][3]);
        body.add(grid[0][4]);
        body.add(grid[1][4]);
        assertEquals(snake.getSnakeBody(), body);
        assertEquals(grid[1][4], snake.getHead());
        assertEquals(BoxType.EMPTY, grid[0][0].type());
        assertEquals(BoxType.EMPTY, grid[0][1].type());
        assertEquals(BoxType.EMPTY, grid[0][2].type());
        assertEquals(BoxType.SNAKE, grid[0][3].type());
        assertEquals(BoxType.SNAKE, grid[0][4].type());
        assertEquals(BoxType.SNAKE, grid[1][4].type());
    }

    @Test
    public void testSnakeMoveOutOfBounds() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> snake.move(grid[-1][0]));
    }

    @Test
    public void testSnakeCrashNull() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        assertTrue(snake.crash(null));
    }

    @Test
    public void testSnakeCrash() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        snake.move(grid[0][1]);
        snake.grow(1);
        snake.move(grid[0][2]);
        snake.grow(1);
        snake.move(grid[0][3]);
        snake.grow(1);
        snake.move(grid[0][4]);
        snake.move(grid[1][4]);
        snake.move(grid[1][3]);
        assertTrue(snake.crash(grid[0][3]));
        assertFalse(snake.crash(grid[1][2]));
    }

    @Test
    public void testEatFood() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        grid[2][4].setType(BoxType.FOODREG);
        Food1 food = new Food1(2, 4);
        assertTrue(food.checkCollision(grid[2][4]));
        assertFalse(food.checkCollision(grid[3][4]));
    }

    @Test
    public void testGrowSize() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        snake.move(grid[0][1]);
        snake.grow(5);
        assertEquals(6, snake.getSnakeBody().size());
    }

    @Test
    public void testGrowNegative() {
        GridBox[][] grid = new GridBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }
        Snake snake = new Snake(grid[0][0]);
        snake.move(grid[0][1]);
        snake.grow(-1);
        assertEquals(1, snake.getSnakeBody().size());
        Queue<GridBox> q = new LinkedList<>();
        q.add(grid[0][1]);
        assertEquals(q, snake.getSnakeBody());
    }
}
