package Model.entity;

import java.awt.*;
import java.awt.geom.Point2D;

public class Footprint {
    private Point2D position;
    private  int timer;
    private static int lastTime;
    private boolean visible = true;
    private static   Color color = new Color(164, 200, 217,255);

    public Footprint(Point2D position) {
        this.position = position;
        color = new Color(164, 200, 217);

    }
    public void fade(){



        }


    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public static   Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
