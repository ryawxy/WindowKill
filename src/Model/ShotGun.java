package Model;

import View.GameFrame;

import java.util.ArrayList;

public class ShotGun extends GameObjects implements movable{

    private int width;
    private int height;
    private int xVelocity;
    private int yVelocity;

    public ShotGun(int x, int y) {
        super(x, y);

    }


    @Override
    public void move() {
        setX(getX()+xVelocity);
        setY(getY()+yVelocity);
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

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

}
