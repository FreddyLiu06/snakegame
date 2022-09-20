package org.cis120.snakegame;

import java.awt.*;

public interface Eatable {
    public void setRow(int r);

    public void setCol(int c);

    public int generateRow();

    public int generateCol();

    public int getRow();

    public int getCol();

    public boolean checkCollision(GridBox nextBox);

    public void draw(Graphics g);

    public int getCollectPoint();
}
