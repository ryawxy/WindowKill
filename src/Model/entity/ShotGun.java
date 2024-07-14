package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import view.GlassFrame;

import javax.swing.*;
import java.util.ArrayList;

public class ShotGun extends GameObjects implements Movable {
    private boolean onFire;
    private int expansion;
    // time interval that each shot expands the frame
    private boolean visible = true;
    private boolean solidShot;



    public ShotGun(int x, int y) {
        super(x, y);
        setLocalX(x);
        setLocalY(y);
        setLocalFrame(GlassFrame.getINSTANCE());
        setPreviousLocalFrame(GlassFrame.getINSTANCE());
        getLocalFrames().add(GlassFrame.getINSTANCE());


    }


    @Override
    public void move() {
        setX((int) (getX()+getXVelocity()));
        setY((int) (getY()+getYVelocity()));
        setLocalX((int) (getLocalX()+getXVelocity()));
        setLocalY((int) (getLocalY()+getYVelocity()));
    }

    @Override
    public int getWidth() {
        return Constants.getShotGunWidth();
    }
    @Override
    public int getHeight() {
        return Constants.getShotGunHeight();
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public int getExpansion() {
        return expansion;
    }

    public void setExpansion(int expansion) {
        this.expansion = expansion;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isSolidShot() {
        return solidShot;
    }

    public void setSolidShot(boolean solidShot) {
        this.solidShot = solidShot;
    }
}
