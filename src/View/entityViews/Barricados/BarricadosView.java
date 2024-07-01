package View.entityViews.Barricados;

import Controller.Constants;
import Model.Drawable;

import java.awt.*;

public class BarricadosView implements Drawable {

    public BarricadosView() {

    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(40,40, Constants.barricadosWidth(),Constants.barricadosWidth());
    }
}
