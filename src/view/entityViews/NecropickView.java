package view.entityViews;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.entity.Necropick;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NecropickView implements Drawable {

    private final JFrame frame;

    BufferedImage image;
    BufferedImage image2;
    private final ArrayList<Necropick> necropicks = Game.getNecropicks();

    public NecropickView(JFrame frame){

        this.frame = frame;

        try {
            image = ImageIO.read(new File("src/images/necropick.png"));
            image2 = ImageIO.read(new File("src/images/hole.png"));
        } catch (IOException ignored) {

        }
    }
    @Override
    public void paint(Graphics2D g) {

        for (Necropick necropick : necropicks) {

                if (!necropick.isDead()) {

                        int globalX = necropick.getLocalX()+necropick.getLocalFrame().getX();
                        int globalY = necropick.getLocalY()+necropick.getLocalFrame().getY() ;

                        Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                                , frame.getWidth(), frame.getHeight());


                        if (bounds.contains(globalX, globalY) || bounds.contains(globalX + necropick.getWidth(), globalY) ||
                                bounds.contains(globalX, globalY + necropick.getHeight()) ||
                                bounds.contains(globalX + necropick.getWidth(), globalY + necropick.getHeight())) {
                            if (necropick.getLocalFrames().size() == 1) {
                                if(necropick.isVisible() ) {
                                    g.drawImage(image,  (necropick.getLocalX()),  (necropick.getLocalY()), necropick.getWidth(), necropick.getHeight(), null);
                                }
                                if(necropick.isShowPortal()){

                                    g.drawImage(image2, (necropick.getLocalX()),  (necropick.getLocalY()), necropick.getWidth(), necropick.getHeight(),null);

                                }


                            } else {

                                g.drawImage(image,  (globalX - bounds.x),  (globalY - bounds.y), necropick.getWidth(), necropick.getHeight(), null);
                                if (necropick.isShowPortal()) {

                                    g.drawImage(image2, (globalX - bounds.x),  (globalY - bounds.y),
                                            necropick.getWidth(), necropick.getHeight(), null);

                                }
                            }
                        }


                } else {
                    if (necropick.isShowCollectibles()) {
                        int globalX = necropick.getLocalX() + necropick.getLocalFrame().getX();
                        int globalY = necropick.getLocalY() + necropick.getLocalFrame().getY();

                        Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                                , frame.getWidth(), frame.getHeight());


                        if (bounds.contains(globalX, globalY) || bounds.contains(globalX +necropick.getWidth(), globalY) ||
                                bounds.contains(globalX, globalY + necropick.getHeight()) ||
                                bounds.contains(globalX + necropick.getWidth(), globalY + necropick.getHeight())) {

                            for (int j = 0; j < necropick.getCollectibles().size(); j++) {


                                Collectible collectible = necropick.getCollectibles().get(j);
                                g.setColor(new Color(0x8CCFE5));
                                g.fillOval(collectible.getX(), collectible.getY(), collectible.getWidth(), collectible.getHeight());

                            }
                        }
                    }
                }
            }
        }
    }

