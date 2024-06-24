package Model.Entity;

import Controller.Game;
import Model.Entity.Epsilon;
import Model.GameObjects;
import Model.Movable;

public class Cerberus extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private double xVelocity2;
    private double yVelocity2;
    private int radius = 6;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;

    public Cerberus(int x, int y) {
        super(x, y);
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

    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
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

}
