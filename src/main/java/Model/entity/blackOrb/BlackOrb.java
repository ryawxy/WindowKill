package Model.entity.blackOrb;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import view.entityViews.blackOrb.BlackOrbFrame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class BlackOrb extends GameObjects {

    private final ArrayList<Laser> lasers = new ArrayList<>();
    private Point spot;

    public BlackOrb(int x, int y) {
        super(x, y);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        setHP(30);

    }


    public ArrayList<Laser> getLasers() {
        return lasers;
    }
    @Override
    public int getHeight() {
        return Constants.orbSize();
    }

    @Override
    public int getWidth() {
        return Constants.orbSize();
    }

    @Override
    public int getNumCollectibles() {
        return 5;
    }

    @Override
    public void decreaseHP(int decrement) {
        super.decreaseHP(decrement);
        if(isDead()) {
            for (Laser laser : getLasers()) {

                laser.setVisible(false);
            }
            for (BlackOrbFrame blackOrbFrame1 : Game.getBlackOrbFrames()) {
                if (!blackOrbFrame1.equals(getLocalFrame())) {
                    for (Laser laser : blackOrbFrame1.getBlackOrb().getLasers()) {
                        if (laser.getBlackOrb2().equals(this))
                            laser.setVisible(false);
                    }
                }
            }
        }
    }
    public void avalanche(){
        if(spot == null){
            Random random = new Random();
            int index  = random.nextInt(lasers.size());
            Laser laser = lasers.get(index);
            Rectangle laserBounds = new Rectangle(-laser.getWidth()/2,-laser.getHeight()/2,laser.getWidth(),laser.getHeight());
            Area laserArea = new Area(laserBounds);
            Area laserArea2 = new Area(laserBounds);
            AffineTransform transform = new AffineTransform();
            AffineTransform transform2 = new AffineTransform();
            transform.translate(laser.getLocalX()+laser.getBlackOrb1().getLocalFrame().getX(),
                    laser.getLocalY()+laser.getBlackOrb1().getLocalFrame().getY());
            transform2.translate(laser.getLocalX()+laser.getBlackOrb2().getLocalFrame().getX(),
                    laser.getLocalY()+laser.getBlackOrb2().getLocalFrame().getY());
            laserArea.transform(transform);
            laserArea2.transform(transform2);
            double halfWidth = (double) laser.getWidth() /2;
            double halfHeight = (double) laser.getHeight() /2;
            double randomX = -halfWidth + random.nextDouble()*laser.getWidth();
            double randomY = -halfHeight + random.nextDouble()*laser.getHeight();

            double translatedX = randomX * Math.cos(laser.getAngle()) - randomY * Math.sin(laser.getAngle());
            double translatedY = randomX * Math.sin(laser.getAngle()) + randomY * Math.cos(laser.getAngle());

            double finalX = translatedX + laser.getLocalX()+ (double) laser.getWidth() /2 + laser.getLocalX()+laser.getBlackOrb1().getLocalFrame().getX();
            double finalY = translatedY + laser.getLocalY()+ (double) laser.getHeight() /2 + laser.getLocalY()+laser.getBlackOrb1().getLocalFrame().getY();


            setSpot(new Point((int) finalX, (int) finalY));

            if(!(laserArea.contains(spot)&& laserArea2.contains(spot))) avalanche();





        }
        }

    public Point2D getSpot() {
        return spot;
    }

    public void setSpot(Point spot) {
        this.spot = spot;
    }
}



