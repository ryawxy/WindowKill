package Model;

import myproject.ProjectData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Epsilon extends GameObjects implements movable {

    private double xVelocity;
    private double yVelocity;
    private int radius;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;
    private int HP = 100;
    private int XP = 110;


    public Epsilon(int x, int y) throws IOException {
        super(x, y);
        setX(x);
        setY(y);

    }

    @Override
    public void move() {
        this.setX((int) (getX()+xVelocity));
        this.setY((int) (getY()+yVelocity));
        this.setxCenter( (getxCenter()+xVelocity));
        this.setyCenter( (getyCenter()+yVelocity));

    }


    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getxCenter() {
        return getX()+radius;
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public double getyCenter() {
        return getY()+radius;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
    public void increaseXP(){
        setXP(getXP()+5);
    }
    public void decreaseHP(){
        setHP(getHP()-10);

    }
}
