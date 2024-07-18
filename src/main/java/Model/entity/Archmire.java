package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import Model.enums.ArchmireType;
import view.GlassFrame;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Archmire extends GameObjects implements Movable {
    private ArchmireType archmireType;
    private final ArrayList<Footprint> footprints;
    private boolean mitosis;

    public Archmire(int x, int y, ArchmireType archmireType) {
        super(x, y);
        this.archmireType = archmireType;
        setLocalFrame(GlassFrame.getINSTANCE());
        setPreviousLocalFrame(GlassFrame.getINSTANCE());
        getLocalFrames().add(getLocalFrame());
        setHP(30);
        footprints = new ArrayList<>();
        Game.getEnemies().add(this);
        Game.getArchmires().add(this);

    }

    @Override
    public void move() {
        Epsilon epsilon = Game.getEpsilon();

        double angle;
        if(epsilon.getLocalFrame().equals(getLocalFrame())){
             angle = Math.atan2(epsilon.getLocalY()-getLocalY(),epsilon.getLocalX()-getLocalX());
        }else{
             angle = Math.atan2(epsilon.getLocalY()+epsilon.getLocalFrame().getY()-(getLocalY()+getLocalFrame().getY()),
                    epsilon.getLocalX()+epsilon.getLocalFrame().getX()-(getLocalX()+getLocalFrame().getX()));
        }

        setXVelocity(3*Math.cos(angle));
        setYVelocity(3*Math.sin(angle));

        Rectangle archmire = new Rectangle(getLocalX()+getLocalFrame().getX(),getLocalY()+getLocalFrame().getY(),getWidth(),getHeight());
        Rectangle epsilon1 = new Rectangle(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
                epsilon.getLocalY()+epsilon.getLocalFrame().getY(),epsilon.getWidth(),epsilon.getHeight());
        if(archmire.intersects(epsilon1)){
            setXVelocity(0);
            setYVelocity(0);
        }


        setX((int) (getX() + getXVelocity()));
        setY((int) (getY() + getYVelocity()));
        setLocalX((int) (getLocalX()+getXVelocity()));
        setLocalY((int) (getLocalY()+getYVelocity()));
        Footprint footprint = new Footprint(new Point2D.Double(getLocalX(), getLocalY()));
        footprint.setLocalFrame(getLocalFrame());
        footprint.setPreviousLocalFrame(getLocalFrame());
        footprint.getLocalFrames().add(getLocalFrame());
           footprints.add(footprint);

    }

    public ArrayList<Footprint> getFootprint() {
        return footprints;
    }

    public void updateFootprint() {

        Iterator<Footprint> footprintIterator = footprints.iterator();
        while(footprintIterator.hasNext()){
            Footprint footprint = footprintIterator.next();
            footprint.fade();
             if(!footprint.isVisible()) footprintIterator.remove();

        }
    }

    public void createGhostArchmire(){
        if(isDead() && !archmireType.equals(ArchmireType.GHOST)) {

            Random random = new Random();
            int radius = Math.max(getHeight(), getWidth());
            for (int i = 0; i < 2; i++) {
                double angle = 2 * Math.PI * random.nextDouble();
                int distance = random.nextInt(radius);

                int x = (int) (getLocalX() + distance * Math.cos(angle));
                int y = (int) (getLocalY() + distance * Math.sin(angle));
                Archmire archmire = new Archmire(x,y,ArchmireType.GHOST);
                archmire.setWidth(getWidth()/2);
                archmire.setHeight(getHeight()/2);
                archmire.setHP(15);
                archmire.setLocalFrame(getLocalFrame());
                archmire.setPreviousLocalFrame(getLocalFrame());
                archmire.getLocalFrames().clear();
                archmire.getLocalFrames().add(archmire.getLocalFrame());
                Game.getEnemies().add(archmire);
                Game.getArchmires().add(archmire);
                setMitosis(true);

            }
        }
    }

    @Override
    public int getWidth() {
        if(archmireType.equals(ArchmireType.MINI)) return Constants.miniArchmireWidth();
        if(archmireType.equals(ArchmireType.LARGE)) return Constants.largeArchmireWidth();

        return 50;
    }

    @Override
    public int getHeight() {
        if(archmireType.equals(ArchmireType.MINI)) return Constants.miniArchmireHeight();
        if(archmireType.equals(ArchmireType.LARGE)) return Constants.largeArchmireHeight();

        return 50;
    }
    public ArchmireType getsize() {
        return archmireType;
    }

    public void setSize(ArchmireType archmireType) {
        this.archmireType = archmireType;
    }
    @Override
    public int getNumCollectibles() {
        if(getsize().equals(ArchmireType.MINI))return 2;
        else return 5;
    }

    public boolean isMitosis() {
        return mitosis;
    }

    public void setMitosis(boolean ghost) {
        this.mitosis = ghost;
    }

    public ArchmireType getArchmireType() {
        return archmireType;
    }
}
