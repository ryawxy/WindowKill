package Model.entity;

import Controller.Constants;
import Model.Collectible;
import Model.GameObjects;
import Model.Movable;
import Model.enums.Size;
import view.GlassFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Archmire extends GameObjects implements Movable {

    private int HP = 30;
    private boolean dead;
    private boolean showCollectibles;
    private boolean visible = true;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private double xVelocity;
    private double yVelocity;
    private Size size;
    private final ArrayList<Footprint> footprints;
    private JFrame localFrame = GlassFrame.getINSTANCE();
    private JFrame previousLocalFrame = GlassFrame.getINSTANCE();
    private int localX;
    private int localY;

    public Archmire(int x, int y, Size size) {
        super(x, y);
        this.size = size;
        footprints = new ArrayList<>();
        initializeCollectibles();
        setLocalX(getX());
        setLocalY(getY());
    }

    @Override
    public void move() {

           xVelocity = 0;
        yVelocity = 1;

        setX((int) (getX() + xVelocity));
        setY((int) (getY() + yVelocity));
        Footprint footprint = new Footprint(new Point2D.Double(getX(), getY()));
           footprints.add(footprint);

    }

    public ArrayList<Footprint> getFootprint() {
        return footprints;
    }

    public void fadeFootprint() {
        for (Footprint footprint : footprints) {

            while (isVisible()) {

                footprint.setTimer(footprint.getTimer() + 1);



                if (footprint.getTimer() >= 500) setVisible(false);
            }

        }
    }

    @Override
    public int getWidth() {
        if(size.equals(Size.MINI)) return Constants.miniArchmireWidth();
        else return Constants.largeArchmireWidth();
    }

    @Override
    public int getHeight() {
        if(size.equals(Size.MINI)) return Constants.miniArchmireHeight();
        else return Constants.largeArchmireHeight();
    }

    @Override
    public void decreaseHP(int decrement) {
        setHP(getHP() - decrement);
        if (getHP() <= 0) {
            setDead(true);
            setShowCollectibles(true);
        }

    }
    public void initializeCollectibles(){
        if(size.equals(Size.LARGE)){
            Collectible collectible1 = new Collectible(getX(),getY());
            collectible1.setRadius(10);

            Collectible collectible2 = new Collectible(getX()+10,getY()+10);
            collectible2.setRadius(10);

            Collectible collectible3 = new Collectible(getX()+10,getY()-10);
            collectible3.setRadius(10);

            Collectible collectible4 = new Collectible(getX()-10,getY()+10);
            collectible4.setRadius(10);

            Collectible collectible5 = new Collectible(getX()-10,getY()-10);
            collectible5.setRadius(10);

            collectibles.add(collectible1);
            collectibles.add(collectible2);
            collectibles.add(collectible3);
            collectibles.add(collectible4);
            collectibles.add(collectible5);
        }else{
            Collectible collectible1 = new Collectible(getX(),getY());
            collectible1.setRadius(10);

            Collectible collectible2 = new Collectible(getX()+10,getY()+10);
            collectible2.setRadius(10);

            Collectible collectible3 = new Collectible(getX()+10,getY()-10);
            collectible3.setRadius(10);

            collectibles.add(collectible1);
            collectibles.add(collectible2);

        }
    }

    @Override
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    @Override
    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    @Override
    public double getxVelocity() {
        return xVelocity;
    }

    @Override
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    @Override
    public double getyVelocity() {
        return yVelocity;
    }

    @Override
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }


    public Size getsize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ArrayList<Footprint> getFootprints() {
        return footprints;
    }

    @Override
    public int getLocalX() {
        return localX;
    }

    @Override
    public int getLocalY() {
        return localY;
    }

    @Override
    public JFrame getLocalFrame() {
        return localFrame;
    }

    @Override
    public JFrame getPreviousLocalFrame() {
        return previousLocalFrame;
    }

    @Override
    public int getGlobalX() {
        return super.getGlobalX();
    }

    @Override
    public int getGlobalY() {
        return super.getGlobalY();
    }
}
