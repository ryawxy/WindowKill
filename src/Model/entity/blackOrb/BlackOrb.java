package Model.entity.blackOrb;

import Controller.Constants;
import Model.GameObjects;
import java.util.ArrayList;

public class BlackOrb extends GameObjects {

    private final ArrayList<Laser> lasers = new ArrayList<>();
    public BlackOrb(int x, int y) {
        super(x, y);
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

}


