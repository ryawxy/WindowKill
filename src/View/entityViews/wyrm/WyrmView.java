package View.entityViews.wyrm;

import Model.Collectible;
import Model.Drawable;
import Model.Entity.Wyrm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class WyrmView implements Drawable {

    private Wyrm wyrm;
    private double rotationAngle;
    private BufferedImage image;

    public WyrmView(Wyrm wyrm, double rotationAngle){

        this.wyrm = wyrm;
        this.rotationAngle = rotationAngle;

        try {
            image = ImageIO.read(new File("src/images/wyrm.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics2D g) {

        if(!wyrm.isDead()) {
            AffineTransform affineTransform = g.getTransform();
            int centerX = 60;
            int centerY = 57;
            g.rotate(rotationAngle, centerX, centerY);
            g.drawImage(image, 0, 0, 100, 75, null);
            g.setTransform(affineTransform);
        }else if(wyrm.isShowCollectibles()){
            g.setColor(new Color(0x6B8AFF));
            for(Collectible collectible : wyrm.getCollectibles()) g.fillOval(collectible.getX(),collectible.getY(),
                    collectible.getRadius(),collectible.getRadius());
        }

    }
}
