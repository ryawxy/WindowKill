package view.entityViews.wyrm;

import controller.Game;
import model.entity.Epsilon;
import model.entity.Wyrm;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;


public class WyrmFrame extends JFrame {

    Wyrm wyrm;
    Rectangle bound;
   private double angle;
   private double xVelocity;
   private double yVelocity;
   private double rotationAngle;

    public WyrmFrame(int x, int y){

        this.setSize(150,150);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Wyrm frame");
        WyrmPanel wyrmPanel = new WyrmPanel(150,150);
        this.setContentPane(wyrmPanel);
        wyrmPanel.setBound(new Rectangle(x,y,150,150));
        wyrmPanel.setItsFrame(this);
        this.setVisible(true);
        wyrm = new Wyrm(30,20);
        wyrm.setLocalFrame(this);
        wyrm.setItsPanel(wyrmPanel);
        wyrmPanel.setWyrmView(new WyrmView(wyrm,wyrmPanel.getRotationAngle()));
        bound = new Rectangle(getX(),getY(),getWidth(),getHeight());
        Game.getFrames().add(this);
        Game.getEnemies().add(wyrm);

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

    public Wyrm getWyrm() {
        return wyrm;
    }
}
