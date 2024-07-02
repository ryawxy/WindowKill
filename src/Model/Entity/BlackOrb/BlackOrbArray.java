package Model.Entity.BlackOrb;

import Controller.Game;
import View.entityViews.BlackOrb.BlackOrbFrame;
import View.entityViews.BlackOrb.InvisibleFrame;

import javax.swing.*;
import java.util.ArrayList;

public class BlackOrbArray {
   static ArrayList<BlackOrbFrame> blackOrbArray = new ArrayList<>();
   static ArrayList<Laser> lasers = new ArrayList<>();

    public static void createBlackOrbArray(int x, int y){


        int sideLength = 150;

        //create orbs
        for(int i=0;i<5;i++){
            double theta = -Math.PI/2+i*2*Math.PI/5;

            double xPosition = sideLength*Math.cos(theta);
            double yPosition = -sideLength*Math.sin(theta);

            BlackOrbFrame blackOrbFrame = new BlackOrbFrame((int) xPosition+x, (int) yPosition+y);

            blackOrbArray.add(blackOrbFrame);
            Game.getFrames().add(blackOrbFrame);
            Game.getBlackOrbFrames().add(blackOrbFrame);
        }
   //     Game.getFrames().add(new InvisibleFrame((blackOrbArray.get(0)).getX()+blackOrbArray.get(1).getX()/2,
    //                    (blackOrbArray.get(0)).getX()+blackOrbArray.get(3).getX()/2 ));


        //create lasers
        for (int i=0;i< blackOrbArray.size();i++){
            for(int j=0;j<blackOrbArray.size();j++){
                BlackOrbFrame blackOrbFrame = blackOrbArray.get(i);
                Laser laser = new Laser(blackOrbFrame.getBlackOrb(),blackOrbArray.get(j).getBlackOrb());
                blackOrbFrame.getBlackOrb().getLasers().add(laser);
                lasers.add(laser);
            }
        }
    }

    public static ArrayList<BlackOrbFrame> getBlackOrbArray() {
        return blackOrbArray;
    }

    public static ArrayList<Laser> getLasers() {
        return lasers;
    }
}

