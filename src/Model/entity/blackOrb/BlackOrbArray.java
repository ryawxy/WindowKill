package Model.entity.blackOrb;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import view.entityViews.blackOrb.BlackOrbFrame;
import view.entityViews.blackOrb.InvisibleFrame;

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
   static BufferedImage image;
   private static int time;

    public  void createBlackOrbArray(int x, int y) throws IOException {


        int sideLength = 180;

        //create orbs
        for(int i=0;i<5;i++) {
            double theta = -Math.PI / 2 + i * 2 * Math.PI / 5;

            double xPosition = sideLength * Math.cos(theta);
            double yPosition = -sideLength * Math.sin(theta);




                BlackOrbFrame blackOrbFrame = new BlackOrbFrame((int) xPosition + x, (int) yPosition + y);
                Game.getEnemies().add(blackOrbFrame.getBlackOrb());

                blackOrbArray.add(blackOrbFrame);




            }




        //create lasers

        try {
            image = ImageIO.read(new File("src/images/laser.png"));
        } catch (IOException ignored) {

        }

        for (int i=0;i< blackOrbArray.size();i++) {
            for (int j = 0; j < blackOrbArray.size(); j++) {
                if (i != j) {

                    double globalX1 = blackOrbArray.get(i).getX() + blackOrbArray.get(i).getBlackOrb().getLocalX();
                    double globalY1 = blackOrbArray.get(i).getY() + blackOrbArray.get(i).getBlackOrb().getLocalY();
                    double globalX2 = blackOrbArray.get(j).getX() + blackOrbArray.get(j).getBlackOrb().getLocalX();
                    double globalY2 = blackOrbArray.get(j).getY() + blackOrbArray.get(j).getBlackOrb().getLocalY();


                    double angle = Math.atan2(globalY2 - globalY1, globalX2 - globalX1);
                    double x1 = blackOrbArray.get(i).getBlackOrb().getLocalX() ;
                    double y1 = blackOrbArray.get(i).getBlackOrb().getLocalY() ;
                    double x2 = x1 + 170 * Math.cos(angle);
                    double y2 = y1 + 170 * Math.sin(angle);


                    // Calculate the angle of the diameter
                    if (globalY2 - globalY1 == 0) angle = 0;
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
                    Laser laser = new Laser(new Point2D.Double((midX-newImageWidth)/2+50,(midY-newImageHeight)/2+68),scaledImage);
                    laser.setWidth(newImageWidth);
                    laser.setHeight(newImageHeight);
                    laser.setAngle(angle);
                    laser.setBlackOrb1(blackOrbArray.get(i).getBlackOrb());
                    laser.setBlackOrb2(blackOrbArray.get(j).getBlackOrb());
                    laser.setLocalFrame(blackOrbArray.get(i));
                    blackOrbArray.get(i).getBlackOrb().getLasers().add(laser);
                    Game.getEnemies().add(laser);


                }
            }
        }

    }
    public  void createInvisibleFrame(){
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
    public void avalanche(){

            Polygon pentagon = new Polygon();
            int[] xPoints = new int[5];
            int[] yPoints = new int[5];

            for(int i=0;i<blackOrbArray.size();i++){
                xPoints[i] = blackOrbArray.get(i).getX()+blackOrbArray.get(i).getBlackOrb().getLocalX();
                yPoints[i] = blackOrbArray.get(i).getY()+blackOrbArray.get(i).getBlackOrb().getLocalY();

            }
            pentagon = new Polygon(xPoints,yPoints,5);


    }

    public  ArrayList<BlackOrbFrame> getBlackOrbArray() {
        return blackOrbArray;
    }

}

