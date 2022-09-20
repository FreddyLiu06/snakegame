=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

  1. 2D Arrays
    I used a 2D array to model the grid of the game. A 2D array was appropriate because the rows and the columns of the
    grid each correspond to one of the dimensions of the array. I stored arrays of GridBox objects, where each GridBox
    represented a box on the grid. This way, I was able to store and track the state of each box on the grid by updating
    each object that corresponded to it in the array. I could then easily find the state of the game by iterating
    through the array to find out the state of each box. By repainting the GridBox objects that needed to be repainted,
    I could display the grid using this layout.

  2. Collections
    I used collections to implement the movement of the snake. More specifically, how the rest of the body keeps up
    with the head of the snakes. I used the queue interface because I simply needed a first in first out data structure,
    which queues are. I kept a queue of GameBox objects, which are boxes on the grid. If a box was in the queue then it
    meant that that box was part of the body of the snake. Each time the snake moves, the new box is enqueued to the
    front of the queue, making it the head of the snake, and the last box is dequeued from the queue, or removed from
    the tail. This helped me implement a snake where the body parts and the tail followed the movement of the head of
    the snake. I have to iterate through the collection when checking if the snake has collided with itself, because I
    must check that each box in the queue is not equal to the nextBox. I also must iterate through the collection
    whenever I repaint the snake to repaint every single object.

  3. Inheritance and Subtyping
    I used inheritance and subtyping to implement the feature of having different types of 'food' in the game. I created
    two different classes for these two types of 'food', but they both implemented the same 'Eatable' interface. This is
    so that in the GameCourt code I can just keep track of one object at a time by just referencing its static class.
    I have used dynamic dispatch in my code when generating the position of the new 'food' when the current one is
    eaten. Since I want the regular one to appear anywhere at random, and have the special one only appear in the
    corners, these methods will have different implementations, but I do not have to worry about differentiating between
    the multiple implementations because of subtyping and dynamic dispatch.

  4. JUnit Testable Component
    I have used JUnit testing when testing the basic functionality of the snake. I have included test cases on the move
    method of the snake, to check that the array of GridBox objects that represents the grid updates properly. I have
    also included cases to check that the snake does not keep moving after it hit itself or hit the wall, and that the
    game should end. I have also tested the grow function for when the snake eats a piece of food and needs to grow in
    size.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  The GridBox class is the class that represents a box on the grid of the game. It has fields to represent the position
  of the box, as well as the type of the box, and respective methods to get and set the different fields. These objects
  are stored in a 2D array to represent the boxes of the grid, and are changed accordingly to represent the state of the
  grid.
  The Snake class is a class responsible for implementing the actual snake. It stores a queue of box objects that
  represent which boxes are part of the snake at a given time. It has methods to move the snake, which takes care of
  enqueuing and de-queuing the boxes to represent the movement of the snake. There are also methods for the snake to
  grow, which means more objects are in the queue of the snake body at a given time, and to check for collisions.
  The Food1 and Food2 classes both implement the Eatable interface. The eatable interface is an interface that has
  methods for checking for collision with the snake, as well as generating a new position for a new food object.
  The GameCourt class is where most of the logic happens. This is where the timer is, and where I include my 2D array
  grid, and that takes in objects of all the previously mentioned classes and puts everything together. The logic of
  moving the snake, checking for collisions with itself and the wall as the game is being played happens. This is also
  where I check for user input and handle the inputs.
  The RunSnakeGame class is the main class that specifies the widgets and the frame for the game and displays it to the
  user.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I found it quite challenging initially to generate a new piece of food after a snake eats the current piece of food.
  First I had to decide which type of food to generate, which was done by using a random object. However the tough part
  was since I had an array of objects that each represented one square on the grid, I had to update the squares that
  were affected by the snake eating the current piece of food. That is, I had to update the fields of the objects that
  were previously food and now were not, and I had to update the new object that were to become food. This was difficult
  because I had the food objects to be implementing the eatable interface, while the gridBoxes did not. My solution to
  this was to make the food objects extend the gridBox objects, and then create a new food object every time.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I believe this design is not the most efficient for implementing a snake game. First, this may be inefficient because
  I am creating lots of objects - each box in a grid is a separate object. Additionally, every time the snake moved, I
  had to update all the box objects that were affected by this move to update the state of the grid. As discussed
  above, whenever the snake ate a piece of food, I also had to use some complex implementation to update the objects
  that were affected, which was also quite inefficient. If I had instead created something similar to the Mushroom of
  Doom example, where the snitch and the square were just 2 objects in front of a canvas widget, then I would not have
  to keep track of so many objects, I would instead just have 2 objects to update.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
