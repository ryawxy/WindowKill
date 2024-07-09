package view.entityViews;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.entity.Epsilon;
import Model.entity.Omenoct;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OmenoctView implements Drawable {
    private final JFrame frame;
    private final ArrayList<Omenoct> omenocts = Game.getOmenocts();
    private BufferedImage image;

    public OmenoctView(JFrame frame) {
        this.frame = frame;
        try {
            image = ImageIO.read(new File("src/images/omenoct.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics2D g) {
        for (Omenoct omenoct : omenocts) {
            if (!omenoct.isDead()) {
                if (omenoct.isVisible()) {


                    int globalX = omenoct.getLocalX() + omenoct.getLocalFrame().getX();
                    int globalY = omenoct.getLocalY() + omenoct.getLocalFrame().getY();

                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                            , frame.getWidth(), frame.getHeight());


                    if (bounds.contains(globalX, globalY) || bounds.contains(globalX + omenoct.getWidth(), globalY) ||
                            bounds.contains(globalX, globalY + omenoct.getHeight()) ||
                            bounds.contains(globalX + omenoct.getWidth(), globalY + omenoct.getHeight())) {
                        if (omenoct.getLocalFrames().size() == 1) {
                            g.setColor(Color.magenta);
                            g.drawImage(image, (int) (omenoct.getLocalX()), (int) (omenoct.getLocalY()), omenoct.getWidth(), omenoct.getHeight(), null);



                        } else {
                            g.setColor(Color.magenta);
                            g.drawImage(image, (int) (globalX - bounds.x), (int) (globalY - bounds.y), omenoct.getWidth(), omenoct.getHeight(), null);



                        }
                    }

                }
            } else {
                if (omenoct.isShowCollectibles()) {
                    int globalX = omenoct.getLocalX() + omenoct.getLocalFrame().getX();
                    int globalY = omenoct.getLocalY() + omenoct.getLocalFrame().getY();

                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                            , frame.getWidth(), frame.getHeight());


                    if (bounds.contains(globalX, globalY) || bounds.contains(globalX + omenoct.getWidth(), globalY) ||
                            bounds.contains(globalX, globalY + omenoct.getHeight()) ||
                            bounds.contains(globalX + omenoct.getWidth(), globalY + omenoct.getHeight())) {
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
}
