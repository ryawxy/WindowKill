package Model.entity.smiley;

import Model.GameObjects;
import Model.Movable;
import Model.enums.SmileyHandSide;
import javax.swing.*;


public class SmileyPunch extends GameObjects implements Movable {

    private SmileyHandSide smileyHandSide;
    private int x;
    private int y;
    private int localX;
    private int localY;
    private JFrame localFrame;
    private double xVelocity;
    private double yVelocity;
    public SmileyPunch(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {

    }

    public SmileyHandSide getSmileyHandSide() {
        return smileyHandSide;
    }

    public void setSmileyHandSide(SmileyHandSide smileyHandSide) {
        this.smileyHandSide = smileyHandSide;
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
    public int getLocalX() {
        return localX;
    }

    @Override
    public void setLocalX(int localX) {
        this.localX = localX;
    }

    @Override
    public int getLocalY() {
        return localY;
    }

    @Override
    public void setLocalY(int localY) {
        this.localY = localY;
    }

    @Override
    public JFrame getLocalFrame() {
        return localFrame;
    }

    @Override
    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }

    @Override
    public double getxVelocity() {
        return xVelocity;
    }

    @Override
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    @Override
    public double getyVelocity() {
        return yVelocity;
    }

    @Override
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
}
