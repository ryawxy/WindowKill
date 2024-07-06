package controller;

import view.SettingsFrame;

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
    public static Dimension glassFrameDimension(){
        return Toolkit.getDefaultToolkit().getScreenSize();
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
        if(SettingsFrame.getChosenSensitivity()==30) {
            return 8;
        }else if(SettingsFrame.getChosenSensitivity() == 20){
            return 6;
        } else if(SettingsFrame.getChosenSensitivity() == 10){
            return 5;
        }
        return 6;
    }
    public static int epsilonAbsVelocity(){

        if(SettingsFrame.getChosenSensitivity()==30) {

            return 5;
        }else if(SettingsFrame.getChosenSensitivity() == 20){
            return 4;
        } else if(SettingsFrame.getChosenSensitivity() == 10){
            return 3;
        }

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
        return 4;
    }
    public static int trigorathLongDistanceSpeed(){
        return 3;
    }
    public static int trigorathNearBySpeed(){
        return 1;
    }
    public static int trigorathAcceleration(){
        return 2;
    }
    public static int squarantineNormalSpeed(){
        return 3;
    }
    public static int squarantineAggressionSpeed(){
        return 8;
    }
    public static int squarantineAcceleration(){
        return 1;
    }
    public static int getSound(){
        if(SettingsFrame.getChosenSound()==0){
            return 0;
        }else if(SettingsFrame.getChosenSound()==50){

            return 50;
        }else if(SettingsFrame.getChosenSound()==100){
            return 100;
        }

        return 50;
    }
    public static int getImpactSpeed(int distance){

        if(distance >=40){
            return 2;
        }else if(distance<=20){
            return 3;
        }else {
            return 4;
        }
    }
    public static int necropickWidth(){
        return 50;
    }
    public static int omenoctWidth(){
        return 10;
    }
    public static int miniArchmireWidth(){
        return 50;
    }
    public static int miniArchmireHeight(){
        return 30;
    }
    public static int largeArchmireWidth(){
        return 75;
    }
    public static int largeArchmireHeight(){
        return 45;
    }
    public static int barricadosWidth(){
        return 300;
    }
    public static int orbSize(){
        return 60;
    }
    public static int wyrmSize(){
        return 60;
    }


}
