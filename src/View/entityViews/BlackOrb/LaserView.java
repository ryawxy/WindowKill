package View.entityViews.BlackOrb;

import Model.Drawable;
import Model.Entity.BlackOrb.BlackOrb;
import Model.Entity.BlackOrb.Laser;
import java.awt.*;


public class LaserView implements Drawable {
    private BlackOrb blackOrb;

    public LaserView(BlackOrb blackOrb){
        this.blackOrb = blackOrb;
    }
    @Override
    public void paint(Graphics2D g) {

        for (int i = 0; i < blackOrb.getLasers().size(); i++) {

            Laser laser = blackOrb.getLasers().get(i);
            if(laser.isVisible())
                g.drawImage(laser.getImage(), (int) laser.getStart().getX(), (int) laser.getStart().getY(), null);
            }

    }
}
