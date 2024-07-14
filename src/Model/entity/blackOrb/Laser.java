package Model.entity.blackOrb;

import Model.GameObjects;


import java.awt.*;
import java.awt.geom.Point2D;

public class Laser extends GameObjects {
    Point2D start;
    Image image;
    Point2D end;
    BlackOrb blackOrb1;
    BlackOrb blackOrb2;
    private boolean visible = true;
    private double angle;

    public Laser(Point2D start, Image image) {
        super((int) start.getX(), (int) start.getY());
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        this.start = start;
        this.image = image;
    }

    public Point2D getStart() {
        return start;
    }
    public Image getImage() {
        return image;
    }

    public BlackOrb getBlackOrb1() {
        return blackOrb1;
    }

    public void setBlackOrb1(BlackOrb blackOrb1) {
        this.blackOrb1 = blackOrb1;
    }

    public BlackOrb getBlackOrb2() {
        return blackOrb2;
    }

    public void setBlackOrb2(BlackOrb blackOrb2) {
        this.blackOrb2 = blackOrb2;
    }

    public Point2D getEnd() {
        return end;
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }
    public void setStart(Point2D start) {
        this.start = start;
    }
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
