package view.entityViews.smiley;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import Model.FrameType;
import Model.entity.smiley.SmileyPunch;
import Model.enums.PunchType;

import javax.swing.*;
import java.awt.*;

public class SmileyPunchFrame extends JFrame implements FrameType {
    Rectangle bound;
    SmileyPunch smileyPunch;

    public SmileyPunchFrame(int x, int y, PunchType punchType){

        this.setSize(200,130);

        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setTitle("Smiley frame");
        SmileyPunchPanel smileyPunchPanel = new SmileyPunchPanel(200,130);
        this.setContentPane(smileyPunchPanel);
        smileyPunchPanel.setBound(new Rectangle(x,y,200,130));
        smileyPunchPanel.setItsFrame(this);
        smileyPunchPanel.setSmileyPunchView(new SmileyPunchView(this));
        this.setVisible(true);
        if(punchType.equals(PunchType.LEFT)) smileyPunch = new SmileyPunch(50,0);
        else if(punchType.equals(PunchType.RIGHT)) smileyPunch = new SmileyPunch(-20,0);
        else if(punchType.equals(PunchType.QUAKE)) smileyPunch = new SmileyPunch(60,50);
        smileyPunch.setPunchType(punchType);
        smileyPunch.setLocalFrame(this);
        bound = new Rectangle(getX(),getY(),getWidth(),getHeight());
        Game.getFrames().add(this);
        Game.getEnemies().add(smileyPunch);
        Game.getSmileyPunches().add(smileyPunch);
        Game.getSmileyPunchFrames().add(this);
        new KeyListener(smileyPunchPanel);
        new MouseListener(smileyPunchPanel);
    }

    @Override
    public boolean isometric() {
        return false;
    }

    @Override
    public boolean solid() {
        return true;
    }

    @Override
    public Rectangle getBound() {
        return bound;
    }

    public SmileyPunch getSmileyPunch() {
        return smileyPunch;
    }
}
