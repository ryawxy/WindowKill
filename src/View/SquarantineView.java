package View;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.Squarantine;

import java.awt.*;

public class SquarantineView implements Drawable {
    @Override
    public void paint(Graphics2D g) {

        // if squarantine is alive paint it
        // if not show its collectible
        for (int i = 0; i < Game.getSquarantine().size(); i++) {
            Squarantine squarantine1 = Game.getSquarantine().get(i);
            if (!squarantine1.isDead()) {
                g.setColor(Color.GREEN);
                g.drawPolygon(squarantine1.getxPoints(), squarantine1.getyPoints(), 4);
            } else {
                if (squarantine1.isShowCollectibles()) {

                    g.setColor(Color.orange);
                    for (int j = 0; j < squarantine1.getCollectibles().size(); j++) {


                        Collectible collectible = squarantine1.getCollectibles().get(j);
                        g.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                    }
                }
            }
        }
    }
}
