package view.entityViews;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.entity.Archmire;
import Model.entity.Footprint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArchmireView implements Drawable {

    private final JFrame frame;
    private BufferedImage image;

    public ArchmireView(JFrame frame){
        this.frame = frame;

        try {
            image = ImageIO.read(new File("src/images/archmire.png"));
        } catch (IOException ignored) {

        }
    }
    @Override
    public void paint(Graphics2D g) {

        for (Archmire archmire : Game.getArchmires()) {
            if(!archmire.isDead()) {
                for (Footprint footprint : archmire.getFootprint()) {
                    int globalX = footprint.getLocalX()+footprint.getLocalFrame().getX();
                    int globalY = footprint.getLocalY()+footprint.getLocalFrame().getY();


                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                            , frame.getWidth(), frame.getHeight());
                    if (footprint.getPosition().getX() != archmire.getLocalX() || footprint.getPosition().getY() != archmire.getLocalY()) {
                        if (footprint.isVisible()) {
                            if (bounds.contains(globalX, globalY) || bounds.contains(globalX + archmire.getWidth(), globalY) ||
                                    bounds.contains(globalX, globalY + archmire.getHeight()) ||
                                    bounds.contains(globalX + archmire.getWidth(), globalY + archmire.getHeight())) {

                                if (footprint.getAlpha() >= 0.1) {
                                    Composite origin = g.getComposite();
                                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, footprint.getAlpha()));
                                    int alphaValue = (int) (footprint.getAlpha() * 255);
                                    g.setColor(new Color(255, 107, 125, alphaValue));

                                    if (archmire.getLocalFrames().size() == 1) {


                                        g.fillOval(footprint.getLocalX(), footprint.getLocalY(), archmire.getWidth(), archmire.getHeight());

                                    } else {
                                        g.fillOval((globalX - bounds.x), (globalY - bounds.y), archmire.getWidth(), archmire.getHeight());
                                    }
                                    g.setComposite(origin);

                                }
                            }
                            }
                    }
                }

                    int globalX = archmire.getLocalX()+archmire.getLocalFrame().getX();
                    int globalY = archmire.getLocalY()+archmire.getLocalFrame().getY();


                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                            , frame.getWidth(), frame.getHeight());


                    if (bounds.contains(globalX, globalY) || bounds.contains(globalX + archmire.getWidth(), globalY) ||
                            bounds.contains(globalX, globalY + archmire.getHeight()) ||
                            bounds.contains(globalX + archmire.getWidth(), globalY + archmire.getHeight())) {
                        if (archmire.getLocalFrames().size() == 1) {

                            g.drawImage(image, (archmire.getLocalX()),  (archmire.getLocalY()), archmire.getWidth(), archmire.getHeight(),null);


                        } else {
                            g.drawImage(image,(globalX - bounds.x), (globalY - bounds.y), archmire.getWidth(), archmire.getHeight(),null);
                        }
                    }




            }else{
                if(archmire.isShowCollectibles()){
                    int globalX = archmire.getLocalX() + archmire.getLocalFrame().getX();
                    int globalY = archmire.getLocalY() + archmire.getLocalFrame().getY();

                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());

                    if (bounds.contains(globalX, globalY) || bounds.contains(globalX + archmire.getWidth(), globalY) ||
                            bounds.contains(globalX, globalY + archmire.getHeight()) ||
                            bounds.contains(globalX + archmire.getWidth(), globalY + archmire.getHeight())) {
                        g.setColor(Color.RED);
                        for (int j = 0; j < archmire.getCollectibles().size(); j++) {


                            Collectible collectible = archmire.getCollectibles().get(j);
                            g.fillOval(collectible.getX(), collectible.getY(), collectible.getWidth(), collectible.getHeight());

                        }
                    }
                }
            }

        }

    }
}
