package Model;

import Controller.Constants;
import Controller.Game;
import Model.entity.Epsilon;
import Model.entity.smiley.Fire;
import Model.entity.smiley.Smiley;
import Model.entity.smiley.SmileyPointFinger;
import Model.entity.smiley.SmileyPunch;
import Model.enums.Attack;
import Model.enums.PunchType;
import view.GlassFrame;
import view.entityViews.smiley.SmileyFrame;
import view.entityViews.smiley.SmileyPunchFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

public class BossAttack {
    private static final Epsilon epsilon = Game.getEpsilon();
    private static ArrayList<Attack> bossAttacks = new ArrayList<>();
    private static long timer;
    private static long powerPunchTimer;
    private static long annihilatorTimer;
    private static int punchXVelocity = Constants.punchVelocity();
    private static int punchYVelocity = Constants.punchVelocity();
    private static boolean canSlap = true;

    public static void squeeze() {
        bossAttacks.add(Attack.SQUEEZE);

        for (SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()) {
            if (Game.getSmileyPointFingers().size() >= 2) {
                JFrame frame = smileyPointFinger.getLocalFrame();
                if (smileyPointFinger.getSmileyHandSide().equals(PunchType.LEFT)) {
                    if (frame.getX() + frame.getWidth()
                            < epsilon.getLocalFrame().getX()) {
                        frame.setLocation(frame.getX() + 1, frame.getY());
                    }
                } else if (frame.getX() > epsilon.getLocalFrame().getX() + epsilon.getLocalFrame().getWidth()) {
                    frame.setLocation(frame.getX() - 1, frame.getY());
                }
            }
        }
    }
    public static void projectile(){
        bossAttacks.add(Attack.PROJECTILE);

        for(SmileyFrame smileyFrame : Game.getSmileyFrames()) smileyFrame.getSmiley().move();
        for(SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()){
            smileyPointFinger.move();
            smileyPointFinger.shot();
        }
    }
    public static void vomit(){
        bossAttacks.add(Attack.VOMIT);

        timer++;
        if(timer<100) {
            for (SmileyFrame smileyFrame : Game.getSmileyFrames()) {
                smileyFrame.getSmiley().setXVelocity(0);
                smileyFrame.getSmiley().setYVelocity(0);
                smileyFrame.getSmiley().aoe();
            }
            for (SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()) {
                smileyPointFinger.setXVelocity(0);
                smileyPointFinger.setYVelocity(0);
            }
        }else{
            timer = 0;
            for(Smiley smiley : Game.getSmilies()){
                for(Fire fire : smiley.getAoeAttacks()){
                    fire.setVisible(false);
                }
            }
        }


    }
    public static void powerPunch() {

        bossAttacks.add(Attack.POWERPUNCH);
        powerPunchTimer++;
        for (SmileyPunchFrame smileyPunchFrame : Game.getSmileyPunchFrames()) {
            if (smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.LEFT)||
            smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.RIGHT)) punchXVelocity+=Constants.punchAcceleration();
            JFrame epsilonFrame = Game.getEpsilon().getLocalFrame();

            if(powerPunchTimer<=20) {
                if(smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.LEFT)) {
                    if (smileyPunchFrame.getX() + smileyPunchFrame.getWidth() + punchXVelocity < epsilonFrame.getX()) {
                        smileyPunchFrame.setLocation(smileyPunchFrame.getX() + punchXVelocity, smileyPunchFrame.getY());
                    } else {
                        smileyPunchFrame.setLocation(smileyPunchFrame.getX() + punchXVelocity, smileyPunchFrame.getY());
                        epsilonFrame.setLocation(epsilonFrame.getX() + punchXVelocity, epsilonFrame.getY());
                        epsilonFrame.setSize(epsilonFrame.getWidth() - punchXVelocity, epsilonFrame.getHeight());
                    }
                }else if(smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.RIGHT)){
                    if(smileyPunchFrame.getX()-punchXVelocity-3>epsilonFrame.getX()+epsilonFrame.getWidth()){
                        smileyPunchFrame.setLocation(smileyPunchFrame.getX() - punchXVelocity, smileyPunchFrame.getY());
                    }else{
                        smileyPunchFrame.setLocation(smileyPunchFrame.getX() - punchXVelocity, smileyPunchFrame.getY());
                        epsilonFrame.setSize(epsilonFrame.getWidth() - punchXVelocity, epsilonFrame.getHeight());
                    }
                }
                }
            else{
                punchXVelocity = Constants.punchVelocity();
                punchYVelocity = Constants.punchVelocity();
            }



            }

        }
        public static void rapidFire(){
        bossAttacks.add(Attack.RAPIDFIRE);
        for(Smiley smiley : Game.getSmilies()) smiley.shot();
        }
        public static void annihilator() {
            annihilatorTimer++;
            for (Smiley smiley : Game.getSmilies()) {
                if (annihilatorTimer <= 4) {

                    smiley.createHoles(5, 70, 70);
                    smiley.applyHoles();
                }

                if (annihilatorTimer >= 900){
                    smiley.getHoles().clear();
                    GlassFrame.getINSTANCE().setShape(new Area(new Rectangle(0,0,
                            GlassFrame.getINSTANCE().getWidth(),GlassFrame.getINSTANCE().getHeight())));
                    GlassFrame.getINSTANCE().repaint();
                }
            }
        }

        public static void quake() {
            bossAttacks.add(Attack.QUAKE);
            for (SmileyPunch smileyPunch : Game.getSmileyPunches()) {
                if (smileyPunch.getPunchType().equals(PunchType.QUAKE)) {
                    if (smileyPunch.getLocalFrame().getY() < 700)
                        smileyPunch.getLocalFrame().setLocation(smileyPunch.getLocalFrame().getX(), smileyPunch.getLocalFrame().getY() + 5);
                    else smileyPunch.quakeEffect();
                    Random random = new Random();
                    if(smileyPunch.isHasPunched()){
                        int x = random.nextInt(Game.getEpsilon().getLocalFrame().getWidth());
                        int y = random.nextInt(Game.getEpsilon().getLocalFrame().getHeight());
                        Game.getMouseListener().setMouseX(x);
                        Game.getMouseListener().setMouseY(y);
                    }
                }

            }
        }

        public static void slap() {

        if(canSlap){
            for (SmileyPunchFrame smileyPunchFrame : Game.getSmileyPunchFrames()) {
                Epsilon epsilon = Game.getEpsilon();
                Rectangle epsilon1 = new Rectangle(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
                        epsilon.getLocalY()+epsilon.getLocalFrame().getY()
                        , epsilon.getWidth(), epsilon.getHeight());
                Rectangle punch = new Rectangle(smileyPunchFrame.getSmileyPunch().getLocalX()+smileyPunchFrame.getX(),
                        smileyPunchFrame.getSmileyPunch().getLocalY()+smileyPunchFrame.getY(),
                        smileyPunchFrame.getSmileyPunch().getWidth(), smileyPunchFrame.getSmileyPunch().getHeight());
                if (smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.LEFT) ||
                        smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.RIGHT))
                    punchXVelocity += Constants.punchAcceleration();
                JFrame epsilonFrame = Game.getEpsilon().getLocalFrame();
                if (smileyPunchFrame.getSmileyPunch().getPunchType().equals(PunchType.LEFT)) {
                    if (smileyPunchFrame.getX() + smileyPunchFrame.getWidth() + punchXVelocity < epsilonFrame.getX()) {
                        smileyPunchFrame.setLocation(smileyPunchFrame.getX() + punchXVelocity, smileyPunchFrame.getY());
                    }
                    smileyPunchFrame.getSmileyPunch().move();
                }
                if(punch.intersects(epsilon1)) canSlap = false;
            }

            }
        }


    public static ArrayList<Attack> getBossAttacks(){
        if(bossAttacks == null) bossAttacks = new ArrayList<>();
        return bossAttacks;
    }

}
