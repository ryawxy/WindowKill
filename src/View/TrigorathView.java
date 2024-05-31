package View;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.Trigorath;

import java.awt.*;

public class TrigorathView implements Drawable {
    @Override
    public void paint(Graphics2D g) {

        // if trigorath is alive paint it
        // if not show its collectible

        for (int i = 0; i < Game.getTrigoraths().size(); i++) {
            Trigorath trigorath1 = Game.getTrigoraths().get(i);
            if (!trigorath1.isDead()) {
                g.setColor(Color.YELLOW);
                g.drawPolygon(trigorath1.getxPoints(), trigorath1.getyPoints(), 3);
            } else {
                if (trigorath1.isShowCollectibles()) {

                    g.setColor(Color.orange);
                    for (int j = 0; j < trigorath1.getCollectibles().size(); j++) {


                        Collectible collectible = trigorath1.getCollectibles().get(j);
                        g.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                    }
                }
            }
        }
    }
}
