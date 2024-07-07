package Model.entity.smiley;

import Model.GameObjects;

public class Fire extends GameObjects {
    private int x;
    private int y;
    private int width = 100;
    private int height = 100;
    public Fire(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
