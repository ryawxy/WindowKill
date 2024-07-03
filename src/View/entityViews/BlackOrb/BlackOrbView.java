package View.entityViews.BlackOrb;

import Controller.Constants;
import Model.Collectible;
import Model.Drawable;
import Model.Entity.BlackOrb.BlackOrb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlackOrbView implements Drawable {

    private BufferedImage image;
    private BlackOrb blackOrb;
    public BlackOrbView(BlackOrb blackOrb){

        this.blackOrb = blackOrb;

        try {
            image = ImageIO.read(new File("src/images/orb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics2D g) {


        g.drawImage(image,45,20, Constants.orbSize(),Constants.orbSize(),null);

        if(blackOrb.isDead() && blackOrb.isShowCollectibles()) {

            for (Collectible collectible : blackOrb.getCollectibles()) {

                g.setColor(new Color(0x7B26B9));
                g.fillOval(collectible.getX(),collectible.getY(),collectible.getRadius(),collectible.getRadius());
            }
        }


    }
}
