package Model.entity;

import Controller.Game;
import Model.GameObjects;
import Model.Movable;

import javax.swing.*;
import java.util.ArrayList;

public class Vertex extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private int radius = 10;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;
    private double xVelocity2;
    private double yVelocity2;
    private int localX;
    private int localY;
    private JFrame localFrame;
    private ArrayList<JFrame> localFrames = new ArrayList<>();



    public Vertex(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        localX = getX();
        localY = getY();

        localFrame = Game.getEpsilon().getLocalFrame();
    }

    @Override
    public void move() {
        Epsilon epsilon = Game.getEpsilon();


        xVelocity = epsilon.getxVelocity();
        yVelocity = epsilon.getyVelocity();
        xVelocity2 = epsilon.getxVelocity2();
        yVelocity2 = epsilon.getyVelocity2();



        this.setX((int) (getX()+xVelocity+xVelocity2));
        this.setY((int) (getY()+yVelocity+yVelocity2));
        this.setxCenter( (getxCenter()+xVelocity+xVelocity2));
        this.setyCenter( (getyCenter()+yVelocity+yVelocity2));
        this.setLocalX((int) (getLocalX()+xVelocity+xVelocity2));
        this.setLocalY((int) (getLocalY()+yVelocity+yVelocity2));


    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getxCenter() {
        return xCenter;
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public double getyCenter() {
        return yCenter;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }


    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
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
}
