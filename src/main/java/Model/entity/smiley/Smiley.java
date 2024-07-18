package Model.entity.smiley;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import Model.entity.Epsilon;
import Model.entity.ShotGun;
import view.GlassFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Smiley extends GameObjects implements Movable {
    private boolean getCloser = true;
    private double angle;
    private final ArrayList<Fire> aoeAttacks = new ArrayList<>();
    private final ArrayList<Rectangle> holes = new ArrayList<>();
    public Smiley(int x, int y) {
        super(x, y);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        setHP(300);
    }

    @Override
    public void move() {


        setXVelocity(0);
        setYVelocity(0);

        Epsilon epsilon = Game.getEpsilon();
        Point2D epsilonPosition = new Point2D.Double(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
                epsilon.getLocalY()+epsilon.getLocalFrame().getY());
        Point2D smileyPosition = new Point2D.Double(getLocalX()+getLocalFrame().getX(),getLocalY()+getLocalFrame().getY());

        double distance = epsilonPosition.distance(smileyPosition);
        int SAFE_DISTANCE = 300;

        if(distance > SAFE_DISTANCE && getCloser  ){

            setXVelocity(0);
            setYVelocity(0);

            angle = (int) Math.atan2(epsilonPosition.getY() - smileyPosition.getY(), epsilonPosition.getX() - smileyPosition.getX());

            this.setXVelocity((int) ((int) (3 * Math.cos(angle))+getXVelocity()));
            this.setYVelocity((int) ((int) (3 * Math.sin(angle))+getYVelocity()));


            getLocalFrame().setLocation((int) (getLocalFrame().getX()+getXVelocity()), (int) (getLocalFrame().getY()+getYVelocity()));

        }else{

            getCloser = false;
            angle += 0.015;

            int newX = (int) (epsilon.getLocalX()+epsilon.getLocalFrame().getX()+SAFE_DISTANCE*Math.cos(angle)-getLocalFrame().getWidth());
            int newY = (int) (epsilon.getLocalY()+epsilon.getLocalFrame().getY()+SAFE_DISTANCE*Math.sin(angle)-getLocalFrame().getHeight());

            getLocalFrame().setLocation(newX,newY);


        }

    }
    public void aoe(){
        Random random = new Random();
        if(aoeAttacks.size()<6) {
            for (int i = 0; i < 6; i++) {

                int x = random.nextInt(Game.getEpsilon().getLocalFrame().getWidth());
                int y = random.nextInt(Game.getEpsilon().getLocalFrame().getHeight());


                aoeAttacks.add(new Fire(x, y));
            }
        }
    }
    public void shot(){
        if(!isDead()) {
            if (Math.random()<0.05) {


                int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
                int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};


                ShotGun shotGun = new ShotGun(getLocalX()+Constants.smileySize()/2, getLocalY()+Constants.smileySize()/2);
                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.getLocalFrames().clear();
                shotGun.setLocalFrame(getLocalFrame());
                shotGun.getLocalFrames().add(getLocalFrame());
                shotGun.setPreviousLocalFrame(getLocalFrame());
                Random random = new Random();
                int x = random.nextInt(9);
                shotGun.setXVelocity(xDirection[x] * 5);
                shotGun.setYVelocity(yDirection[x] * 5);

                getShots().add(shotGun);
            }
        }

        for (ShotGun shot : getShots()) shot.move();
    }
    public void createHoles(int count, int maxWidth, int maxHeight){

        Random random = new Random();

        for(int i=0; i<count; i++){
            int w = random.nextInt(maxWidth);
            int h = random.nextInt(maxHeight);
            int x = random.nextInt(GlassFrame.getINSTANCE().getWidth()-w);
            int y = random.nextInt(GlassFrame.getINSTANCE().getHeight()-h);
            holes.add(new Rectangle(x,y,w,h));
        }

    }
    public void applyHoles(){

        JFrame frame = GlassFrame.getINSTANCE();
        Area area = new Area(new Rectangle(0,0,frame.getWidth(),frame.getHeight()));
        for(Rectangle hole : holes) {
            area.subtract(new Area(new Ellipse2D.Double(hole.x, hole.y, hole.width, hole.height)));
        }
        frame.setShape(area);
    }

    @Override
    public int getWidth() {
        return Constants.smileySize();
    }

    @Override
    public int getHeight() {
        return Constants.smileySize();
    }

    public ArrayList<Fire> getAoeAttacks() {
        return aoeAttacks;
    }

    public ArrayList<Rectangle> getHoles() {
        return holes;
    }
}
