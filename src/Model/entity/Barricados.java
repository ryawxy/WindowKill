package Model.entity;

import Controller.Constants;
import Model.GameObjects;
import Model.enums.BarricadosType;
import javax.swing.*;

public class Barricados extends GameObjects  {
    BarricadosType type;
    private final long currentTime = System.currentTimeMillis();
    private boolean visible = true;
    private JFrame itsFrame;
    public Barricados(int x, int y) {
        super(x, y);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
    }

    public void invisible(){

        if((System.currentTimeMillis()-currentTime)/1000>=120) visible = false;
    }

    public BarricadosType getType() {
        return type;
    }

    public void setType(BarricadosType type) {
        this.type = type;
    }

    @Override
    public int getWidth() {
        return Constants.barricadosWidth();
    }

    @Override
    public int getHeight() {
        return Constants.barricadosWidth();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }
}
