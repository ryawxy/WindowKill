package Model.entity.smiley;

import Controller.Constants;
import Controller.Game;
import Model.*;
import Model.entity.Epsilon;
import Model.enums.PunchType;
import view.GlassFrame;
import view.SettingsFrame;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;


public class SmileyPunch extends GameObjects implements Movable, FrameType {

    private PunchType punchType;
    private int punchSize = 40;
    private int alpha = 255;
    private final ArrayList<Point> cracks = new ArrayList<>();
    private final Random random;
    private final Epsilon epsilon = Game.getEpsilon();
    public SmileyPunch(int x, int y) {
        super(x, y);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        random = new Random();
    }

    @Override
    public void move() {
        double angle = Math.atan2(epsilon.getLocalY()+epsilon.getLocalFrame().getY()-(getLocalFrame().getY()+getLocalY()),
                epsilon.getLocalX()+epsilon.getLocalFrame().getX()-(getLocalFrame().getX()+getLocalX()));
        getLocalFrame().setLocation((int) (getLocalFrame().getX()+Math.cos(angle)*3.0), (int) (getLocalFrame().getY()+Math.sin(angle)*3.0));

        setXVelocity(Math.cos(angle)*5.0);
        setYVelocity(Math.sin(angle)*5.0);

        setLocalX((int) (getLocalX()+getXVelocity()));
        setLocalY((int) (getLocalY()+getYVelocity()));




    }

    public void quakeEffect(){

        if(punchSize<=120) {
            punchSize += 20;
            alpha -= 100;
        }

        if(punchSize==120) {

            int x = random.nextInt(GlassFrame.getINSTANCE().getWidth() / 2);
            int y = random.nextInt(GlassFrame.getINSTANCE().getHeight() / 2);
            Point2D point2D = new Point2D.Double(getLocalX()+getLocalFrame().getX(),getLocalY()+getLocalFrame().getY());
            ObjectsIntersection.getIntersectionPoints().add(new IntersectionPoint(point2D,20, false,false,
                    Game.getEpsilon(),this));
            cracks.add(new Point(x, y));
        }

            if (alpha <= 0) {
                alpha = 0;

            }
        }
    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }

    @Override
    public int getHeight() {
        return Constants.smileyPunchHeight();
    }

    @Override
    public int getWidth() {
        return Constants.smileyPunchWidth();
    }

    @Override
    public boolean isometric() {
        return true;
    }

    @Override
    public boolean solid() {
        return true;
    }

    @Override
    public Rectangle getBound() {
        return null;
    }

    public int getPunchSize() {
        return punchSize;
    }

    public int getAlpha() {
        return alpha;
    }

    public ArrayList<Point> getCracks() {
        return cracks;
    }
}
