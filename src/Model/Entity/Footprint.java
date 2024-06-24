package Model.Entity;

import java.awt.geom.Point2D;

public class Footprint {
    private Point2D position;
    private int timer;
    private boolean visible = true;

    public Footprint(Point2D position) {
        this.position = position;

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
}
