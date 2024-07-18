package Model.entity;

import Model.GameObjects;

import java.awt.*;
import java.awt.geom.Point2D;

public class Footprint extends GameObjects {
    private Point2D position;
    private float alpha;
    private static final float FADE_RATE = 0.1f;
    private long lastTime;


    public Footprint(Point2D position) {
        super((int) position.getX(), (int) position.getY());
        this.position = position;
        this.alpha = 0.3f;
    }
    public void fade() {

        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastTime) / 1000 >= 5) {
            if (alpha > 0) alpha -= FADE_RATE;
            else if(alpha<0.1f) {
                alpha = 0.1f;
                setVisible(false);
            }
            lastTime = currentTime;
        }
    }


    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public float getAlpha() {
        return alpha;
    }
}
