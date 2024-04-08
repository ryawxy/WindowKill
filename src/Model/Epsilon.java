package Model;

import myproject.ProjectData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Epsilon extends GameObjects implements movable {

    private int xVelocity;
    private int yVelocity;
    private int radius;
    private int xCenter;
    private int yCenter;
    private int HP;
    private int XP;

    public Epsilon(int x, int y) throws IOException {
        super(x, y);
        setX(x);
        setY(y);
//        this.setWidth(100);
//        this.setHeight(100);
//        this.setSize(getWidth(),getHeight());
        //   background = ImageIO.read(new File("C:\\Users\\Namazi\\Ap2024\\windowKill\\src"));
    }

    @Override
    public void move() {
        this.setX(getX()+xVelocity);
        this.setY(getY()+yVelocity);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        //    g2D.drawImage(background,0,0,getWidth(),getHeight(),null);
        g2D.setColor(Color.RED);
        g2D.drawOval(100,100,50,50);
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
}
