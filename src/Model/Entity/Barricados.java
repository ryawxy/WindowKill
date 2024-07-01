package Model.Entity;

import Controller.Constants;
import Model.Collectible;
import Model.GameObjects;
import Model.enums.BarricadosType;
import View.entityViews.Barricados.BarricadosFrame;
import View.entityViews.Barricados.BarricadosView;

public class Barricados extends GameObjects  {
    BarricadosType type;
    BarricadosFrame localFrame;
    BarricadosView barricadosView;
    public Barricados(int x, int y) {
        super(x, y);

    }

    public BarricadosType getType() {
        return type;
    }

    public void setType(BarricadosType barricadosType) {
        this.type = type;
    }

    @Override
    public BarricadosFrame getLocalFrame() {
        return localFrame;
    }

    public BarricadosView getBarricadosView() {
        return barricadosView;
    }

    public void setBarricadosView(BarricadosView barricadosView) {
        this.barricadosView = barricadosView;
    }

    @Override
    public int getWidth() {
        return Constants.barricadosWidth();
    }

    @Override
    public int getHeight() {
        return Constants.barricadosWidth();
    }
}
