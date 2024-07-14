package view.entityViews.barricados;

import Controller.Game;
import Model.Drawable;
import Model.entity.Barricados;
import Model.enums.BarricadosType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarricadosView implements Drawable {

    private BufferedImage image1;
    private BufferedImage image2;
    private final JFrame frame;

    public BarricadosView(JFrame frame) {
        this.frame = frame;


        try {
            image1 = ImageIO.read(new File("src/images/barricados1.png"));
            image2 = ImageIO.read(new File("src/images/barricados2.png"));
        } catch (IOException ignored) {

        }
    }


    @Override
    public void paint(Graphics2D g) {
        BufferedImage image;
        for (Barricados barricados : Game.getBarricados()) {
            int globalX = barricados.getLocalX() + barricados.getLocalFrame().getX();
            int globalY = barricados.getLocalY() + barricados.getLocalFrame().getY();

            if(barricados.getType().equals(BarricadosType.T1)) image = image1;
            else image = image2;

            Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                    , frame.getWidth(), frame.getHeight());


            if (bounds.contains(globalX, globalY) || bounds.contains(globalX + barricados.getWidth(), globalY) ||
                    bounds.contains(globalX, globalY + barricados.getHeight()) ||
                    bounds.contains(globalX + barricados.getWidth(), globalY + barricados.getHeight())) {

               if(barricados.getLocalFrames().size()==1) {
                   g.drawImage(image, barricados.getLocalX(), barricados.getLocalY()
                           , barricados.getWidth(), barricados.getHeight(), null);
               }else{
                   g.drawImage(image, (globalX - bounds.x), (globalY - bounds.y), barricados.getWidth(), barricados.getHeight(), null);
               }


            }
        }
    }
}
