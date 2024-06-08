package Model;

import Controller.Game;

public class Vertex extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private int radius = 10;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;
    private double xVelocity2;
    private double yVelocity2;



    public Vertex(int x, int y) {
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
}
