package org.cis120.snakegame;

/*
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    private GridBox[][] grid = new GridBox[NUMBERCELLS][NUMBERCELLS];

    // the state of the game logic
    private Snake snake; // the Black Snake, controlled by the keyboard
    private Eatable food; // the food that the snake eats to gain length and gain points

    private Direction dir = Direction.NONE;

    private boolean playing = false; // whether the game is running
    private JLabel status; // Current status text, i.e. "Running...", tells the score

    private int score = 0; // score of the game, number of foodes eaten

    // Game constants
    public static final int COURT_WIDTH = 800;
    public static final int COURT_HEIGHT = 800;
    public static final int CELLSIZE = 20;
    public static final int NUMBERCELLS = COURT_HEIGHT / CELLSIZE;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 60;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // When an arrow key is pressed, the direction of the snake changes. This
        // affects the getNextBox method that
        // determines where the snake is going next.
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (dir != Direction.RIGHT) {
                        dir = Direction.LEFT;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (dir != Direction.LEFT) {
                        dir = Direction.RIGHT;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (dir != Direction.UP) {
                        dir = Direction.DOWN;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (dir != Direction.DOWN) {
                        dir = Direction.UP;
                    }
                }
            }
        });

        for (int i = 0; i < NUMBERCELLS; i++) {
            for (int j = 0; j < NUMBERCELLS; j++) {
                grid[i][j] = new GridBox(i, j, BoxType.EMPTY);
            }
        }

        this.status = status;
    }

    public GridBox[][] getGrid() {
        return Arrays.copyOf(this.grid, this.grid.length);
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        snake = new Snake(grid[1][1]);
        food = new Food1(15, 15);
        grid[15][15].setType(BoxType.FOODREG);

        dir = Direction.NONE;
        playing = true;
        status.setText("Running...");
        score = 0;

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    private GridBox getNextBox(GridBox currentBox) {
        int curRow = currentBox.getRow();
        int curCol = currentBox.getCol();
        int newRow;
        int newCol;
        try {
            if (dir == Direction.UP) {
                newRow = curRow - 1;
                return grid[newRow][curCol];
            } else if (dir == Direction.DOWN) {
                newRow = curRow + 1;
                return grid[newRow][curCol];
            } else if (dir == Direction.LEFT) {
                newCol = curCol - 1;
                return grid[curRow][newCol];
            } else if (dir == Direction.RIGHT) {
                newCol = curCol + 1;
                return grid[curRow][newCol];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            playing = false;
        }
        return currentBox;
    }

    void eatReg() {
        int currRow = food.getRow();
        int currCol = food.getCol();
        score = score + 1;
        status.setText("Score: " + score);
        snake.grow(food.getCollectPoint());
        Random rand = new Random();
        int x = rand.nextInt(5);
        if (x <= 3) {
            food = new Food1(-1, -1);
            int foodr = 0;
            int foodc = 0;
            boolean spawned = false;
            while (!spawned) {
                foodr = food.generateRow();
                foodc = food.generateCol();
                if ((grid[foodr][foodc].type() != BoxType.SNAKE) && (currRow != foodr)
                        && (currCol != foodc)) {
                    spawned = true;
                }
            }
            food.setRow(foodr);
            food.setCol(foodc);
            grid[currRow][currCol].setType(BoxType.SNAKE);
            grid[foodr][foodc].setType(BoxType.FOODREG);
        } else {
            food = new Food2(-1, -1);
            int foodr = 0;
            int foodc = 0;
            boolean spawned = false;
            while (!spawned) {
                foodr = food.generateRow();
                foodc = food.generateCol();
                if ((grid[foodr][foodc].type() != BoxType.SNAKE) && (currRow != foodr)
                        && (currCol != foodc)) {
                    spawned = true;
                }
            }
            food.setRow(foodr);
            food.setCol(foodc);
            grid[currRow][currCol].setType(BoxType.SNAKE);
            grid[foodr][foodc].setType(BoxType.FOODSPECIAL);
        }
    }

    void eatSpec() {
        int currRow = food.getRow();
        int currCol = food.getCol();
        score = score + 5;
        status.setText("Score: " + score);
        snake.grow(food.getCollectPoint());
        Random rand = new Random();
        int x = rand.nextInt(5);
        if (x <= 3) {
            food = new Food1(-1, -1);
            int foodr = 0;
            int foodc = 0;
            boolean spawned = false;
            while (!spawned) {
                foodr = food.generateRow();
                foodc = food.generateCol();
                if ((grid[foodr][foodc].type() != BoxType.SNAKE) && (currRow != foodr)
                        && (currCol != foodc)) {
                    spawned = true;
                }
            }
            food.setRow(foodr);
            food.setCol(foodc);
            grid[currRow][currCol].setType(BoxType.SNAKE);
            grid[foodr][foodc].setType(BoxType.FOODREG);
        } else {
            food = new Food2(-1, -1);
            int foodr = 0;
            int foodc = 0;
            boolean spawned = false;
            while (!spawned) {
                foodr = food.generateRow();
                foodc = food.generateCol();
                if ((grid[foodr][foodc].type() != BoxType.SNAKE) && (currRow != foodr)
                        && (currCol != foodc)) {
                    spawned = true;
                }
            }
            food.setRow(foodr);
            food.setCol(foodc);
            grid[currRow][currCol].setType(BoxType.SNAKE);
            grid[foodr][foodc].setType(BoxType.FOODSPECIAL);
        }
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            // advance the snake in their current direction.
            if (dir != Direction.NONE) {
                GridBox nxtBox = getNextBox(snake.getHead());
                if (snake.crash(nxtBox)) {
                    playing = false;
                    status.setText("You Lose! Final Score: " + score);
                    dir = Direction.NONE;
                } else if (food.checkCollision(nxtBox)) {
                    if (food.getCollectPoint() == 1) {
                        eatReg();
                    } else if (food.getCollectPoint() == 5) {
                        eatSpec();
                    }
                    snake.move(nxtBox);
                } else {
                    snake.move(nxtBox);
                }
            }
            // update the display
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        food.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}