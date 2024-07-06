package Model.entity.blackOrb;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Laser {

    Point2D start;
    Image image;
    Point2D end;
    int width;
    int height;
    BlackOrb blackOrb1;
    BlackOrb blackOrb2;
    JFrame localFrame;
    private boolean visible = true;
    private double angle;

    public Laser(Point2D start, Image image) {
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

    public JFrame getLocalFrame() {
        return localFrame;
    }

    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }

    public Point2D getEnd() {
        return end;
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setStart(Point2D start) {
        this.start = start;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
