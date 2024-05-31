package View;

import Controller.Game;
import Model.Drawable;
import Model.Epsilon;

import java.awt.*;

public class EpsilonView implements Drawable {

    private final Epsilon epsilon = Game.getEpsilon();

    @Override
    public void paint(Graphics2D g) {

        g.setColor(Color.RED);
        g.drawOval(epsilon.getX(), epsilon.getY(), epsilon.getRadius(), epsilon.getRadius());

    }
}
