package view.entityViews.smiley;

import Controller.Game;
import Model.Drawable;
import Model.entity.smiley.SmileyPunch;
import Model.enums.PunchType;
import view.GlassFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmileyPunchView implements Drawable {

    private final JFrame frame;
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private BufferedImage upImage;
    private BufferedImage downImage;
    private BufferedImage quakeImage;
    private BufferedImage crack;

    public SmileyPunchView(JFrame frame){

        this.frame = frame;

        try {
            leftImage = ImageIO.read(new File("src/images/leftPunch.png"));
            rightImage = ImageIO.read(new File("src/images/rightPunch.png"));
            upImage = ImageIO.read(new File("src/images/upPunch.png"));
            downImage = ImageIO.read(new File("src/images/downPunch.png"));
            quakeImage = ImageIO.read(new File("src/images/quakeEffect.png"));
            crack =  ImageIO.read(new File("src/images/crack.png"));
        }catch (IOException ignored){}

    }
    @Override
    public void paint(Graphics2D g) {
        BufferedImage image;
        int width = quakeImage.getWidth();
        int height = quakeImage.getHeight();
        for (SmileyPunch smileyPunch : Game.getSmileyPunches()) {
            if (smileyPunch.getPunchType().equals(PunchType.LEFT)) image = leftImage;
            else if (smileyPunch.getPunchType().equals(PunchType.RIGHT)) image = rightImage;
            else if (smileyPunch.getPunchType().equals(PunchType.DOWN)) image = downImage;
            else if (smileyPunch.getPunchType().equals(PunchType.QUAKE)) image = quakeImage;
            else image = upImage;

            int globalX = smileyPunch.getLocalX() + smileyPunch.getLocalFrame().getX();
            int globalY = smileyPunch.getLocalY() + smileyPunch.getLocalFrame().getY();


            Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                    , frame.getWidth(), frame.getHeight());


            if (bounds.contains(globalX, globalY) || bounds.contains(globalX + smileyPunch.getWidth(), globalY) ||
                    bounds.contains(globalX, globalY + smileyPunch.getHeight()) ||
                    bounds.contains(globalX + smileyPunch.getWidth(), globalY + smileyPunch.getHeight())) {


                if (smileyPunch.getLocalFrames().size() == 1) {

                    if (smileyPunch.getPunchType().equals(PunchType.QUAKE)) {
                        if (smileyPunch.getAlpha() > 0)
                            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, smileyPunch.getAlpha() / 255.0f));
                        g.drawImage(image, smileyPunch.getLocalX() - smileyPunch.getPunchSize() / 2, smileyPunch.getLocalY() - smileyPunch.getPunchSize() / 2,
                                smileyPunch.getPunchSize(), smileyPunch.getPunchSize() * width / height, null);

                    } else g.drawImage(image, smileyPunch.getLocalX(), smileyPunch.getLocalY(),
                            smileyPunch.getWidth(), smileyPunch.getHeight(), null);

                } else {
                    if (smileyPunch.getPunchType().equals(PunchType.QUAKE)) {
                        if (smileyPunch.getAlpha() > 0)
                            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, smileyPunch.getAlpha() / 255.0f));
                        g.drawImage(image, (globalX - bounds.x) - smileyPunch.getPunchSize() / 2, (globalY - bounds.y) - smileyPunch.getPunchSize() / 2,
                                smileyPunch.getPunchSize(), smileyPunch.getPunchSize() * width / height, null);
                    } else g.drawImage(image, (globalX - bounds.x), (globalY - bounds.y),
                            smileyPunch.getWidth(), smileyPunch.getHeight(), null);


                }
            }
            for (Point point : smileyPunch.getCracks()) {
                if (frame.equals(GlassFrame.getINSTANCE())) {
                    g.drawImage(crack,point.x,point.y,crack.getWidth()/2,crack.getHeight()/2,null);
                }
            }
        }

        }
    }
