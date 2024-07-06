package View.entityViews;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.entity.Necropick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NecropickView implements Drawable {

    BufferedImage image;
    private final ArrayList<Necropick> necropicks = Game.getNecropicks();
    @Override
    public void paint(Graphics2D g) {

        for (Necropick necropick : necropicks) {

                if (!necropick.isDead()) {
                    if(necropick.isVisible()) {
                        try {
                            image = ImageIO.read(new File("src/images/necropick.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        g.drawImage(image, necropick.getX(), necropick.getY(), 50, 50, null);
                    }
                } else {
                    if (necropick.isShowCollectibles()) {
                        g.setColor(Color.orange);
                        for (int j = 0; j < necropick.getCollectibles().size(); j++) {


                            Collectible collectible = necropick.getCollectibles().get(j);
                            g.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                        }
                    }
                }
            }
        }
    }

