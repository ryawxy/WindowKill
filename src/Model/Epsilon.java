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
    private int XP = 80;


    public Epsilon(int x, int y) throws IOException {
        super(x, y);
        setX(x);
        setY(y);

    }

    @Override
    public void move() {
        this.setX((int) (getX()+xVelocity));
        this.setY((int) (getY()+yVelocity));
        this.setxCenter((int) (getxCenter()+xVelocity));
        this.setyCenter((int) (getyCenter()+yVelocity));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        //    g2D.drawImage(background,0,0,getWidth(),getHeight(),null);
        g2D.setColor(Color.RED);
        g2D.drawOval(100,100,50,50);
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

    public int getxCenter() {
        return getX()+radius;
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return getY()+radius;
    }

    public void setyCenter(int yCenter) {
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
