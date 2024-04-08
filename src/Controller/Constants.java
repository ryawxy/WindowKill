package Controller;

import java.awt.*;

public class Constants {

    private static  int FRAME_WIDTH = 700;
    private static  int FRAME_HEIGHT = 700;
    private static  Dimension SCREEN_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    public static int getFrameWidth(){
        return 700;
    }
    public static int getFrameHeight(){
        return 700;
    }
    public static Dimension getFrameDimension(){
        return SCREEN_SIZE;
    }
    public static int getEpsilonRadius(){
        return 30;
    }
    public static int getShotGunWidth(){
        return 8;
    }
    public static int getShotGunHeight(){
        return 3;
    }
    public static int getShotGunSpeed(){
        return 8;
    }
    public static int epsilonAbsVelocity(){
        return 5;
    }
    public static int frameWidthForEpsilon(){
        return FRAME_WIDTH - 8;
    }
    public static int frameHeightForEpsilon(){
        return FRAME_HEIGHT - 8;
    }
    public static int HP(){
        return 100;
    }
    public static int XP(){
        return 0;
    }
    public static void setFrameWidth(int frameWidth){
        FRAME_WIDTH = frameWidth;
    }
    public static void setFrameHeight(int frameHeight){
        FRAME_HEIGHT = frameHeight;
    }
    public static void setScreenSize(Dimension dimension){
        SCREEN_SIZE = new Dimension(FRAME_WIDTH,FRAME_HEIGHT);
    }
    public static int shrinkAmount(){
        return 1;
    }
    public static int expandAmount(){
        return 3;
    }
    public static int trigorathLongDistanceSpeed(){
        return 3;
    }
    public static int trigorathNearBySpeed(){
        return 1;
    }
    public static int squarantineNormalSpeed(){
        return 2;
    }
    public static int squarantineAggressionSpeed(){
        return 8;
    }
    public static int squarantineAcceleration(){
        return 4;
    }

}
