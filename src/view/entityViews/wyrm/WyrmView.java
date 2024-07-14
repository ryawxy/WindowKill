package view.entityViews.wyrm;

import Controller.Constants;
import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.entity.Wyrm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class WyrmView implements Drawable {


    private double rotationAngle;
    private BufferedImage image;
    private final JFrame frame;

    public WyrmView(JFrame frame) {

        this.frame = frame;
        try {
            image = ImageIO.read(new File("src/images/wyrm.png"));
        } catch (IOException ignored) {

        }
    }

    @Override
    public void paint(Graphics2D g) {

        for (Wyrm wyrm : Game.getWyrms()) {
            if (!wyrm.isDead()) {
                int globalX = wyrm.getLocalX() + wyrm.getLocalFrame().getX();
                int globalY = wyrm.getLocalY() + wyrm.getLocalFrame().getY();


                Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                        , frame.getWidth(), frame.getHeight());


                if (bounds.contains(globalX, globalY) || bounds.contains(globalX + wyrm.getWidth(), globalY) ||
                        bounds.contains(globalX, globalY + wyrm.getHeight()) ||
                        bounds.contains(globalX + wyrm.getWidth(), globalY + wyrm.getHeight())) {
                    AffineTransform affineTransform = g.getTransform();
                    int centerX = 60;
                    int centerY = 57;
                    //                   rotationAngle += 0.02;
                    g.rotate(rotationAngle, centerX, centerY);
                    if (wyrm.getLocalFrames().size() == 1) {

                        g.drawImage(image, wyrm.getLocalX(), wyrm.getLocalY(), Constants.wyrmWidth(), Constants.wyrmHeight(), null);


                    } else {



                        g.drawImage(image, (globalX - bounds.x), (globalY - bounds.y), wyrm.getWidth(), wyrm.getHeight(), null);
                    }
                    g.setTransform(affineTransform);
                }
            } else if (wyrm.isShowCollectibles()) {
                g.setColor(new Color(0x6B8AFF));
                for (Collectible collectible : wyrm.getCollectibles()) {
                    int globalX = collectible.getLocalX() + collectible.getLocalFrame().getX();
                    int globalY = collectible.getLocalY() + collectible.getLocalFrame().getY();

                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());

                    if (bounds.contains(globalX, globalY) || bounds.contains(globalX + collectible.getWidth(), globalY) ||
                            bounds.contains(globalX, globalY + collectible.getHeight()) ||
                            bounds.contains(globalX + collectible.getWidth(), globalY + collectible.getHeight())) {
                        g.setColor(Color.RED);
                        for (int j = 0; j < wyrm.getCollectibles().size(); j++) {
                            g.fillOval(collectible.getX(), collectible.getY(),
                                    collectible.getWidth(), collectible.getHeight());
                        }

                    }
                }


            }
        }
    }
}
