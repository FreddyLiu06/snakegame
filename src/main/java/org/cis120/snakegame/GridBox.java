package org.cis120.snakegame;

public class GridBox {

    private int row;
    private int col;
    private BoxType type;

    public GridBox(int r, int c, BoxType t) {
        this.row = r;
        this.col = c;
        this.type = t;
    }

    public int getX() {
        return this.row * GameCourt.CELLSIZE;
    }

    public int getY() {
        return this.col * GameCourt.CELLSIZE;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setRow(int r) {
        this.row = r;
    }

    public void setCol(int c) {
        this.col = c;
    }

    public BoxType type() {
        return this.type;
    }

    public void setType(BoxType t) {
        this.type = t;
    }
}
