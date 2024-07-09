package view.entityViews.blackOrb;

import Controller.Constants;
import Model.Collectible;
import Model.Drawable;
import Model.entity.blackOrb.BlackOrb;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlackOrbView implements Drawable  {

    private BufferedImage image =  ImageIO.read(new File("src/images/orb.png"));
    private BlackOrb blackOrb;
    private JFrame frame;
    public BlackOrbView(BlackOrb blackOrb,JFrame frame) throws IOException {

        this.blackOrb = blackOrb;
        this.frame = frame;


    }
    @Override
    public void paint(Graphics2D g) {

        Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                , frame.getWidth(), frame.getHeight());

        int globalX = blackOrb.getLocalX() + blackOrb.getLocalFrame().getX();
        int globalY = blackOrb.getLocalY() + blackOrb.getLocalFrame().getY();

        if (bounds.contains(globalX, globalY) || bounds.contains(globalX + Constants.orbSize(), globalY) ||
                bounds.contains(globalX, globalY +Constants.orbSize() ) ||
                bounds.contains(globalX + Constants.orbSize(), globalY + Constants.orbSize())) {
            if (blackOrb.getLocalFrames().size() == 1) {

                g.drawImage(image,blackOrb.getLocalX(), (int) (blackOrb.getLocalY()), Constants.orbSize(), Constants.orbSize(),null);

            } else {


                g.drawImage(image,(globalX - bounds.x), (globalY - bounds.y), Constants.orbSize(), Constants.orbSize(),null);
            }
        }



        if(blackOrb.isDead() && blackOrb.isShowCollectibles()) {

            for (Collectible collectible : blackOrb.getCollectibles()) {

                g.setColor(new Color(0x7B26B9));
                g.fillOval(collectible.getX(),collectible.getY(),collectible.getRadius(),collectible.getRadius());
            }
        }


    }
}
