package view.entityViews.smiley;

import Controller.Game;
import Model.Drawable;
import Model.entity.smiley.Smiley;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FireView implements Drawable {

    private JFrame frame;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;

    public FireView() {


        try {
            image1 = ImageIO.read(new File("src/images/vomit2.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        try {
            image2 = ImageIO.read(new File("src/images/vomit3.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        try {
            image3 = ImageIO.read(new File("src/images/vomit4.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void paint(Graphics2D g) {


        for (Smiley smiley : Game.getSmilies()) {
            for (int i = 0; i < smiley.getAoeAttacks().size(); i++) {

                if (i % 3 == 0) {
                    g.drawImage(image3, smiley.getAoeAttacks().get(i).getX(), smiley.getAoeAttacks().get(i).getY(),100,100, null);
                } else if (i % 3 == 1){
                    g.drawImage(image1, smiley.getAoeAttacks().get(i).getX(), smiley.getAoeAttacks().get(i).getY(),100,100, null);
                } else g.drawImage(image2, smiley.getAoeAttacks().get(i).getX(), smiley.getAoeAttacks().get(i).getY(),100,100, null);
            }
        }
    }
}
