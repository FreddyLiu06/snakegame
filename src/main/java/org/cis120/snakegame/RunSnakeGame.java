package org.cis120.snakegame;
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * 
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunSnakeGame implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Snake Game");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Pop-up prompt
        String message = "This is my implementation of the snake game. In this game, " +
                "you control the black snake that roams around the board. " +
                "\nYour objective is to control the snake to capture the pieces of " +
                "'food' that will be in yellow and green,"
                +
                " that are lying around the board.\nT" +
                "he snake automatically moves, but you are " +
                "able to control the direction it moves in using the arrow keys.\n"
                +
                "As the snake moves forward, the trail of its body parts follows it from behind.\n"
                +
                "Once the snake captures a piece of food, you" +
                " gain a point to your score, but the snake also gains a unit in length.\n"
                +
                "A new piece of 'food' is randomly generated after this and the process " +
                "would repeat. "
                +
                "Moving into the wall or moving into a part of the " +
                "snake's body will cause you to lose and cause the game to end.\n"
                +
                "The green pieces of 'food' are special in that t" +
                "hey only spawn in the corners of the board, but th" +
                "ey are worth 5 points and capturing them makes you gain 5 units of length.\n"
                +
                "These green pieces have a lower probability of s" +
                "pawning compared to the regular yellow pieces of 'food'.\n"
                +
                "The game gets increasingly more challenging as it is harder to avoid the snak" +
                "e's body parts itself.";
        JOptionPane.showMessageDialog(frame, message);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }
}