package Model;

import javax.swing.*;

public class Collectible extends GameObjects  {
    private int radius;
    private boolean show;
    public Collectible(int x, int y, JFrame localFrame) {
        super(x, y);
        setLocalFrame(localFrame);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
    }

    @Override
    public int getWidth() {
        return radius;
    }

    @Override
    public int getHeight() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

}
