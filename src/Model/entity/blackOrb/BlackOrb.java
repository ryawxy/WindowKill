package Model.entity.blackOrb;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import view.entityViews.blackOrb.BlackOrbFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BlackOrb extends GameObjects {

    private final ArrayList<Laser> lasers = new ArrayList<>();
    private Paint spot;

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
            for(Laser laser : lasers){
                int x = random.nextInt(laser.getWidth());
            }
        }
    }
}


