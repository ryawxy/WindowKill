package view.entityViews;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.entity.Omenoct;

import java.awt.*;
import java.util.ArrayList;

public class OmenoctView implements Drawable {
    private final ArrayList<Omenoct> omenocts = Game.getOmenocts();
    @Override
    public void paint(Graphics2D g) {
        for(Omenoct omenoct : omenocts){
            if(!omenoct.isDead()) {
                if(omenoct.isVisible()) {
                    g.setColor(Color.magenta);
                    g.fillPolygon(omenoct.getxPoints(), omenoct.getyPoints(), 6);
                }
            }else{
                if (omenoct.isShowCollectibles()) {

                    g.setColor(Color.orange);
                    for (int j = 0; j < omenoct.getCollectibles().size(); j++) {


                        Collectible collectible = omenoct.getCollectibles().get(j);
                        g.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                    }
                }
            }
        }
    }
}
