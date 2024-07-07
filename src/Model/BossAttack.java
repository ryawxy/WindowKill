package Model;

import Controller.Game;
import Model.entity.Epsilon;
import Model.entity.smiley.Smiley;
import Model.entity.smiley.SmileyPointFinger;
import Model.enums.Attack;
import Model.enums.SmileyHandSide;
import view.entityViews.smiley.SmileyFrame;
import javax.swing.*;
import java.util.ArrayList;

public class BossAttack {
    private static Epsilon epsilon = Game.getEpsilon();
    private static ArrayList<Attack> bossAttacks;
    private static long timer;

    public static void squeeze() {
        bossAttacks.add(Attack.SQUEEZE);

        for (SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()) {
            if (Game.getSmileyPointFingers().size() >= 2) {
                JFrame frame = smileyPointFinger.getLocalFrame();
                if (smileyPointFinger.getSmileyHandSide().equals(SmileyHandSide.LEFT)) {
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
    public static void Projectile(){
        bossAttacks.add(Attack.PROJECTILE);

        for(SmileyFrame smileyFrame : Game.getSmileyFrames()) smileyFrame.getSmiley().move();
        for(SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()){
            smileyPointFinger.move();
            smileyPointFinger.shot();
        }
    }
    public static void vomit(){
        bossAttacks.add(Attack.VOMIT);

        long currentTime = System.currentTimeMillis();

            for (SmileyFrame smileyFrame : Game.getSmileyFrames()) {
                smileyFrame.getSmiley().setxVelocity(0);
                smileyFrame.getSmiley().setyVelocity(0);
                smileyFrame.getSmiley().aoe();
            }
            for (SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()) {
                smileyPointFinger.setxVelocity(0);
                smileyPointFinger.setyVelocity(0);
            }


    }
    public static ArrayList<Attack> getBossAttacks(){
        if(bossAttacks == null) bossAttacks = new ArrayList<>();
        return bossAttacks;
    }

}
