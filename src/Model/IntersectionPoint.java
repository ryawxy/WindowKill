package Model;

import java.awt.geom.Point2D;

public class IntersectionPoint {

    private Point2D point;
    private int elapsedTime = 0;
    private final int time;

    public IntersectionPoint(Point2D point, int time) {
        this.point = point;
        this.time = time;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getTime() {
        return time;
    }


}
