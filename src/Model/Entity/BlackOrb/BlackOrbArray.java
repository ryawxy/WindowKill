package Model.Entity.BlackOrb;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import View.entityViews.BlackOrb.BlackOrbFrame;
import View.entityViews.BlackOrb.InvisibleFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BlackOrbArray {
   static ArrayList<BlackOrbFrame> blackOrbArray = new ArrayList<>();
   static ArrayList<Laser> lasers = new ArrayList<>();
   static BufferedImage image;

    public static void createBlackOrbArray(int x, int y){


        int sideLength = 150;
        int xLoc;
        int yLoc;
        int width;
        int height;


        //create orbs
        for(int i=0;i<5;i++){
            double theta = -Math.PI/2+i*2*Math.PI/5;

            double xPosition = sideLength*Math.cos(theta);
            double yPosition = -sideLength*Math.sin(theta);

            BlackOrbFrame blackOrbFrame = new BlackOrbFrame((int) xPosition+x, (int) yPosition+y);
            Game.getEnemies().add(blackOrbFrame.getBlackOrb());

            KeyListener keyListener = new KeyListener((JPanel) blackOrbFrame.getContentPane());

            blackOrbArray.add(blackOrbFrame);
            Game.getFrames().add(blackOrbFrame);
            Game.getBlackOrbFrames().add(blackOrbFrame);
        }



        //create lasers

        try {
            image = ImageIO.read(new File("src/images/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0;i< blackOrbArray.size();i++) {
            for (int j = 0; j < blackOrbArray.size(); j++) {
                if (i != j) {

                    double globalx1 = blackOrbArray.get(i).getX() + blackOrbArray.get(i).getBlackOrb().getX();
                    double globaly1 = blackOrbArray.get(i).getY() + blackOrbArray.get(i).getBlackOrb().getY();
                    double globalx2 = blackOrbArray.get(j).getX() + blackOrbArray.get(j).getBlackOrb().getX();
                    double globaly2 = blackOrbArray.get(j).getY() + blackOrbArray.get(j).getBlackOrb().getY();

                    double angle = Math.atan2(globaly2 - globaly1, globalx2 - globalx1);
                    double x1 = blackOrbArray.get(i).getBlackOrb().getX() ;
                    double y1 = blackOrbArray.get(i).getBlackOrb().getY() ;
                    double x2 = x1 + 200 * Math.cos(angle);
                    double y2 = y1 + 200 * Math.sin(angle);


                    // Calculate the angle of the diameter
                    if (globaly2 - globaly1 == 0) angle = 0;
                    else angle = Math.toDegrees(angle);

                    // Rotate the PNG image
                    BufferedImage rotatedImage = rotateImage(image, angle);

                    // Calculate the position to place the image
                    double lineLength = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                    int imageWidth = rotatedImage.getWidth();
                    int imageHeight = rotatedImage.getHeight();
                    double scaleFactor = lineLength / imageWidth;
                    int newImageWidth = (int) (imageWidth * scaleFactor);
                    int newImageHeight = (int) (imageHeight * scaleFactor);
                    Image scaledImage = rotatedImage.getScaledInstance(newImageWidth, newImageHeight, Image.SCALE_SMOOTH);

                    // Calculate the midpoint of the line
                    double midX = (x1 + x2) / 2;
                    double midY = (y1 + y2) / 2;
                    Laser laser = new Laser(new Point2D.Double((midX-newImageWidth)/2+65,(midY-newImageHeight)/2+50),scaledImage);
                    laser.setWidth(newImageWidth);
                    laser.setHeight(newImageHeight);

                    laser.setBlackOrb1(blackOrbArray.get(i).getBlackOrb());
                    laser.setBlackOrb2(blackOrbArray.get(j).getBlackOrb());
                    laser.setLocalFrame(blackOrbArray.get(i));
                    blackOrbArray.get(i).getBlackOrb().getLasers().add(laser);


                }
            }
        }

//        xLoc = blackOrbArray.get(4).getX()+blackOrbArray.get(4).getWidth();
//        width = Math.abs(xLoc-blackOrbArray.get(1).getX());
//        yLoc = (int) blackOrbArray.get(2).getY()+blackOrbArray.get(2).getHeight();
//        height =(int) Math.abs(yLoc-blackOrbArray.get(0).getY());
//        InvisibleFrame invisibleFrame = new InvisibleFrame((int) 800,(int) 600,(int) 300,(int) 300);
//        new KeyListener((JPanel) invisibleFrame.getContentPane());
//        new MouseListener((JPanel) invisibleFrame.getContentPane());
//
//       Game.getInvisibleFrames().add(invisibleFrame);

    }
    public static void createInvisibleFrame(){
               int  xLoc = blackOrbArray.get(4).getX()+blackOrbArray.get(4).getWidth();
      int  width = Math.abs(xLoc-blackOrbArray.get(1).getX());
      int  yLoc = (int) blackOrbArray.get(2).getY()+blackOrbArray.get(2).getHeight();
      int   height =(int) Math.abs(yLoc-blackOrbArray.get(0).getY());
        InvisibleFrame invisibleFrame = new InvisibleFrame((int) xLoc-10,(int) yLoc-10,(int) width+10,(int) height+10);
        new KeyListener((JPanel) invisibleFrame.getContentPane());
        new MouseListener((JPanel) invisibleFrame.getContentPane());

        Game.getInvisibleFrames().add(invisibleFrame);
        Game.getFrames().add(invisibleFrame);
    }
    private static BufferedImage rotateImage(BufferedImage img, double angle) {
        double radians = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int w = img.getWidth();
        int h = img.getHeight();
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(newW, newH, img.getType());
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((double) (newW - w) / 2, (double) (newH - h) / 2);
        at.rotate(radians, (double) w / 2, (double) h / 2);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return rotated;
    }

    public static ArrayList<BlackOrbFrame> getBlackOrbArray() {
        return blackOrbArray;
    }

    public static ArrayList<Laser> getLasers() {
        return lasers;
    }
}

