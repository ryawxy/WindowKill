package Controller;

import Model.ShotGun;
import View.GameFrame;

import java.io.IOException;

public class Intersection {
    GameFrame gameFrame;
    private boolean shotIntersectsFrame;
    public Intersection(GameFrame gameFrame) throws IOException {
        this.gameFrame = gameFrame;
    }

    public Direction shotIntersectsFrame(ShotGun shotGun){

        if(shotGun.getX()>=gameFrame.getFRAME_WIDTH()){
            return Direction.RIGHT;
        } if(shotGun.getY() >= gameFrame.getFRAME_HEIGHT()){
            return Direction.DOWN;
        } if(shotGun.getX() <= 0){
            return Direction.LEFT;
        } if(shotGun.getY() <= 0){
            return Direction.UP;
        }
        return null;
    }


}
