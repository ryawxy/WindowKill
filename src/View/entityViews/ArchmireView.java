package View.entityViews;

import Controller.Constants;
import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.Entity.Archmire;
import Model.Entity.Footprint;
import Model.enums.Size;

import java.awt.*;
import java.awt.geom.Point2D;

public class ArchmireView implements Drawable {
    @Override
    public void paint(Graphics2D g) {

        for (Archmire archmire : Game.getArchmires()) {
            if(!archmire.isDead()) {
                for (Footprint footprint : archmire.getFootprint()) {
                    if (footprint.getPosition().getX() != archmire.getX() || footprint.getPosition().getY() != archmire.getY()) {
                        if (footprint.isVisible()) {

                            g.setColor(Footprint.getColor());
                            if (archmire.getsize().equals(Size.MINI)) {
                                g.fillOval((int) footprint.getPosition().getX(), (int) footprint.getPosition().getY(), Constants.miniArchmireWidth(), Constants.miniArchmireHeight());
                            }else g.fillOval((int) footprint.getPosition().getX(), (int) footprint.getPosition().getY(), Constants.largeArchmireWidth(), Constants.largeArchmireHeight());

                        }
                    }
                }
                g.setColor(new Color(4, 55, 93));
                if (archmire.getsize().equals(Size.MINI)) {
                    g.fillOval(archmire.getX(), archmire.getY(), Constants.miniArchmireWidth(), Constants.miniArchmireHeight());
                }else g.fillOval(archmire.getX(), archmire.getY(), Constants.largeArchmireWidth(), Constants.largeArchmireHeight());


            }else{
                if(archmire.isShowCollectibles()){
                    for(Collectible collectible : archmire.getCollectibles()){
                        g.setColor(Color.orange);
                        g.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());
                    }
                }
            }

        }

    }
}
