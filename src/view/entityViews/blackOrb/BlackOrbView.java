package view.entityViews.blackOrb;

import Controller.Constants;
import Controller.Game;
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
    private final BufferedImage image =  ImageIO.read(new File("src/images/orb.png"));
    private final JFrame frame;
    public BlackOrbView(JFrame frame) throws IOException {

        this.frame = frame;


    }
    @Override
    public void paint(Graphics2D g) {

        for(BlackOrb blackOrb : Game.getBlackOrbs()) {

                Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                        , frame.getWidth(), frame.getHeight());

                int globalX = blackOrb.getLocalX() + blackOrb.getLocalFrame().getX();
                int globalY = blackOrb.getLocalY() + blackOrb.getLocalFrame().getY();

                if (bounds.contains(globalX, globalY) || bounds.contains(globalX + Constants.orbSize(), globalY) ||
                        bounds.contains(globalX, globalY + Constants.orbSize()) ||
                        bounds.contains(globalX + Constants.orbSize(), globalY + Constants.orbSize())) {
                    if (blackOrb.getLocalFrames().size() == 1) {

                        g.drawImage(image, blackOrb.getLocalX(), (blackOrb.getLocalY()), Constants.orbSize(), Constants.orbSize(), null);

                    } else {



                        g.drawImage(image, (globalX - bounds.x), (globalY - bounds.y), blackOrb.getWidth(), blackOrb.getHeight(), null);
                    }
                }

             if (blackOrb.isShowCollectibles()) {

                for (Collectible collectible : blackOrb.getCollectibles()) {

                    int globalx = collectible.getLocalX() + collectible.getLocalFrame().getX();
                    int globaly = collectible.getLocalY() + collectible.getLocalFrame().getY();

                    Rectangle bound = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());

                    if (bound.contains(globalx, globaly) || bound.contains(globalx + collectible.getWidth(), globaly) ||
                            bound.contains(globalx, globaly + collectible.getHeight()) ||
                            bound.contains(globalx + collectible.getWidth(), globaly + collectible.getHeight())) {
                        g.setColor(new Color(0x7B26B9));
                        for (int j = 0; j < blackOrb.getCollectibles().size(); j++) {
                            g.fillOval(collectible.getX(), collectible.getY(),
                                    collectible.getWidth(), collectible.getHeight());
                        }

                    }



                }
            }
        }


    }
}
