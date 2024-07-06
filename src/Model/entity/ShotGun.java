package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import view.GlassFrame;

import javax.swing.*;
import java.util.ArrayList;

public class ShotGun extends GameObjects implements Movable {

    private int width;
    private int height;
    private double xVelocity;
    private double yVelocity;
    private boolean onFire;
    private int expansion;
    // time interval that each shot expands the frame
    private boolean visible = true;
    private JFrame localFrame = Game.getEpsilon().getLocalFrame();
    private JFrame previousLocalFrame =  Game.getEpsilon().getLocalFrame();
    private int localX;
    private int localY;
    private ArrayList<JFrame> localFrames =new ArrayList<>();



    public ShotGun(int x, int y) {
        super(x, y);
        localX = x;
        localY = y;
        localFrames.add(GlassFrame.getINSTANCE());

    }


    @Override
    public void move() {
        setX((int) (getX()+xVelocity));
        setY((int) (getY()+yVelocity));
        setLocalX((int) (getLocalX()+xVelocity));
        setLocalY((int) (getLocalY()+yVelocity));
    }

    @Override
    public int getWidth() {
        return Constants.getShotGunWidth();
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return Constants.getShotGunHeight();
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public boolean isOnFire() {
        return onFire;
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

    @Override
    public JFrame getLocalFrame() {
        return localFrame;
    }

    @Override
    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }

    @Override
    public JFrame getPreviousLocalFrame() {
        return previousLocalFrame;
    }

    @Override
    public void setPreviousLocalFrame(JFrame previousLocalFrame) {
        this.previousLocalFrame = previousLocalFrame;
    }

    @Override
    public int getLocalX() {
        return localX;
    }

    @Override
    public void setLocalX(int localX) {
        this.localX = localX;
    }

    @Override
    public int getLocalY() {
        return localY;
    }

    @Override
    public void setLocalY(int localY) {
        this.localY = localY;
    }

    @Override
    public int getGlobalX() {
        return localX+localFrame.getX();
    }

    @Override
    public int getGlobalY() {
        return localY+localFrame.getY();
    }

}
